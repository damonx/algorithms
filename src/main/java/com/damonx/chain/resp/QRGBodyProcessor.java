
package com.damonx.chain.resp;

public class QRGBodyProcessor extends QRGProcessor {

	public QRGBodyProcessor(final String processorName) {
		super(processorName);
	}

	@Override
	public void processQrg(final String modelNumber) {
		System.out.println("Genrate the body for product: " + modelNumber);

	}

}
