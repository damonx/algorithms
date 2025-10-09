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
package com.damonx.chain.resp;

public abstract class QRGProcessor {

	protected QRGProcessor successor;

	protected String processorName;

	public QRGProcessor(final String processorName) {
		this.processorName = processorName;
	}

	public void setSuccessor(final QRGProcessor successor) {
		this.successor = successor;
	}

	public abstract void processQrg(String modelNumber);

}
