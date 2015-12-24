package org.uncodeframework.core.common.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.uncodeframework.core.common.bind.annotation.Service;
import org.uncodeframework.core.common.entity.AbstractEntity;
import org.uncodeframework.core.common.orm.BaseRepository;
import org.uncodeframework.core.common.orm.search.Searchable;
import org.uncodeframework.core.common.utils.SpringUtils;
import org.uncodeframework.core.common.utils.Utils;

@Transactional
public abstract class BaseServiceImpl<T extends AbstractEntity<ID>, ID extends Serializable> implements BaseService<T, ID> {

	private BaseRepository<T, ID> baseRepository;

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() throws Exception {
		Service repository = this.getClass().getAnnotation(Service.class);
		if (Utils.isNotEmpty(repository)) {
			if (Utils.isNotEmpty(repository.name())) {
				baseRepository = SpringUtils.getBean(repository.name());
			} else if (Utils.isNotEmpty(repository.repositoryType())) {
				baseRepository = (BaseRepository<T, ID>) SpringUtils.getBean(repository.repositoryType());
			} else {
				throw new Exception("org.uncodeframework.core.common.bind.annotation.Service 注解的name和type必填一个!");
			}
		} else {
			throw new Exception("BaseServiceImpl的实现类必须添加上org.uncodeframework.core.common.bind.annotation.Service这个注解 !");
		}
	}

	@Override
	public T save(T t) {
		return baseRepository.save(t);
	}

	@Override
	public List<? extends T> save(List<T> entities) {
		return (List<? extends T>) baseRepository.save(entities);
	}

	@Override
	public void remove(@SuppressWarnings("unchecked") ID... ids) {
		baseRepository.delete(ids);
	}

	@Override
	public void remove(T t) {
		baseRepository.delete(t);
	}

	@Override
	public void remove(List<? extends T> entities) {
		baseRepository.delete(entities);
	}

	@Override
	public void removeAll() {
		baseRepository.deleteAll();
	}

	@Override
	public void update(T t) {
		baseRepository.update(t);
	}

	@Override
	public T findObject(ID id) {
		return baseRepository.findOne(id);
	}

	@Override
	public T findObject(Searchable searchable) throws RuntimeException {
		List<T> list = baseRepository.findAll(searchable).getContent();
		if (Utils.isEmpty(list)) {
			return null;
		}
		if (list.size() > 1) {
			throw new RuntimeException("查询出多条记录！");
		}
		return list.get(0);
	}

	@Override
	public boolean isExist(ID id) {
		return baseRepository.exists(id);
	}

	@Override
	public boolean isExist(Searchable searchable) {
		return Utils.isNotEmpty(baseRepository.findAll(searchable).getContent());
	}

	@Override
	public List<? extends T> findAll() {
		return (List<? extends T>) baseRepository.findAll();
	}

	@Override
	public List<? extends T> findList(Sort sort) {
		return (List<? extends T>) baseRepository.findAll(sort);
	}

	@Override
	public List<? extends T> findList(Searchable searchable) {
		return baseRepository.findAll(searchable).getContent();
	}

	@Override
	public List<? extends T> findList(List<ID> ids) {
		return (List<? extends T>) baseRepository.findAll(ids);
	}

	@Override
	public Page<T> findPage(Pageable pageable) {
		return baseRepository.findAll(pageable);
	}

	@Override
	public Page<T> findPage(Searchable searchable) {
		return baseRepository.findAll(searchable);
	}

	@Override
	public Long count() {
		return baseRepository.count();
	}

	@Override
	public Long count(Searchable searchable) {
		return baseRepository.count(searchable);
	}

	@Override
	public void update(String hqlKey, Object... params) {
		baseRepository.update(hqlKey, params);
	}

	@Override
	public List<T> findList(String hqlKey, Object... params) {
		return baseRepository.findAll(hqlKey, params);
	}

	@Override
	public Page<T> findPage(Pageable pageable, String sqlKey, Object... params) {
		return baseRepository.findPage(pageable, sqlKey, params);
	}

	@Override
	public void remove(String hqlKey, Object... params) {
		baseRepository.delete(hqlKey, params);
	}

}
