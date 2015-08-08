package labs.aio.pe.core.framework.executor;

/**
 * This interface allows the executors to perform actions based on the execution
 * state of the executor itself. It is more like a helper so that some of the 
 * tasks could be hooked into the code base itself.
 * @author pcnicky
 *
 */
public interface ExecutionStateAwareTaskExecutor extends TaskExecutor {
	/**
	 * This method is called after the task's initialization.
	 */
	void preExecution ();
	
	/**
	 * This method is called after the task's execution has completed.
	 */
	void postExecution ();
}
