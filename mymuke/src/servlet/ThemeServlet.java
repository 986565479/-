package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.IThemeService;
import serviceimpl.ThemeServiceImpl;

/**
 * Servlet implementation class ThemeServlet
 */
@WebServlet("/ThemeServlet")
public class ThemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IThemeService iTheService = new ThemeServiceImpl();
    public ThemeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if(action!=null&&action!=""){
			switch(action){
			   case "getTheme":
				   getTheme(request,response);
			}
		}
	}

	
	private void getTheme(HttpServletRequest request, HttpServletResponse response) {
		 List list = null;
		 list = iTheService.getAll();
		 Gson gson = new Gson();
		 String dataJSON = gson.toJson(list);
		 System.out.println(dataJSON);
		 response.setContentType("text/html;charset=utf-8");
		 try {
			response.getWriter().print("{\"res\":1, \"data\":"+dataJSON+"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
