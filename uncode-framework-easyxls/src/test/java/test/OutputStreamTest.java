package test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import po.Charges;

import com.uncodeframework.core.plugins.easyxls.EasyXls;
import com.uncodeframework.core.plugins.easyxls.bean.ExcelConfig;

public class OutputStreamTest {

	@Test
	public void testOutputStream() {
		InputStream is = Xls2ListTest.class.getResourceAsStream("2.xls");
		try {
			String xmlPath = Xls2ListTest.class.getResource("/ChargesMap.xml").getPath();
			List list = EasyXls.xls2List(xmlPath, is);
			Map map = new HashMap();
			map.put("year", 2013);
			map.put("ownersname", "测试户主");
			list.add(map);
			Map map1 = new HashMap();
			map.put("year", 2012);
			map.put("roomno", "171-602");
			map.put("ownersname", "测试户主2");
			list.add(map1);
			EasyXls.list2Xls(list, xmlPath, "c:/", "testMap.xls");
			ExcelConfig config = new ExcelConfig.Builder(Charges.class).sheetNum(0).startRow(1).separater(",").key("name")
					.addColumn("year,年度", "communityid,小区ID", "roomno,房号", "ownersid,户主ID", "ownersname,户主姓名", "property,物业费").build();
			OutputStream os = new FileOutputStream("C:/Users/a5jtkzz/Documents/testOutputStream.xls");
			EasyXls.list2Xls(config, list, os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
