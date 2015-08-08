package labs.aio.pe.core.framework.executor;

import java.io.*;

import labs.aio.pe.core.framework.configuration.*;

public class DefaultAnnotationDrivenTaskExecutor extends AbstractAnnotationDrivenTaskExecutor {
	
	public DefaultAnnotationDrivenTaskExecutor(String configFileName,
			ConfigFileLineParser lineParser, ConfigFileParser fileParser)
			throws FileNotFoundException {
		super(configFileName, lineParser, fileParser);
	}

	@Override
	public void preExecution() {
		// Do nothing
	}

	@Override
	public void postExecution() {
		// Do nothing
	}
	
}
