package labs.aio.pe.core.framework.executor;

import java.io.*;
import java.util.*;

import labs.aio.pe.core.framework.configuration.*;

/**
 * This class provides the base of the task executor. The class lays down
 * a structure that allows reading properties files for the task completion.
 * The properties files can have a set of properties as defined by TaskProperty
 * enum and the implementing classes will perform more important functions 
 * on the basis of these properties. Note that the properties itself may vary
 * based on the Executor type used.
 * @author pcnicky
 *
 */
public abstract class AbstractTaskExecutor implements ExecutionStateAwareTaskExecutor {
	// Provides config options for the executor.
	protected Map<TaskProperty, String> properties;
	
	public AbstractTaskExecutor (String fileName) throws FileNotFoundException {
		this (fileName, null);
	}
	
	public AbstractTaskExecutor (String configFileName, 
			ConfigFileLineParser lineParser) throws FileNotFoundException {
		this (configFileName, lineParser, null);
	}
	
	/**
	 * Creates the instance of the executor.
	 * @param packageName
	 * @throws IllegalArgumentException
	 */
	public AbstractTaskExecutor (String configFileName, 
			ConfigFileLineParser lineParser, 
			ConfigFileParser fileParser) throws FileNotFoundException {
		if (fileParser == null) {
			fileParser = new DefaultConfigFileParser (configFileName, lineParser);
		}
		properties = fileParser.parse();
	}
}
