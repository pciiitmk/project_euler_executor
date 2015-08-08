package labs.aio.pe.solver;

import labs.aio.pe.core.framework.executor.*;

public class Solver {
	public static void main (String[] args) throws Exception {
		TaskExecutor executor = new DefaultAnnotationDrivenTaskExecutor("config", null, null);
		executor.doWork();
		return;
	}
}
