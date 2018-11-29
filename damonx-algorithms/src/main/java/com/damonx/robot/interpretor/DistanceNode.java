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
