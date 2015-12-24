package org.uncodeframework.core.common.orm.search.exception;

import org.springframework.core.NestedRuntimeException;

public class SearchException extends NestedRuntimeException {

    /** * @Fileds serialVersionUID :  */
	private static final long serialVersionUID = 1L;

	public SearchException(String msg) {
        super(msg);
    }

    public SearchException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
