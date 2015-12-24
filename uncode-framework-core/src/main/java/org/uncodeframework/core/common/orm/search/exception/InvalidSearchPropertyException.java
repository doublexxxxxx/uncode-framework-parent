package org.uncodeframework.core.common.orm.search.exception;

public final class InvalidSearchPropertyException extends SearchException {

    /** * @Fileds serialVersionUID :  */
	private static final long serialVersionUID = 1L;

	public InvalidSearchPropertyException(String searchProperty, String entityProperty) {
        this(searchProperty, entityProperty, null);
    }

    public InvalidSearchPropertyException(String searchProperty, String entityProperty, Throwable cause) {
        super("Invalid Search Property [" + searchProperty + "] Entity Property [" + entityProperty + "]", cause);
    }


}
