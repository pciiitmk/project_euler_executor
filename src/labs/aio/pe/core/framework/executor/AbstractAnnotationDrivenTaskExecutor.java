package labs.aio.pe.core.framework.executor;

import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

import sun.net.*;

import labs.aio.pe.core.framework.annotations.*;
import labs.aio.pe.core.framework.configuration.*;

public abstract class AbstractAnnotationDrivenTaskExecutor extends AbstractTaskExecutor {
	public AbstractAnnotationDrivenTaskExecutor(String configFileName, 
			ConfigFileLineParser lineParser, 
			ConfigFileParser fileParser) throws FileNotFoundException {
		super(configFileName, lineParser, fileParser);
	}

	/**
	 * This method does a lot of work.
	 * 1. It reads the configuration properties and will decide on the course of action
	 * based on this.
	 * 2. It will read annotations from the classes and decide on which task needs to be
	 * executed.
	 * 3. Once it has read and created the necessary instances, its going to execute the 
	 * executable method, postconstruct, and predestroy methods.
	 */
	@Override
	public void doWork() {
		boolean isBasePackageConfigured = properties.get(TaskProperty.BASE_PACKAGE) != null;
		boolean isClasssNameConfigured = properties.get(TaskProperty.CLASS_NAME) != null;
		if (isClasssNameConfigured) {
			// Only one class is configured
			try {
				Class claz = Class.forName(properties.get(TaskProperty.CLASS_NAME));
				if (isTask(claz)) {
					Map<MethodType, Method> taskMethods = getAnnotatedMethods(claz);
					// Instantiate the instance on which the method has to be executed
					Object object = claz.newInstance();
					Method preexec = taskMethods.get(MethodType.PRE_EXECUTION);
					Method exec = taskMethods.get(MethodType.POST_EXECUTION);
					Method postexec = taskMethods.get(MethodType.EXECUTABLE);
					if (preexec != null) {
						preexec.invoke(object);
					}
					if (exec != null) {
						this.preExecution();
						exec.invoke(object);
						this.postExecution();
					}
					if (postexec != null) {
						postexec.invoke(object);
					}
				}
			} catch (ClassNotFoundException 
					| InstantiationException 
					| IllegalAccessException 
					| IllegalArgumentException 
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Map<MethodType, Method> getAnnotatedMethods (Class claz) {
		Method[] methods = claz.getDeclaredMethods();
		Map<MethodType, Method> taskMethods = new HashMap<>();
		for (Method method : methods) {
			Annotation[] annotations = method.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof TaskMethod) {
					MethodType type = ((TaskMethod) annotation).type();
					switch (type) {
					case PRE_EXECUTION:
						taskMethods.put(MethodType.PRE_EXECUTION, method);
						break;
					case EXECUTABLE:
						taskMethods.put(MethodType.EXECUTABLE, method);
						break;
					case POST_EXECUTION:
						taskMethods.put(MethodType.POST_EXECUTION, method);
						break;
					}
				}
			}
		}
		return taskMethods;
	}
	
	private boolean isTask (Class claz) {
		Annotation[] annotations = claz.getDeclaredAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation instanceof Task) {
				return true;
			}
		}
		return false;
	}
}
