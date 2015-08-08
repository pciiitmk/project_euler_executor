package labs.aio.pe.core.framework.annotations;

import java.lang.annotation.*;

/**
 * Tells what methods can be task methods. In current implementation,
 * there are three types of task methods that actually do something. They
 * are PRE_EXECUTION, POST_EXECUTION, EXECUTION. NONE type methods are
 * ignored. No restrictions have been placed on number of methods that can
 * have same annotation but the result would be system dependent if something 
 * like that is attempted.
 * @author pcnicky
 *
 */
@Retention (RetentionPolicy.RUNTIME)
public @interface TaskMethod {
	/**
	 * Defines the method type
	 * @return
	 */
	MethodType type () default MethodType.NONE;
}
