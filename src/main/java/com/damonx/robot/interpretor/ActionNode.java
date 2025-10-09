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

public class ActionNode extends AbstractNode {
	private final String action;

	public ActionNode(final String action) {
		this.action = action;
	}

	@Override
	public String interpret() {
		if ("move".equalsIgnoreCase(this.action)) {
			return "move slowly";
		} else if ("run".equalsIgnoreCase(this.action)) {
			return "run";
		} else {
			return "Invalid command";
		}
	}

}
