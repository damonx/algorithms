
package com.damonx.observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AllyControlCenter {
	protected String allyName;
	protected List<Observer> players = new ArrayList<>();

	public void setAllyName(final String allyName) {
		this.allyName = allyName;
	}

	public String getAllyName() {
		return this.allyName;
	}

	// Register observers;
	public void join(final Observer obs) {
		System.out.println(obs.getName() + " is joining " + this.allyName + " team!");
		this.players.add(obs);
	}

	// Register observers;
	public void join(final Observer... obs) {
		final List<Observer> obsAsList = Arrays.asList(obs);
		System.out.println(obsAsList.toString() + " are joining " + this.allyName + " team!");
		this.players.addAll(obsAsList);
	}

	public void quit(final Observer obs) {
		System.out.println(obs.getName() + " quit " + this.allyName + " team.");
		this.players.remove(obs);
	}

	public abstract void notifyObservers(String name);

}
