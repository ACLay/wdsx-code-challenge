package aclay.codechallenge.program;

import java.util.ArrayList;
import java.util.List;

import aclay.codechallenge.json.Device;

public class Selector {
	
	private List<Device> devices;

	public Selector(List<Device> devices){
		this.devices = devices;
	}
	
	public List<Device> searchDevices(String brand, String model){
		List<Device> filtered = new ArrayList<Device>();
		
		for(Device device : devices) {
			if(device.getBrand().equals(brand) && device.getModel().equals(model)){
				filtered.add(device);
			}
		}
		
		return filtered;
	}
	
	public List<Device> searchBrand(String brand){
		List<Device> filtered = new ArrayList<Device>();
		
		for(Device device : devices) {
			if(device.getBrand().equals(brand)){
				filtered.add(device);
			}
		}
		
		return filtered;
	}
	
	public List<Device> searchModel(String model){
		List<Device> filtered = new ArrayList<Device>();
		
		for(Device device : devices) {
			if(device.getModel().equals(model)){
				filtered.add(device);
			}
		}
		
		return filtered;
	}
}
