package adminservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import service.IUserService;
import serviceimpl.UserServiceImpl;
import util.Page;
import vo.MessageCriteria;

/**
 * Servlet implementation class AdminUserServlet
 */
@WebServlet("/AdminUserServlet")
public class AdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserService service = new UserServiceImpl();  
  
    public AdminUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if(action!=null&&action!=""){
			switch(action){
			   case "getUser":
				   getUser(request,response);break;
			   case "deleteUser":
				   dateteUser(request,response);break;
			   case "restoreUser":
				   restoreUser(request,response);break;
			
			}
		}
	
	}

	
	private void restoreUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userid = request.getParameter("userid");
		int state = 1;
		int res=0;
		res=service.updateState(Integer.parseInt(userid), state);
		response.setContentType("text/html;charset=utf-8");
		if(res>0){
			try {
				response.getWriter().print("{\"res\":1}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void dateteUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userid = request.getParameter("userid");
		//System.out.println(userid);
		int state = 0;
		int res=0;
		res=service.updateState(Integer.parseInt(userid), state);
		response.setContentType("text/html;charset=utf-8");
		if(res>0){
			try {
				response.getWriter().print("{\"res\":1}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void getUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("username");
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null||pageNum.equals("")){
			pageNum="1";
		}
		Page page = new Page();
		page.setCurPage(Integer.parseInt(pageNum));
		page = service.queryByName(username, page);
		Gson gson = new GsonBuilder().setDateFormat("M/d").create();	
        String dataJSON = gson.toJson(page);	
        //System.out.println(dataJSON);
	    response.setContentType("text/html;charset=utf-8");
	    try {
			response.getWriter().print("{\"res\":1, \"data\":"+dataJSON+"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
