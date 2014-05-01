package challenges;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FollowDirections {

	//	FollowDirections
	//
	//	Problem:
	//
	//	In this problem, you program will simulate a robot moving about a grid. You are given a set of directions. Your job is to compute your final position.
	//
	//	Details:
	//
	//	0) Directions will appear 1 per line.
	//	1) You start facing North at 0,0.
	//	2) Move directions will look like this: Move <signed 32-bit integer>.  
	//	3) Turn directions will look like this: Turn <left|right>
	//	4) Turns are relative to the direction you are currently facing.
	//	5) Interpret negative movement as moving backwards *without* changing direction.
	//	6) Express your answer as comma-separated integer pair where the first number is the distance travelled along East-West, and the second is the distance travelled along North-South. 

	//  SAMPLE INPUT
	//	Move 2
	//	Turn right
	//	Move 4
	//	Turn left
	//	Move -5
	//	Turn right
	//	Move 10
	//	Turn left
	//	Move -2
	//	Turn left
	//	Turn left
	//	Move 5
	//	Move -2
	//	Turn right
	//	Move 1
	//	Move 0

	//  SAMPLE OUTPUT
	//  13,-8
	
	public static int x, y = 0;
	public static String facing = "North";
	
	/**
	 * @param args
	 * @throws IOExcepton 
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
			parseLine(line);
		}
		
		System.out.println("(" + x + ", " + y + ")");
		
		br.close();
		
	} // end main
	
	public static void parseLine(String line)
	{		
		// check if the command is a turn 		
		if(line.substring(0, 4).equals("Turn"))
		{
			// we are turning, now find which direction we will point.
			String RightorLeft = line.substring(5, line.length());
			
			// case we are turning right.
			if (RightorLeft.equals("right"))
			{
				// find which direction we are previously pointing
				if (facing.equals("North"))
				{
					// update facing to the new direction
					facing = "East";
				}
				else if (facing.equals("East"))
				{
					// update facing to the new direction
					facing = "South";
				}
				else if (facing.equals("South"))
				{
					// update facing to the new direction
					facing = "West";
				}
				else if (facing.equals("West"))
				{
					// update facing to the new direction
					facing = "North";
				}
			}
			
			// case we are turning left.
			else if (RightorLeft.equals("left"))
			{
				// find which direction we are previously pointing
				if (facing.equals("North"))
				{
					// update facing to the new direction
					facing = "West";
				}
				else if (facing.equals("East"))
				{
					// update facing to the new direction
					facing = "North";
				}
				else if (facing.equals("South"))
				{
					// update facing to the new direction
					facing = "East";
				}
				else if (facing.equals("West"))
				{
					// update facing to the new direction
					facing = "South";
				}
			}
		}
		
		// check if the command is a move
		else if (line.substring(0, 4).equals("Move"))
		{
			// we are moving, now find which direction
			
			String distance = line.substring(5, line.length());
			
			int dist = Integer.parseInt(distance);
			
			// cases for facing north, positive movement and negative movement
			if(facing.equals("North"))
				y += dist;
			
			// cases for facing east, positive movement and negative movement
			else if (facing.equals("East"))
				x += dist;
			
			// cases for facing south, positive movement and negative movement
			else if (facing.equals("South"))
				y -= dist;
			
			// cases for facing west, positive movement and negative movement
			else if (facing.equals("West"))
				x -= dist;
			
		}
				
	}

} // end class
