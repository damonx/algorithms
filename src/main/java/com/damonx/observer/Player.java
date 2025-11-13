package com.damonx.observer;

public class Player implements Observer {
	private String name;

	public Player(final String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;

	}

	@Override
	public void help() {
		System.out.println("Hold on, " + this.name + " is coming over to help you now!");

	}

	@Override
	public void beAttacked(final AllyControlCenter acc) {
		System.out.println(this.name + " is being attacked.");
		acc.notifyObservers(this.name);
	}

}
