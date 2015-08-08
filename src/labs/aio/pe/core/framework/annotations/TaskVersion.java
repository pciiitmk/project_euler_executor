package labs.aio.pe.core.framework.annotations;

import java.lang.annotation.*;

/**
 * In current implementation this has no effect but in later versions
 * it has been decided that the version can also be used as distinguishing
 * criteria as to which EXCEUTION type method needs to be executed.
 * @author pcnicky
 *
 */
@Retention (RetentionPolicy.RUNTIME)
public @interface TaskVersion {
	String version () default "1.0";
}
