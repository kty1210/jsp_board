package board;

import java.util.Date;

public class BoardVO {

	//변수생성
	private int num;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int cnt;
	
	//기본생성자
	public BoardVO() {
		super();
	}
	
	//필드생성자
	public BoardVO(int num, String title, String writer, String content, Date regdate, int cnt) {
		super();
		this.num = num;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.cnt = cnt;
	}




	//getter setter 생성
	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getWriter() {
		return writer;
	}



	public void setWriter(String writer) {
		this.writer = writer;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public Date getRegdate() {
		return regdate;
	}



	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}



	public int getCnt() {
		return cnt;
	}



	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	//toString 오버라이딩 
	//toString은 Java Object에서 상속받는 기본 메소드
	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", title=" + title + ",writer=" + writer + ",content=" + content + ",regdate=" + regdate + ", cnt=" + cnt + "]"; 
	}
}
