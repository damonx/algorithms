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

public class AndNode extends AbstractNode {
	private final AbstractNode left;
	private final AbstractNode right;

	public AndNode(final AbstractNode left, final AbstractNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public String interpret() {
		return this.left.interpret() + " and " + this.right.interpret();
	}

}
