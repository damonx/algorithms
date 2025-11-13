package com.damonx.interpretor;

import java.util.stream.IntStream;

public class LoopCommandNode implements Node {
	private int number;
	private Node commandNode;

	@Override
	public void interpret(final Context context) {
		context.skipToken("LOOP");
		this.number = context.currentNumber();
		context.nextToken();
		this.commandNode = new ExpressionNode();
		this.commandNode.interpret(context);
	}

	@Override
	public void execute() {
		IntStream.rangeClosed(0, this.number - 1).forEach(i -> this.commandNode.execute());
	}

}
