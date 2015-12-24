package org.uncodeframework.core.common.orm.search.exception;

public final class InvalidSearchValueException extends SearchException {

    /** * @Fileds serialVersionUID :  */
	private static final long serialVersionUID = 1L;

	public InvalidSearchValueException(String searchProperty, String entityProperty, Object value) {
        this(searchProperty, entityProperty, value, null);
    }

    public InvalidSearchValueException(String searchProperty, String entityProperty, Object value, Throwable cause) {
        super("Invalid Search Value, searchProperty [" + searchProperty + "], " +
                "entityProperty [" + entityProperty + "], value [" + value + "]", cause);
    }

}
