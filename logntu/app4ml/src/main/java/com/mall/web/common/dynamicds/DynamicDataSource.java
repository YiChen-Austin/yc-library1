package com.mall.web.common.dynamicds;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		String ds=DataSourceSwitcher.getDataSource();
		return ds;
	}

}
