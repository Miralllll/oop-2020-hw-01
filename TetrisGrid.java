//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.

import java.util.Vector;

public class TetrisGrid {

	private boolean[][] grid;

	/**
	 * Constructs a new instance with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public TetrisGrid(boolean[][] grid) {
		this.grid = grid;
		validatedTertisGrid();
	}
	
	/**
	 * Does row-clearing on the grid (see handout).
	 */
	public void clearRows() {
		validatedTertisGrid();
		int removedLinesNum = 0;
		for(int c = 0; c < grid[0].length; c++){
			if(isLineFilled(c)) removedLinesNum ++;
			else moveLinesDown(c, removedLinesNum);
		}
		addEmptyLines(removedLinesNum);
	}

	/**
	 * @param index
	 * @return true if tetris line (with index) is filled (true, true ...)
	 *  alsoo ->>> I added that situation (false, false, fasle ...), it will be considered as filled.
	 */
	private boolean isLineFilled(int index) {
		Boolean lineContains = null; // true
		for(int j = 0; j < grid.length; j++)
			// comment this line and will work only for trues
			if(lineContains == null) lineContains = grid[j][index];
			else if(grid[j][index] != lineContains) return false;
		return true;
	}

	/**
	 * Copies current line where it should be after erasing down-lines.
	 * @param index - it is line index
	 * @param removedNum - it describes the number of lines
	 *                which is already considered as removed.
	 */
	private void moveLinesDown(int index, int removedNum) {
		if(removedNum == 0) return;
		for(int i = 0; i < grid.length; i++)
			grid[i][index - removedNum] = grid[i][index];
	}

	/**
	 * Adds false meanings from the head in the first k(removedLinesNum) lines.
	 * @param removedLinesNum
	 */
	private void addEmptyLines(int removedLinesNum) {
		for(int j = grid[0].length - removedLinesNum; j < grid[0].length; j++)
			for (int i = 0; i < grid.length; i++)
				grid[i][j] = false;
	}

	/**
	 * Returns the internal 2d grid array.
	 * @return 2d grid array
	 */
	boolean[][] getGrid() {
		validatedTertisGrid();
		return grid;
	}

	/**
	 * throws IllegalArgumentException if grid is illegal
	 */
	private void validatedTertisGrid() {
		if(grid == null || grid.length <= 0 || grid[0].length <= 0)
			throw new IllegalArgumentException("Char[][] is Illegal!");
	}
}
