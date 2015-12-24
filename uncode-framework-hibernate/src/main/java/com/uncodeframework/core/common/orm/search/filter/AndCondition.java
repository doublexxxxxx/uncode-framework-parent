package com.uncodeframework.core.common.orm.search.filter;

import java.util.ArrayList;
import java.util.List;

public class AndCondition implements SearchFilter {

	private List<SearchFilter> andFilters = new ArrayList<>();

	AndCondition() {
	}

	public AndCondition add(SearchFilter filter) {
		this.andFilters.add(filter);
		return this;
	}

	public List<SearchFilter> getAndFilters() {
		return andFilters;
	}

	@Override
	public String toString() {
		return "AndCondition{" + andFilters + '}';
	}
}
