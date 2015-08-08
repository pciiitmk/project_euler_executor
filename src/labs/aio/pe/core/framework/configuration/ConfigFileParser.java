package labs.aio.pe.core.framework.configuration;

import java.util.*;

/**
 * This is the most generic parser. It can be used to parse any type of
 * file. This interface makes the application completely decoupled from
 * how the configuration is provided to the application. Users can create 
 * ConfigFileParser for xml, json etc.
 * @author pcnicky
 *
 */
public interface ConfigFileParser {
	/**
	 * This method returns the result of the parsed config file as
	 * a map type. A reference example is given as DefaultConfigileParser
	 * @return
	 */
	Map<TaskProperty, String> parse ();
}
