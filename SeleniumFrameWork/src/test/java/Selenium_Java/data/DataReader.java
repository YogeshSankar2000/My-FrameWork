package Selenium_Java.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader{ 
	public List<HashMap<String, String>> getJsonData() throws IOException {
	Charset charsetName = StandardCharsets.UTF_8;
	
	//The Below line will read Json file and convert it to string(In bussiness we would get data in from of json)
	//Charset is standard argument need to be passed
	String jsonContent = FileUtils.readFileToString(new File
			("D:\\Eclipse_workspace\\SeleniumFrameWork\\src\\test\\java\\Selenium_Java\\data\\PurchaseOrder.json"), charsetName);
	
	//Converting the String to HashMap which then can be parsed to dataprovider
	//For this operatioin jackson builder is used add dependies 
	//ObjectMapper is a class in that that would help us
	
	ObjectMapper obj = new ObjectMapper();
	
	List<HashMap<String,String>> data = obj.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
	
	//{map, map} is the format of data which is list so we are converting Jsoncontent to an List of hashMap using tyeprefernce and readvalue method
	
	return data;
	}
}
