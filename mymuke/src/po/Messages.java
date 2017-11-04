package po;

import java.sql.Timestamp;

public class Messages {
  private int msgid;
  private int userid;
  private String msgtopic;
  private String msgcontents;
  private Timestamp msgtime;
  private String msgip;
  private int theid;
  private int state;
  
  
public Messages() {
	super();
}
public Messages(int msgid, int userid, String msgtopic, String msgcontents, Timestamp msgtime, String msgip, int theid,
		int state) {
	super();
	this.msgid = msgid;
	this.userid = userid;
	this.msgtopic = msgtopic;
	this.msgcontents = msgcontents;
	this.msgtime = msgtime;
	this.msgip = msgip;
	this.theid = theid;
	this.state = state;
}
  

public Messages(int userid, String msgtopic, String msgcontents, String msgip, int theid, int state) {
	super();
	this.userid = userid;
	this.msgtopic = msgtopic;
	this.msgcontents = msgcontents;
	this.msgip = msgip;
	this.theid = theid;
	this.state = state;
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
public String getMsgtopic() {
	return msgtopic;
}
public void setMsgtopic(String msgtopic) {
	this.msgtopic = msgtopic;
}
public String getMsgcontents() {
	return msgcontents;
}
public void setMsgcontents(String msgcontents) {
	this.msgcontents = msgcontents;
}
public Timestamp getMsgtime() {
	return msgtime;
}
public void setMsgtime(Timestamp msgtime) {
	this.msgtime = msgtime;
}
public String getMsgip() {
	return msgip;
}
public void setMsgip(String msgip) {
	this.msgip = msgip;
}
public int getTheid() {
	return theid;
}
public void setTheid(int theid) {
	this.theid = theid;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
  
  
}
