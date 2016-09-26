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
			// retrieve the json object
			JsonObject jsonDevice;
			try {
				jsonDevice = jsonArray.getJsonObject(i);
			} catch (ClassCastException e) {
				System.err.println("Device " + i + " is not a json object");
				continue;
			}
			
			// convert it to a Device object
			Device device;
			try {
				device = parseDevice(jsonDevice);
			} catch (DeviceException e) {
				System.err.println("Device " + i + " is invalid. " + e.getMessage());
				continue;
			}
			
			// check it's unique
			for(int j = 0; j < i; j++){
				Device otherDevice = devices.get(j);
				if(otherDevice.getBrand().equals(device.getBrand()) && otherDevice.getModel().equals(device.getModel())){
					System.err.println("Device " + i + " is invalid. Has same Brand and Model as device " + j);
				}
			}
			
			devices.add(device);
		}
		
		return devices;
	}
	
	public Device parseDevice(JsonObject json) throws DeviceException{
		
		String brand;
		String model;
		String formString;
		FormFactor form;
		JsonArray jsonAttributes;
		try {
			brand = json.getString("brand");
			model = json.getString("model");
			formString = json.getString("formFactor");
			jsonAttributes = json.getJsonArray("attributes");
		} catch (NullPointerException e) {
			throw new DeviceException(e.getMessage());
		} catch (ClassCastException e) {
			throw new DeviceException(e.getMessage());
		}
		try {
			form = FormFactor.valueOf(formString);
		} catch (IllegalArgumentException e) {
			throw new DeviceException("Illegal form factor");
		}
		
		if (brand == null || brand.equals("")) {
			throw new DeviceException("Brand not provided");
		}
		if (brand.length() > 50) {
			throw new DeviceException("Brand too long, 50 characters max");
		}
		
		if (model == null || model.equals("")) {
			throw new DeviceException("Model not provided");
		}
		if (model.length() > 50) {
			throw new DeviceException("Model too long, 50 characters max");
		}
		
		
		
		Map<String, String> attributes = new HashMap<String, String>();
		for(int i = 0; i < jsonAttributes.size(); i++){
			JsonObject attribute;
			try {
				attribute = jsonAttributes.getJsonObject(i);
			} catch (ClassCastException e) {
				throw new DeviceException("Attribute " + i + " is not a json object");
			}
			String name;
			String value;
			try {
				name = attribute.getString("name");
			} catch (NullPointerException e) {
				throw new DeviceException("Attribute " + i + " has no name");
			} catch (ClassCastException e) {
				throw new DeviceException("Attribute " + i + " has no name");
			}
			try {
				value = attribute.getString("value");
			} catch (NullPointerException e) {
				throw new DeviceException("Attribute " + i + " has no value");
			} catch (ClassCastException e) {
				throw new DeviceException("Attribute " + i + " has no value");
			}
			
			if (name.length() > 20) {
				throw new DeviceException("Attribute " + i + " name too long, 20 characters max");
			}
			if (value.length() > 100) {
				throw new DeviceException("Attribute " + i + " value too long, 100 characters max");
			}
			attributes.put(name, value);
		}
		
		return new Device(brand, model, form, attributes);
	}
}
