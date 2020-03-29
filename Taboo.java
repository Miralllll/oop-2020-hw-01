
/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/

import java.lang.reflect.Array;
import java.util.*;

public class Taboo<T> {

	private HashMap<T, HashSet<T>> tabooCombs;
	/**
	 * Constructs a new Taboo using the given rules (see handout.)
	 * @param rules rules for new Taboo
	 */
	public Taboo(List<T> rules) {
		validatedList(rules);
		tabooCombs = new HashMap<T, HashSet<T>>();
		Iterator<T> itMap = rules.iterator();
		T prev = null, curr = null; // saves previous version too
		while(itMap.hasNext()){
			curr = itMap.next();
			if(curr != null && prev != null){
				// if(!curr.equals(prev)) -- if aaaab -> notFollow(a) -> {b}
				if (!tabooCombs.containsKey(prev)) tabooCombs.put(prev, new HashSet<>());
				tabooCombs.get(prev).add(curr);
			}
			prev = curr;
		}
	}
	
	/**
	 * Returns the set of elements which should not follow
	 * the given element.
	 * @param elem
	 * @return elements which should not follow the given element
	 */
	public Set<T> noFollow(T elem) {
		if(tabooCombs.containsKey(elem)) return tabooCombs.get(elem);
		return Collections.emptySet();
	}
	
	/**
	 * Removes elements from the given list that
	 * violate the rules (see handout).
	 * @param list collection to reduce
	 */
	public void reduce(List<T> list) {
		validatedList(list);
		Iterator<T> it = list.iterator();
		T curr = null, prev = null;
		Set<T> notAllowed = null;
		while(it.hasNext()) {
		    curr = it.next();
		    // if null was entered and client want to be removed curr != null would be deleted.
			if(curr != null && notAllowed != null && notAllowed.contains(curr)) it.remove();
			else {
                prev = curr;
                if(prev != null) notAllowed = tabooCombs.get(prev);
                else notAllowed = null;
            }
		} // while iterates everything and remove element if they aren't allowed
	}

	/**
	 * throws IllegalArgumentException if list is illegal
	 */
	private <T> void validatedList(List<T> l) {
		if(l == null) throw new IllegalArgumentException("Collection is Illegal!");
	}
}
