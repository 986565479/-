package po;

import java.sql.Timestamp;

public class User {
   private int userid;
   private String username;
   private String password;
   private String realname;
   private String sex;
   private String hobbys;
   private String birthday;
   private String city;
   private String email;
   private String qq;
   private Timestamp createtime;
   private int state;
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
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
public String getHobbys() {
	return hobbys;
}
public void setHobbys(String hobbys) {
	this.hobbys = hobbys;
}
public String getBirthday() {
	return birthday;
}
public void setBirthday(String birthday) {
	this.birthday = birthday;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getQq() {
	return qq;
}
public void setQq(String qq) {
	this.qq = qq;
}
public Timestamp getCreatetime() {
	return createtime;
}
public void setCreatetime(Timestamp createtime) {
	this.createtime = createtime;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}

public User() {
	super();
}
public User(int userid,String username, String password, String realname, String sex, String hobbys, String birthday, String city,
		String email, String qq, Timestamp createtime, int state) {
	super();
	this.userid=userid;
	this.username = username;
	this.password = password;
	this.realname = realname;
	this.sex = sex;
	this.hobbys = hobbys;
	this.birthday = birthday;
	this.city = city;
	this.email = email;
	this.qq = qq;
	this.createtime = createtime;
	this.state = state;
}
public User(int userid, String realname, String sex, String hobbys, String birthday, String city, String email,
		String qq) {
	super();
	this.userid = userid;
	this.realname = realname;
	this.sex = sex;
	this.hobbys = hobbys;
	this.birthday = birthday;
	this.city = city;
	this.email = email;
	this.qq = qq;
}
   
}
