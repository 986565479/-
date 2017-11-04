package vo;

import java.sql.Timestamp;
//我的问题查询条件类
public class MyMsg {
   private int msgid; 
   private String msgtopic;
   private Timestamp time;
   private int accesscount;
   private int replycount;
public MyMsg(int msgid,String msgtopic, Timestamp time, int accesscount, int replycount) {
	super();
	this.msgid=msgid;
	this.msgtopic = msgtopic;
	this.time = time;
	this.accesscount = accesscount;
	this.replycount = replycount;
}
public MyMsg() {
	super();
}

public int getMsgid() {
	return msgid;
}
public void setMsgid(int msgid) {
	this.msgid = msgid;
}
public String getMsgtopic() {
	return msgtopic;
}
public void setMsgtopic(String msgtopic) {
	this.msgtopic = msgtopic;
}
public Timestamp getTime() {
	return time;
}
public void setTime(Timestamp time) {
	this.time = time;
}
public int getAccesscount() {
	return accesscount;
}
public void setAccesscount(int accesscount) {
	this.accesscount = accesscount;
}
public int getReplycount() {
	return replycount;
}
public void setReplycount(int replycount) {
	this.replycount = replycount;
}
   
}
