package com.bankonet.lib;

@SuppressWarnings("serial")
public class CompteNonTrouveException extends CompteException {
	//constructeurs
	public CompteNonTrouveException() {}

	public CompteNonTrouveException(String msg) {
		super(msg);
	}

}
