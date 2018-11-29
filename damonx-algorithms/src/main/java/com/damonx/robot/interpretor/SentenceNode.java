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
