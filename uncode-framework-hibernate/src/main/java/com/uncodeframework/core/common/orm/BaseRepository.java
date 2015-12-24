package com.uncodeframework.core.common.orm;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.uncodeframework.core.common.entity.AbstractEntity;
import com.uncodeframework.core.common.orm.search.Searchable;

/**
 * <p>
 * 抽象DAO层基类 提供一些简便方法<br/>
 * <p/>
 * 泛型 ：T 表示实体类型；ID表示主键类型
 * <p>
 */
public interface BaseRepository<T extends AbstractEntity<ID>, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

	/**
	 * 根据主键删除
	 * 
	 * @param ids
	 */
	public void delete(ID... ids);

	/**
	 * 根据条件查询所有 条件 + 分页 + 排序
	 * 
	 * @param searchable
	 * @return
	 */
	public Page<T> findAll(Searchable searchable);
	/**
	 * 根据条件统计所有记录数
	 * 
	 * @param searchable
	 * @return
	 */
	public long count(Searchable searchable);

	public void update(T entity);

	/**
	 * 自定义hql更新
	 * 
	 * @param sqlKey
	 * @param params
	 */
	public void update(String sqlKey, Object... params);
	/**
	 * 自定义hql查询
	 * 
	 * @param sqlKey
	 * @param params
	 * @return
	 */
	public List<T> findAll(String sqlKey, Object... params);
	public Page<T> findPage(Pageable pageable, String sqlKey, Object... params);
	/**
	 * 自定义hql删除
	 * 
	 * @param sqlKey
	 * @param params
	 */
	public void delete(String sqlKey, Object... params);
}
