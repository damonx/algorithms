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

import org.apache.commons.lang3.StringUtils;

public class CommandNode implements Node {
	private Node node;

	@Override
	public void interpret(final Context context) {
		if (StringUtils.equals(context.currentToken(), "LOOP")) {
			this.node = new LoopCommandNode();
			this.node.interpret(context);
		} else {
			this.node = new PrimitiveCommandNode();
			this.node.interpret(context);
		}

	}

	@Override
	public void execute() {
		this.node.execute();
	}

}
