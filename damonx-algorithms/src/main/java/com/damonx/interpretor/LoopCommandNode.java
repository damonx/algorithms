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
