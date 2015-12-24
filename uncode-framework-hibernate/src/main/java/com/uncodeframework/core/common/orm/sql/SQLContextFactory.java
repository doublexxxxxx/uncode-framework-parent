package com.uncodeframework.core.common.orm.sql;

import java.io.IOException;

import org.dom4j.DocumentException;
import org.springframework.core.io.Resource;

import com.uncodeframework.core.common.UtilsConstants;

public class SQLContextFactory {

	public static SQLContext createSQLContext(Resource springResource) throws IOException, DocumentException {
		SQLContextFileBuilder builder = SQLContextFileBuilder.getInstance();
		builder.loadFullConfigFile(springResource, UtilsConstants.CHARSET);
		return builder.loadSQLContext();
	}

}
