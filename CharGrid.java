// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.

import java.util.Comparator;

public class CharGrid {

	private char[][] grid;

	/**
	 * Constructs a new CharGrid with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public CharGrid(char[][] grid) {
		this.grid = grid;
		validatedArray();
	}

	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {
		validatedArray();
		int minR = Integer.MAX_VALUE, minC = Integer.MAX_VALUE;
		int maxR = 0, maxC = 0; // this integer saves the max length of the sides
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j] == ch){
					minR = Math.min(minR, i);
					minC = Math.min(minC, j);
					maxR = Math.max(maxR, i);
					maxC = Math.max(maxC, j);
				}
			}
		} // checks if charArray doesn't contains ch.
		if(minR == Integer.MAX_VALUE) return 0;
		return (maxR - minR + 1) * (maxC - minC + 1);
	}

	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * * @return number of + in grid
	 */
	public int countPlus() {
		validatedArray();
		int countPlus = 0;
		for(int i = 1; i < grid.length - 1; i++){
			for(int j = 1; j < grid[0].length - 1; j++){
				if(isPlus(i, j)) countPlus ++;
			}
		}
		return countPlus;
	}

	/**
	 * Checks lengths of arms.
	 * If one of them is different or less than 2 return false.
	 * @param i is row coordinate
	 * @param j is column coordinate
	 * @return true if it found plus
	 */
	private boolean isPlus(int i, int j) {
		int len = countArmLength(grid[i][j], i, j, -1, 0);
		if(len <= 1) return false;
		if(len != countArmLength(grid[i][j], i, j, 1, 0)) return false;
		if(len != countArmLength(grid[i][j], i, j, 0, -1)) return false;
		if(len != countArmLength(grid[i][j], i, j, 0, 1)) return false;
		else return true;
	}

	/**
	 * @param ch what character should be on the arm
	 * @param i  @param j where is method now (i, j)
	 * @param mx @param my how method should follow the arm.
	 * @return the length of arm
	 */
	private int countArmLength(char ch, int i, int j, int mx, int my) {
		if(!inBounds(i, j) || grid[i][j] != ch) return 0;
		return countArmLength(ch, i + mx, j + my, mx, my) + 1;
	}

	/**
	 * @param i @param j current coordinates
	 * @return true if this coordinates is inside of the char-array
	 */
	private boolean inBounds(int i, int j) {
		if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length)
			return false;
		return true;
	}

	/**
	 * throws IllegalArgumentException if Length is less than 0
	 */
	private void validatedArray() {
		if(grid == null) throw new IllegalArgumentException("Char[][] is Illegal!");
	}
}
