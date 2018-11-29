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

/*
 *
 * expression ::= direction action distance | composite //表达式
   composite ::= expression 'and' expression //复合表达式
   direction ::= 'up' | 'down' | 'left' | 'right' //移动方向
   action ::= 'move' | 'run' //移动方式
   distance ::= an integer //移动距离
 *
 * */

public class Client {

	public static void main(final String[] args) {
		final InstructionHandler handler = new InstructionHandler();
		handler.handle("up move 5 and down run 10 and left move 5");
		System.out.println(handler.output());
	}

}
