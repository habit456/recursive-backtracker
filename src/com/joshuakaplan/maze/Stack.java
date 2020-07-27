package com.joshuakaplan.maze;

import java.util.ArrayList;
import java.util.List;

public class Stack {
	private List<Cell> stack = new ArrayList<>();
	
	public Stack() {
		
	}
	
	public Cell pop() {
		return stack.remove(stack.size() - 1);
	}
	
	public void push(Cell cell) {
		stack.add(cell);
	}
	
	public int size() {
		return stack.size();
	}
}
