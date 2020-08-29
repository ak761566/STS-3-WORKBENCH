package com.innodata.dataTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class uniqueIDs {
	
	public static void main(String[]  args) throws Exception {
		
		File f = new File(args[0]);
		Set<String> lines = new HashSet<String>();
		
		
		//PrintWriter pw = new PrintWriter("C:\\kumar\\lokender\\uniqueId.txt");
		//BufferedReader br = new BufferedReader(new FileReader("C:\\kumar\\lokender\\i.txt"));
		
		PrintWriter pw = new PrintWriter(args[0] + "-out.txt");
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		
		String line = br.readLine();
		boolean available = false;
		boolean hasDuplicate = false;
		
	  while(line != null) {
			
			if(lines.contains(line)) {
				hasDuplicate = true;
			}
			
			lines.add(line);
			
			if(hasDuplicate)
			{
				System.out.println(line);
				pw.print(line);
				pw.println();
				hasDuplicate = false;
				pw.flush();
			}
			
			line = br.readLine();
		
	  }
		/*
		 * while (line != null && !available) {
		 * 
		 * BufferedReader br2 = new BufferedReader(new
		 * FileReader("C:\\kumar\\lokender\\uniqueId.txt"));
		 * 
		 * String target = br2.readLine();
		 * 
		 * while(target != null) {
		 * 
		 * if(line.equals(target)) {
		 * 
		 * available = true; System.out.println(line); break; }
		 * 
		 * target = br2.readLine(); }
		 * 
		 * if(available == false) {
		 * 
		 * pw.print(line); pw.flush();
		 * 
		 * }
		 * 
		 * 
		 * line = br.readLine();
		 * 
		 * }
		 */
		
		
	}

}
