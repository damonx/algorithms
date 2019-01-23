
package com.damonx.effective;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PosCount {
	public int[] position;
	public int count;
	public char character;

	public PosCount(final int[] pos, final int count) {
		this.position = pos;
		this.count = count;
	}

	public static void main(final String[] args) {
		final char[] a = "AAEEECCCDDAA".toCharArray();
		char[] manipulated = squeezeChar(manipulate(a));
		if (String.valueOf(manipulated).trim().length() > 2 && isArrDuplicate(squeezeChar(manipulated))) {
			manipulated = squeezeChar(manipulate(manipulated));
		}
		System.out.println(String.valueOf(manipulated).trim().hashCode() == 0 ? "EMPTY" : String.valueOf(manipulated).trim());
	}

	public static char[] squeezeChar(final char[] arr) {
		final char[] result = new char[arr.length];
		int resultIndx = 0;
		for (final char element : arr) {
			if (element != Character.MIN_VALUE) {
				result[resultIndx] = element;
				resultIndx++;
			}
		}
		return result;
	}

	public static char[] manipulate(final char[] str) {
		if (str.length <= 2) {
			return str;
		}
		final char[] resultChars = new char[str.length];
		final Map<Character, PosCount> strMap = new HashMap<>();
		for (int i = 0; i < str.length; i++) {
			final Character indexChar = Character.valueOf(str[i]);
			final PosCount posCount = strMap.get(indexChar);
			if (posCount != null) {
				posCount.position[i]++;
				posCount.count++;
			} else {
				final int[] pos = new int[str.length];
				pos[i]++;
				strMap.put(indexChar, new PosCount(pos, 1));
			}
		}

		strMap.forEach((character, poscount) -> {
			if (poscount.count < 3 || !isArrConsecutive(poscount.position)) {
				for (int j = 0; j < poscount.position.length; j++) {
					if (poscount.position[j] != 0) {
						resultChars[j] = character;
					}
				}
			}
		});

		return resultChars;
	}

	public static boolean isArrDuplicate(final char[] str) {
		if (String.valueOf(str).hashCode() == 0) {
			return false;
		}
		for (int i = 0; i < str.length - 1; i++) {
			for (int j = 1; j < str.length - 1; j++) {
				if (str[j] == str[i] && str[j + 1] == str[i]) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isArrConsecutive(final int[] arr) {
		final List<Integer> indexes = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 1) {
				indexes.add(i);
			}
		}
		int count = 0;
		for (int k = 0; k < indexes.size() - 1; k++) {
			if (indexes.get(k + 1) - indexes.get(k) == 1) {
				count++;
			} else {
				count--;
			}
		}
		return count >= 2;

	}

}
