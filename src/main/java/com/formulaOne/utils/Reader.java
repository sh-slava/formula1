package com.formulaOne.utils;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;

import com.formulaOne.constants.ExceptionsConstants;

public class Reader {
	public List<String> readFile(String fileName) throws IOException {
		if (fileName == null || fileName.isEmpty()) {
			throw new IllegalArgumentException(ExceptionsConstants.EMPTY_FILE_NAME);
		}
		try {
			List<String> lines = Files.readAllLines(Paths.get(
					Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(fileName)).toURI()));

			if (lines.isEmpty()) {
				throw new IllegalArgumentException(ExceptionsConstants.EMPTY_FILE);
			}
			return lines;
		} catch (NullPointerException | URISyntaxException e) {
			throw new FileNotFoundException(ExceptionsConstants.FILE_NOT_FOUND);
		}
	}
}
