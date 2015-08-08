package labs.aio.pe.core.framework.configuration;

import java.io.*;
import java.text.*;
import java.util.*;

import labs.aio.pe.core.framework.configuration.ConfigFileLineParser.Line;

public class DefaultConfigFileParser implements ConfigFileParser {
	private static final String NON_NULLABLE_ERROR_MESSAGE = "{0} cannot be null";
	private static final String NOT_EMPTY_ERROR_MESSAGE = "{0} cannot be empty";
	private static final String FILE_NOT_FOUND_ERROR_MESSAGE = "File {0} was not found";
	
	private Map <TaskProperty, String> properties;
	
	public DefaultConfigFileParser (String fileName) 
			throws FileNotFoundException, IllegalArgumentException {
		this (fileName, null);
	}
	
	public DefaultConfigFileParser (String fileName, ConfigFileLineParser lineParser) 
			throws FileNotFoundException, IllegalArgumentException {
		if (fileName == null) {
			throw new IllegalArgumentException(MessageFormat.format(NON_NULLABLE_ERROR_MESSAGE, fileName));
		}
		if (fileName.isEmpty()) {
			throw new IllegalArgumentException(MessageFormat.format(NOT_EMPTY_ERROR_MESSAGE, fileName));
		}
		
		File file = new File (fileName);
		if (!file.exists()) {
			throw new FileNotFoundException(MessageFormat.format(FILE_NOT_FOUND_ERROR_MESSAGE, fileName));
		}
		
		BufferedReader reader = null;
		if (lineParser == null) {
			lineParser = new DefaultConfigFileLineParser ();
		}
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			this.properties = new HashMap<>();
			while ((line = reader.readLine()) != null) {
				Line inputLine = lineParser.parseLine(line);
				this.properties.put(TaskProperty.getProperty(inputLine.getKey()), inputLine.getValue());
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public Map<TaskProperty, String> parse () {
		return properties;
	}
	
	private class DefaultConfigFileLineParser implements ConfigFileLineParser {
		@Override
		public Line parseLine (String input) {
			String[] data = input.split(":");
			Line line = new Line (data[0], data[1]);
			return line;
		}
	}
}
