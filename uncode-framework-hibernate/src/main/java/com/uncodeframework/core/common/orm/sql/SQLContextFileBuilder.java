package com.uncodeframework.core.common.orm.sql;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import com.uncodeframework.core.common.utils.CloseUtil;
import com.uncodeframework.core.common.utils.Utils;

/**
 * sql配置文件
 * 
 */
public class SQLContextFileBuilder {

	private Element rootDoc = null;
	private static SQLContextFileBuilder sqlContextFileBuilder = null;

	public SQLContext loadSQLContext() {
		SQLContext sqlContext = new SQLContext();
		List els = rootDoc.elements();
		for (Object o : els) {
			Element element = (Element) o;
			String idKey = element.attribute("id").getValue();
			String sql = element.getTextTrim();
			Attribute attribute = element.attribute("resultClass");
			String resultClass = Utils.isNotEmpty(attribute) ? attribute.getValue() : null;
			if (!sqlContext.checkKeyIsExist(idKey)) {
				sqlContext.put(idKey, new SQL(idKey, sql, resultClass));
			} else {
				throw new RuntimeException("请检查sql的配置文件，sql的key已经存在(key=" + idKey + ")!");
			}
		}
		return sqlContext;
	}

	// 解析import包含的子配置文件
	@SuppressWarnings({"unchecked", "rawtypes"})
	public Document loadFullConfigFile(Resource resource, String encoding) throws UnsupportedEncodingException, IOException, DocumentException {
		SAXReader reader = null;
		Document document = null;
		reader = new SAXReader();
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(resource.getInputStream(), encoding);
			document = reader.read(isr);
		} finally {
			CloseUtil.close(isr);
		}
		final Element root = document.getRootElement();
		List list = document.selectNodes("//import");
		for (int i = 0; i < list.size(); i++) {
			Element n = (Element) list.get(i);
			String file = n.attribute("resource").getValue();
			Resource fr = new ClassPathResource(file.substring(ResourceUtils.CLASSPATH_URL_PREFIX.length()), ClassUtils.getDefaultClassLoader());
			Document includedDoc = loadFullConfigFile(fr, encoding);
			List content = root.content();
			int indexOfPos = content.indexOf(n);
			content.remove(indexOfPos);
			Element ie = includedDoc.getRootElement();
			List ie_children = ie.content();
			for (int k = ie_children.size() - 1; k >= 0; k--) {
				content.add(indexOfPos, ie_children.get(k));
			}
		}
		this.rootDoc = document.getRootElement();
		return document;
	}

	public static SQLContextFileBuilder getInstance() {
		if (sqlContextFileBuilder == null) {
			sqlContextFileBuilder = new SQLContextFileBuilder();
		}
		return sqlContextFileBuilder;
	}
}
