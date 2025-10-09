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
package com.damonx.observer;

public class ObserverClient {

	public static void main(final String[] args) {
		// 定义观察目标对象
		final AllyControlCenter acc = new ConcreteAllyControlCenter("金庸群侠");

		// 定义四个观察者对象
		Observer player1, player2, player3, player4;

		player1 = new Player("杨过");
		acc.join(player1);

		player2 = new Player("令狐冲");
		acc.join(player2);

		player3 = new Player("张无忌");
		acc.join(player3);

		player4 = new Player("段誉");
		acc.join(player4);

		// acc.join(player1, player2, player3, player4);

		// 某成员遭受攻击
		player1.beAttacked(acc);

	}

}
