package po;

import java.sql.Timestamp;

public class Reply {
   private int replyid;
   private int msgid;
   private int userid;
   private String replycontents;
   private Timestamp replytime;
   private String replyip;
public Reply(int replyid, int msgid, int userid, String replycontents, String replyip) {
	super();
	this.replyid = replyid;
	this.msgid = msgid;
	this.userid = userid;
	this.replycontents = replycontents;
	
	this.replyip = replyip;
}
public Reply() {
	super();
}
public int getReplyid() {
	return replyid;
}
public void setReplyid(int replyid) {
	this.replyid = replyid;
}
public int getMsgid() {
	return msgid;
}
public void setMsgid(int msgid) {
	this.msgid = msgid;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getReplycontents() {
	return replycontents;
}
public void setReplycontents(String replycontents) {
	this.replycontents = replycontents;
}
public Timestamp getReplytime() {
	return replytime;
}
public void setReplytime(Timestamp replytime) {
	this.replytime = replytime;
}
public String getReplyip() {
	return replyip;
}
public void setReplyip(String replyip) {
	this.replyip = replyip;
}
   
}
