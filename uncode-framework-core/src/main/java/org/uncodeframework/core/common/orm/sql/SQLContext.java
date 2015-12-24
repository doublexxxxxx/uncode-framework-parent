package org.uncodeframework.core.common.orm.sql;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * sql配置文件
 * 
 */
public class SQLContext {

	private Map<String, SQL> sqls = new HashMap<>();

	public SQL getSQL(String sqlKey) {
		return sqls.get(sqlKey);
	}

	public Set<String> getKeys() {
		return sqls.keySet();
	}

	public void put(String sqlKey, SQL sql) {
		this.sqls.put(sqlKey, sql);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("sqls:\n");
		Set<String> keys = sqls.keySet();
		for (String key : keys) {
			sb.append("key:" + key + "   value:" + sqls.get(key) + "\n");
		}
		return sb.toString();
	}

	public boolean checkKeyIsExist(String key) {
		return this.sqls.containsKey(key);
	}
}
