package vo;

import java.sql.Timestamp;

public class MessageInfo {
   private  int msgid;
   private String msgtopic;
   private int theid;
   private String thename;
   private String realname;
   private String sex;
   private String city;
   private String msgcontents;
   private String msgip;
   private Timestamp msgtime;
public MessageInfo() {
	super();
}
public MessageInfo(int msgid, String msgtopic, String thename,int theid, String realname, String sex, String city,
		String msgcontents, String msgip,Timestamp msgtime) {
	super();
	this.msgid = msgid;
	this.msgtopic = msgtopic;
	this.theid = theid;
	this.thename = thename;
	this.realname = realname;
	this.sex = sex;
	this.city = city;
	this.msgcontents = msgcontents;
	this.msgip=msgip;
	this.msgtime = msgtime;
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
public String getThename() {
	return thename;
}
public void setThename(String thename) {
	this.thename = thename;
}
public String getRealname() {
	return realname;
}
public void setRealname(String realname) {
	this.realname = realname;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
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
   
}
