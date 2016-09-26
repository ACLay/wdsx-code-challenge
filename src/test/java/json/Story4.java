package json;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import aclay.codechallenge.json.Device;
import aclay.codechallenge.json.DeviceException;
import aclay.codechallenge.json.Reader;
import aclay.codechallenge.program.Selector;

public class Story4 {

	@Test
	public void testBrandSearch() {
		// Load the devices from file
		Reader reader = new Reader();
		List<Device> devices = null;
		try {
			devices = reader.readDevicesFromFile("devices.json");
		} catch (IOException e) {
			e.printStackTrace();
			fail("unable to read file");
		} catch (DeviceException e) {
			e.printStackTrace();
			fail("unable to parse devices");
		}
		
		// Run searches on the test data
		Selector selector = new Selector(devices);
		List<Device> filtered = selector.searchBrand("Mockia");
		assertSame("Should only return 1 item", filtered.size(), 1);
		assertEquals("Device should be a Mockia", filtered.get(0).getBrand(), "Mockia");
		
		filtered = selector.searchBrand("X11");
		assertSame("Should not match model name", filtered.size(), 0);
		
		filtered = selector.searchBrand("foo");
		assertSame("Should not match non existent brand", filtered.size(), 0);
		
	}
	
	@Test
	public void testModelSearch() {
		// Load the devices from file
		Reader reader = new Reader();
		List<Device> devices = null;
		try {
			devices = reader.readDevicesFromFile("devices.json");
		} catch (IOException e) {
			e.printStackTrace();
			fail("unable to read file");
		} catch (DeviceException e) {
			e.printStackTrace();
			fail("unable to parse devices");
		}
		
		// Run test searches on the device list
		Selector selector = new Selector(devices);
		List<Device> filtered = selector.searchModel("5800");
		assertSame("Should only return 1 item", filtered.size(), 1);
		assertEquals("Device should be a 5800 model", filtered.get(0).getModel(), "5800");
		
		filtered = selector.searchModel("Phony");
		assertSame("Should not match brand name", filtered.size(), 0);
		
		filtered = selector.searchModel("bar");
		assertSame("Should not match non existent model", filtered.size(), 0);
		
	}

}
