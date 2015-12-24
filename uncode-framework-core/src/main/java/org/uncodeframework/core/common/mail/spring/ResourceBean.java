package org.uncodeframework.core.common.mail.spring;

import org.springframework.core.io.InputStreamSource;

public class ResourceBean {
	private InputStreamSource source;
	/** * @Fileds fileName : 文件名 */
	private String fileName;

	public InputStreamSource getSource() {
		return source;
	}

	public void setSource(InputStreamSource source) {
		this.source = source;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
