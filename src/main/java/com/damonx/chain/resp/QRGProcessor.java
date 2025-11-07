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
