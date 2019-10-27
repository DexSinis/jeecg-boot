package org.jeecg.exception;

public class InterfaceZxException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InterfaceZxException(String message){
		super(message);
	}

	public InterfaceZxException(Throwable cause)
	{
		super(cause);
	}

	public InterfaceZxException(String message,Throwable cause)
	{
		super(message,cause);
	}
}
