package po;

public class Admin {
   private int id;
   private String name;
   private String pwd;
   private int authority;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public int getAuthority() {
	return authority;
}
public void setAuthority(int authority) {
	this.authority = authority;
}
public Admin() {
	super();
}
public Admin(String name, String pwd) {
	super();
	this.name = name;
	this.pwd = pwd;
}
   
}
