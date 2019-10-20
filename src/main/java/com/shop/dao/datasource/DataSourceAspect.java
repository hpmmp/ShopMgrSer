package com.shop.dao.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by Cater.Tian
 */
public class DataSourceAspect {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public void before(JoinPoint point){
        Object target = point.getTarget();
        String method = point.getSignature().getName();

//        Class<?>[] clzz = target.getClass().getInterfaces();

        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
                .getMethod().getParameterTypes();
        try {
            Method m = target.getClass().getMethod(method, parameterTypes);
            logger.info("current method:{},current dataSource {}", method, DynamicDataSourceHolder.getDataSouce());
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource data = m.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.putDataSource(data.value());
                logger.info(target.getClass().getName() + "." + m.getName() + " set dataSource:" + data.value());
            } else if (!"readWriteDS".equals(DynamicDataSourceHolder.getDataSouce())) {
                logger.info("change current dataSource '{}' to defaultDataSource.", DynamicDataSourceHolder.getDataSouce());
                DynamicDataSourceHolder.clearDBType();
            }
        } catch (Exception e) {
            logger.error("set dataSource error:", e);
        }
    }
}
