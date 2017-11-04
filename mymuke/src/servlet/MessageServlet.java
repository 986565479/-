package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import po.Reply;
import po.User;
import po.Messages;
import service.IMessageService;
import service.IReplyService;
import serviceimpl.MessageServiceImpl;
import serviceimpl.ReplyServiceImpl;
import util.IPUtil;
import util.Page;
import vo.MessageInfo;


@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IMessageService iService=new MessageServiceImpl();  
    private IReplyService iReplyService = new ReplyServiceImpl();
    public MessageServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		System.out.println(action);
		if(action!=null&&action!=""){
		switch(action){
		  case "myMsg":
			  myMsg(request,response);
			  break;
		  case "topNew":
			  topNew(request,response);
			  break;
		  case "getMsg":
			  getMsg(request,response);break;
		  case "getReply":
			  getReply(request,response);break;
		  case "ReplyMsg":
			  ReplyMsg(request,response);break;
		  case "addMsg":
			  addMsg(request,response);break;
		  case "topHot":
			  topHot(request,response);break;
		  case "topTheme":
			  topTheme(request,response);break;
		}
	}
}
	
	private void topTheme(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String pageNum=request.getParameter("pageNum");//获取当前页
		if(pageNum==null||pageNum.equals("")){
			pageNum="1";
		}
		//设置分页信息
		Page page = new Page();
		page.setCurPage(Integer.parseInt(pageNum));
		//根据分页信息查询信息
		page=iService.queryTheme(page);
		
		Gson gson = new GsonBuilder().setDateFormat("M/d").create();	// 创建GSON对象
		String dataJSON = gson.toJson(page);	// 将page对象转换为JSON字符串
		System.out.print(dataJSON);
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().print("{\"res\":1, \"data\":"+dataJSON+"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void topHot(HttpServletRequest request, HttpServletResponse response) {
		String pageNum=request.getParameter("pageNum");//获取当前页
		if(pageNum==null||pageNum.equals("")){
			pageNum="1";
		}
		//设置分页信息
		Page page = new Page();
		page.setCurPage(Integer.parseInt(pageNum));
		//根据分页信息查询信息
		page=iService.queryHot(page);
		
		Gson gson = new GsonBuilder().setDateFormat("M/d").create();	// 创建GSON对象
		String dataJSON = gson.toJson(page);	// 将page对象转换为JSON字符串
		
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().print("{\"res\":1, \"data\":"+dataJSON+"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		


private void addMsg(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		User user=(User)request.getSession().getAttribute("user");
		int userid=0;
		if(user == null){
			try {
				response.getWriter().print("{\"res\":-1}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		userid=user.getUserid();
		int state = 1;
		String msgtopic = request.getParameter("msgtopic");
		String theid = request.getParameter("theid");
	    String msgcontents = request.getParameter("msgcontents");
		String msgip = IPUtil.getIP(request);
		Messages message = null;
        message = new Messages(userid,msgtopic,msgcontents,msgip,Integer.parseInt(theid),state);
		int res = 0;
		res = iService.addMsg(message);
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


	private void ReplyMsg(HttpServletRequest request, HttpServletResponse response) {
		
		//获取具体信息
		String msgid = request.getParameter("msgid");
		//获取回复者信息
		User user=(User)request.getSession().getAttribute("user");
		int userid=0;
		Reply reply=null;
		int res=0;
		userid=user.getUserid();
		//获取回复内容
		String replycontent = request.getParameter("replycontent");
		//获取ip
		String replyip=IPUtil.getIP(request);
		
	    reply = new Reply(0, Integer.parseInt(msgid), userid, replycontent,replyip);
	    res=iReplyService.replyMsg(reply);
	    if(res>0){
	    	try {
				response.getWriter().print("{\"res\":1}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
	}


	private void getReply(HttpServletRequest request, HttpServletResponse response) {
		String msgid = request.getParameter("msgid");
		//System.out.println(msgid);
		String pageNum=request.getParameter("pageNum");//获取当前页
		if(pageNum==null||pageNum.equals("")){
			pageNum="1";
		}
		//设置分页信息
		Page page = new Page();
		page.setCurPage(Integer.parseInt(pageNum));
		//调用service层
		page=iService.getReply(Integer.parseInt(msgid), page);
		Gson gson = new GsonBuilder().setDateFormat("M/d").create();	// 创建GSON对象
		String dataJSON = gson.toJson(page);	// 将page对象转换为JSON字符串
		//System.out.print(dataJSON);
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().print("{\"res\":1, \"data\":"+dataJSON+"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void getMsg(HttpServletRequest request, HttpServletResponse response) {
		String msgid = request.getParameter("msgid");
		MessageInfo info = null;
	    info = iService.get(Integer.parseInt(msgid));
	    Gson gson = new GsonBuilder().setDateFormat("M/d").create();	// 创建GSON对象
		String dataJSON = gson.toJson(info);	// 将page对象转换为JSON字符串
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().print("{\"res\":1, \"data\":"+dataJSON+"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void topNew(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pageNum=request.getParameter("pageNum");//获取当前页
		if(pageNum==null||pageNum.equals("")){
			pageNum="1";
		}
		//设置分页信息
		Page page = new Page();
		page.setCurPage(Integer.parseInt(pageNum));
		//根据分页信息查询信息
		page=iService.queryNew(page);
		
		Gson gson = new GsonBuilder().setDateFormat("M/d").create();	// 创建GSON对象
		String dataJSON = gson.toJson(page);	// 将page对象转换为JSON字符串
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print("{\"res\":1, \"data\":"+dataJSON+"}");
	}


	private void myMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user=(User)request.getSession().getAttribute("user");
		int userid=0;
		userid=user.getUserid();
		String pageNum=request.getParameter("pageNum");//获取当前页
		if(pageNum==null||pageNum.equals("")){
			pageNum="1";
		}
		//设置分页信息
		Page page = new Page();
		page.setCurPage(Integer.parseInt(pageNum));
		//根据分页信息查询信息
		page=iService.MyMsg(userid, page);
		Gson gson = new GsonBuilder().setDateFormat("M/d").create();		// 创建GSON对象
		String dataJSON = gson.toJson(page);	// 将page对象转换为JSON字符串
		response.setContentType("text/html;charset=utf-8");
		System.out.println(dataJSON);
		
		response.getWriter().print("{\"res\":1, \"data\":"+dataJSON+"}");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
