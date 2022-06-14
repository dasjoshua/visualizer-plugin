package com.sweilab.testvisualizer.handlers;

import java.io.LineNumberReader;
import java.io.FileReader;
public class FindText {
	public String searchWord(String key) throws Exception {

	    LineNumberReader lnr = new LineNumberReader(new FileReader("C:\\Users\\jo3da\\runtime-EclipseApplication\\s\\src\\s\\Targettest.java"));
	    String line = null;
	    line = recursiveSearch(lnr.readLine(), key, lnr);

	    return line;
	}

	public String recursiveSearch(String currentLineText, String key, LineNumberReader lnr)
	        throws Exception {

	    if (currentLineText != null) {
	        String lCase = currentLineText.toLowerCase();
	        if (lCase.contains(key.toLowerCase())) {

	            return ("Line " + lnr.getLineNumber() + ": " + currentLineText
	                    + "\n");
	        }
	    }
	    return recursiveSearch(lnr.readLine(), key, lnr);
	}
}
