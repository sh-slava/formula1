package com.formulaOne;

import java.io.IOException;

import com.formulaOne.utils.*;

public class Main {

	public static void main(String[] args) throws IOException {
		String startName = "start.log";
      String endName = "end.log";
      String abbreviations = "abbreviations.txt";

      Printer printer = new Printer();
      Reader reader = new Reader();
      Parser parser = new Parser();

      Result result = new Result(reader, parser);

      System.out.println(printer.getStringForPrinting(result.findResultLaps(startName, endName, abbreviations), 15));

	}

}
