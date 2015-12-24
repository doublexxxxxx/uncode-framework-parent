package com.uncodeframework.core.common.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 * 抽象实体基类，提供统一的ID，和相关的基本功能方法 如果是oracle请参考{@link BaseOracleEntity}
 * <p>
 */
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> extends AbstractEntity<ID> {

	/** * @Fileds serialVersionUID :  */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "uuidGen")
	@GenericGenerator(name = "uuidGen", strategy = "uuid")
	private ID id;

	@Override
	public ID getId() {
		return id;
	}

	@Override
	public void setId(ID id) {
		this.id = id;
	}

}
