package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.google.gson.Gson;

import po.User;
import service.IUserService;
import serviceimpl.UserServiceImpl;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserService uService=new UserServiceImpl();  
   
    public UserServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//动作类型
		request.setCharacterEncoding("utf-8");
		
		String action=request.getParameter("action");
		System.out.println(action);
		//调用不同方法
		if(action!=null){
		  	switch(action){
		  	case "register":
		  		register(request,response);break;
		  	case "namevalidate":
		  		namevalidate(request,response);break;
		  	case "login":
		  		login(request,response);break;
		  	case "logout":
		  		logout(request,response);break;
		  	case "updateuser":
		  		updateuser(request,response);break;
		  	case "updatePW":
		  		updatePW(request,response);break;
		  	case "getuser"://获取个人信息
				getuser(request, response);
				break;
		  	}
		}
	}

	private void updatePW(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.setContentType("text/html;charSet=utf-8");
		String oldpassword=request.getParameter("oldpassword");
		String newpassword=request.getParameter("newpassword");
		
		HttpSession session =request.getSession(false);
		User user=(User)session.getAttribute("user");//从session中取得user对象
		int userid=user.getUserid();//获取id
		if(uService.uppassword(userid, newpassword,oldpassword)==1){//如果修改密码成功
			try {
				response.getWriter().print("{\"res\":1}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{//如果密码修改失败,返回-1 ("{\"res\": -1, \"info\":\"该用户名已存在，请重新输入！\"}");
			try {
				response.getWriter().print("{\"res\":-1,\"info\":\"密码输入错误\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}


	private void updateuser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        response.setContentType("text/html;charSet=utf-8");
   
        String realname=request.getParameter("realname");
        String sex=request.getParameter("sex");
        String hobbys=request.getParameter("hobbys");
        String birthday=request.getParameter("birthday");
        String city=request.getParameter("city");
        String email=request.getParameter("email");
        String qq=request.getParameter("qq");
        System.out.println(sex);
        HttpSession session =request.getSession(false);
		User user=(User)session.getAttribute("user");//从session中取得user对象
		int userid=user.getUserid();//获取id
        User  user2=new User(userid,realname,sex,hobbys,birthday,city,email,qq);
		if(uService.upuser(userid, user2)==1){//修改成功
			try {
				response.getWriter().print("{\"res\":1}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else{//修改失败
			try {
				response.getWriter().print("{\"res\":-1,\"info\":\"修改失败\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	private void getuser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
				HttpSession session =request.getSession(false);
				User user =null;
				user=(User)session.getAttribute("user");//从session中取得user对象
				String username=user.getUsername();//获取用户名
			
				User user2=uService.query(username);
				if(user!=null){//获取成功
					Gson gson = new Gson();		// 创建GSON对象
					String dataJSON = gson.toJson(user2);
					response.getWriter().print("{\"res\":1,\"user\":"+dataJSON+"}");
					//System.out.println(dataJSON);
				}
			}
			
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		session.removeAttribute("user");
		try {
			
			response.getWriter().print(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user = uService.login(username, password);
		if(user!=null){
			
			session.setAttribute("user", user);
			
			response.getWriter().print("{\"res\": 1}");
		}else{
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("{\"res\": -1, \"info\":\"用户名或密码错误！\"}");
		}
	}


	private void namevalidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		if(!uService.namevalidate(username)){
			response.getWriter().print("{\"message\": 0}");
		}
	}


	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String realname=request.getParameter("realname");
		String sex=request.getParameter("sex");
		String hobbys=request.getParameter("hobbys");
		String birthday=request.getParameter("birthday");
		String city=request.getParameter("city");
		String email=request.getParameter("email");
		String qq=request.getParameter("qq");
		long time=System.currentTimeMillis();
		Timestamp createtime=new Timestamp(time);
		int state=1;
		User user=new User(0,username, password, realname, sex, hobbys, birthday, city, email, qq, createtime, state);
		int result=uService.UserResgister(user);
		
		if(result==0){
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("{\"res\": -1, \"info\":\"用户名已存在！\"}");
		}
		if(result>0){
			
			response.getWriter().print("{\"res\": 1}");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
