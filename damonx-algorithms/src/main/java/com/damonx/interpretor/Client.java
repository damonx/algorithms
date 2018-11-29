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
package com.damonx.interpretor;

public class Client {

	public static void main(final String[] args) {
		final String text = "LOOP 2 PRINT DAMON SPACE SPACE PRINT ZOE BREAK END PRINT IAN SPACE SPACE PRINT ELODIE BREAK";
		final String text2 = "LOOP 2 LOOP 2 PRINT 杨过 SPACE SPACE PRINT 小龙女 BREAK END PRINT 郭靖 SPACE SPACE PRINT 黄蓉 BREAK END";
		final Context context = new Context(text);
		final Node node = new ExpressionNode();
		node.interpret(context);
		node.execute();

		final Context context1 = new Context(text2);
		final Node node1 = new ExpressionNode();
		node1.interpret(context1);
		node1.execute();
	}

}
