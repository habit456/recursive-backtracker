package com.joshuakaplan.maze;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {
	private int width;
	private int height;
	private int size;
	private Cell[] cells;
	private Cell dummy;
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		this.size = width * height;
		
		dummy = new Cell();
		dummy.setVisited(true);
		
		initCells();
	}
	
	private void initCells() {
		Cell[] cellsTemp = new Cell[size];
		
		for (int i = 0; i < size; i++) {
			cellsTemp[i] = new Cell();
		}
		
		cells = cellsTemp;
	}
	
	public Cell getCell(int x, int y) {
		int i = y * this.width + x;
		Cell cell = this.cells[i];
		cell.setX(x);
		cell.setY(y);
		return cell;
	}
	
	public void setWalls(int x, int y, Walls walls) {
		int i = y * this.width + x;
		cells[i].setWalls(walls);
	}
	
	public Walls getWalls(int x, int y) {
		int i = y * this.width + x;
		return cells[i].getWalls();
	}
	
	public void clearWall(int x, int y, String direction) {
		Cell cell = getCell(x, y);
		Walls walls = cell.getWalls();
		
		switch (direction) {
			case "up":
				walls.setUp(false);
				break;
			case "down":
				walls.setDown(false);
				break;
			case "right":
				walls.setRight(false);
				break;
			case "left":
				walls.setLeft(false);
				break;
		}
	}
	
	public Map<String, Cell> getNeighbors(int x, int y) {
		
		Cell up = y > 0 ? getCell(x, y - 1) : dummy;
		Cell right = x < width - 1 ? getCell(x + 1, y) : dummy;
		Cell down = y < height - 1 ? getCell(x, y + 1) : dummy;
		Cell left = x > 0 ? getCell(x - 1, y) : dummy;
		
		Map<String, Cell> neighbors = new HashMap<>();
		neighbors.put("up", up);
		neighbors.put("down", down);
		neighbors.put("right", right);
		neighbors.put("left", left);
		
		return neighbors;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int y = 0; y < height; y++) {
			// part of the cell (1/2)
			for (int p = 0; p < 2; p++) {
			
				for (int x = 0; x < width; x++) {
					Cell cell = getCell(x, y);
					Walls walls = cell.getWalls();
					
					if (p == 0) {
						int num = walls.hasUp() ? 1 : 0;
						sb.append(1).append(num)                                       ;
					} else {
						int num = walls.hasLeft() ? 1 : 0;
						sb.append(num).append("0");
					}
				}
				sb.append("1").append("\n");
			}
		}
		
		for (int i = 0; i < width * 2 + 1; i++) {
			sb.append("1");
		}
		
		return sb.toString();
	}
	
	public void save() {
		try {
			String maze = toString();
			String desktop = System.getProperty("user.home") + "\\Desktop";
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(desktop, "maze.txt")));
			writer.write(maze);
			writer.close();
		} catch (Exception e) {
			System.out.println("Error finding path to desktop.");
			manualSave();
		}
	}
	
	public void manualSave() {
		Scanner scanner = Main.getScanner();
		boolean validPath = false;
		
		while (!validPath) {
			System.out.print("Please enter full path to file: ");
			String path = scanner.nextLine();
			
			try {
				File file = new File(path, "maze.txt");
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				writer.write(toString());
				writer.close();
				validPath = true;
			} catch(Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}
