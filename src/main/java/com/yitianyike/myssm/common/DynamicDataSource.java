package com.yitianyike.myssm.common;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author fuqiang
 *配置多数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource{

	public static final String DATASOURCE_A = "dataSourceA";
    public static final String DATASOURCE_B = "dataSourceB";
    public static final String DATASOURCE_C = "dataSourceC";
    //本地线程，获取当前正在执行的currentThread
    public static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setCustomerType(String customerType) {
        //把当前请求的参数，存入当前线程，这个参数是我们定义的DATASOURCE_A或者DATASOURCE_B
        System.out.println("当前切换的数据源="+customerType);
        contextHolder.set(customerType);
    }
    public static String getCustomerType() {

        return contextHolder.get();
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }

    protected Object determineCurrentLookupKey() {
        return getCustomerType();
    }
}
