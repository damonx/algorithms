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

public class ConcreteAllyControlCenter extends AllyControlCenter {

	public ConcreteAllyControlCenter(final String allName) {
		this.allyName = allName;
		System.out.println(this.allyName + " team was constructed succsessfully.");
		System.out.println("============================================");
	}

	@Override
	public void notifyObservers(final String name) {
		System.out.println(this.allyName + " team is notifying: ally " + name + " is being attacked!");
		this.players.stream().filter(e -> !e.getName().equalsIgnoreCase(name)).forEach(e -> e.help());
	}

}
