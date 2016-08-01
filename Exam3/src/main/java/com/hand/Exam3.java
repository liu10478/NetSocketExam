package com.hand;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * http://hq.sinajs.cn/list=sz300170
 *
 */
public class Exam3 
{
   

	public static void main( String[] args )
    {
		String arr[] = null;
    	try {
    		String line=null;
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			URL url = new URL("http://hq.sinajs.cn/list=sz300170");
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("encoding", "UTF-8");
			connection.setDoInput(true);
			InputStream is= connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is,"gb2312");
			BufferedReader br = new BufferedReader(isr);
			while((line=br.readLine())!=null){
				arr= line.split(",");
			}
			br.close();
			isr.close();
			is.close();
			
			
			try {
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.newDocument();
				Element root = document.createElement("xml");
				
				Element stock = document.createElement("stock");
				root.appendChild(stock);
				Element name = document.createElement("name");
				name.setTextContent("汉得信息");
				Element open = document.createElement("open");
				open.setTextContent(arr[1]);
				Element close = document.createElement("close");
				close.setTextContent(arr[2]);
				Element current = document.createElement("current");
				current.setTextContent(arr[3]);
				Element high = document.createElement("high");
				high.setTextContent(arr[4]);
				Element low = document.createElement("low");
				low.setTextContent(arr[5]);
				stock.appendChild(name);
				stock.appendChild(open);
				stock.appendChild(close);
				stock.appendChild(current);
				stock.appendChild(high);
				stock.appendChild(low);
				
				document.appendChild(root);
				try {
					TransformerFactory factory2 = TransformerFactory.newInstance();
					Transformer transformer = factory2.newTransformer();
					StringWriter stringWriter = new StringWriter();
					transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
	
				transformer.transform(new DOMSource(document), new StreamResult("test.xml"));
				} catch (TransformerConfigurationException e) {
					e.printStackTrace();
				} catch (TransformerFactoryConfigurationError e) {
					e.printStackTrace();
				} catch (TransformerException e) {
					e.printStackTrace();
				}
				
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			
			
			
			JsonObject object = new JsonObject();
			object.addProperty("name", "汉得信息");
			object.addProperty("open", arr[1]);
			object.addProperty("close", arr[2]);
			object.addProperty("current", arr[2]);
		
			object.addProperty("high", arr[3]);
			
			object.addProperty("low", arr[4]);
			
			FileOutputStream fos = new FileOutputStream("test2.txt");
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(object.toString());
			bw.flush();
			bw.close();
			osw.close();
			fos.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
