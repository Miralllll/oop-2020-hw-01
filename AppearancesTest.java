import org.junit.Test;

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class AppearancesTest {

	// utility -- converts a string to a list with one
	// elem for each char.
	private List<String> stringToList(String s) {
		List<String> list = new ArrayList<String>();
		for (int i=0; i<s.length(); i++) {
			list.add(String.valueOf(s.charAt(i)));
			// note: String.valueOf() converts lots of things to string form
		}
		return list;
	}

	@Test
	public void testSameCount1() {
		List<String> a = stringToList("abbccc");
		List<String> b = stringToList("cccbba");
		assertEquals(3, Appearances.sameCount(a, b));
	}

	@Test
	public void testSameCount2() {
		// basic List<Integer> cases
		List<Integer> a = Arrays.asList(1, 2, 3, 1, 2, 3, 5);
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 9, 9, 1)));
		assertEquals(2, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1)));
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1, 1)));
	}

	@Test
	public void testSameCountSimpleString(){
		// no common string generally
		List<String> a1 = stringToList("abbccc");
		List<String> b1 = stringToList("dddkk4324#943kkggg");
		assertEquals(0, Appearances.sameCount(a1, b1));
		// one common string with equal frequencies
		// b2 contains this string (front, middle, end) indices.
		List<String> b2 = stringToList("cdddkk32434kck&%%*kkgggc");
		assertEquals(1, Appearances.sameCount(a1, b2));
		// one common string with non-equal frequencies
		List<String> b3 = stringToList(")8ccdddk)kkckkkgggc");
		assertEquals(0, Appearances.sameCount(a1, b3));
		// six equal string and frequencies with no letter chatacters too
		List<String> a2 = stringToList("ab%b%cc--cc%222");
		List<String> b4 = stringToList("cdd%dbk%akkckbk--%ckgggc222");
		assertEquals(6, Appearances.sameCount(a2, b4));
	}

	@Test
	public void testSameCountEmptyList(){
		List<String> a1 = new ArrayList<String>();
		List<String> b1 = stringToList("dddkk4324#943kkggg");
		// one empty list
		assertEquals(0, Appearances.sameCount(a1, b1));
		assertEquals(0, Appearances.sameCount(b1, a1));
		// two empty list
		assertEquals(0, Appearances.sameCount(a1, a1));
	}

	@Test
	public void testSameCountBoolean(){
		Boolean[] aArr = { true, false, true, true, true, false};
		assertEquals(1, Appearances.sameCount(Arrays.asList(aArr), Arrays.asList(false, true, false)));
		assertEquals(1, Appearances.sameCount(Arrays.asList(aArr), Arrays.asList(true, true, true, true)));
		assertEquals(0, Appearances.sameCount(Arrays.asList(aArr), Arrays.asList(true, true, false)));
	}

	@Test
	public void testSameCountCharacters(){
		Character[] aArr = { 'r', 'r', 'k', 'a', '3', '%', '3'};
		assertEquals(2, Appearances.sameCount(Arrays.asList(aArr), Arrays.asList('r', 'r', 'a')));
		assertEquals(3, Appearances.sameCount(Arrays.asList(aArr), Arrays.asList('%', 'a', 'k', '5')));
		assertEquals(0, Appearances.sameCount(Arrays.asList(aArr), Arrays.asList()));
	}

	@Test
	// bigger test - time test
	public void testSameCountDouble(){
		List<Double> a = new ArrayList<Double>();
		List<Double> b = new ArrayList<Double>();
		for(int i = 0; i < 500; i++){
			if(i % 5 == 0) a.add(Double.valueOf(i));
			if(i % 3 == 0) b.add(Double.valueOf(i));
		}
		assertEquals(34, Appearances.sameCount(a, b));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSameCountInvalidNull(){
		// list is null
		Appearances.sameCount(Arrays.asList(1, 4, 5), null);
	}
}
