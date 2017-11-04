package vo;

import java.sql.Timestamp;

public class ReplyInfo {
  private int replyid;
  private int magid;
  private String realname;
  private String sex;
  private String city;
  private String replycontents;
  private String replyip;
  private Timestamp replytime;
public ReplyInfo(int replyid, int magid, String realname, String sex, String city, String replycontents, String replyip,
		Timestamp replytime) {
	super();
	this.replyid = replyid;
	this.magid = magid;
	this.realname = realname;
	this.sex = sex;
	this.city = city;
	this.replycontents = replycontents;
	this.replyip = replyip;
	this.replytime = replytime;
}
public ReplyInfo() {
	super();
}
public int getReplyid() {
	return replyid;
}
public void setReplyid(int replyid) {
	this.replyid = replyid;
}
public int getMagid() {
	return magid;
}
public void setMagid(int magid) {
	this.magid = magid;
}
public String getRealname() {
	return realname;
}
public void getRealname(String realname) {
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
public String getReplycontents() {
	return replycontents;
}
public void setReplycontents(String replycontents) {
	this.replycontents = replycontents;
}
public String getReplyip() {
	return replyip;
}
public void setReplyip(String replyip) {
	this.replyip = replyip;
}
public Timestamp getReplytime() {
	return replytime;
}
public void setReplytime(Timestamp replytime) {
	this.replytime = replytime;
}
  
  
  
}
