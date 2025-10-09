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
