package controller;

public class RentExceptions extends Exception {
	private String message;
	public RentExceptions() {
		super();
	}
	public RentExceptions(String message, Throwable cause){
		super(message,cause);
	}
	public RentExceptions(String message){
		super(message);
		this.message=message;
	}
	
}
