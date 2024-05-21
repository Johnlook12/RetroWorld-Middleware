package com.pinguela.retroworld.util;

public class SQLUtils {
	public static final String wrapLike (String pattern) {
		StringBuilder a = new StringBuilder();
		a.append("%").append(pattern.toUpperCase()).append("%");
		return a.toString();
	}
}
