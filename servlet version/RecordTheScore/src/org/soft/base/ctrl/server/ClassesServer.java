package org.soft.base.ctrl.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;
import org.soft.base.ctrl.dao.ClassesDao;
import org.soft.base.ctrl.dao.SplitPageDao;
import org.soft.base.ctrl.factory.FactoryServer;
import org.soft.base.ctrl.impl.ClassesDaoImplements;
import org.soft.base.model.Admin;
import org.soft.base.model.Classes;

@WebServlet("/classesServer")
public class ClassesServer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// 判断操作的类型
	private String op = null;
	// 页面跳转的路劲
	private String path = null;

	// 获取工厂
	private FactoryServer factoryServer = FactoryServer.instance();
	// 通过工厂获取ClassesDao接口
	private ClassesDao classesDao = factoryServer.classesDaoInstance();
	// 通过工厂获取splitPageDao接口
	private SplitPageDao splitPageDao = factoryServer.splitPageDaoInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		op = request.getParameter("op");
		if (op == "classesCreate" || "classesCreate".equals(op)) {
			this.classesCreasteServer(request, response);
		} else if (op == "classesQueryById" || "classesQueryById".equals(op)) {
			this.classesQuerybyIdServer(request, response);
		} else if (op == "classesQueryAll" || "classesQueryAll".equals(op)) {
			this.classesQueryAllServer(request, response);
		} else if (op == "classesUpdate" || "classesUpdate".equals(op)) {
			this.classesUpdateServer(request, response);
		} else if (op == "classesQueryAlltoCreateStudent" || "classesQueryAlltoCreateStudent".equals(op)) {
			this.classesQueryAllServerToCreateStudent(request, response);
		} else if (op == "studentToSearchPage" || "studentToSearchPage".equals(op)) {
			this.classesQueryAllServerToStudentSeachPage(request, response);
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void classesCreasteServer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String classesName = request.getParameter("classesName");
		String classesDesc = request.getParameter("classesDesc");

		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int adminId = admin.getAdminId();
		Classes classes = new Classes(0, classesName, null, classesDesc, adminId);

		boolean b = classesDao.classesCreate(classes);
		if (b) {
			path = "/classesServer?op=classesQueryAll";
		} else {
			path = "/msg.jsp?msg=createClassesError";
		}
	}

	protected void classesQuerybyIdServer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int classesId = Integer.parseInt(request.getParameter("classesId"));

		Classes classes = classesDao.classesShowById(classesId);
		if (classes != null) {
			request.setAttribute("classes", classes);
			path = "/classes/classesShowbyId.jsp";
		} else {
			path = "/main/main.jsp";
		}

	}

	protected void classesQueryAllServer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String currentPageNumValue = request.getParameter("currentPageNum");
		String type = request.getParameter("type");
		int size = 5;
		int currentPageNum = 0;
		if (currentPageNumValue == null || currentPageNumValue == "" || "".equals(currentPageNumValue)) {
			currentPageNum = 1;

		} else {
			currentPageNum = Integer.parseInt(currentPageNumValue);
		}

		String queryTotalRowSql = null;
		// 数据库中查询的初始行数
		int currentRow = splitPageDao.currentRow(currentPageNum, size);
		String sql = null;
		Object[] objects = null;
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int adminId = admin.getAdminId();
		if (type == "adminOwner" || "adminOwner".equals(type)) {
			
			queryTotalRowSql = "select count(*) from classes where adminId = " + adminId;
			
			sql = "SELECT * FROM classes WHERE adminId = ? ORDER BY classesCreateTime DESC LIMIT ?,?";
			objects = new Object[]{adminId,currentRow,size};
			path = "/classes/classesShowAll.jsp?flag=1";
		} else if(type == "showScorePage" || "showScorePage".equals(type)){
			
			queryTotalRowSql = "select count(*) from classes where adminId = " + adminId;
			sql = "SELECT * FROM classes WHERE adminId = ? ORDER BY classesCreateTime DESC LIMIT ?,?";
			objects = new Object[]{adminId,currentRow,size};
			path = "/score/classesShowAllByAdminId.jsp";
			
		}else {
			sql = "select * from classes limit ?,?";
			queryTotalRowSql = "select count(*) from classes";
			objects = new Object[]{currentRow,size};
			path = "/classes/classesShowAll.jsp";
		}

	
		ResultSet rs = splitPageDao.currentPageContent(objects, sql);

		// 每页显示的班级信息
		List<Classes> classeslist = classesDao.classesShowPage(rs);

		request.setAttribute("classeslist", classeslist);
		

		// 求总行数
		
		int totalRows = splitPageDao.totalRow(queryTotalRowSql);

		// 求总页数
		int totalPages = splitPageDao.totalPage(totalRows, size);

		request.setAttribute("totalPages", totalPages);


		
		

	}

	protected void classesQueryAllServerToStudentSeachPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Classes> classeslist = classesDao.classesShowAll();
		if (classeslist.size() > 0) {
			request.setAttribute("classeslist", classeslist);
			path = "/student/studentQuery.jsp";
		} else {
			path = "/student/studentQuery.jsp?msg=empty";
		}

	}

	protected void classesQueryAllServerToCreateStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Classes> classeslist = classesDao.classesShowAll();
		if (classeslist.size() > 0) {
			request.setAttribute("classeslist", classeslist);
			path = "/student/studentCreate.jsp";
		} else {
			path = "/student/studentCreate.jsp?msg=empty";
		}

	}

//	protected void classesQuerybyAdminIdServer(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		Admin admin = (Admin) request.getSession().getAttribute("admin");
//		int adminId = admin.getAdminId();
//
//		
//		// 每页显示的班级信息
//		List<Classes> classeslist = classesDao.classesShowToAdmin(adminId);
//
//		request.setAttribute("classeslist", classeslist);
//
//		String showType = request.getParameter("showType");
//
//		if (classeslist.size() > 0) {
//			request.setAttribute("classeslist", classeslist);
//			if (showType == "showScorePage" || "showScorePage".equals(showType)) {
//				path = "/score/classesShowAllByAdminId.jsp";
//			} else {
//				path = "/classes/classesShowAll.jsp?flag=1";
//			}
//		} else {
//			if (showType == "showScorePage" || "showScorePage".equals(showType)) {
//				
//				path = "/score/classesShowAllByAdminId.jsp?msg=empty";
//			} else {
//				path = "/classes/classesShowAll.jsp?msg=empty&flag=1";
//			}
//		}
//	}

	protected void classesUpdateServer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String classesName = request.getParameter("classesName");
		String classesDesc = request.getParameter("classesDesc");
		int classesId = Integer.parseInt(request.getParameter("classesId"));
		Classes classes = new Classes(classesId, classesName, null, classesDesc, 0);

		boolean b = classesDao.classesUpdate(classes);
		if (b) {
			path = "/classesServer?op=classesQueryByAdminId";
		} else {
			path = "/admin/ClassesUpdateError.jsp";
		}

		// json  ajax 
	}
}
