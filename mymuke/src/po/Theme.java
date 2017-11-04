package po;

public class Theme {
   private int theid;
   private String thename;
   private int count;
public Theme(int theid, String thename) {
	super();
	this.theid = theid;
	this.thename = thename;
}
public Theme() {
	super();
}
public int getTheid() {
	return theid;
}
public void setTheid(int theid) {
	this.theid = theid;
}
public String getThename() {
	return thename;
}
public void setThename(String thename) {
	this.thename = thename;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
   
}
