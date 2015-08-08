package labs.aio.pe.core.framework.configuration;

/**
 * This interface provides the APPI for single line of input parsers
 * @author pcnicky
 *
 */
public interface ConfigFileLineParser {
	static class Line {
		private String key;
		private String value;
		
		Line (String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey () {
			return this.key;
		}
		
		public String getValue () {
			return this.value;
		}
	}
	
	/**
	 * Returns the instance of line by extracting key and value from the config line
	 * @param line
	 * @return
	 */
	Line parseLine (String line);
}
