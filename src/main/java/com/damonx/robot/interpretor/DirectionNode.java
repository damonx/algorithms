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

import java.util.Arrays;

public class DirectionNode extends AbstractNode {
	private final String direction;

	public DirectionNode(final String direction) {
		this.direction = direction;
	}

	@Override
	public String interpret() {
		if (Arrays.asList("up", "down", "left", "right").stream().anyMatch(e -> e.equals(this.direction))) {
			return this.direction.concat("ward");
		} else {
			return "Invalid Command!";
		}
	}

}
