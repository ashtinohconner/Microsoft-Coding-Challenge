/*
 * NDrome Class for Microsoft Code Challenge at the University
 * of Utah.
 * 
 * This class takes 1 argument that in the run configurations of
 * eclipse I specified as SampleInputNDrome.txt
 * This varies from each users eclipse as to what argument filename
 * they want to pass.
 * 
 * This class simply takes in a line such as abc|1 and checks to see
 * if the string before the | is an NDrome based on the number of 
 * characters we will be comparing following the |.  
 * In this case abc is not the same forwards as it is 
 * backwards by comparing 1 character.
 * 
 * Written by Austin O'Connor
 */

package challenges;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NDrome {

	// NDrome
	//
	// Problem:
	//
	// You must write a problem to determine if input phrases are an 'N-Drome'. 
	//
	// Details:
	//
	// 0) An N-Drome is a string that is the same forwards and backwards when split into n-character units. 
	// 1) For example, a string would be a 1-drome if and only if it were a standard palindrome. 
	// 2) An example of a 2-drome would be abcdab since its 2-character units are ab, cd, and ab, and its first and last 2-character units are identical. 
	// 3) To solve this challenge, your program should indicate whether or not a given string is a valid N-Drome. 
	// 4) Each input line will consist of a string to evaluate, followed by a pipe character (|), and the N to use in your evaluation.  
	// 6) Your output file must contain the input information followed by an additional pipe character, and then a 1 or 0 to indicate that the input is or is not a valid N-Drome, respectively. 
	
	// Sample Input
	// abc|1
	// abcdedcba|1
	// 121212|3
	// 123456123456|6
		
	// Sample output
	// abc|1|0
	// abcdedcba|1|1
	// 121212|3|0
	// 123456123456|6|1
	
	// string to hold the original line read in.
	static String originalLine = "";
	
	// ArrayList to hold what is returned by the getPipe method.
	static ArrayList<String> strings = new ArrayList<String>();
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	{
		// check the arguments
		if(args.length != 1)
		{
			System.err.println("Incorrect number of arguments!");
			return;
		}
		
		// set the file to the argument
		File file = new File(args[0]);
		
		if (!file.isFile())
		{
			System.err.println("File not found!");	
			return;
		}
		
		// read in the file
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		
		// while we can get a next line.
		while ((line = br.readLine()) != null) 
		{
			// split the word into two strings at the pipe.
			strings = getPipe(line); 
			
			// check if the string is an NDrome
			if(isNDrome(strings))
			{
				// add a |1 to the end since it is
				originalLine += "|1"; 
			}
			
			else
			{
				// add a |0 since it is not
				originalLine += "|0"; 
			}
			
			// print out the final result
			System.out.println(originalLine);
		}
		
		br.close(); // close
		
	} // end main
		
	/**
	 * Method to chop the string at the | and return the string before and number for the drome.
	 * 
	 * @return 2 strings, the first with the string the second with the number of the drome.
	 */
	public static ArrayList<String> getPipe(String s)
	{
		originalLine = s;
		ArrayList<String> twoStrings = new ArrayList<String>(2);
		String word = "";
		String n = "";
		
		// iterate through the String looking for the | character.
		// keeping track of where we are so we can get the number and the
		// string before it.
		
		for(int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			
			if(c == '|')
			{
				// The position of | is eaqual to i at this point.
				
				// Get the letters before the |
				for (int j = 0; j < i; j++)
				{
					char ch = s.charAt(j);
					word += ch;
				}
				
				// Get the number immediately after the |
				
				// advance the iterator to skip over the |
				int j = i + 1;
				
				// loop through the remaining characters and get the numbers
				for(int l = j; l < s.length(); l++)
				{
					char ch = s.charAt(l);
					n += ch;
				}
				
				// Add the 2 strings to my list.
				twoStrings.add(word);
				twoStrings.add(n);
				break;
			}
			
		}
		
		return twoStrings;
	}

	/**
	 * Method to check whether the string is an NDrome or not.
	 * 
	 * @return true if the string is an NDrome, false otherwise.
	 */
	public static boolean isNDrome(ArrayList<String> s)
	{
		// place holders for the values in the array
		String in = s.get(0);
		String nu = s.get(1);
		
		int number = Integer.parseInt(nu); // parse the number into the number we want.
		
		// an array to hold the strings of length specified from the input string
		ArrayList<String> chopped = new ArrayList<String>();
		
		// chop the string into the specified length
		for (int i = 0; i < in.length(); i += number) // <= important to make sure we increment by i += number
		{
			// i = current position, i + number = the length that we want
			String a = in.substring(i, i + number);
			chopped.add(a); // add to the array
		}
		
		
		// case 1: string is an even number.
		if (in.length() % 2 == 0)
		{
			// setup the start and end variables for the array.
			int endcount = chopped.size() - 1;
			
			// loop through the arrays and check if the first and last are equal.
			for (int begcount = 0; begcount < endcount; begcount++)
			{
				String first = chopped.get(begcount);
				String last = chopped.get(endcount);
				if (!(first.equals(last))) // .equals for comparing objects...
					return false;
				endcount --;
			}
			
			// if we make it through the loop all the values are equal, return true
			return true;
		}
		
		// case 2: string is an odd number.
		else //if (in.length() % 2 == 1)
		{
			// ignore the middle value in this case.
			
			// setup the start and end variables for the array.
			int endcount = chopped.size() - 1;
			int begcount = 0;
			
			// loop through the arrays and check if the first and last are equal.
			for (int i = 0; i < endcount; i++)
			{
				String first = chopped.get(begcount);
				String last = chopped.get(endcount);
				if (!(first.equals(last))) // .equals for comparing objects...
					return false;
				endcount --;
				begcount ++;
				if (endcount == begcount)
					break; // we are at the middle.
			}
			
			// if we make it through the loop all the values are equal, return true
			return true;
		}
		
	}
	
	

} // end class 
