package spring.io.BetterPlace.Enums;

public enum JwtStatus {
	DONE("성공"),
	FAILE("실패"),
	EXPIRE("기간만료"),
	ERROR("파싱 중 에러");

	private String msg;
	
	JwtStatus(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return this.msg;
	}
}
