package ex07_ajax;

public class MyHandleException extends Exception{


	private static final long serialVersionUID = 2179968399669024314L;
	private int errorCode;
	
	public MyHandleException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
