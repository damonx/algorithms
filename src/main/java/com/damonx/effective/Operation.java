
package com.damonx.effective;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Operation {
	PLUS("+") {
		@Override
		public double apply(final double x, final double y) {
			return x + y;
		}
	},
	MINUS("-") {
		@Override
		public double apply(final double x, final double y) {
			return x - y;
		}
	},
	TIMES("*") {
		@Override
		public double apply(final double x, final double y) {
			return x * y;
		}
	},
	DIVIDE("/") {
		@Override
		public double apply(final double x, final double y) {
			return x / y;
		}
	},
	COMBINATION("#") {
		@Override
		public double apply(final double x, final double y) {
			return (x + y) / (x - y);
		}
	};

	private final String symbol;

	Operation(final String symbol) {
		this.symbol = symbol;
	}

	private static final Map<String, Operation> stringToEnum = Stream.of(values()).collect(Collectors.toMap(Object::toString, e -> e));

	public static Optional<Operation> fromString(final String symbol) {
		return Optional.ofNullable(stringToEnum.get(symbol));
	}

	@Override
	public String toString() {
		return this.symbol;
	}

	public abstract double apply(double x, double y);

	public static void main(final String[] args) {
		final double x = 2.0;
		final double y = 4.0;
		for (final Operation op : Operation.values()) {
			System.out.printf("%f%s%f=%f%n", x, op, y, op.apply(x, y));
		}
		Operation.fromString("#").ifPresent(op -> {
			System.out.println("COMBINATION OPERATION: " + op.apply(x, y));
		});
	}
}
