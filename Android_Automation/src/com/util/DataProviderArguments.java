package com.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The Interface DataProviderArguments.
 * @author Dhanashree
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DataProviderArguments {
	String path();
}
