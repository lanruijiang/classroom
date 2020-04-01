package org.soft.base.ctrl.server;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soft.base.ctrl.dao.ScoreDao;
import org.soft.base.ctrl.dao.SplitPageDao;
import org.soft.base.ctrl.factory.FactoryServer;
import org.soft.base.ctrl.impl.ScoreDaoImplements;
import org.soft.base.model.Score;
import org.soft.base.model.Student;

@WebServlet("/scoreServer")
public class ScoreServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String op;
	private String path;
	
	//获取工厂
	private FactoryServer factoryServer = FactoryServer.instance();
	private ScoreDao scoreDao = factoryServer.scoreDaoInstance();
	private SplitPageDao splitPageDao = factoryServer.splitPageDaoInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		op = request.getParameter("op");
		if (op == "scoreAppend" || "scoreAppend".equals(op)) {
			this.scoreAppendServer(request, response);
		} else if (op == "scoreReduce" || "scoreReduce".equals(op)) {
			this.scoreReduceServer(request, response);
		} else if (op == "scorebyStudentId" || "scorebyStudentId".equals(op)) {
			this.scorebyStudentIdServer(request, response);
		} else if (op == "scoreRanking" || "scoreRanking".equals(op)) {
			this.scoreRankingServer(request, response);
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void scoreAppendServer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int scoreId = Integer.parseInt(request.getParameter("scoreId"));
		int num = Integer.parseInt(request.getParameter("num"));
		int classesId = Integer.parseInt(request.getParameter("classesId"));
		
		boolean b = scoreDao.scoreAppend(num, scoreId);
		if (b) {
			path = "/scoreServer?op=scoreRanking&classesId=" + classesId;
		} else {
			path = "/main/appendError.jsp";
		}
	}

	protected void scoreReduceServer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int scoreId = Integer.parseInt(request.getParameter("scoreId"));
		int num = Integer.parseInt(request.getParameter("num"));
		int classesId = Integer.parseInt(request.getParameter("classesId"));
		
		boolean b = scoreDao.scoreReduce(num, scoreId);
		if (b) {
			path = "/scoreServer?op=scoreRanking&classesId=" + classesId;
		} else {
			path = "/main/reduceError.jsp";
		}
	}

	protected void scorebyStudentIdServer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		
		Score score = scoreDao.scorebyStudentId(studentId);
		if (score != null) {
			request.setAttribute("score", score);

		} else {

		}
	}

	protected void scoreRankingServer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String currentPageNumValue = request.getParameter("currentPageNum");
		int size = 10;
		int currentPageNum = 0;
		if (currentPageNumValue == null || currentPageNumValue == "" || "".equals(currentPageNumValue)) {
			currentPageNum = 1;
		} else {
			currentPageNum = Integer.parseInt(currentPageNumValue);
		}

		int classesId = Integer.parseInt(request.getParameter("classesId"));
		String queryTotalRowSql = "select count(*) from student where classesId = " + classesId;
		String sql = "select sc.* , st.studentName from score sc inner join student st on sc.studentId = st.studentId and sc.classesId = ? order by sc.scoreNumber DESC limit ?,?";
		// 求总行数
		int totalRows = splitPageDao.totalRow(queryTotalRowSql);
		// 求总页数
		int totalPages = splitPageDao.totalPage(totalRows, size);
		
		// 数据库中查询的初始行数
		int currentRow = splitPageDao.currentRow(currentPageNum, size);
		Object[] objects = { classesId, currentRow, size };
		ResultSet rs = splitPageDao.currentPageContent(objects, sql);

		List<Score> scores = scoreDao.scoreRankingShowPage(rs);
		request.setAttribute("totalPages", totalPages);
		if (scores.size() > 0) {
			request.setAttribute("scoreRankinges", scores);
			path = "/score/showStudentScoreRanking.jsp";
		} else {
			path = "/score/showStudentScoreRanking.jsp?msg=empty";
		}

	}
}
