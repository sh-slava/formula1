package com.formulaOne.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class ReaderTest {
	Reader reader = new Reader();

	@Test
	void readFile_shouldThrowIllegalArgumentException_whenFileNameEmptyOrNull() {
		assertAll(
				() -> assertThrows(IllegalArgumentException.class, () -> reader.readFile(null)),
				() -> assertThrows(IllegalArgumentException.class, () -> reader.readFile("")));
	}

	@Test
	void readFile_shouldThrowFileNotFoundException_whenFileNotExist() {
		assertThrows(FileNotFoundException.class, () -> reader.readFile("fooFileName"));
	}

	@Test
	void readFile_shouldThrowFileNotFoundException_whenFileNotExist2() {
		assertThrows(IllegalArgumentException.class, () -> reader.readFile("emptyTestFile.txt"));
	}
}
