package aclay.codechallenge.json;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class Reader {

	public String readFile(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
	}
	
	public List<Device> readDevicesFromFile(String fileName) throws IOException, DeviceException{
		String fileContent = readFile(fileName);
		Parser parser = new Parser();
		return parser.parseDeviceArray(parser.readJsonArray(fileContent));
	}
}
