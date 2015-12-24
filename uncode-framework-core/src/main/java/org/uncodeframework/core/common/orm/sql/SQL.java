package org.uncodeframework.core.common.orm.sql;

import org.uncodeframework.core.common.utils.Utils;

public class SQL {
	private String id;
	private String sql;
	private Class<?> resultClass;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public Class<?> getResultClass() {
		return resultClass;
	}
	public void setResultClass(Class<?> resultClass) {
		this.resultClass = resultClass;
	}

	public SQL(String id, String sql, String resultClass) {
		this.id = id;
		this.sql = sql;
		try {
			if (Utils.isNotEmpty(resultClass))
				this.resultClass = Class.forName(resultClass);
			else
				this.resultClass = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "sql [id=" + id + ", sql=" + sql + ", resultClass=" + resultClass + "]";
	}

}
