package core;

import java.io.FileReader;
import java.net.URL;
import java.util.Properties;

public class UtilFunctions {

	public static Properties getPropertyFile(String fileName) throws Exception {
		URL path = UtilFunctions.class.getResource("/testdata/" + fileName);
		System.out.println(path.getPath());
		FileReader reader = new FileReader(path.getPath());
		Properties p = new Properties();
		p.load(reader);
		return p;
	}
}
