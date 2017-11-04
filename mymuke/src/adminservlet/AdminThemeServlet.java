package adminservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import po.Theme;
import po.Messages;
import po.User;
import service.IThemeService;
import serviceimpl.ThemeServiceImpl;
import util.IPUtil;
import util.Page;


@WebServlet("/AdminThemeServlet")
public class AdminThemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IThemeService iService = new ThemeServiceImpl();  
   
    public AdminThemeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if(action!=null&&action!=""){
			switch(action){
			   case "addTheme":
				   addTheme(request,response);break;
			   case "deleteTheme":
				   deleteTheme(request,response);break;
			   case "searchTheme":
				   searchTheme(request,response);break;
			
			}
		}
	
	}

	
	private void searchTheme(HttpServletRequest request, HttpServletResponse response) {
		
		String key = request.getParameter("key");
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null||pageNum.equals("")){
			pageNum="1";
		}
		Page page = new Page();
		page.setCurPage(Integer.parseInt(pageNum));
		page =iService.query(key, page);
		Gson gson = new GsonBuilder().setDateFormat("M/d").create();	
        String dataJSON = gson.toJson(page);	
        System.out.println(dataJSON);
	    response.setContentType("text/html;charset=utf-8");
	    try {
			response.getWriter().print("{\"res\":1, \"data\":"+dataJSON+"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void deleteTheme(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String theid = request.getParameter("theid");
		int res = 0;
		res = iService.delete(Integer.parseInt(theid));
		if(res>0){
			try {
				response.getWriter().print("{\"res\":1}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().print("{\"res\":-2}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	private void addTheme(HttpServletRequest request, HttpServletResponse response) {
	
		int count = 1;
		//String theid = request.getParameter("theid");
		String thename = request.getParameter("thename");
		System.out.println(thename);
	    int res = 0;
	    Theme theme = new Theme();
	    theme.setThename(thename);
	    theme.setCount(count);
		res = iService.add(theme);
		if(res>0){
			try {
				response.getWriter().print("{\"res\":1}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().print("{\"res\":-2}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
