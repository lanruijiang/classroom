package org.soft.base.ctrl.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soft.base.ctrl.dao.AdminDao;
import org.soft.base.ctrl.factory.FactoryServer;
import org.soft.base.ctrl.impl.AdminDaoImplements;
import org.soft.base.model.Admin;

@WebServlet("/adminserver")
public class AdminServer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// 判断操作的类型
	private String op = null;
	// 页面跳转的路劲
	private String path = null;
	
	//获取工厂
	private FactoryServer factoryServer = FactoryServer.instance();
	//通过工厂获取AdminDao接口
	private AdminDao adminDao = factoryServer.AdminDaoInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		op = request.getParameter("op");
		if (op == "login" || "login".equals(op)) {
			this.adminLoginServer(request, response);
		}else if(op == "adminCreate" || "adminCreate".equals(op)) {
			this.adminCreateServer(request, response);
		}else if(op == "logOut" || "logOut".equals(op)) {
			this.adminlogOut(request, response);
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void adminLoginServer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminName = request.getParameter("adminName");
		String adminPass = request.getParameter("adminPass");
		
		Admin admin = new Admin(0, adminName, adminPass, null);
		admin = adminDao.adminLogin(admin);
		if(admin != null) {
			request.getSession().setAttribute("admin", admin);
			path ="/main/main.jsp";
		}else {
			path = "/admin/adminLogin.jsp";
		}
	}
	
	
	protected void adminCreateServer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminName = request.getParameter("adminName");
		String adminPass = request.getParameter("adminPass");
		String adminDesc = request.getParameter("adminDesc");
		Admin admin = new Admin(0, adminName, adminPass, adminDesc);
		boolean b = adminDao.adminCreate(admin);
		if(b) {
			path ="/msg.jsp?msg=creareOk";
		}else {
			path = "/msg.jsp?msg=creareError";
		}
		
	}
	
	protected void adminlogOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("admin");
		path = "/main/main.jsp";
	}
	

}
