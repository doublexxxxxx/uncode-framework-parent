package org.uncodeframework.core.common.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.uncodeframework.core.common.entity.AbstractEntity;
import org.uncodeframework.core.common.orm.search.Searchable;

public interface BaseService<T extends AbstractEntity<ID>, ID extends Serializable> {
	/**
	 * 保存单个实体
	 * 
	 * @param m
	 *            实体
	 * @return 返回保存的实体
	 */
	public T save(T t);
	
	/**
	 * 批量保存
	 * 
	 * @param entities
	 * @return
	 */
	public List<? extends T> save(List<T> entities);
	
	/**
	 * 根据主键删除相应实体
	 * 
	 * @param id
	 *            主键 可以是单个对象id，多个对象id用逗号分隔，数组，list....
	 */
	public void remove(@SuppressWarnings("unchecked") ID... ids);

	/**
	 * 删除实体
	 * 
	 * @param m
	 *            实体
	 */
	public void remove(T t);

	/**
	 * 批量删除实体
	 * 
	 * @param entities
	 */
	public void remove(List<? extends T> entities);

	/**
	 * 删除所有记录
	 */
	public void removeAll();

	/**
	 * 更新单个实体
	 * 
	 * @param m
	 *            实体
	 * @return 返回更新的实体
	 */
	public void update(T t);

	/**
	 * 查询单个对象
	 * 
	 * @param id
	 * @return
	 */
	public T findObject(ID id);

	/**
	 * 按条件查询对象
	 * 
	 * @param params
	 * @param page
	 * @return
	 * @throws RuntimeException
	 */
	public T findObject(Searchable searchable) throws RuntimeException;

	/**
	 * 实体是否存在
	 * 
	 * @param id
	 *            主键
	 * @return 存在 返回true，否则false
	 */
	public boolean isExist(ID id);

	/**
	 * 判断是否存在
	 * 
	 * @return 存在 true 不存在 false
	 */
	public boolean isExist(Searchable searchable);

	/**
	 * 查询出所有的记录
	 * 
	 * @return
	 */
	public List<? extends T> findAll();

	/**
	 * 按照顺序查询所有实体
	 * 
	 * @param sort
	 * @return
	 */
	public List<? extends T> findList(Sort sort);

	/**
	 * 
	 * @param searchable
	 *            条件
	 * @return
	 */
	public List<? extends T> findList(Searchable searchable);

	/**
	 * 根据Ids查询
	 * 
	 * @param ids
	 * @return
	 */
	public List<? extends T> findList(List<ID> ids);

	/**
	 * 分页及排序查询实体
	 * 
	 * @param pageable
	 *            分页及排序数据
	 * @return
	 */
	public Page<T> findPage(Pageable pageable);

	/**
	 * 按条件分页并排序查询实体
	 * 
	 * @param searchable
	 *            条件
	 * @return
	 */
	public Page<T> findPage(Searchable searchable);

	/**
	 * 统计实体总数
	 * 
	 * @return 实体总数
	 */
	public Long count();

	/**
	 * 按条件分页并排序统计实体数量
	 * 
	 * @param searchable
	 *            条件
	 * @return
	 */
	public Long count(Searchable searchable);

	/**
	 * 
	 * @param hqlKey
	 * @param params
	 *            可以使map,数组,list,也可以是以多个参数
	 */
	public void update(String sqlKey, Object... params);

	/**
	 * 自定义hql查询
	 * 
	 * @param hqlKey
	 * @param params
	 *            可以使map,数组,list,也可以是以多个参数
	 * @return
	 */
	public List<T> findList(String sqlKey, Object... params);
	
	/**
	 * 自定义hql分页查询
	 * 
	 * @param pageable
	 * @param sqlKey
	 * @param params
	 * @return
	 */
	public Page<T> findPage(Pageable pageable, String sqlKey, Object... params);
	
	/**
	 * 自定义hql删除
	 * 
	 * @param hqlKey
	 * @param params
	 *            可以使map ,数组,list,也可以是以多个参数
	 */
	public void remove(String sqlKey, Object... params);
}
