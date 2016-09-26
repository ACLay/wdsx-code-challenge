package json;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import aclay.codechallenge.json.Device;
import aclay.codechallenge.json.DeviceException;
import aclay.codechallenge.json.Reader;
import aclay.codechallenge.program.Selector;

public class Story2 {

	@Test
	public void testFilter() {
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
		List<Device> filtered = selector.searchDevices("Mockia", "5800");
		assertSame("Should only return 1 item", filtered.size(), 1);
		assertEquals("Device should be a Mockia 5800", filtered.get(0).getBrand(), "Mockia");
		assertEquals("Device should be a Mockia 5800", filtered.get(0).getModel(), "5800");
		
		filtered = selector.searchDevices("Phony", "X11");
		assertSame("Should only return 1 item", filtered.size(), 1);
		assertEquals("Device should be a Phony X11", filtered.get(0).getBrand(), "Phony");
		assertEquals("Device should be a Phony X11", filtered.get(0).getModel(), "X11");
		
		filtered = selector.searchDevices("foo", "bar");
		assertSame("Should not return any items", filtered.size(), 0);
		
	}

}
