package labs.aio.pe.core.framework.configuration;

/**
 * Defines all the parameters (configuration) that could be set
 * for the tasks.
 * @author pcnicky
 *
 */
public enum TaskProperty {
	// Name of the package that needs to be scanned
	BASE_PACKAGE ("base-package"),
	
	// Name of the class that needs to be loaded and executed
	CLASS_NAME ("class-name"),

	// {comma separated class names}
	// Execute only the tasks provided by this list
	CLASS_NAMES("class-names"),
	
	PROPERTY_NONE ("");
	
	private String propertyName;
	
	private TaskProperty (String propertyName) {
		this.propertyName = propertyName;
	}
	
	@Override
	public String toString () {
		return this.propertyName;
	}
	
	public static TaskProperty getProperty (String name) {
		for (TaskProperty property : values()) {
			if (property.toString().equals(name)) {
				return property;
			}
		}
		return PROPERTY_NONE;
	}
}
