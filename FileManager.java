package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class FileManager {

	private static Gson gson = new Gson();

	// Define the root directory and the toggle directory within it
	private static File ROOT_DIR = new File("client");
	public static File TOGGLE_DIR = new File(ROOT_DIR, "Toggle");

	// Initialize the file manager by creating necessary directories if they do not exist
	public static void init() {
		if(!ROOT_DIR.exists()) { ROOT_DIR.mkdirs(); }
		if(!TOGGLE_DIR.exists()) { TOGGLE_DIR.mkdirs(); }
	}

	// Getter method to retrieve the Gson instance used for JSON serialization/deserialization
	public static Gson getGson() {
		return gson;
	}

	// Write an object to a JSON file
	public static boolean writeJsonToFile(File file, Object obj) {

		try {
			// If the file does not exist, create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// Open a FileOutputStream for the specified file
			FileOutputStream outputStream = new FileOutputStream(file);

			// Write the JSON representation of the object to the file
			outputStream.write(gson.toJson(obj).getBytes());

			// Flush and close the output stream
			outputStream.flush();
			outputStream.close();

			// Indicate that the operation was successful
			return true;
		}
		catch (IOException e) {
			// Print the stack trace if an exception occurs during the operation
			e.printStackTrace();
			// Indicate that the operation failed
			return false;
		}

	}

	// Read an object from a JSON file
	public static <T extends Object> T readFromJson(File file, Class<T> c) {

		try {
			// Open a FileInputStream for the specified file
			FileInputStream fileInputStream = new FileInputStream(file);

			// Create InputStreamReader and BufferedReader for efficient reading
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			// Read the contents of the file into a StringBuilder
			StringBuilder builder = new StringBuilder();
			String line;

			// Append each line of the file to the StringBuilder
			while((line = bufferedReader.readLine()) != null) {
				builder.append(line);
			}

			// Close the BufferedReader, InputStreamReader, and FileInputStream
			bufferedReader.close();
			inputStreamReader.close();
			fileInputStream.close();

			// Convert the JSON string to the specified object type using Gson
			return gson.fromJson(builder.toString(), c);

		}
		catch (IOException e) {
			// Print the stack trace if an exception occurs during the operation
			e.printStackTrace();
			// Return null to indicate failure
			return null;
		}
	}

}
