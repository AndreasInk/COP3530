package primer;

import java.io.*;
import java.util.*;

public class TokenCounter {
	public static void main(String[] args) throws FileNotFoundException {

		Scanner input = new Scanner(System.in);
		System.out.print("Enter an input file-name: ");
		String fileName = input.nextLine();
		input.close();

		File file = new File(fileName);

		if( !file.exists() )
			throw new FileNotFoundException("Fatal error. File cannot be found!");

	    Scanner fileInput = new Scanner(file);
	    int count = 0;
	    
	    long start = System.currentTimeMillis();

		while (fileInput.hasNextLine()) {
			String[] tokens = fileInput.nextLine().split(" ");
	        for( String token : tokens )
	      	    System.out.println( "<" + token + "> ");
			count += tokens.length;
	    }
		fileInput.close();

	    System.out.println("File: " + fileName + ", #Tokens: " + count);
	    System.out.println("Time taken to count: " + (double) (System.currentTimeMillis() - start)/1000 + " seconds");
	}
}
