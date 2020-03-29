// TabooTest.java
// Taboo class tests -- nothing provided.

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.*;

public class TabooTest {
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
    public void testNoFollowStrings(){
        // many same elements
        List<String> a = stringToList("aaaab");
        Taboo<String> tabooElem = new Taboo<String>(a);
        assertTrue(Arrays.deepEquals(new String[]{"a", "b"}, tabooElem.noFollow("a").toArray()));
        // mixed same elements
        a = stringToList("acab");
        tabooElem = new Taboo<String>(a);
        assertTrue(Arrays.deepEquals(new String[]{"b", "c"}, tabooElem.noFollow("a").toArray()));
        // complicated mixed + no letters
        a = stringToList("%d%d%d%dkkkm%mmm%ff%7ჰჰ%სდდდდ");
        tabooElem = new Taboo<String>(a);
        assertTrue(Arrays.deepEquals(new String[]{"ს", "d", "f", "7", "m"}, tabooElem.noFollow("%").toArray()));
    }

    @Test
    public void testNoFollowIntegers(){
        List<Integer> a = Arrays.asList(1, 2, 3, 1, 2, 3, 5);
        Taboo<Integer> tabooElem = new Taboo<Integer>(a);
        assertTrue(Arrays.deepEquals(new Integer[]{2}, tabooElem.noFollow(1).toArray()));
        assertTrue(Arrays.deepEquals(new Integer[]{1,5}, tabooElem.noFollow(3).toArray()));
        // after integer that is not in the list
        assertTrue(Arrays.deepEquals(new Integer[]{}, tabooElem.noFollow(11).toArray()));
        // last element
        assertTrue(Arrays.deepEquals(new Integer[]{}, tabooElem.noFollow(5).toArray()));
    }

    @Test
    public void testNoFollowDoubles(){
        // null situations too
        List<Double> a = new ArrayList<Double>();
        for(int i = 0; i < 500; i ++) {
            if (i % 10 == 0) a.add(null);
            else if (i % 10 == 2) a.add((double)(511));
            else a.add((double)i);
        }
        Taboo<Double> tabooElem = new Taboo<Double>(a);
        /*for(int i = 0; i < 500; i ++) {
            if (i % 10 == 9) assertTrue(!tabooElem.noFollow((double)i).contains((double)(i + 2)));
            else assertTrue(!tabooElem.noFollow((double)i).contains((double)i));
        }*/
        assertEquals(50, tabooElem.noFollow(Double.valueOf(511)).size());
    }

    @Test
    public void testNoFollowNullSituation(){
        List<Double> a = Arrays.asList(2.0, 5.4, 4.0, 2.0, 3.0, 2.0, 3.0, null, 7.0, null);
        Taboo<Double> tabooElem = new Taboo<Double>(a);
        // null should not be considered
        assertEquals(0, tabooElem.noFollow(null).size());
        assertEquals(1, tabooElem.noFollow(3.0).size());
        // same elements in not-follow list
        assertEquals(2, tabooElem.noFollow(2.0).size());
    }

    @Test
    public  void testNoFollowEmptyList(){
        List<Character> a = Arrays.asList();
        Taboo<Character> tabooElem = new Taboo<Character>(a);
        assertEquals(0, tabooElem.noFollow(null).size());
        // character is not in the list
        assertEquals(0, tabooElem.noFollow('@').size());
    }

    @Test
    public void testReduceString(){
        List<String> ls = stringToList("acab");
        Taboo<String> tabooElem = new Taboo<String>(ls);
        List<String> text = stringToList("acbxca");
        tabooElem.reduce(text);
        assertTrue(Arrays.deepEquals(new String[]{"a", "x", "c"}, text.toArray()));
    }

    @Test
    public void testReduceStringEmpty(){
        // empty text reduce
        List<String> ls1 = stringToList("acab");
        Taboo<String> tabooElem1 = new Taboo<String>(ls1);
        List<String> text1 = new ArrayList<String>();
        tabooElem1.reduce(text1);
        assertTrue(text1.isEmpty());
        // empty rules reduce
        List<String> ls2 = new ArrayList<String>();
        Taboo<String> tabooElem2 = new Taboo<String>(ls2);
        List<String> text2 = stringToList("acab");
        tabooElem2.reduce(text2);
        assertTrue(Arrays.deepEquals(new String[]{"a", "c", "a", "b"}, text2.toArray()));
    }

    @Test
    public void testReduceCharactersAndNull(){
        List<Character> ls1 = new ArrayList<Character>(Arrays.asList('a', 'b', null, 'c', 'a', 'c', 'd'));
        Taboo<Character> tabooElem1 = new Taboo<Character>(ls1);
        tabooElem1.reduce(ls1);
        assertTrue(Arrays.deepEquals(new Character[]{'a', null, 'c', 'c'}, ls1.toArray()));
        List<Character> text1 = new ArrayList<Character>(Arrays.asList('c', 'a', 'c', 'c', 'd', 'p'));
        tabooElem1.reduce(text1);
        assertTrue(Arrays.deepEquals(new Character[]{'c', 'c', 'c', 'p'}, text1.toArray()));
    }

    @Test
    public void testReduceCharacters(){
        // test is rules with half elements which is reduced already
        List<Character> ls = new ArrayList<Character>();
        for(char i = 'A'; i <='z'; i ++)
            if(i % 5 == 0) ls.add(i);
        Taboo<Character> tabooElem = new Taboo<Character>(ls);
        List<Character> text = new ArrayList<Character>();
        for(char i = 'A'; i <='z'; i ++){
            if(i % 10 == 0) text.add(i);
        }
        List<Character> textCopy = new ArrayList<Character>(text);
        tabooElem.reduce(text);
        System.out.println(text.toString());
        System.out.println(textCopy.toString());
        assertTrue(Arrays.deepEquals(textCopy.toArray(), text.toArray()));
        // same configuration string
        ls = textCopy;
        tabooElem = new Taboo<Character>(ls);
        tabooElem.reduce(text);
        assertEquals(text.size(), ls.size() / 2);
    }

    @Test
    public void testReduceBoolean(){
        // possible true ->> true
        Taboo<Boolean> tabooElem = new Taboo<Boolean>(Arrays.asList(true, false, false, true));
        // first false will delete everything
        List<Boolean> text = new ArrayList<Boolean>(Arrays.asList(false, true, true, false, false));
        tabooElem.reduce(text);
        // first true saves other trues
        text = new ArrayList<Boolean>(Arrays.asList(true, true, false, true, false));
        tabooElem.reduce(text);
        assertTrue(Arrays.deepEquals(new Boolean[]{true, true, true}, text.toArray()));
    }

    @Test
    public void testReduceIntegerSameElems(){
        List<Integer> a = Arrays.asList(11, 11);
        Taboo<Integer> tabooElem = new Taboo<Integer>(a);
        List<Integer> text = new ArrayList<Integer>(Arrays.asList(11, 11, 2, 11, 11));
        tabooElem.reduce(text);
        assertTrue(Arrays.deepEquals(new Integer[]{11, 2, 11}, text.toArray()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNull(){
        // list is null
        Taboo<Integer> tabooElem = new Taboo<Integer>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReduceNull(){
        // list is null
        Taboo<Integer> tabooElem = new Taboo<Integer>(Arrays.asList(11, 11, 2, 11, 11));
        tabooElem.reduce(null);
    }
}
