
package com.damonx.chain.resp;

public class Client {

	public static void main(final String[] args) {
		final QRGProcessor qrgHeaderProcessor, qrgBodyProcessor;
		qrgHeaderProcessor = new QRGHeaderProcessor("qrgHeader Processor");
		qrgBodyProcessor = new QRGBodyProcessor("qrgBody processor");
		qrgHeaderProcessor.setSuccessor(qrgBodyProcessor);

		qrgHeaderProcessor.processQrg("12345");
	}

}
