package org.uncodeframework.core.common.orm.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.uncodeframework.core.common.bind.annotation.Repository;
import org.uncodeframework.core.common.entity.AbstractEntity;
import org.uncodeframework.core.common.entity.LogicDeleteable;
import org.uncodeframework.core.common.orm.BaseRepository;
import org.uncodeframework.core.common.orm.RepositoryHelper;
import org.uncodeframework.core.common.orm.search.Searchable;
import org.uncodeframework.core.common.orm.sql.SQL;
import org.uncodeframework.core.common.orm.sql.SQLContext;
import org.uncodeframework.core.common.utils.Utils;

public class BaseRepoitoryImpl<T extends AbstractEntity<ID>, ID extends Serializable> implements BaseRepository<T, ID> {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private SQLContext sqlContext;

	private Class<T> clazz;
	/**
	 * 统计QL
	 */
	private String countAllQL;
	public static final String COUNT_QUERY_STRING = "select count(x) from %s x where 1=1 ";
	/**
	 * 查询所有的QL
	 */
	private String findAllQL;
	public static final String FIND_QUERY_STRING = "from %s x where 1=1 ";

	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession(); //sessionFactory.openSession();
	}

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() throws Exception {
		Repository repository = this.getClass().getAnnotation(Repository.class);
		if (Utils.isNotEmpty(repository)) {
			if (Utils.isNotEmpty(repository.entity())) {
				this.clazz = (Class<T>) repository.entity();
				this.countAllQL = String.format(COUNT_QUERY_STRING, clazz.getName());
				this.findAllQL = String.format(FIND_QUERY_STRING, clazz.getName());
			} else {
				throw new Exception(Repository.class + "注解的entity不能为空!");
			}
		} else {
			throw new Exception(this.getClass() + " 必须要使用" + Repository.class + "注解!");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Sort sort) {
		Query query = this.getSession().createQuery("from " + this.clazz.getName() + RepositoryHelper.prepareOrder(sort));
		return (List<T>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<T> findAll(Pageable pageable) {
		Query query = this.getSession().createQuery("from " + this.clazz.getName());
		RepositoryHelper.setPageable(query, pageable);
		return new PageImpl<T>(query.list(), pageable, this.count());
	}

	@Override
	public void update(T entity) {
		this.getSession().merge(entity);
	}

	@Override
	public <S extends T> S save(S entity) {
		this.getSession().save(entity);
		return entity;
	}

	@Override
	public <S extends T> List<S> save(Iterable<S> entities) {
		if (Utils.isNotEmpty(entities)) {
			List<S> result = new ArrayList<S>();
			for (S entity : entities) {
				result.add(save(entity));
			}
			return result;
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findOne(ID id) {
		return (T) this.getSession().get(clazz, id);
	}

	@Override
	public boolean exists(ID id) {
		T entity = this.findOne(id);
		return Utils.isNotEmpty(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return this.getSession().createQuery(findAllQL).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<T> findAll(Searchable searchable) {
		Query query = this.getSession().createQuery(this.findAllQL + RepositoryHelper.prepareCompleteHQL(searchable));
		RepositoryHelper.setPageable(query, searchable);
		RepositoryHelper.setValues(query, searchable);
		return new PageImpl<T>(query.list(), searchable.getPage(), this.count());
	}

	@Override
	public List<T> findAll(Iterable<ID> ids) {
		List<T> entitys = new ArrayList<>();
		for (ID id : ids) {
			T entity = this.findOne(id);
			entitys.add(entity);
		}
		return entitys;
	}

	@Override
	public long count() {
		Query query = this.getSession().createQuery("select count(id) from " + this.clazz.getName());
		return ((Long) query.iterate().next()).longValue();
	}

	@Override
	public long count(Searchable searchable) {
		Query query = this.getSession().createQuery(this.countAllQL + RepositoryHelper.prepareCompleteHQL(searchable));
		RepositoryHelper.setValues(query, searchable);
		return ((Long) query.iterate().next()).longValue();
	}

	@Override
	public void delete(ID id) {
		if (Utils.isNotEmpty(id)) {
			T m = findOne(id);
			delete(m);
		}
	}

	@Override
	public void delete(T entity) {
		if (Utils.isNotEmpty(entity)) {
			if (entity instanceof LogicDeleteable) {
				((LogicDeleteable) entity).markDeleted();
				update(entity);
			} else {
				this.getSession().delete(entity);
			}
		}
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		if (Utils.isNotEmpty(entities)) {
			Iterator<? extends T> iterator = entities.iterator();
			while (iterator.hasNext()) {
				this.delete(iterator.next());
			}
		}
	}

	@Override
	public void deleteAll() {
		Query query = null;
		if (checkLogicDeleteable()) {
			query = this.getSession().createQuery("update " + this.clazz + " set deleted = 1");
		} else {
			query = this.getSession().createQuery("delete from " + this.clazz.getName());
		}
		query.executeUpdate();
	}

	@Override
	public void delete(@SuppressWarnings("unchecked") ID... ids) {
		for (ID id : ids) {
			this.delete(id);
		}
	}

	/**
	 * 检查是否实现了逻辑删除接口
	 * 
	 * @return
	 */
	private boolean checkLogicDeleteable() {
		Class<?>[] inters = this.clazz.getInterfaces();
		boolean flag = false;
		for (int i = 0; i < inters.length; i++) {
			if ("LogicDeleteable".equals(inters[i].getSimpleName())) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public void update(String sqlKey, Object... params) {
		Query query = this.getSession().createSQLQuery(this.sqlContext.getSQL(sqlKey).getSql());
		RepositoryHelper.setValues(query, params);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(String sqlKey, Object... params) {
		SQL sql = this.sqlContext.getSQL(sqlKey);
		SQLQuery sqlQuery = this.getSession().createSQLQuery(sql.getSql());
		RepositoryHelper.setValues(sqlQuery, params);
		RepositoryHelper.setResultTransformer(sqlQuery, sql.getResultClass());
		return sqlQuery.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Page<T> findPage(Pageable pageable, String sqlKey, Object... params) {
		SQL sql = this.sqlContext.getSQL(sqlKey);
		SQLQuery sqlQuery = this.getSession().createSQLQuery(sql.getSql());
		RepositoryHelper.setValues(sqlQuery, params);
		RepositoryHelper.setResultTransformer(sqlQuery, sql.getResultClass());
		return new PageImpl<T>(sqlQuery.list(), pageable, this.count());
	}

	@Override
	public void delete(String sqlKey, Object... params) {
		Query query = this.getSession().createSQLQuery(this.sqlContext.getSQL(sqlKey).getSql());
		RepositoryHelper.setValues(query, params);
		query.executeUpdate();
	}

}
