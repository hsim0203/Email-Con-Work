package kr.ac.kopo.trash.vo;


public class TrashVO {

	private String id;
	private String writer;
	private String receiver;
	private String title;
	private String emailBody;
	private String regDate;
	private int	   no;
	
	public TrashVO() {
		super();
	}

	public TrashVO(String writer, String receiver, String title, String emailBody, String regDate, int no) {
		super();
		this.writer = writer;
		this.receiver = receiver;
		this.title = title;
		this.emailBody = emailBody;
		this.regDate = regDate;
		this.no = no;
	}


	public TrashVO(String writer, String receiver, String title, String emailBody, String regDate) {
		super();
		this.writer = writer;
		this.receiver = receiver;
		this.title = title;
		this.emailBody = emailBody;
		this.regDate = regDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	

	@Override
	public String toString() {
		return "TrashVO [writer=" + writer + ", receiver=" + receiver + ", title=" + title
				+ ", emailBody=" + emailBody + ", regDate=" + regDate + ", no=" + no + "]";
	}
	
}