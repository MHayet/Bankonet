package com.bankonet.lib;

@SuppressWarnings("serial")
public class DebitException extends CompteException {
	//att
	
	//constructeurs
	public DebitException(){}
	
	public DebitException(String msg){
		super(msg);
	}
}
