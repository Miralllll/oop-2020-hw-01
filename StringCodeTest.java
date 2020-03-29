// StringCodeTest
// Some test code is provided for the early HW1 problems,
// and much is left for you to add.

import org.junit.*;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.assertEquals;

public class StringCodeTest {
	//
	// blowup
	//
	@Test
	public void testBlowup1() {
		// basic cases
		assertEquals("xxaaaabb", StringCode.blowup("xx3abb"));
		assertEquals("xxxZZZZ", StringCode.blowup("2x3Z"));
	}

	@Test
	public void testBlowup2() {
		// things with digits
		// digit at end
		assertEquals("axxx", StringCode.blowup("a2x3"));
		// digits next to each other
		assertEquals("a33111", StringCode.blowup("a231"));
		// try a 0
		assertEquals("aabb", StringCode.blowup("aa0bb"));
	}

	@Test
	public void testBlowup3() {
		// weird chars, empty string
		assertEquals("AB&&,- ab", StringCode.blowup("AB&&,- ab"));
		assertEquals("", StringCode.blowup(""));
		// string with only digits
		assertEquals("", StringCode.blowup("2"));
		assertEquals("33", StringCode.blowup("23"));
	}

	@Test
	public void testBlowupMore(){
		// digits next to each other from the first letters
		assertEquals("444xxxxx", StringCode.blowup("34x"));
		// when blownup string is same
		assertEquals("xxxbbbbc", StringCode.blowup("xxxbbbbc"));
	}

	//
	// maxRun
	//
	@Test
	public void testRun1() {
		assertEquals(2, StringCode.maxRun("hoopla"));
		assertEquals(3, StringCode.maxRun("hoopllla"));
	}

	@Test
	public void testRun2() {
		assertEquals(3, StringCode.maxRun("abbcccddbbbxx"));
		assertEquals(0, StringCode.maxRun(""));
		assertEquals(3, StringCode.maxRun("hhhooppoo"));
	}

	@Test
	public void testRun3() {
		// "evolve" technique -- make a series of test cases
		// where each is change from the one above.
		assertEquals(1, StringCode.maxRun("123"));
		assertEquals(2, StringCode.maxRun("1223"));
		assertEquals(2, StringCode.maxRun("112233"));
		assertEquals(3, StringCode.maxRun("1112233"));
	}

	//
	// stringIntersect
	//
	@Test
	public void testIntersectLettersTrue() {
		// same substring from the first letters
		assertEquals(true, StringCode.stringIntersect("abcdef","abg", 2));
		// same substring is the last characters
		assertEquals(true, StringCode.stringIntersect("abcdeef","ef", 2));
		// the first string has two - ef and the second one - ef
		assertEquals(true, StringCode.stringIntersect("abeffcdef","gggefhf", 2));
		// has more than one same substring (length - len)
		assertEquals(true, StringCode.stringIntersect("miralmiral","whats miral", 3));
	}

	@Test
	public void testIntersectLettersFalse() {
		// no common substring generally
		assertEquals(false, StringCode.stringIntersect("nobodyyyy","human", 5));
		// no common substring  >=len-substring(actual substring)
		assertEquals(false, StringCode.stringIntersect("i am nobodyyyy","somebody", 5));
		// one empty string
		assertEquals(false, StringCode.stringIntersect("","Robot", 1));
		// two empty strings
		assertEquals(false, StringCode.stringIntersect("","", 1));
	}

	@Test
	public void testIntersectMixedCharsTrue(){
		// same substring from the first letters
		assertEquals(true, StringCode.stringIntersect("23.@0","mmmm23.", 3));
		// same substring is the last characters
		assertEquals(true, StringCode.stringIntersect("sdf242qeq3","2qeq3", 4));
		// has more than one same substring (length - len)
		assertEquals(true, StringCode.stringIntersect("lmi23#llmi23#l","whats 23#l", 4));
	}

	@Test
	public void testIntersectMixedCharsFalse(){
		// no common substring  >=len-substring(actual substring)
		assertEquals(false, StringCode.stringIntersect("bod@34yyyy","somed@34y", 6));
		// empty string
		assertEquals(false, StringCode.stringIntersect("@3@#32","", 2));
	}

	@Test
	public void testIntersectZeroLength(){
		// It's an extra feature ->>> no empty strings
		assertEquals(true, StringCode.stringIntersect("i am nobodyyyy","somebody", 0));
		// empty strings
		assertEquals(false, StringCode.stringIntersect("Robot","", 1));
		assertEquals(false, StringCode.stringIntersect("","", 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIntersectInvalidString(){
		// illegal string case
		StringCode.stringIntersect(null,"", 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIntersectInvalidLength(){
		// ilegal length case
		StringCode.stringIntersect("das","asdsa", -1);
	}
}
