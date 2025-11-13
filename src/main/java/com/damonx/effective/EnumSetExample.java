package com.damonx.effective;

import java.util.EnumSet;
import java.util.Set;

public class EnumSetExample {
	public enum Style {
		BOLD,
		ITALIC,
		UNDERLINE,
		STRIKETHROUGH
	}

	public void applyStyles(final Set<Style> styles) {
		styles.stream().forEach(e -> System.out.println(e.toString()));
	}

	public static void main(final String[] args) {
		new EnumSetExample().applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
	}
}
