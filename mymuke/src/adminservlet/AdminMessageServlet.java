package adminservlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import service.IMessageService;
import serviceimpl.MessageServiceImpl;
import util.DateUtil;
import util.Page;
import vo.MessageCriteria;


@WebServlet("/AdminMessageServlet")
public class AdminMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IMessageService iService = new MessageServiceImpl();   
   
    public AdminMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if(action!=null&&action!=""){
			switch(action){
			   case "getMsgCount":
				   getMsgCount(request,response);break;
			   case "serchMsg":
			    serchMsg(request,response);break;
			   case "deleteMsg":
				   deleteMsg(request,response);break;
			   case "restoreMsg":
				   restoreMsg(request,response);break;
			   case "toTop":
				   toTop(request,response);break;
			   case "wonderful":
				   wonderful(request,response);break;
			}
		}
	
	}

	
	private void wonderful(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String msgid = request.getParameter("msgid");
		int state = 5;
		int res = 0;
		res = iService.updateState(Integer.parseInt(msgid), state);
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


	private void toTop(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("222");
		String msgid = request.getParameter("msgid");
		int state = 10;
		int res = 0;
		res = iService.updateState(Integer.parseInt(msgid), state);
		response.setContentType("text/html;charset=utf-8");
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


	private void restoreMsg(HttpServletRequest request, HttpServletResponse response) {
		String msgid = request.getParameter("msgid");
		int state = 1;
		int res=0;
		res=iService.updateState(Integer.parseInt(msgid), state);
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


	private void deleteMsg(HttpServletRequest request, HttpServletResponse response) {
		String msgid = request.getParameter("msgid");
		int state = 0;
		int res=0;
		res=iService.updateState(Integer.parseInt(msgid), state);
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


	private void  serchMsg(HttpServletRequest request, HttpServletResponse response) {
		
		String key = request.getParameter("key");
		String name = request.getParameter("name");
		String thename = request.getParameter("thename");
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null||pageNum.equals("")){
			pageNum="1";
		}
		MessageCriteria mCriteria = new MessageCriteria(thename,key,name);
		Page page = new Page();
		page.setCurPage(Integer.parseInt(pageNum));
		page = iService.serch(mCriteria, page);
		Gson gson = new GsonBuilder().setDateFormat("M/d").create();	
        String dataJSON = gson.toJson(page);	
	    response.setContentType("text/html;charset=utf-8");
	    try {
			response.getWriter().print("{\"res\":1, \"data\":"+dataJSON+"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void getMsgCount(HttpServletRequest request, HttpServletResponse response) {
		Date date = new Date();
		Date startDate = DateUtil.getToday(date);
		Date endDate = DateUtil.getTomorrow(date);
		long counttoday = iService.queryCountByDate(startDate, endDate);
		startDate = DateUtil.getWeekAgo(date);
		endDate = DateUtil.getTomorrow(date);
		long countthisweek = iService.queryCountByDate(startDate, endDate);
		startDate = DateUtil.getMonthAgo(endDate);
		long countthismonth = iService.queryCountByDate(startDate, endDate);
		List count=new ArrayList();
		count.add(counttoday);
		count.add(countthisweek);
		count.add(countthismonth);
	    Gson gson = new Gson();
		String dataJSON = gson.toJson(count);
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
