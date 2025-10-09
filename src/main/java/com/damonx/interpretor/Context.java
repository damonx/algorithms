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

import java.util.StringTokenizer;

public class Context {
	private final StringTokenizer tokenizer;
	private String currentToken;

	public Context(final String text) {
		this.tokenizer = new StringTokenizer(text);
		nextToken();

	}

	public String nextToken() {
		if (this.tokenizer.hasMoreTokens()) {
			this.currentToken = this.tokenizer.nextToken();
		} else {
			this.currentToken = null;
		}
		return this.currentToken;
	}

	public String currentToken() {
		return this.currentToken;
	}

	public void skipToken(final String token) {
		if (!token.equals(this.currentToken)) {
			System.err.println("Error: " + this.currentToken + "incorrect interpret...");
		}
		nextToken();
	}

	public int currentNumber() {
		int number = 0;
		try {
			number = Integer.parseInt(this.currentToken);
		} catch (final NumberFormatException e) {
			System.err.println("Error: " + e);
		}
		return number;
	}
}
