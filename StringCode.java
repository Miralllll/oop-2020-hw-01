import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// CS108 HW1 -- String static methods

public class StringCode {

	/**
	 * Given a string, returns the length of the largest run.
	 * A a run is a series of adajcent chars that are the same.
	 *
	 * @param str
	 * @return max run length
	 */
	public static int maxRun(String str) {
		validatedString(str);
		int maxRunLength = 0;
		for (int i = 0; i < str.length(); ) {
			int runTo = i + 1;
			// counts same numbers in the line
			while (runTo < str.length() && str.charAt(i) == str.charAt(runTo))
				runTo++;
			maxRunLength = Math.max(runTo - i, maxRunLength);
			i = runTo; // updates the last letter's index
		}
		return maxRunLength;
	}

	/**
	 * Given a string, for each digit in the original string,
	 * replaces the digit with that many occurrences of the character
	 * following. So the string "a3tx2z" yields "attttxzzz".
	 *
	 * @param str
	 * @return blown up string
	 */
	public static String blowup(String str) {
		validatedString(str);
		String blownupStr = "";
		for (int i = 0; i < str.length() - 1; i++) {
			if (Character.isDigit(str.charAt(i)))
					blownupStr += str.substring(i + 1, i + 2).repeat(str.charAt(i) - '0');
			else blownupStr += str.charAt(i);
		}
		if (!str.isEmpty() && !Character.isDigit(str.charAt(str.length() - 1)))
			blownupStr += str.charAt(str.length() - 1);
		return blownupStr;
	}

	/**
	 * Given 2 strings, consider all the substrings within them
	 * of length len. Returns true if there are any such substrings
	 * which appear in both strings.
	 * Compute this in linear time using a HashSet. Len will be 1 or more.
	 */
	public static boolean stringIntersect(String a, String b, int len) {
		validatedString(a);
		validatedString(b);
		validatedLength(len);
		HashSet<String> substringsA = findAllnLengthSubStr(a, len);
		HashSet<String> substringsB = findAllnLengthSubStr(b, len);
		return substringsA.removeAll(substringsB); // or retrainAll
	}

	/**
	 * Method : findAllnLengthSubStr
	 * @param str
	 * @param len
	 * @return all n-length substrings of arg-string ->>> O(n)
	 */
	private static HashSet<String> findAllnLengthSubStr(String str, int len) {
		HashSet<String> substrings = new HashSet<String>();
		for (int i = 0; i <= str.length() - len; i++)
			substrings.add(str.substring(i, i + len));
		return substrings;
	}

	/**
	 * throws IllegalArgumentException if string is null
	 * @param str
	 */
	private static void validatedString(String str) {
		if (str == null) throw new IllegalArgumentException("Argument String is Illegal!");
	}

	/**
	 * throws IllegalArgumentException if Length is less than 0
	 * @param len
	 */
	private static void validatedLength(int len) {
		if(len < 0) throw new IllegalArgumentException("Argument-Length is Illegal!");
	}
}