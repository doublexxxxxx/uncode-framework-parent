package org.uncodeframework.core.common.template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.uncodeframework.core.common.data.DataMng;
import org.uncodeframework.core.common.data.Table;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ("/applicationContext.xml"))
public class Generator extends AbstractJUnit4SpringContextTests {

	@Autowired
	private DataMng dataMng;

	public static final String package_common_name = "test";

	Map<String, Object> data = new HashMap<String, Object>();

	File f = null;

	String tpl = null;

	public static final String ENCODING = "UTF-8";

	/**
	 * 从数据库表生成javaBean, action, service, dao
	 * 
	 * @throws TemplateException
	 * @throws IOException
	 */
	@Test
	public void generator() throws TemplateException, IOException {
		start();
	}

	/**
	 * 数据库 指定表
	 * 
	 * @throws TemplateException
	 * @throws IOException
	 */
	public void start() throws TemplateException, IOException {
		String[] tableNames = { "user" };
		List<String> entitys = new ArrayList<String>();
		if (null != tableNames && tableNames.length > 0) {
			// 数据库 指定表
			for (String tablename : tableNames) {
				Table t = dataMng.findTable(tablename);
				String entity = replaceLine((trimFirst(t.getName())));
				data.put("table", t.getName());
				data.put("fields", dataMng.listFields(t.getName()));
				loadData(entity);
				generatorAll();
				entitys.add(entity);
			}
		} else {
			// 数据库所有表
			List<Table> tabels = dataMng.listTabels();
			for (Table t : tabels) {
				String entity = replaceLine((trimFirst(t.getName())));
				data.put("table", t.getName());
				data.put("fields", dataMng.listFields(t.getName()));
				loadData(entity);
				generatorAll();
				entitys.add(entity);
			}
		}
		data.put("entitys", entitys);
	}

	/**
	 * 数据组装
	 * 
	 * @param entity
	 */
	private void loadData(String entity) {
		String Entity = upperCaseFirst(entity);
		data.put("Entity", Entity);
		data.put("entity", entity);
		data.put("entity_package", package_common_name + "/entity");
		data.put("template_dir", this.getClass().getResource("/").getPath());
	}

	/**
	 * 生成
	 * 
	 * @throws IOException
	 * @throws TemplateException
	 */
	public void generatorAll() throws TemplateException, IOException {
		// bean
		f = new File(getFilePath((String) data.get("entity_package"), data.get("Entity") + ".java"));
		tpl = converString((String) data.get("template_dir"));
		index(tpl, f);
	}

	/**
	 * 生成
	 * 
	 * @param tpl
	 * @param f
	 * @throws TemplateException
	 * @throws IOException
	 */
	public void index(String tpl, File f) throws TemplateException, IOException {
		File parent = f.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
			f = new File(getFilePath((String) data.get("endity_package"), data.get("Entity") + ".java"));
		}
		Writer out = null;
		try {
			Configuration cfg = new Configuration();
			cfg.setClassForTemplateLoading(this.getClass(),"");
			Template template = cfg.getTemplate("/entity.txt");
			OutputStream os = new FileOutputStream(f);
			out = new OutputStreamWriter(os, ENCODING);
			template.process(data, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 项目classpath相对路径
	 * 
	 * @param packageName
	 * @param name
	 * @return
	 */
	private String getFilePath(String packageName, String name) {
		String path = converString(packageName);
		return "src/main/java/" + path + "/" + name;
	}

	/**
	 * \\. 换 /
	 * 
	 * @param s
	 * @return
	 */
	private String converString(String s) {
		return s.replaceAll("\\.", "/");
	}

	/**
	 * 去掉表头
	 * 
	 * @return
	 */
	private String trimFirst(String tableName) {
		String[] splits = tableName.split("_");
		if ("psm".equalsIgnoreCase(splits[0])) {
			tableName = tableName.substring(4);
		} else if ("mm".equalsIgnoreCase(splits[0])) {
			tableName = tableName.substring(3);
		} else if ("dm".equalsIgnoreCase(splits[0])) {
			tableName = tableName.substring(3);
		} else if ("pm".equalsIgnoreCase(splits[0])) {
			tableName = tableName.substring(3);
		} else if ("sm".equalsIgnoreCase(splits[0])) {
			tableName = tableName.substring(3);
		} else if ("tm".equalsIgnoreCase(splits[0])) {
			tableName = tableName.substring(3);
		} else if ("um".equalsIgnoreCase(splits[0])) {
			tableName = tableName.substring(3);
		}
		return tableName;
	}

	/**
	 * 去掉下划线
	 * 
	 * @param s
	 * @return
	 */
	private String replaceLine(String s) {
		return s.replaceAll("_", "");
	}

	/**
	 * 头字母大写
	 * 
	 * @param s
	 * @return
	 */
	private String upperCaseFirst(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}
