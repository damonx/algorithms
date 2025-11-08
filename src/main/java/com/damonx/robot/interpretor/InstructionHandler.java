package com.damonx.robot.interpretor;

import java.util.Stack;

public class InstructionHandler {
	private AbstractNode node;

	public InstructionHandler() {

	}

	public void handle(final String instruction) {
		AbstractNode left, right;
		AbstractNode direction, action, distance;

		final Stack<AbstractNode> stack = new Stack<>();
		final String[] words = instruction.split(" ");
		for (int i = 0; i < words.length; i++) {
			if (words[i].equalsIgnoreCase("and")) {
				left = stack.pop();
				final String word1 = words[++i];
				direction = new DirectionNode(word1);
				final String word2 = words[++i];
				action = new ActionNode(word2);
				final String word3 = words[++i];
				distance = new DistanceNode(word3);
				right = new SentenceNode(direction, action, distance);
				stack.push(new AndNode(left, right));
			} else {
				final String word1 = words[i];
				direction = new DirectionNode(word1);
				final String word2 = words[++i];
				action = new ActionNode(word2);
				final String word3 = words[++i];
				distance = new DistanceNode(word3);
				left = new SentenceNode(direction, action, distance);
				stack.push(left);
			}

		}
		this.node = stack.pop();
	}

	public String output() {
		return this.node.interpret();
	}
}
