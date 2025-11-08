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
