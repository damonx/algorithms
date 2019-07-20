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
package com.damonx.effective;

import java.util.EnumSet;
import java.util.Set;

public class EnumSetExample {
	public enum Style {
		BOLD,
		ITALIC,
		UNDERLINE,
		STRIKETHROUGH
	}

	public void applyStyles(final Set<Style> styles) {
		styles.stream().forEach(e -> System.out.println(e.toString()));
	}

	public static void main(final String[] args) {
		new EnumSetExample().applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
	}
}
