package json;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import org.junit.Test;

import aclay.codechallenge.json.Device;
import aclay.codechallenge.json.DeviceException;
import aclay.codechallenge.json.Reader;

public class Story3 {

	@Test
	public void test() {
		//Redirect Stderr
		final ByteArrayOutputStream err = new ByteArrayOutputStream();
		System.setErr(new PrintStream(err));
		
		//Load devices using the Reader class
		Reader reader = new Reader();
		List<Device> devices = null;
		try {
			devices = reader.readDevicesFromFile("bad_devices.json");
		} catch (IOException e) {
			e.printStackTrace();
			fail("unable to read file");
		} catch (DeviceException e) {
			e.printStackTrace();
			fail("unable to parse devices");
		}
		
		// Check the expected errors are reported
		String errors = err.toString();
		String expectation = "Device 0 is invalid. Illegal form factor\nDevice 1 is invalid. Attribute 0 has no name\nDevice 2 is invalid. Attribute 0 name too long, 20 characters max\n";
		assertEquals(errors, expectation);
		//TODO add tests for every way a device could be invalid.
	}

}
