
// Test cases for CharGrid -- a few basic tests are provided.

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharGridTest {
	//
	// CharArea
	//
	@Test
	public void testCharArea1() {
		char[][] grid = new char[][]{
				{'a', 'y', ' '},
				{'x', 'a', 'z'},
		};
		CharGrid cg = new CharGrid(grid);
		assertEquals(4, cg.charArea('a'));
		assertEquals(1, cg.charArea('z'));
	}

	@Test
	public void testCharArea2() {
		char[][] grid = new char[][]{
				{'c', 'a', ' '},
				{'b', ' ', 'b'},
				{' ', ' ', 'a'},
		};
		CharGrid cg = new CharGrid(grid);
		assertEquals(6, cg.charArea('a'));
		assertEquals(3, cg.charArea('b'));
		assertEquals(1, cg.charArea('c'));
	}

	@Test
	public void testCharAreaDoesntContains() {
		char[][] grid = new char[][]{
				{'c', 'a', ' '},
				{'k', 'k', 'k'},
				{' ', 'P', 'P'},
		};
		CharGrid cg = new CharGrid(grid);
		assertEquals(0, cg.charArea('p'));
	}

	@Test
	public void testCharAreaEmptyGrid() {
		char[][] grid = new char[0][0];
		CharGrid cg = new CharGrid(grid);
		assertEquals(0, cg.charArea(' '));
	}

	@Test
	public void testAreaCharOneElem() {
		char[][] grid = new char[][]{{'2'},};
		CharGrid cg = new CharGrid(grid);
		assertEquals(1, cg.charArea('2'));
		assertEquals(0, cg.charArea(' '));
	}

	@Test
	public void testCharAreaOneLine() {
		// one column
		char[][] grid1 = new char[][]{{'a'}, {'k'}, {'a'}, {' '}, {'x'},};
		CharGrid cg1 = new CharGrid(grid1);
		assertEquals(3, cg1.charArea('a'));
		assertEquals(1, cg1.charArea(' '));
		assertEquals(0, cg1.charArea('p'));
		// one row
		char[][] grid2 = new char[][]{{'a', 'k', 'a', ' ', 'x'},};
		CharGrid cg2 = new CharGrid(grid2);
		assertEquals(3, cg2.charArea('a'));
		assertEquals(1, cg2.charArea(' '));
		assertEquals(0, cg2.charArea('p'));
	}

	//
	// CountPlus
	//
	@Test
	public void testCountPlusSimple() {
		// not valid character to be checked
		char[][] grid1 = new char[][]{
				{'a', 'a'},
				{'a', 'a'},
				{'a', 'a'}
		};
		CharGrid cg1 = new CharGrid(grid1);
		assertEquals(0, cg1.countPlus());
		// simple plus sign
		char[][] grid2 = new char[][]{
				{'c', 'a', 'a'},
				{'a', 'a', 'a'},
				{' ', 'a', 'a'}
		};
		CharGrid cg2 = new CharGrid(grid2);
		assertEquals(1, cg2.countPlus());
	}

	@Test
	public void testCountPlusSimpleNoPlus() {
		// one column - not valid character to be checked
		char[][] grid1 = new char[][]{{'a'}, {'a'}, {'a'}};
		CharGrid cg1 = new CharGrid(grid1);
		assertEquals(0, cg1.countPlus());
		// one row - not valid character to be checked
		char[][] grid2 = new char[][]{{'a', 'a', 'a'}};
		CharGrid cg2 = new CharGrid(grid2);
		assertEquals(0, cg2.countPlus());
	}

	@Test
	public void testCountPlusEmpty() {
		char[][] grid = new char[0][0];
		CharGrid cg = new CharGrid(grid);
		assertEquals(0, cg.countPlus());
	}

	@Test
	public void testCountPlusOnediffArms() {
		// one long arm - 'p' - X down / 'x' - X left
		// + ' ' - this case too
		char[][] grid1 = new char[][]{
				{' ', ' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', 'p', ' ', 'k', ' ', ' ', ' ', 'k'},
				{'p', 'p', 'p', 'p', 'p', 'z', ' ', 'x', ' '},
				{' ', ' ', 'p', ' ', ' ', 'y', 'y', 'x', 'x'},
				{' ', ' ', ' ', ' ', 'y', 'y', 'y', 'x', ' '},
		};
		CharGrid cg1 = new CharGrid(grid1);
		assertEquals(1, cg1.countPlus());
		// one long arm - 'p' - X up / 'x' - X right
		// + ' ' - this case too
		char[][] grid2 = new char[][]{
				{' ', ' ', 'p', ' ', 'k', ' ', ' ', ' ', 'k'},
				{'p', 'p', 'p', 'p', 'p', 'z', ' ', 'x', ' '},
				{' ', ' ', 'p', ' ', ' ', 'y', 'x', 'x', ' '},
				{' ', ' ', 'p', ' ', 'y', 'y', 'y', 'x', ' '},
		};
		CharGrid cg2 = new CharGrid(grid2);
		assertEquals(0, cg2.countPlus());
	}

	@Test
	public void testCountPlusComplicated(){
		// 'p' + (little plus) / 'x' +
		char[][] grid2 = new char[][] {
				{' ', ' ', 'p', ' ', ' ', 'p', ' ', ' ', ' '},
				{' ', ' ', 'p', ' ', 'p', 'p', 'p', 'x', ' '},
				{'p', 'p', 'p', 'p', 'p', 'p', 'x', 'x', 'x'},
				{' ', ' ', 'p', ' ', ' ', 'y', ' ', 'x', ' '},
				{' ', ' ', 'p', 'z', 'y', 'y', 'y', ' ', ' '},
				{'z', 'z', 'z', 'z', 'z', 'y', 'z', 'z', 'z'},
				{' ', ' ', 'x', 'z', ' ', 'y', ' ', ' ', ' '}
		};
		CharGrid cg2 = new CharGrid(grid2);
		assertEquals(2, cg2.countPlus());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorInvalid(){
		// illegal char[][] case
		CharGrid cg = new CharGrid(null);
	}
}
