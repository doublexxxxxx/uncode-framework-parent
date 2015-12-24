package org.uncodeframework.core.common.template;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerUtils {

	private static Configuration cfg = null;

	/**
	 * 获取freemarker的配置 freemarker本身支持classpath,目录和从ServletContext获取.
	 * 
	 * @return 返回Configuration对象
	 */
	private static Configuration getConfiguration() {
		if (null == cfg) {
			cfg = new Configuration();
			// 这里有三种方式读取
			// （一个文件目录）
			// cfg.setDirectoryForTemplateLoading(new File("templates"));
			// classpath下的一个目录（读取jar文件）
			cfg.setClassForTemplateLoading(FreemarkerUtils.class,"/templates");
			// 相对web的根路径来说 根目录
			// cfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), "templates");
			// setEncoding这个方法一定要设置国家及其编码，不然在flt中的中文在生成html后会变成乱码
			cfg.setEncoding(Locale.getDefault(), "UTF-8");
			// 设置对象的包装器
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			// 设置异常处理器//这样的话就可以${a.b.c.d}即使没有属性也不会出错
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		}
		return cfg;
	}

	/**
	 * 
	 * @param ftl
	 *            模板文件名,相对上面的模版根目录templates路径,例如/module/view.ftl
	 *            templates/module/view.ftl
	 * @param data
	 *            填充数据
	 * @param targetFile
	 *            要生成的静态文件的路径,相对设置中的根路径,例如 "jsp/user/1.html"
	 * @return
	 */
	public static boolean createFile(String ftl, Map<String, Object> data, String targetFile) {
		try {
			// 创建Template对象
			Configuration cfg = FreemarkerUtils.getConfiguration();
			Template template = cfg.getTemplate(ftl);
			template.setEncoding("UTF-8");
			// 生成静态页面
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8"));
			template.process(data, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (TemplateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
        /*HashMap map = new HashMap();
        map.put("image", re == null ? "" : FileHelper.addTimestamp(re.getUrl()));// 背景图片
        map.put("categorylist", list); // 子项目list
        map.put("size", list.size());  // 子项目size
        map.put("url", "phone!categorySearch?id="); // 子项目url链接
        map.put("basePath",FileHelper.combineBasePath()); //　网页base url
        
        // 生成HTML的完整路径
        String htmlFullPath = FileHelper.getHtmlAbsolutePath(template.getOutputPath(), category.getId());
        FreemarkerUtils.createFile("view.ftl", map, htmlFullPath);*/
	}
}