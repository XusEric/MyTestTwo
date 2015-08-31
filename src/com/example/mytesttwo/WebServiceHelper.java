package com.example.mytesttwo;


import java.util.HashMap;  
import java.util.Map.Entry;  
import org.ksoap2.SoapEnvelope;  
import org.ksoap2.serialization.SoapObject;  
import org.ksoap2.serialization.SoapSerializationEnvelope;  
import org.ksoap2.transport.HttpTransportSE;  
import android.annotation.SuppressLint;  
/** 
 * ����Web Service�Ĺ����� 
 * @author jCuckoo 
 *  
 */  
@SuppressLint("NewApi")  
public class WebServiceHelper {  
//    static {  
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.ICE_CREAM_SANDWICH){  
//            // 4.0�Ժ���Ҫ�����������д��룬�ſ��Է���Web Service  
//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
//                    .detectDiskReads().detectDiskWrites().detectNetwork()  
//                    .penaltyLog().build());  
//  
//            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
//                    .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()  
//                    .penaltyLog().penaltyDeath().build());  
//        }  
//        //4.0��ǰ�汾����Ҫ��������  
//    }  
    /** 
     * @param url          web service·�� 
     * @param serviceName  web service�������� 
     * @param nameSpace    web service���ƿռ� 
     * @param methodName   web service�������� 
     * @param params       web service�������� 
     */  
    public static SoapObject getSoapObject(String serviceName,  
            String methodName, String soapAction, HashMap<String, Object> params) {  
        String URL = "http://192.168.31.161:8080/CXFWebservice/"+ serviceName + "?wsdl";  
        String NAMESPACE = "http://iservice.java.com/";// ���ƿռ䣬�����������ɵ�namespace����ֵ  
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