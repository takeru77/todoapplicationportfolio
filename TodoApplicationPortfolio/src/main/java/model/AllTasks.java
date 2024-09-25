package model;

import java.io.Serializable;
import java.sql.Date;

public class AllTasks implements Serializable {
	
	private int userid;
	private int piece;
	private String title;
	// StringBuilderが使い辛ければ、Stringに変える
	private StringBuilder memo;
	private Date deadlinedate;
	
	public AllTasks() {}
	
	public AllTasks(int userid, int piece, String title, StringBuilder memo, Date deadlinedate) {
		//
		this.userid = userid;
		this.piece = piece;
		this.title = title;
		this.memo = memo;
		this.deadlinedate = deadlinedate;
	}
	
	public int getUserid() { return userid; }
	public void setUserid(int userid) { this.userid = userid; }
	
	public int getPiece() { return piece; }
	public void setPiece(int piece) { this.piece = piece; }
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	public StringBuilder getMemo() { return memo; }
	public void setMemo(StringBuilder memo) { this.memo = memo; }
	
	public Date getDeadlinedate() { return deadlinedate; }
	public void setDeadlinedate(Date deadlinedate) { this.deadlinedate = deadlinedate; }
}
