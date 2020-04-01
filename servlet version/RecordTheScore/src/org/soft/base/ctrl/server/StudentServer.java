package org.soft.base.ctrl.server;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soft.base.ctrl.dao.ClassesDao;
import org.soft.base.ctrl.dao.SplitPageDao;
import org.soft.base.ctrl.dao.StudentDao;
import org.soft.base.ctrl.factory.FactoryServer;
import org.soft.base.ctrl.impl.ClassesDaoImplements;
import org.soft.base.ctrl.impl.StudentDaoImplements;
import org.soft.base.model.Classes;
import org.soft.base.model.Student;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;


@WebServlet("/studentServer")
public class StudentServer extends HttpServlet {
	private String op = null;
	private String path = null;

	// 获取工厂
	private FactoryServer factoryServer = FactoryServer.instance();

	private StudentDao studentDao = factoryServer.studentDaoInstance();

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
		if (op == "studentCreate" || "studentCreate".equals(op)) {
			this.studentCreasteServer(request, response);
		} else if (op == "studentQueryByid" || "studentQueryByid".equals(op)) {
			this.studentQueryByid(request, response);
		} else if (op == "studentQueryByClassesId" || "studentQueryByClassesId".equals(op)) {
			this.studentQueryByClassesId(request, response);
		} else if (op == "studentDelete" || "studentDelete".equals(op)) {
			this.studentDelete(request, response);
		} else if (op == "studentUpdate" || "studentUpdate".equals(op)) {
			this.studentUpdate(request, response);
		} else if (op == "studentQueryByName" || "studentQueryByName".equals(op)) {
			this.studentQueryByName(request, response);
		} else if (op == "studentUpdateHeadImage" || "studentUpdateHeadImage".equals(op)) {
			this.studentUpdateHeadImage(request, response);

		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * 创建学生信息server
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void studentCreasteServer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SmartUpload smartUpload = new SmartUpload();
		smartUpload.initialize(getServletConfig(), request, response);
		// 当使用二进制流的 形式传递参数到servlet中，需要使用插件提供的Request
		Request smartRequest = smartUpload.getRequest();
		String fileName = null;
		try {
			smartUpload.upload();
			Files files = smartUpload.getFiles();
			File file = files.getFile(0);
			fileName = file.getFileName();
			String uuid = UUID.randomUUID().toString();
			fileName = uuid + fileName;
			String path = "D:\\code\\data\\image\\RecordTheScore\\";
			file.saveAs(path + fileName);
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}

		String studentName = smartRequest.getParameter("studentName");
		String studentGender = smartRequest.getParameter("studentGender");
		String studentAddr = smartRequest.getParameter("studentAddr");
		String studentEmail = smartRequest.getParameter("studentEmail");
		String studentDescribe = smartRequest.getParameter("studentDescribe");
		String studentHeadImage = fileName;
		String studentPhone = smartRequest.getParameter("studentPhone");

		int classesId = Integer.parseInt(smartRequest.getParameter("classesId"));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date studentBirthday = null;
		try {
			studentBirthday = simpleDateFormat.parse(smartRequest.getParameter("studentBirthday"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Student student = new Student(0, studentName, studentEmail, studentPhone, studentAddr, studentGender,
				studentHeadImage, studentDescribe, studentBirthday, classesId);

		System.out.println("studentName = " + studentName);
		boolean b = studentDao.studentCreate(student);
		if (b) {
			path = "/studentServer?op=studentQueryByClassesId&classesId=" + classesId;
		} else {
			path = "/student/studentCreateError.jsp";
		}
	}

	protected void studentQueryByid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int studentId = Integer.parseInt(request.getParameter("studentId"));

		Student student = studentDao.studentById(studentId);
		ClassesDao classesDao = new ClassesDaoImplements();
		List<Classes> classeslist = classesDao.classesShowAll();
		if (student != null) {
			request.setAttribute("classeslist", classeslist);
			request.setAttribute("student", student);
			String type = request.getParameter("type");
			if (type == "show" || "show".equals(type)) {
				path = "/student/studentShowInfo.jsp";
			} else if (type == "updateHeadImage" || "updateHeadImage".equals(type)) {
				path = "/student/studentShowHeadImage.jsp";
			} else {
				path = "/student/studentShowbyId.jsp";
			}

		} else {
			path = "/student/studentEorror.jsp";
		}

	}

	/**
	 * 通过班级查询学生信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void studentQueryByClassesId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String currentPageNumValue = request.getParameter("currentPageNum");
		int size = 5;
		int currentPageNum = 0;
		if (currentPageNumValue == null || currentPageNumValue == "" || "".equals(currentPageNumValue)) {
			currentPageNum = 1;
		} else {
			currentPageNum = Integer.parseInt(currentPageNumValue);
		}

		int classesId = Integer.parseInt(request.getParameter("classesId"));
		String queryTotalRowSql = "select count(*) from student where classesId = " + classesId;
		String sql = "select * from student where classesId = ? limit ?,?";
		// 求总行数
		int totalRows = splitPageDao.totalRow(queryTotalRowSql);
		// 求总页数
		int totalPages = splitPageDao.totalPage(totalRows, size);
		
		// 数据库中查询的初始行数
		int currentRow = splitPageDao.currentRow(currentPageNum, size);
		Object[] objects = { classesId, currentRow, size };
		ResultSet rs = splitPageDao.currentPageContent(objects, sql);

		List<Student> studentList = studentDao.studentShowPage(rs);
		request.setAttribute("totalPages", totalPages);
		
		if (studentList.size() > 0) {
			request.setAttribute("studentList", studentList);	
			path = "/student/showStudentToClasses.jsp";
		} else {
			path = "/student/showStudentToClasses.jsp?msg=empty";
		}

	}

	protected void studentQueryByName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String studentName = request.getParameter("studentName");

		List<Student> studentList = studentDao.studentByName(studentName);
		if (studentList.size() > 0) {
			request.setAttribute("studentList", studentList);
			path = "/student/showStudentToClasses.jsp";
		} else {
			path = "/student/showStudentToClasses.jsp?msg=empty";
		}
	}

	protected void studentDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		int classesId = Integer.parseInt(request.getParameter("classesId"));

		boolean b = studentDao.studentDelete(studentId);
		if (b) {
			path = "/studentServer?op=studentQueryByClassesId&classesId=" + classesId;
		} else {
			path = "/student/studentEorror.jsp";

		}
	}

	protected void studentUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		int classesId = Integer.parseInt(request.getParameter("classesId"));
		String studentName = request.getParameter("studentName");
		String studentEmail = request.getParameter("studentEmail");
		String studentPhone = request.getParameter("studentPhone");
		String studentAddr = request.getParameter("studentAddr");
		String studentDescribe = request.getParameter("studentDescribe");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date studentBirthday = null;
		try {
			studentBirthday = simpleDateFormat.parse(request.getParameter("studentBirthday"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Student student = new Student(studentId, studentName, studentEmail, studentPhone, studentAddr, null, null,
				studentDescribe, studentBirthday, classesId);

		boolean b = studentDao.studentUpdate(student);
		if (b) {
			path = "/studentServer?op=studentQueryByClassesId&classesId=" + classesId;
		} else {
			path = "/student/studentEorror.jsp";
		}
	}

	protected void studentUpdateHeadImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SmartUpload smartUpload = new SmartUpload();
		smartUpload.initialize(getServletConfig(), request, response);
		Request smartRequest = smartUpload.getRequest();

		String filePath = "D:\\code\\data\\image\\RecordTheScore\\";
		// 获取就图片路径

		String fileName = null;
		try {
			smartUpload.upload();

			String oldImageName = smartRequest.getParameter("oldImageName");
			java.io.File oldFile = new java.io.File(filePath + oldImageName);
			if (oldFile.exists()) {
				boolean b = oldFile.delete();
			}

			Files files = smartUpload.getFiles();
			File file = files.getFile(0);
			fileName = UUID.randomUUID().toString() + file.getFileName();

			file.saveAs(filePath + fileName);
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}

		int studentId = Integer.parseInt(smartRequest.getParameter("studentId"));
		Student student = new Student(studentId, null, null, null, null, null, fileName, null, null, 0);
		boolean b = studentDao.studentUpdateHeadImage(student);
		if (b) {
			path = "/studentServer?op=studentQueryByid&type=show&studentId=" + studentId;
		} else {
			path = "/student/studentEorror.jsp";
		}

	}
}