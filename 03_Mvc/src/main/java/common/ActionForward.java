package common;

public class ActionForward {
	
	// 일종의 빈이다. bean
	private String path;		// 응답할 경로 (Jsp의 이름)
	private boolean isRedirect;	// 이동 방식(true이면 redirect, false이면 forward)
	
	
	// 디폴트생성자
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	// 이걸 서비스들이 우선적으로 모두 가져다가 쓴다.
	
	
	

}
