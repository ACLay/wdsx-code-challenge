package program;

import java.io.IOException;
import java.util.List;

import javax.json.JsonArray;
import javax.json.stream.JsonParsingException;

import aclay.codechallenge.json.Device;
import aclay.codechallenge.json.DeviceException;
import aclay.codechallenge.json.Parser;
import aclay.codechallenge.json.Reader;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String contents = "empty";
		try {
			contents = new Reader().readFile("devices.json");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(contents);
		
		JsonArray json = null;
		try {
			json = new Parser().readJsonArray(contents);
		} catch (JsonParsingException e) {
			e.printStackTrace();
		}
		if (json != null) {
			System.out.println("valid json");
		}
		
		List<Device> devices = null;
		try {
			devices = new Parser().parseDeviceArray(json);
		} catch (DeviceException e) {
			e.printStackTrace();
		}
		if (devices != null) {
			System.out.println("valid devices");
		}
	}

}
