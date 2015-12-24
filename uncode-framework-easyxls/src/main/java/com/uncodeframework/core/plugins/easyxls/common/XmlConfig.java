package com.uncodeframework.core.plugins.easyxls.common;

import java.io.File;

import com.uncodeframework.core.plugins.easyxls.bean.ExcelConfig;
import com.uncodeframework.core.plugins.easyxls.bean.Field;

/**
 * 读取xml文件
 *
 */
public class XmlConfig {

    /**
     * 获取xml配置对象
     *
     * @param xmlPath xml完整路径
     * @return xml配置对象
     */
    public static ExcelConfig getXmlConfig(String xmlPath) {
        ExcelConfig config = XmlUtil.fromXml(new File(xmlPath), ExcelConfig.class);
        try {
            Class<?> clazz = Class.forName(config.getClazz());
            //自动补充没有填写type的列
            for (int i = 0; i < config.getColumns().getColumns().size(); i++) {
                if (config.getColumn(i).getType() == null || config.getColumn(i).getType().equals("")) {
                    Field field = FieldUtil.getField(clazz, config.getColumn(i).getName());
                    config.getColumn(i).setType(field.getCanonicalName());
                }
            }
        } catch (ClassNotFoundException e) {
            //ignore
        }
        return config;
    }

    /**
     * 写入到xml文件
     *
     * @param dlExcel 配置对象
     * @param xmlPath xml保存路径
     * @return true成功，false失败
     * @throws Exception
     */
    public static boolean WriteXml(ExcelConfig dlExcel, String xmlPath) throws Exception {
        //写入xml
        return XmlUtil.toXml(dlExcel, new File(xmlPath));
    }

}