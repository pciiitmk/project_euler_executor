package labs.aio.pe.core.framework.annotations;

/**
 * Defines different types of methods available in a Task.
 * @author pcnicky
 *
 */
public enum MethodType {
	NONE,
	/**
	 * Annotates a method that needs to be executed in a task.
	 */
	EXECUTABLE, 
	/**
	 * A task can be initialized via this type of method.
	 */
	PRE_EXECUTION, 
	/**
	 * This type of method does a cleanup job.
	 */
	POST_EXECUTION;
}
