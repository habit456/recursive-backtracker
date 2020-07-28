package com.joshuakaplan.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
	private static Stack stack = new Stack();
	private static Board board;
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		initBoard(args);
		carveTo(0, 0);
		System.out.println("Saving...");
		board.save();
		scanner.close();
	}

	private static void initBoard(String[] args) {
		try {
			int height = Integer.parseInt(args[0]);
			int width = Integer.parseInt(args[1]);
			
			if (height < 2 || width < 2) {
				throw new Exception();
			}
			
			board = new Board(width, height);
			
		} catch(Exception e) {
			try {
				System.out.print("Width of maze: ");
				int width = Integer.parseInt(scanner.nextLine());
				System.out.print("Height of maze: ");
				int height = Integer.parseInt(scanner.nextLine());
				
				if (height < 2 || width < 2) {
					throw new Exception("Height or width must be greater than 2");
				} 
				
				board = new Board(width, height);
				
			} catch (Exception ee) {
				initBoard(args);
			}
		}
	}
	
	public static void carveTo(int x, int y) {
		Cell cell = board.getCell(x, y);
		
		if (cell.isVisited()) {
			stack.pop();
			Cell next = stack.pop();
			board.getCell(next.getX(), next.getY()).setVisited(false);
			
			if (stack.size() > 0) {
				carveTo(next.getX(), next.getY());
			}
			
			return;
		}
		
		cell.setVisited(true);
		stack.push(cell);
		
		Map<String, Cell> neighbors = board.getNeighbors(x, y);
		String[] keys = getShuffledDirections();
		String rand;
		
		for (int i = 0; i < keys.length; i++) {
			rand = keys[i];
			
			switch (rand) {
				case "up":
					if (!neighbors.get("up").isVisited()) {
						board.clearWall(x, y, "up");
						board.clearWall(x, y - 1, "down");
						y--;
						i = keys.length;
					}
					break;
				case "down":
					if (!neighbors.get("down").isVisited()) {
						board.clearWall(x, y, "down");
						board.clearWall(x, y + 1, "up");
						y++;
						i = keys.length;
					}
					break;
				case "right":
					if (!neighbors.get("right").isVisited()) {
						board.clearWall(x, y, "right");
						board.clearWall(x + 1, y, "left");
						x++;
						i = keys.length;
					}
					break;
				case "left":
					if (!neighbors.get("left").isVisited()) {
						board.clearWall(x, y, "left");
						board.clearWall(x - 1, y, "right");
						x--;
						i = keys.length;
					}
					break;
			}
		}
		
		
		carveTo(x, y);
	}
	
	public static String[] getShuffledDirections() {
		List<String> directions = new ArrayList<>();
		directions.add("up");
		directions.add("right");
		directions.add("down");
		directions.add("left");
		
		String[] shuffled = new String[4];
		
		for (int i = 0; i < shuffled.length; i++) {
			Random random = new Random();
			int randInt = random.nextInt(directions.size());
			shuffled[i] = directions.get(randInt);
			directions.remove(randInt);
		}
		
		return shuffled;
	}
	
	public static Scanner getScanner() {
		return scanner;
	}
}
