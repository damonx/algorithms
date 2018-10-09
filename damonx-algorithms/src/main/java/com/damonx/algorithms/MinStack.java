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
package com.damonx.algorithms;

import java.util.EmptyStackException;
import java.util.List;

public class MinStack {
	private final List<Integer> data;
	private final List<Integer> mins;

	public MinStack(final List<Integer> data, final List<Integer> mins) {
		this.data = data;
		this.mins = mins;
	}

	public List<Integer> getData() {
		return this.data;
	}

	public List<Integer> getMins() {
		return this.mins;
	}

	public void push(final int num) {
		this.data.add(num);
		if (this.mins.size() == 0) {
			this.mins.add(0);
		} else {
			final int min = getMin();
			if (num < min) {
				this.mins.add(this.data.size() - 1);
			}
		}
	}

	public int pop() {
		if (this.data.size() == 0) {
			throw new EmptyStackException();
		}

		final int popIndex = this.data.size() - 1;
		final int minIndex = this.mins.get(this.mins.size() - 1);

		if (popIndex == minIndex) {
			this.mins.remove(this.mins.size() - 1);
		}
		return this.data.remove(this.data.size() - 1);
	}

	public int getMin() {
		if (this.data.size() == 0) {
			throw new EmptyStackException();
		}
		final int minIndex = this.mins.get(this.mins.size() - 1);
		return this.data.get(minIndex);
	}
}
