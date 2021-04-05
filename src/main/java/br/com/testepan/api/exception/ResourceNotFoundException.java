package br.com.testepan.api.exception;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -130169163473055417L;
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
