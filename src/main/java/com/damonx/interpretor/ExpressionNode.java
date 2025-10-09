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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ExpressionNode implements Node {
	private final List<Node> list = new ArrayList<>();

	@Override
	public void interpret(final Context context) {
		while (true) {
			if (StringUtils.isBlank(context.currentToken())) {
				break;
			} else if (context.currentToken().equals("END")) {
				context.skipToken("END");
				break;
			} else {
				final Node commandNode = new CommandNode();
				commandNode.interpret(context);
				this.list.add(commandNode);
			}
		}

	}

	@Override
	public void execute() {
		this.list.stream().forEach(e -> e.execute());
	}

}
