package com.damonx.robot.interpretor;

public class SentenceNode extends AbstractNode {
	private final AbstractNode direction;
	private final AbstractNode action;
	private final AbstractNode distance;

	public SentenceNode(final AbstractNode direction, final AbstractNode action, final AbstractNode distance) {
		this.direction = direction;
		this.action = action;
		this.distance = distance;
	}

	@Override
	public String interpret() {
		return this.direction.interpret() + this.action.interpret() + this.distance.interpret();
	}

}
