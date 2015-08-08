package labs.aio.pe.core.framework.annotations;

import java.lang.annotation.*;

/**
 * This annotation defines a class to be a Task.
 * @author pcnicky
 *
 */
@Retention (RetentionPolicy.RUNTIME)
public @interface Task {
	TaskVersion version () default @TaskVersion;
	String createdOn () default "";
}
