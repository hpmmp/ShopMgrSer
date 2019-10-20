package com.shop.dao.datasource;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.ProxoolFacade;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by Cater.Tian
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * override determineCurrentLookupKey
     * <p>
     * Title: determineCurrentLookupKey
     * </p>
     * <p>
     * Description: 自动查找datasource
     * </p>
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSouce();
    }
}
