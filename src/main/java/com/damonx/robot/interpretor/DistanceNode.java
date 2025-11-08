package com.damonx.robot.interpretor;

public class DistanceNode extends AbstractNode {
	private final String distance;

	public DistanceNode(final String distance) {
		this.distance = distance;
	}

	@Override
	public String interpret() {
		return this.distance;
	}

}
