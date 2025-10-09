package com.damonx.algorithms;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class MinStackTest {

	private MinStack stack;

	@Before
	public void setup() {
		this.stack = new MinStack(new ArrayList<Integer>(), new ArrayList<Integer>());
	}

	@Test
	public void testPush() {
		Stream.of(7, 8, 1, 1, 1, 3, 2, 12, 2, 19, 4, 13, 6, 5).forEach(e -> {
			this.stack.push(e);
		});
		assertThat(this.stack.getData()).isNotEmpty();
		assertThat(this.stack.getData().size()).isEqualTo(14);
		assertThat(this.stack.getMins().size()).isEqualTo(2);
		assertThat(this.stack.getMins()).containsExactly(0, 2);
	}

	@Test
	public void testPop() {
		// GIVEN
		Stream.of(7, 2, 1).forEach(e -> {
			this.stack.push(e);
		});

		// WHEN
		// THEN
		assertThat(this.stack.getMins().size()).isEqualTo(3);
		assertThat(this.stack.getMins()).containsExactly(0, 1, 2);
		assertThat(this.stack.getMin()).isEqualTo(1);
		this.stack.pop();
		assertThat(this.stack.getData().size()).isEqualTo(2);
		assertThat(this.stack.getData()).containsExactly(7, 2);
		assertThat(this.stack.getMins()).containsExactly(0, 1);
		assertThat(this.stack.getMin()).isEqualTo(2);
	}
}
