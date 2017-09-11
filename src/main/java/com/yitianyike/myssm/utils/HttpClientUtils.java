package com.yitianyike.myssm.utils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("deprecation")
public class HttpClientUtils {
	private static final Log log = LogFactory.getLog(HttpClientUtils.class);
	/**
	 * 初始化HttpClient
	 */
	private static HttpClient httpClient = null;

	/**
	 * 生产HttpClient实例 公开，静态的工厂方法，需要使用时才去创建该单体
	 *
	 * @return
	 */
	public static HttpClient getHttpClient() {
		if (httpClient == null) {
			httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());
		}
		return httpClient;
	}

	/**
	 * POST
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String executeByPOST(String url, List<NameValuePair> params) {
		HttpClient httpclient = getHttpClient();
		HttpPost post = new HttpPost(url);

		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseJson = null;
		try {
			if (params != null) {
				post.setEntity(new UrlEncodedFormEntity(params));
			}
			responseJson = httpclient.execute(post, responseHandler);
			log.info("HttpClient POST请求结果：" + responseJson);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			log.info("HttpClient POST请求异常：" + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().closeExpiredConnections();
			httpclient.getConnectionManager().closeIdleConnections(30, TimeUnit.SECONDS);
		}
		return responseJson;
	}

	/**
	 * GET
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String executeByGET(String url, Object[] params) {
		HttpClient httpclient = getHttpClient();
		String messages = MessageFormat.format(url, params);
		HttpGet get = new HttpGet(messages);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseJson = null;
		try {
			responseJson = httpclient.execute(get, responseHandler);
			log.info("HttpClient GET请求结果：" + responseJson);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			log.info("HttpClient GET请求异常：" + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			log.info("HttpClient GET请求异常：" + e.getMessage());
		} finally {
			httpclient.getConnectionManager().closeExpiredConnections();
			httpclient.getConnectionManager().closeIdleConnections(30, TimeUnit.SECONDS);
		}
		return responseJson;
	}

	public static String executeByGET(String url) {
		HttpClient httpclient = getHttpClient();
		HttpGet get = new HttpGet(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseJson = null;
		try {
			responseJson = httpclient.execute(get, responseHandler);
			log.info("HttpClient GET请求结果：" + responseJson);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			log.info("HttpClient GET请求异常：" + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			log.info("HttpClient GET请求异常：" + e.getMessage());
		} finally {
			httpclient.getConnectionManager().closeExpiredConnections();
			httpclient.getConnectionManager().closeIdleConnections(30, TimeUnit.SECONDS);
		}
		return responseJson;
	}



	
	public
	static Map<String, Object> varysimipeJSON2Map(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		JSONObject json = JSON.parseObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
				map.put(k.toString(), v);
			}
		return map;
	}
	
	/**
	 * map转javaBean
	 * 
	 * @param type
	 * @param map
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 */
	public static Object convertMap(Class type, Map<String,Object> map)
			throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
		Object obj = type.newInstance(); // 创建 JavaBean 对象

		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();

			if (map.containsKey(propertyName)) {
				// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
				try {
					Object value = map.get(propertyName);
					Object[] args = new Object[1];
					args[0] = value;
					descriptor.getWriteMethod().invoke(obj, args);
				} catch (Exception e) {
					continue;
				}

			}
		}
		return obj;
	}

	
	public static String newget(String url) throws Exception, IOException{
	       HttpClient httpclient = new DefaultHttpClient();  
        String result = "";  
         try {  
               // 连接超时  
              httpclient.getParams().setParameter(  
                          CoreConnectionPNames. CONNECTION_TIMEOUT, 5000);  
               // 读取超时  
              httpclient.getParams().setParameter(  
                          CoreConnectionPNames. SO_TIMEOUT, 5000);  

               HttpGet hg = new HttpGet (url);  
               //模拟浏览器  
              hg.addHeader( "User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31");   
              String charset = "GBK";   
              hg.setURI( new java.net.URI(url));   
                    HttpResponse response = httpclient.execute(hg);   
                    HttpEntity entity = response.getEntity();   
                    if (entity != null) {   
                        charset = getContentCharSet(entity);  
                           // 使用EntityUtils的toString方法，传递编码，默认编码是ISO-8859-1   
                        result = EntityUtils.toString(entity, charset);   
                  }   
    
        } finally {  
              httpclient.getConnectionManager().shutdown();  
        }  
         return result;  
	}
	
	
	
	   public static String getContentCharSet(final HttpEntity entity)   
		        throws ParseException {   
		   
		        if (entity == null) {   
		            throw new IllegalArgumentException("HTTP entity may not be null");   
		        }   
		        String charset = null;   
		        if (entity.getContentType() != null) {    
		            HeaderElement values[] = entity.getContentType().getElements();   
		            if (values.length > 0) {   
		                NameValuePair param = values[0].getParameterByName("charset" );   
		                if (param != null) {   
		                    charset = param.getValue();   
		                }   
		            }   
		        }   
		         
		        if(StringUtils.isEmpty(charset)){  
		            charset = "GBK";  
		        }  
		        return charset;   
		    }  
}
