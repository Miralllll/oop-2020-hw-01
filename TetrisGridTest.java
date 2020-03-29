
import org.junit.Test;

import java.util.*;
import static org.junit.Assert.assertTrue;

public class TetrisGridTest {

	@Test
	// Provided simple clearRows() test - width 2, height 3 grid
	public void testClear1() {
		boolean[][] before = {
				{true, true, false, },
				{false, true, true, }
			};
		boolean[][] after = {
				{true, false, false},
				{false, true, false}
			};
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();
		assertTrue(Arrays.deepEquals(after, tetris.getGrid()));
	}

	@Test
	public void testClearSimple(){
		// one cell
		boolean[][] before = { {true} };
		boolean[][] after = { {false} };
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();
		assertTrue(Arrays.deepEquals(after, tetris.getGrid()));
		// one column cells
		boolean[][] beforeC = { {true, true, true} };
		boolean[][] afterC = { {false, false, false} };
		TetrisGrid tetrisC = new TetrisGrid(beforeC);
		tetrisC.clearRows();
		assertTrue(Arrays.deepEquals(afterC, tetrisC.getGrid()));
		// one row cells
		boolean[][] beforeR = { {true}, {true}, {true} };
		boolean[][] afterR = { {false}, {false}, {false} };
		TetrisGrid tetrisR = new TetrisGrid(beforeR);
		tetrisR.clearRows();
		assertTrue(Arrays.deepEquals(afterR, tetrisR.getGrid()));
	}

	@Test
	// this feature is only my idea ...
	public void testClearNotStundart(){
		boolean[][] beforeC = { {true, false, true} };
		boolean[][] afterC = { {false, false, false} };
		TetrisGrid tetrisC = new TetrisGrid(beforeC);
		tetrisC.clearRows();
		assertTrue(Arrays.deepEquals(afterC, tetrisC.getGrid()));
	}

	@Test
	public void testClearBigTetrisSituation(){
		// 1 line - filled, 2 line - contains (0) - false, 3 line - contains (8) - false
		// 4 line - filled, 5 line - contains (4) - false, 6 line - empty
		boolean[][] before = {
				{ true, false, true, true, true, false },
				{ true, true, true, true, true, false },
				{ true, true, true, true, true, false },
				{ true, true, true, true, true, false },
				{ true, true, true, true, false, false },
				{ true, true, true, true, true, false },
				{ true, true, true, true, true, false },
				{ true, true, true, true, true, false },
				{ true, true, false, true, true, false }
		};
		boolean[][] after = {
				{ false, true, true, false, false, false },
				{ true, true, true, false, false, false },
				{ true, true, true, false, false, false },
				{ true, true, true, false, false, false },
				{ true, true, false, false, false, false },
				{ true, true, true, false, false, false },
				{ true, true, true, false, false, false },
				{ true, true, true, false, false, false },
				{ true, false, true, false, false, false}
		};
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();
		assertTrue(Arrays.deepEquals(after, tetris.getGrid()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorInvalidGrid(){
		// illegal char[][] case
		TetrisGrid tetris = new TetrisGrid(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidGridWidth(){
		// illegal char[][] case
		boolean[][] grid = new boolean[0][1];
		TetrisGrid tetris = new TetrisGrid(grid);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidGridHeight(){
		// illegal char[][] case
		boolean[][] grid = new boolean[1][0];
		TetrisGrid tetris = new TetrisGrid(grid);
	}
}
