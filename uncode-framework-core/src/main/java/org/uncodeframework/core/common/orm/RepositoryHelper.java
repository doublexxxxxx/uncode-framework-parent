package org.uncodeframework.core.common.orm;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.uncodeframework.core.common.orm.search.SearchOperator;
import org.uncodeframework.core.common.orm.search.Searchable;
import org.uncodeframework.core.common.orm.search.filter.AndCondition;
import org.uncodeframework.core.common.orm.search.filter.Condition;
import org.uncodeframework.core.common.orm.search.filter.OrCondition;
import org.uncodeframework.core.common.orm.search.filter.SearchFilter;
import org.uncodeframework.core.common.utils.Utils;

/**
 * dao辅助类
 */
public class RepositoryHelper {

	private static final String paramPrefix = "param_";

	/**
	 * 动态拼HQL where、group by having
	 * 
	 * @param ql
	 * @param search
	 */
	public static void prepareQL(StringBuilder ql, Searchable search) {
		if (!search.hasSearchFilter()) {
			return;
		}

		int paramIndex = 1;
		for (SearchFilter searchFilter : search.getSearchFilters()) {

			if (searchFilter instanceof Condition) {
				Condition condition = (Condition) searchFilter;
				if (condition.getOperator() == SearchOperator.custom) {
					continue;
				}
			}

			ql.append(" and ");

			paramIndex = genCondition(ql, paramIndex, searchFilter, search.getAliasWithDot());

		}
	}

	private static int genCondition(StringBuilder ql, int paramIndex, SearchFilter searchFilter, String aliasWithDot) {
		boolean needAppendBracket = searchFilter instanceof OrCondition || searchFilter instanceof AndCondition;

		if (needAppendBracket) {
			ql.append("(");
		}

		if (searchFilter instanceof Condition) {
			Condition condition = (Condition) searchFilter;
			// 自定义条件
			String entityProperty = condition.getEntityProperty();
			String operatorStr = condition.getOperatorStr();
			// 实体名称
			ql.append(aliasWithDot);
			ql.append(entityProperty);
			// 操作符
			// 1、如果是自定义查询符号，则使用SearchPropertyMappings中定义的默认的操作符
			ql.append(" ");
			ql.append(operatorStr);

			if (!condition.isUnaryFilter()) {
				ql.append(" :");
				ql.append(paramPrefix);
				ql.append(paramIndex++);
				return paramIndex;
			}
		} else if (searchFilter instanceof OrCondition) {
			boolean isFirst = true;
			for (SearchFilter orSearchFilter : ((OrCondition) searchFilter).getOrFilters()) {
				if (!isFirst) {
					ql.append(" or ");
				}
				paramIndex = genCondition(ql, paramIndex, orSearchFilter, aliasWithDot);
				isFirst = false;
			}
		} else if (searchFilter instanceof AndCondition) {
			boolean isFirst = true;
			for (SearchFilter andSearchFilter : ((AndCondition) searchFilter).getAndFilters()) {
				if (!isFirst) {
					ql.append(" and ");
				}
				paramIndex = genCondition(ql, paramIndex, andSearchFilter, aliasWithDot);
				isFirst = false;
			}
		}

		if (needAppendBracket) {
			ql.append(")");
		}
		return paramIndex;
	}
	/**
	 * 根据search给query赋值及设置分页信息
	 * 
	 * @param query
	 * @param search
	 */
	public static void setValues(Query query, Searchable search) {
		int paramIndex = 1;
		for (SearchFilter searchFilter : search.getSearchFilters()) {
			paramIndex = setValues(query, searchFilter, paramIndex);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setValues(Query query, Object... params) {
		if (params[0] instanceof Map) {
			Map mapParams = (Map) params[0];
			Set<String> keys = mapParams.keySet();
			for (String key : keys) {
				query.setParameter(key, mapParams.get(key));
			}
		} else {
			int paramIndex = 0;
			for (Object o : params) {
				query.setParameter(paramIndex++, o);
			}
		}
	}

	private static int setValues(Query query, SearchFilter searchFilter, int paramIndex) {
		if (searchFilter instanceof Condition) {

			Condition condition = (Condition) searchFilter;
			if (condition.getOperator() == SearchOperator.custom) {
				return paramIndex;
			}
			if (condition.isUnaryFilter()) {
				return paramIndex;
			}
			query.setParameter(paramPrefix + paramIndex++, formtValue(condition, condition.getValue()));

		} else if (searchFilter instanceof OrCondition) {

			for (SearchFilter orSearchFilter : ((OrCondition) searchFilter).getOrFilters()) {
				paramIndex = setValues(query, orSearchFilter, paramIndex);
			}

		} else if (searchFilter instanceof AndCondition) {
			for (SearchFilter andSearchFilter : ((AndCondition) searchFilter).getAndFilters()) {
				paramIndex = setValues(query, andSearchFilter, paramIndex);
			}
		}
		return paramIndex;
	}

	private static Object formtValue(Condition condition, Object value) {
		SearchOperator operator = condition.getOperator();
		if (operator == SearchOperator.like || operator == SearchOperator.notLike) {
			return "%" + value + "%";
		}
		if (operator == SearchOperator.prefixLike || operator == SearchOperator.prefixNotLike) {
			return value + "%";
		}

		if (operator == SearchOperator.suffixLike || operator == SearchOperator.suffixNotLike) {
			return "%" + value;
		}
		return value;
	}

	public static void setPageable(Query query, Searchable search) {
		if (search.hasPageable()) {
			Pageable pageable = search.getPage();
			query.setFirstResult(pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}
	}

	public static void setPageable(Query query, Pageable pageable) {
		if (Utils.isNotEmpty(pageable)) {
			query.setFirstResult(pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}
	}

	public static void prepareOrder(StringBuilder ql, Searchable search) {
		if (search.hashSort()) {
			ql.append(" order by ");
			for (Sort.Order order : search.getSort()) {
				ql.append(String.format("%s%s %s, ", search.getAliasWithDot(), order.getProperty(), order.getDirection().name().toLowerCase()));
			}

			ql.delete(ql.length() - 2, ql.length());
		}
	}

	public static String prepareCompleteHQL(Searchable searchable) {
		StringBuilder s = new StringBuilder("");
		prepareQL(s, searchable);
		prepareOrder(s, searchable);
		return s.toString();
	}

	/**
	 * 拼排序
	 * 
	 * @param sort
	 * @return
	 */
	public static String prepareOrder(Sort sort) {
		if (sort == null || !sort.iterator().hasNext()) {
			return "";
		}
		StringBuilder orderBy = new StringBuilder("");
		orderBy.append(" order by ");
		orderBy.append(sort.toString().replace(":", " "));
		return orderBy.toString();
	}

	/**
	 * 设置返回后的查询结果集
	 * 
	 * @param query
	 * @param resultClass
	 */
	public static void setResultTransformer(SQLQuery query, Class<?> resultClass) {
		if (Utils.isNotEmpty(resultClass)) {
			if (resultClass.equals(List.class)) {
				query.setResultTransformer(Transformers.TO_LIST);
			} else if (resultClass.equals(Map.class)) {
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			} else {
				query.addEntity(resultClass);
			}
		}
	}
}
