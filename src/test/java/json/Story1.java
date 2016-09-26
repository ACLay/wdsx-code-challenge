package json;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import aclay.codechallenge.json.Device;
import aclay.codechallenge.json.DeviceException;
import aclay.codechallenge.json.Reader;

public class Story1 {

	@Test
	public void testDeviceLoading() {
		//Load devices using the Reader class
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
		
		// Check the loading worked as expected
		assertNotNull("Devices should have been loaded", devices);
		assertSame("List should have 3 devices", devices.size(), 3);
	}

}
