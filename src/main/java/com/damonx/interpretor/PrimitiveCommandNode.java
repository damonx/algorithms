package com.damonx.interpretor;

import java.util.Arrays;

public class PrimitiveCommandNode implements Node {
	private String name;
	private String text;

	@Override
	public void interpret(final Context context) {
		this.name = context.currentToken();
		context.skipToken(this.name);
		if (!Arrays.asList("PRINT", "BREAK", "SPACE").stream().anyMatch(e -> this.name.equals(e))) {
			System.out.println("Invalid Command!!");
		}
		if (this.name.equals("PRINT")) {
			this.text = context.currentToken();
			context.nextToken();
		}
	}

	@Override
	public void execute() {
		switch (this.name) {
			case "PRINT":
				System.out.print(this.text);
				break;
			case "SPACE":
				System.out.print(" ");
				break;
			case "BREAK":
				System.out.println();
				break;
			default:
				break;
		}

	}

}
