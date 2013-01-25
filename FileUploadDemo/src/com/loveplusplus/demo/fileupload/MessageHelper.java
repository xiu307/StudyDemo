package com.loveplusplus.demo.fileupload;

import java.io.IOException;
import java.util.Properties;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.content.Context;
import android.content.res.Resources.NotFoundException;


public class MessageHelper {

	private static String NAMESPACE = null;
	private static String URL = null;
	private static String METHOD_NAME = null;
	private static String SOAP_ACTION = null;


	public MessageHelper(Context context) {
		try {
			Properties p = new Properties();
			p.load(context.getResources().openRawResource(R.raw.system));
			NAMESPACE = p.getProperty("NAMESPACE");
			URL = p.getProperty("URL");
			METHOD_NAME = p.getProperty("METHOD_NAME");
			SOAP_ACTION = NAMESPACE + METHOD_NAME;
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 调用webservice
	 * @param json
	 * @return
	 */
	public  String sendMsg(String json) {
		try {
			SoapObject rpc = new SoapObject(NAMESPACE, METHOD_NAME);
			rpc.addProperty("arg0", json);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = false;
			envelope.encodingStyle = "UTF-8";
			envelope.setOutputSoapObject(rpc);
			new MarshalBase64().register(envelope);
			HttpTransportSE aht = new HttpTransportSE(URL, 60 * 1000); 

			aht.call(SOAP_ACTION, envelope);
			Object result = (Object) envelope.getResponse();
			return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
