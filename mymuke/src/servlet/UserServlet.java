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
		//��������
		request.setCharacterEncoding("utf-8");
		
		String action=request.getParameter("action");
		System.out.println(action);
		//���ò�ͬ����
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
		  	case "getuser"://��ȡ������Ϣ
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
		User user=(User)session.getAttribute("user");//��session��ȡ��user����
		int userid=user.getUserid();//��ȡid
		if(uService.uppassword(userid, newpassword,oldpassword)==1){//����޸�����ɹ�
			try {
				response.getWriter().print("{\"res\":1}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{//��������޸�ʧ��,����-1 ("{\"res\": -1, \"info\":\"���û����Ѵ��ڣ����������룡\"}");
			try {
				response.getWriter().print("{\"res\":-1,\"info\":\"�����������\"}");
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
		User user=(User)session.getAttribute("user");//��session��ȡ��user����
		int userid=user.getUserid();//��ȡid
        User  user2=new User(userid,realname,sex,hobbys,birthday,city,email,qq);
		if(uService.upuser(userid, user2)==1){//�޸ĳɹ�
			try {
				response.getWriter().print("{\"res\":1}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else{//�޸�ʧ��
			try {
				response.getWriter().print("{\"res\":-1,\"info\":\"�޸�ʧ��\"}");
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
				user=(User)session.getAttribute("user");//��session��ȡ��user����
				String username=user.getUsername();//��ȡ�û���
			
				User user2=uService.query(username);
				if(user!=null){//��ȡ�ɹ�
					Gson gson = new Gson();		// ����GSON����
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
			response.getWriter().print("{\"res\": -1, \"info\":\"�û������������\"}");
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
			response.getWriter().print("{\"res\": -1, \"info\":\"�û����Ѵ��ڣ�\"}");
		}
		if(result>0){
			
			response.getWriter().print("{\"res\": 1}");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
