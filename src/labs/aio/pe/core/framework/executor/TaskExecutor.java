package labs.aio.pe.core.framework.executor;

/**
 * Base API for an executor. It should be able to execute a method.
 * This method is executed via a call to the doWork ().
 * @author pcnicky
 *
 */
public interface TaskExecutor {
	/**
	 * All executors must implement this method and provide a way
	 * to execute the file. The method and class to be executed should
	 * be identified based on the rules defined by the executor.
	 */
	void doWork ();
}
