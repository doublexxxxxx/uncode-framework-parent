package com.uncodeframework.core.freemarker;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
 
public class FmAppUseage {
     
    public static void main(String[] args){
        List<Object> list = new ArrayList<Object>();
        list.add(new Attr("username", "String"));
        list.add(new Attr("password", "String"));
        list.add(new Attr("age", "int"));
        list.add(new Attr("hobby", "String"));
         
        Map<String,Object> root = new HashMap<String, Object>();
        root.put("packageName", "com.uncodeframework.core.freemarker");
        root.put("className", "User");
        root.put("attrs", list);
        root.put("author", "adams");
         
        Configuration cfg = new Configuration();
        String path = FmAppUseage.class.getResource("/").getPath()+"template";
        try {
            cfg.setDirectoryForTemplateLoading(new File(path));
            Template template = cfg.getTemplate("/demo.ftl");
            StringWriter out = new StringWriter();
            template.process(root, out);
             
            System.out.println(out.toString());
        } catch (IOException e) {
            System.out.println("Cause==>" + e.getCause());
        } catch (TemplateException e) {
            System.out.println("Cause==>" + e.getCause());
        }
    }
}