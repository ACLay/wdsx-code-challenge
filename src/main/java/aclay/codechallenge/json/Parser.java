package aclay.codechallenge.json;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParsingException;

public class Parser {
	
	public JsonArray readJsonArray(String data) throws JsonParsingException{
		JsonReader jsonReader = Json.createReader(new StringReader(data));
		JsonArray json = jsonReader.readArray();
		jsonReader.close();
		return json;
	}
	
	public List<Device> parseDeviceArray(JsonArray jsonArray) throws DeviceException {
		List<Device> devices = new ArrayList<Device>();
		
		for(int i = 0; i < jsonArray.size(); i++){
			JsonObject jsonDevice;
			try {
				jsonDevice = jsonArray.getJsonObject(i);
			} catch (ClassCastException e) {
				throw new DeviceException("List	element is not a json object");
			}
			
			Device device = parseDevice(jsonDevice);
			devices.add(device);
		}
		
		
		return devices;
	}
	
	public Device parseDevice(JsonObject json) throws DeviceException{
		
		String brand;
		String model;
		String formFactor;
		JsonArray jsonAttributes;
		try {
			brand = json.getString("brand");
			model = json.getString("model");
			formFactor = json.getString("formFactor");
			jsonAttributes = json.getJsonArray("attributes");
		} catch (NullPointerException e) {
			throw new DeviceException(e.getMessage());
		} catch (ClassCastException e) {
			throw new DeviceException(e.getMessage());
		}
		
		Map<String, String> attributes = new HashMap<String, String>();
		for(int i = 0; i < jsonAttributes.size(); i++){
			JsonObject attribute;
			try {
				attribute = jsonAttributes.getJsonObject(i);
			} catch (ClassCastException e) {
				throw new DeviceException("Attribute is not a json object");
			}
			String name = attribute.getString("name");
			String value = attribute.getString("value");
			try {
				name = attribute.getString("name");
				value = attribute.getString("value");
			} catch (NullPointerException e) {
				throw new DeviceException(e.getMessage());
			} catch (ClassCastException e) {
				throw new DeviceException(e.getMessage());
			}
			attributes.put(name, value);
		}
		
		return new Device(brand, model, formFactor, attributes);
	}
}
