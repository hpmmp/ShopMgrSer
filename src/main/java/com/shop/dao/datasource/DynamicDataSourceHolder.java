package com.shop.dao.datasource;

import org.apache.log4j.Logger;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.ProxoolFacade;

/**
 * Created by Cater.Tian
 */
public class DynamicDataSourceHolder {

    private static Logger logger = Logger.getLogger(DynamicDataSourceHolder.class);
    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static final String DB_TYPE_RW = "readWriteDS";
    public static final String DB_TYPE_R = "readDS";
    public static final String NUM_STATISTICS = "numStatistics";

    /**
     * 设置本线程的dbtype
     *
     * @param name
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void putDataSource(String name) {
        contextHolder.set(name);
    }

    public static String getDataSouce() {
        String db = contextHolder.get();
        String[] alias = ProxoolFacade.getAliases();

        if (db == null) {
            db = DB_TYPE_RW;// 默认是读写库
            //当前使用的数据源为统计库，切换到默认库需要先删除其连接，numStatistics为统计库表名
            if (alias.length != 0 && !"world".equals(alias[0])) {
                try {
                    ProxoolFacade.removeConnectionPool("numStatistics");
                    logger.info("remove connection pool named numStatistics");
                } catch (ProxoolException e) {
                    e.printStackTrace();
                }
            }
        } else {
            //当前数据源为默认库，切换到统计库需要先删除其连接，world为默认库别名
            if (alias.length != 0 && !"numStatistics".equals(alias[0])) {
                try {
                    ProxoolFacade.removeConnectionPool("world");
                    logger.info("remove connection pool named world");
                } catch (ProxoolException e) {
                    e.printStackTrace();
                }
            }
        }
        return db;
    }

    /**
     * clearDBType
     *
     * @Title: clearDBType
     * @Description: 清理连接类型
     */
    public static void clearDBType() {
        contextHolder.remove();
    }
}
