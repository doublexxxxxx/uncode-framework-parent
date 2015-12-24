package org.uncodeframework.core.common.orm.search.exception;

import org.uncodeframework.core.common.orm.search.SearchOperator;

public final class InvlidSearchOperatorException extends SearchException {

    /** * @Fileds serialVersionUID :  */
	private static final long serialVersionUID = 1L;

	public InvlidSearchOperatorException(String searchProperty, String operatorStr) {
        this(searchProperty, operatorStr, null);
    }

    public InvlidSearchOperatorException(String searchProperty, String operatorStr, Throwable cause) {
        super("Invalid Search Operator searchProperty [" + searchProperty + "], " +
                "operator [" + operatorStr + "], must be one of " + SearchOperator.toStringAllOperator(), cause);
    }
}
