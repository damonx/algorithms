package com.damonx.chain.resp;

public class QRGHeaderProcessor extends QRGProcessor {

	public QRGHeaderProcessor(final String processorName) {
		super(processorName);
	}

	@Override
	public void processQrg(final String modelNumber) {
		System.out.println("Genrate the header for product: " + modelNumber);
		this.successor.processQrg(modelNumber);
	}

}
