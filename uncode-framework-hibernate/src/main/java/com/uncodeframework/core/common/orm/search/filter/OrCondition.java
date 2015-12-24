package com.uncodeframework.core.common.orm.search.filter;

import java.util.ArrayList;
import java.util.List;

public class OrCondition implements SearchFilter {

	private List<SearchFilter> orFilters = new ArrayList<>();

	OrCondition() {
	}

	public OrCondition add(SearchFilter filter) {
		this.orFilters.add(filter);
		return this;
	}

	public List<SearchFilter> getOrFilters() {
		return orFilters;
	}

	@Override
	public String toString() {
		return "OrCondition{" + orFilters + '}';
	}
}
