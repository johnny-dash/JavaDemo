import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class Determine {
	public static void main(String args[]) throws NumberFormatException, IOException
	{
		//parameter input
		if (args.length == 0){
			System.out.println("No parameter input!");
			return;
		}
		
		//set parameter
		String inputfileURL = args[0];
		String outputfileURL = args[1];		
			
		//initialization
		File file = new File(inputfileURL);
		HashMap<String, int[]> database = new HashMap<String, int[]>();
		HashMap<String, Double> outputhash = new HashMap<String, Double>();
		String tempString = null;
		String[] spiltstring = new String[8];
		int[] score = new int[7];
		
		//read file from text file
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while((tempString = reader.readLine()) != null)
		{
			spiltstring = tempString.trim().split(",");	  //trim() is used to delete the space before and after the string	
			for(int i=0;i<7;i++){ 				
				score[i] = Integer.parseInt(spiltstring[i+1].trim());
				//System.out.println(score[i]);
			}
			database.put(spiltstring[0], score);
			
			//reset temp storage
			score = new int[7];
			spiltstring = new String[8];
		}
		
		//get the letter score of each student
		outputhash = scoreCalculation(database);
		
		//output of the report
		System.out.println("Letter grade has been calculated for students listed in input file "
							+ inputfileURL
							+ " and written to output file "
							+ outputfileURL);
		System.out.println();
		System.out.println("Here is the class averages:");
		System.out.println("		Q1	Q2	Q3	Q4	Mid1	Mid2	Final");
		
		//Output average score
		System.out.println("Average:	"
							+AvarageScore(database,0)
							+"	"
							+AvarageScore(database,1)
							+"	"
							+AvarageScore(database,2)
							+"	"
							+AvarageScore(database,3)
							+"	"
							+AvarageScore(database,4)
							+"	"
							+AvarageScore(database,5)
							+"	"
							+AvarageScore(database,6));
		
		//Out put Minimum score
		System.out.println("Minimum:	"
							+LowestScore(database,0)
							+"	"
							+LowestScore(database,1)
							+"	"
							+LowestScore(database,2)
							+"	"
							+LowestScore(database,3)
							+"	"
							+LowestScore(database,4)
							+"	"
							+LowestScore(database,5)
							+"	"
							+LowestScore(database,6));
		
		//Out put Maximum score
		System.out.println("Maximum:	"
							+Highest(database,0)
							+"	"
							+Highest(database,1)
							+"	"
							+Highest(database,2)
							+"	"
							+Highest(database,3)
							+"	"
							+Highest(database,4)
							+"	"
							+Highest(database,5)
							+"	"
							+Highest(database,6));
		System.out.println();
		
		//out put of the file
		PrintWriter writer = new PrintWriter(outputfileURL, "UTF-8");
		
		//this two loop is used to find the highest score student and print his letter grade first
		for(int j=0; j< database.size();j++)
		{
			double highest = 0;
			String name = null;
			for(Map.Entry<String, Double> entry : outputhash.entrySet())
			{
				if(entry.getValue() >= highest)
				{
					highest = entry.getValue();
					name = entry.getKey();
				}
			}
			//System.out.println(entry.getKey() + ":  " + entry.getValue());
			writer.print(name + ":"); 
			
			//This loop is used to align the output
			for(int i=0; i <= 20 - name.length();i++)
			{
				writer.print(" ");
			}
			writer.println(letterGrade(highest));
			outputhash.remove(name, highest);
		}

		writer.close();
		
		//Press Enter to continue
	    System.out.println("Press \"ENTER\" to continue...");
	    System.in.read();

	}
	
	//this is the main function to transfer the double value score to a letter grade
	public static HashMap<String, Double> scoreCalculation(HashMap<String, int[]> input)
	{
		HashMap<String, Double> output = new HashMap<String, Double>();
		double finalscore = 0;
		
		for(Map.Entry<String, int[]> entry : input.entrySet())
		{
			for(int i = 0; i<7; i++)
			{	
				if(i <= 3) finalscore = finalscore + entry.getValue()[i] * 0.1;
				if(i == 4) finalscore = finalscore + entry.getValue()[i] * 0.2; 
				if(i == 5) finalscore = finalscore + entry.getValue()[i] * 0.15; 
				if(i == 6) finalscore = finalscore + entry.getValue()[i] * 0.25; 
			}
			output.put(entry.getKey(), finalscore);
			finalscore = 0;
		}
		
		return output;
	}
	
	//this is the standard of transformation
	public static String letterGrade(double score)
	{
		if(score >= 90) return "A";
		else if(score >= 80) return "B";
		else if(score >= 70) return "C";
		else if(score >= 60) return "D";
		else return "F";
	}
	
	//this funciton is used to get averagescore
	public static int AvarageScore(HashMap<String, int[]> input, int type)
	{
		int avarageScore = 0;
		for(int[] value : input.values())
		{
			avarageScore = avarageScore + value[type];
		}
		avarageScore = avarageScore/input.size();
		return avarageScore;
	}
	
	//this function is used to get lowest socre
	public static int LowestScore(HashMap<String, int[]> input, int type)
	{
		int lowestScore = 100;
		for(int[] value : input.values())
		{
			if(value[type] <= lowestScore) lowestScore = value[type];
		}
		return lowestScore;
	}
	
	//this function is used to get hightest score
	public static int Highest(HashMap<String, int[]> input, int type)
	{
		int highest = 0;
		for(int[] value : input.values())
		{
			if(value[type] >= highest) highest = value[type];
		}
		return highest;
	}
	
	
}
