package primer;

import java.io.*;
import java.util.*;

public class RandomIntFileGenerator {

	public static void main(String[] args) throws IOException {

		Scanner input = new Scanner(System.in);
		System.out.print("Enter an output file-name: ");
		String fileName = input.nextLine();

		System.out.print("How many integers would you like to generate? ");
		int numberOfIntegersToGenerate = input.nextInt();
		input.close();

		FileWriter fileWriter = new FileWriter( fileName );
		Random generator = new Random(); // The Random class can be used for generating random numbers
		
		for(int i = 0; i < numberOfIntegersToGenerate; i++)
	        fileWriter.write(generator.nextInt(10000) + "\n");

		fileWriter.close();
		System.out.println("Output file generation is completed.");
	}
}
