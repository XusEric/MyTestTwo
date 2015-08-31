package com.example.mytesttwo;


import java.util.HashMap;  
import java.util.Map.Entry;  
import org.ksoap2.SoapEnvelope;  
import org.ksoap2.serialization.SoapObject;  
import org.ksoap2.serialization.SoapSerializationEnvelope;  
import org.ksoap2.transport.HttpTransportSE;  
import android.annotation.SuppressLint;  
/** 
 * 访问Web Service的工具类 
 * @author jCuckoo 
 *  
 */  
@SuppressLint("NewApi")  
public class WebServiceHelper {  
//    static {  
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.ICE_CREAM_SANDWICH){  
//            // 4.0以后需要加入下列两行代码，才可以访问Web Service  
//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
//                    .detectDiskReads().detectDiskWrites().detectNetwork()  
//                    .penaltyLog().build());  
//  
//            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
//                    .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()  
//                    .penaltyLog().penaltyDeath().build());  
//        }  
//        //4.0以前版本不需要以上设置  
//    }  
    /** 
     * @param url          web service路径 
     * @param serviceName  web service服务名称 
     * @param nameSpace    web service名称空间 
     * @param methodName   web service方法名称 
     * @param params       web service方法参数 
     */  
    public static SoapObject getSoapObject(String serviceName,  
            String methodName, String soapAction, HashMap<String, Object> params) {  
        String URL = "http://192.168.31.161:8080/CXFWebservice/"+ serviceName + "?wsdl";  
        String NAMESPACE = "http://iservice.java.com/";// 名称空间，服务器端生成的namespace属性值  
        String METHOD_NAME = methodName;  
        String SOAP_ACTION = soapAction;  
  
        SoapObject soap = null;  
        try {  
            SoapObject rpc = new SoapObject(NAMESPACE, METHOD_NAME);  
            if (params != null && params.size() > 0) {  
                for (Entry<String, Object> item : params.entrySet()) {  
                    rpc.addProperty(item.getKey(), item.getValue().toString());  
                }  
            }  
  
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);  
            envelope.bodyOut = rpc;  
            envelope.dotNet = false;// true--net; false--java;  
            envelope.setOutputSoapObject(rpc);  
  
            HttpTransportSE ht = new HttpTransportSE(URL);  
            ht.debug = true;  
            ht.call(SOAP_ACTION, envelope);  
            try {  
                soap = (SoapObject) envelope.getResponse();  
            } catch (Exception e) {  
                soap = (SoapObject) envelope.bodyIn;  
            }  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
        return soap;  
    }  
}  
