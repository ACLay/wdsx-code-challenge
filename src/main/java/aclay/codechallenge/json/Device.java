package aclay.codechallenge.json;

import java.util.Map;
import java.util.Set;

public class Device {

	private String brand;
	private String model;
	private FormFactor form;
	private Map<String, String> attributes;
	
	public Device(String brand, String model, FormFactor form, Map<String, String> attributes){
		this.brand = brand;
		this.model = model;
		this.form = form;
		this.attributes = attributes;
	}
	
	public String getBrand(){
		return brand;
	}
	public String getModel(){
		return model;
	}
	public FormFactor getFormFactor(){
		return form;
	}
	public Set<String> getAttributeNames(){
		return attributes.keySet();
	}
	public String getAttribute(String attributeName){
		return attributes.get(attributeName);
	}

}
