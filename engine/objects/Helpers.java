package engine.objects;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.Gson;

public class Helpers {

	public static GsonMap getGsonMap(String mapName) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(mapName), StandardCharsets.UTF_8);
		
		StringBuilder builder = new StringBuilder();
		for(String s : lines) {
		    builder.append(s);
		}
		String totalFile =  builder.toString();
		
		Gson gson = new Gson();
		GsonMap gsonMap = gson.fromJson(totalFile, GsonMap.class);
		return gsonMap;
	}
	/**
	 * A helper method to get the file extension of a file.
	 * @param filename - Name of the file.
	 * @return - The extension of the file.
	 */
	public static Object getExtension(String filename) {
		int i = filename.lastIndexOf('.');
		String extension = "";
		if (i > 0) {
		    extension = filename.substring(i+1);
		}
		return extension;
	}
	
}
