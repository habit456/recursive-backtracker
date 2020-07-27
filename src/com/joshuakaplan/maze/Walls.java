package com.joshuakaplan.maze;

public class Walls {
	private boolean up = true;
	private boolean right = true;
	private boolean down = true;
	private boolean left = true;
	
	public Walls() {
		
	}

	public boolean hasUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean hasDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean hasRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean hasLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
	
	@Override 
	public String toString() {
		boolean[] walls = {up, right, down, left};
		StringBuilder sb = new StringBuilder();
		
		for (boolean wall : walls) {
			if (wall) {
				sb.append("1");
			} else {
				sb.append("0");
			}
		}
		
		return sb.toString();
	}
}
