package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class AllTasks implements Serializable {
	
	private int userid;
	private int piece;
	private String title;
	// StringBuilderが使い辛ければ、Stringに変える
	private StringBuilder memo;
	private Optional<LocalDate> deadlinedate;
	
	public AllTasks() {
		this.deadlinedate = Optional.ofNullable(null);
	}
	
	public AllTasks(int piece) {
		this.piece = piece;
	}
	
	public AllTasks(int userid, int piece, String title, StringBuilder memo, Optional<LocalDate> deadlinedate) {
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
	
//	public Optional<LocalDate> getDeadlinedate() { return deadlinedate; }
	
	public Optional<LocalDate> getDeadlinedate() {
		if (deadlinedate == null) {
//			return Optional.empty();
			return Optional.ofNullable(null);
		}
//		LocalDate localDate = deadlinedate.get();
//		return Optional.ofNullable(localDate);
		return deadlinedate;
	}
	
	public void setDeadlinedate(Optional<LocalDate> deadlinedate) { this.deadlinedate = deadlinedate; }
	
	public String toString() {
		String useridString = String.valueOf(userid);
		String pieceString = String.valueOf(piece);
		String memoString;
		if (memo != null) {
			memoString = memo.toString();
		} else {
			memoString = "";
		}
		String deadlinedateString;
		if (deadlinedate.isPresent()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate deadlinelocaldate = deadlinedate.get();
			deadlinedateString = deadlinelocaldate.format(formatter);
		} else {
			deadlinedateString = "";
		}
		return "userid:" + useridString + " piece:" + pieceString + " title:" + title + " memo:" + memoString + " deadlinedate:" + deadlinedateString;
	}
	// AllTasksのtitleにはnullは入らないようになっているはず。
	// だが、もしエラーが出た場合はここも疑ってみる。
	
	public boolean equals(Object o) {
		if (o == this) return true;
		if (o == null) return false;
		if (!(o instanceof AllTasks)) return false;
		AllTasks alltasks = (AllTasks)o;
		if (!(this.piece == alltasks.piece)) {
			return false;
		}
		return true;
	}
}
