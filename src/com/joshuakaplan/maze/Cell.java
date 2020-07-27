package com.joshuakaplan.maze;

public class Cell {
	private boolean visited = false;
	private Walls walls = new Walls();
	private int x;
	private int y;
	
	public Cell() {
		
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public Walls getWalls() {
		return walls;
	}

	public void setWalls(Walls walls) {
		this.walls = walls;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
