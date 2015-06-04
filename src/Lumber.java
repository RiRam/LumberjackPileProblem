/*
 * Ri Ram
 * reddit.com/r/dailyprogrammer Challenge #217 Lumberjack Pile Problem
 * http://redd.it/3840rp
 * 6-3-2015
 */

import java.util.Scanner;

public class Lumber {
	public static final Scanner input = new Scanner(System.in);
	public int logs, grid;
	public int[][] logPiles;
	
	public Lumber(int l, int g) {
		logs = l;
		grid = g;
		//create two dimensional array of grid size
		logPiles = new int[grid][grid];
	}
	
	public int getLogs()
	{	return logs;	}
	
	public int getGrid()
	{	return grid;	}
	
	public static void main(String[] args)
	{	
		//gets user input for number of logs and size of grid
		int sizeGrid = getSizeGrid();
		int numLogs = getNumLogs();
		
		//constructs Lumber object with parameters based on user input
		Lumber lumberyard = new Lumber(numLogs, sizeGrid);
		
		//fills two dimensional array with values read in from user input
		lumberyard.fillPiles();
		
		//get starting time to approximate runtime (not counting user input time)
		long startTime = System.currentTimeMillis();
		
		//prints input before adding on logs
		System.out.print(lumberyard.printInput());
		
		//loop calculating smallest, then adding logs, until no more logs
		while(lumberyard.getLogs() > 0)	{
			int smallest = lumberyard.findSmallest();
			lumberyard.addToSmallestPiles(smallest);
		}
		
		//prints results after adding on logs
		System.out.print(lumberyard.printResults());
		
		//calculates and prints runtime
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("***Runtime***: " + totalTime + " milliseconds.");		
	}
	
	private static int getNumLogs() {
		System.out.println("Enter number of logs: ");
		boolean correctInput = false;
		int numLogs = -1;
		while(!correctInput) {
			if(!input.hasNextInt()) {
				System.out.println("Invalid. Try again.");
				input.next();
				continue;
			}
			else {
				numLogs = input.nextInt();
				if (numLogs > 0)
					correctInput = true;
			}
		}
		return numLogs;
	}
	
	private static int getSizeGrid() {
		System.out.println("Enter size of grid (i.e. 3 x 3 grid, enter 3): ");
		boolean correctInput = false;
		int gridSize = -1;
		while(!correctInput) {
			if(!input.hasNextInt()) {
				System.out.println("Invalid. Try again.");
				input.next();
				continue;
			}
			else {
				gridSize = input.nextInt();
				if (gridSize > 0)
					correctInput = true;
			}
		}
		return gridSize;
	}
	
	//fills piles with user input
	private void fillPiles() {	
		//populate logPiles
		for(int j = 0; j < grid; j++) {
			for(int i = 0; i < grid; i++) {
				//checking to make sure valid integer values were inputted
				boolean correctInput = false;
				while(!correctInput) {
					System.out.println("Enter number of logs in pile at [" + i + "," + j + "]: ");
					if(!input.hasNextInt()) {
						System.out.println("Invalid. Try again.");
						input.next();
						continue;
					}
					else {
						int temp = input.nextInt();
						if(temp >= 0) {
							correctInput = true;
							logPiles[j][i] = temp;
						}
					}
				}
			}
		}
		return;
	}
	
	//finds the pile of the smallest size
	private int findSmallest() {
		int smallest = Integer.MAX_VALUE;
		for(int j = 0; j < grid; j++) {
			for(int i = 0; i < grid; i++) {
				if(logPiles[j][i] < smallest)
					smallest = logPiles[j][i];
			}
		}
		return smallest;
	}
	
	//adds a log each to the piles of the smallest size until there are no more logs
	private void addToSmallestPiles(int smallest) {
		for(int j = 0; j < grid; j++) {
			for(int i = 0; i < grid; i++) {
				if(logPiles[j][i] == smallest)	{
					logPiles[j][i]++;
					logs--;
				}
				if(logs == 0)
					return;
			}
		}
	}
	
	//prints input as given by user
	private String printInput() {
		String result = "";
		result += "Size of grid: " + grid + " x " + grid + "\n";
		result += "Number of logs: " + logs + "\n";
		for(int j = 0; j < grid; j++) {
			for(int i = 0; i < grid; i++) {
				result += logPiles[j][i] + " ";
			}
			result += "\n";
		}
		return result;
	}
	
	//prints results after adding all the logs
	private String printResults() {
		String result = "";
		result += "After adding logs: \n";
		for(int j = 0; j < grid; j++) {
			for(int i = 0; i < grid; i++) {
				result += logPiles[j][i] + " ";
			}
			result += "\n";
		}
		return result;
	}
}
