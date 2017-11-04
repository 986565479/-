package adminservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import po.Admin;

import service.IAdminService;
import serviceimpl.AdminServiceImpl;


@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IAdminService iService =  new AdminServiceImpl();   
   
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
				request.setCharacterEncoding("utf-8");
				
				String action=request.getParameter("action");
				System.out.println(action);
			
				if(action!=null){
				  	switch(action){
				  	case "login":
				  		login(request,response);break;
				  	case "adminlogout":
				  		adminlogout(request,response);break;
				  	
				  	}
				}
			}



	
	private void adminlogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		session.removeAttribute("admin");
		try {
			
			response.getWriter().print(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String name=request.getParameter("username");
		String pwd=request.getParameter("password");
		Admin admin = iService.login(name, pwd);
		if(admin!=null){
			
			session.setAttribute("admin", admin);
			response.setContentType("text/html;charset=utf-8");
			response.sendRedirect("/mymuke/admin/index.jsp");
		}else{
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("用户名或密码错误");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
