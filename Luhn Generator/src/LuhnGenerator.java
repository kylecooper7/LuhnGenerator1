import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LuhnGenerator {
	
	// How often you want the ArrayList to be emptied:
	static int maxArrayListSize = 25;
	static int[] luhnOrder = {0, 5, 1, 6, 2, 7, 3, 8, 4, 9};
	static String[] theFiles = {"AddUpTo0", "AddUpTo1", "AddUpTo2", "AddUpTo3", "AddUpTo4", "AddUpTo5", "AddUpTo6", "AddUpTo7", "AddUpTo8", "AddUpTo9"};
	static ArrayList<String> writingQueue = new ArrayList<String>();
	public static void main(String[] args) {
//fillAddUpToFiles();
		//writeln("List Of Luhn Numbers", "test");
		
		//loopThrough(8);
		// System.out.println(read2ndLastLine("AddUpTo5"));
		//readAddUpToFiles("LuhnList", 0);
		/*
		writingQueue.add("duhhhh");
		writingQueue.add("durrrrr");
		writeArrayList("LuhnList", writingQueue);
		*/
		//readAddUpToFiles("LuhnList", 1234);
		
		
		
		/*  Important Controls */
		//fillAddUpToFiles(500);
		loopThrough(20);
		
	}

	public static void loopThrough(int iterations) {
		int leftOffOdd = -1;
		int leftOffEven = -1;
		/*
		String line = read2ndLastLine("List Of Luhn Numbers");
		String temp1 = ""; 
		for(int i = 0; i < line.length(); i = i + 2) {
			temp1 += line.substring(i,  i+1);
		}
		if(!(temp1.equals(""))) {
			leftOffOdd = Integer.parseInt(temp1);
		}
		*/
		int[] theDigits = new int[16];
		for(int i = leftOffOdd + 1; i < /*Math.pow(10, 8) */ iterations; i++) {
			
			int addUpTo = (10 - (addDigits(i) % 10))%10;
			
			
			
			
		
			readAddUpToFiles(theFiles[addUpTo], i);
			
		}
		
	}
	
	public static int addDigits(long number) {
		int sum = 0;
		for(int i = 1; i <= Math.log10(number) + 1; i++) {
			sum+= digitGetter(number, i);
		}
		return sum;
	}
	public static long digitGetter(long number, int digitPlace) {
		return (long) ((long)(number%Math.pow(10, (long)(Math.log10(number) + 1 - digitPlace) + 1))/(long)(Math.pow(10, (long)(Math.log10(number) + 1 - digitPlace)))); 
	}
	public static long digitGetter8(long number, int digitPlace) {
		if(digitPlace < 8 - Math.log10(number)) {
			return 0;
		}
		else {
			digitPlace = digitPlace - (8 - (int)Math.log10(number) - 1);
		return (long) ((long)(number%Math.pow(10, (long)(Math.log10(number) + 1 - digitPlace) + 1))/(long)(Math.pow(10, (long)(Math.log10(number) + 1 - digitPlace)))); 
	
		}
	}
	public static void writeln(String fileName, String line) 
	{
    try {   
        FileWriter fileWriter = new FileWriter(fileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(line);
        bufferedWriter.newLine();
        bufferedWriter.close();
    	}
    catch(IOException ex) 
    	{
        System.out.println("Error writing to file '" + fileName + "'");
    	}
	}

	public static void writeArrayList(String fileName, ArrayList<String> listHere) 
	{
    try {   
        FileWriter fileWriter = new FileWriter(fileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        
        for(String line: listHere) {
        bufferedWriter.write(line);
        bufferedWriter.newLine();
        }
        
        bufferedWriter.close();
    	}
    catch(IOException ex) 
    	{
        System.out.println("Error writing to file '" + fileName + "'");
    	}
	}
	
	public static void fillAddUpToFiles(int iterations) {
		int leftOffAt = -1;
		
		for(String file: theFiles) {
			int theDig = Integer.parseInt(read2ndLastLine(file));
			if(leftOffAt < theDig) {
				leftOffAt = theDig;
			}
		}
		
		for(int i = leftOffAt + 1; i < /*Math.pow(10, 8 )*/ iterations+ leftOffAt; i++) {
			
			writeln(theFiles[addDigits(i)%10], Integer.toString(i));
		}
		
		
	}
	
	
	public static void readLines(String fileName)
	{
		String line = null;
		try
			{
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				while ((line = bufferedReader.readLine()) != null)
					{
						// method(line);
						
					}
				
				bufferedReader.close();
			} catch (FileNotFoundException ex)
			{
				System.out.println("Unable to open file '" + fileName + "'");
			} catch (IOException ex)
			{
				System.out.println("Error reading file '" + fileName + "'");
			}

	}
	
	public static String read2ndLastLine(String fileName)
	{
		String line = null;
		String last2 = "";
		String last = "";
		int counter = 0;
		try
			{
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				while ((line = bufferedReader.readLine()) != null)
					{
					// last2 = last;
					last2 = line;
					counter ++;
						// method(line);

					}
				bufferedReader.close();
			} catch (FileNotFoundException ex)
			{
				System.out.println("Unable to open file '" + fileName + "'");
			} catch (IOException ex)
			{
				System.out.println("Error reading file '" + fileName + "'");
			}
		
		return last2;

	}

	public static void readAddUpToFiles(String fileName, int theDigits)
	{
		String line = null;
		
		int counter = 0;
		try
			{
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				while ((line = bufferedReader.readLine()) != null && counter < 100)
					{
					String s = "";
					
					for(int i = 0; i < 16; i++) {
						int temp1 = 0; 
						if(i%2 == 0) {
						temp1 = luhnOrder[(int) (digitGetter8(theDigits, i/2 + 1))];
							if(temp1>9) {
								temp1 = temp1%10 + 1;
							}
						s = s + Integer.toString(temp1);
						}
						else {
							s = s + Integer.toString( (int) digitGetter8(Integer.parseInt(line), (i + 1)/2 ));
						}
						
						
						
					}
					
					 //System.out.println(s);
					
					writingQueue.add(s);
					
					if(writingQueue.size() >= maxArrayListSize) {
						/*
						for(String str: writingQueue) {
							System.out.println(str);
						}
						*/
						
						// Change back to LuhnList:
						
						writeArrayList("LuhnList", writingQueue);
						writingQueue.clear();
					}
					
					counter++;
					}
				
				bufferedReader.close();
			} catch (FileNotFoundException ex)
			{
				System.out.println("Unable to open file '" + fileName + "'");
			} catch (IOException ex)
			{
				System.out.println("Error reading file '" + fileName + "'");
			}

	}

	public static void printLuhnNumbers(int[] theDigits) {
		String s = "";
		for(int i = 0; i < 16; i++) {
		if(i%2 == 0) {
			theDigits[i] = luhnOrder[theDigits[i]];
		}	
		
		s+= Integer.toString(theDigits[i]);
		
		}
		System.out.println(s);
		//writeln("List Of Luhn Numbers", s);
	}
	
}
