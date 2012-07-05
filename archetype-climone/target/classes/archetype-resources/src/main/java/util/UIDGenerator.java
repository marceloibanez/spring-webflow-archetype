package ${package}.util;

import org.apache.commons.lang.RandomStringUtils;

public class UIDGenerator {

	private static final String ALPHABET = "abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVXYZ0123456789";

	public static final String generateUID() {
		return RandomStringUtils.random(64, ALPHABET);
	}
}
