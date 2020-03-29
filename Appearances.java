import java.util.*;

public class Appearances {
	
	/**
	 * Returns the number of elements that appear the same number
	 * of times in both collections. Static method. (see handout).
	 * @return number of same-appearance elements
	 */
	public static <T> int sameCount(Collection<T> a, Collection<T> b) {
		validatedCollection(a);
		validatedCollection(b);
		HashMap<T, Integer> aColl = elemFrequencies(a);
		HashMap<T, Integer> bColl = elemFrequencies(b);
		int countSameP = 0;
		for(T elem : aColl.keySet())
			if(bColl.containsKey(elem))
				//if(aColl.get(elem).hashCode() == bColl.get(elem).hashCode())
					if(aColl.get(elem).equals(bColl.get(elem))) countSameP ++;
		return countSameP;
	}

	/**
	 * @param a is collection
	 * @return HashMap which contains every T element and its frequencies in the collection
	 */
	private static <T> HashMap<T, Integer> elemFrequencies(Collection<T> a) {
		validatedCollection(a);
		HashMap<T, Integer> collMap = new HashMap<T, Integer>();
		for(T elem : a)
			if(collMap.containsKey(elem)) collMap.put(elem, 1 + collMap.get(elem));
			else collMap.put(elem, 1);
		return collMap;
	}

	/**
	 * throws IllegalArgumentException if collection is illegal
	 */
	private static <T> void validatedCollection(Collection<T> c) {
		if(c == null) throw new IllegalArgumentException("Collection is Illegal!");
	}
}
