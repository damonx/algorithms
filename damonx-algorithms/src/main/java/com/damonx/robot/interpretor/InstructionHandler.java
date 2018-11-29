/*
 * Copyright (c) Fisher and Paykel Appliances.
 *
 * This document is copyright. Except for the purpose of fair reviewing, no part
 * of this publication may be reproduced or transmitted in any form or by any
 * means, electronic or mechanical, including photocopying, recording, or any
 * information storage and retrieval system, without permission in writing from
 * the publisher. Infringers of copyright render themselves liable for
 * prosecution.
 */
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
