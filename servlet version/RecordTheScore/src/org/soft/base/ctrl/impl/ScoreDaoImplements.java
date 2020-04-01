package org.soft.base.ctrl.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.soft.base.ctrl.dao.ScoreDao;
import org.soft.base.ctrl.db.CRUDDB;
import org.soft.base.model.Score;

public class ScoreDaoImplements extends CRUDDB implements ScoreDao {

	@Override
	public boolean scoreAppend(int num , int scoreId) {

		String sql = "update score set scoreNumber= scoreNumber+? where scoreId=?";
		Object[] objects = { num, scoreId };
		boolean b = executionUpdate(sql, objects);
		return b;
	}

	@Override
	public boolean scoreReduce(int num , int scoreId) {
		String sql = "update score set scoreNumber= scoreNumber-? where scoreId=?";
		Object[] objects = { num, scoreId };
		boolean b = executionUpdate(sql, objects);
		return b;
	}

	@Override
	public Score scorebyStudentId(int studentId) {
		String sql ="select sc.*  , st.studentName from score sc inner join student st on sc.studentId=? and sc.studentId = st.studentId";
		Object[] objects = {studentId};
		ResultSet rs = executiongQuery(sql, objects);
		Score score = null;
		try {
			while(rs.next()) {
				int scoreNumber = rs.getInt("scroeNumber");
				int classesId = rs.getInt("classesId");
				int scoreId = rs.getInt("scoreId");
				String studentName = rs.getString("studentName");
			    score = new Score(scoreId, scoreNumber, classesId, studentId, studentName);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
		}	
		return score;
	}

	@Override
	public List<Score> scoreRanking(int classesId) {
		// select * from student where classesId =?
		String sql = "select sc.* , st.studentName from score sc inner join student st on sc.studentId = st.studentId and sc.classesId = ? order by sc.scoreNumber DESC";
		Object[] objects = { classesId };
		ResultSet rs = executiongQuery(sql, objects);
		Score score = null;
		List<Score> scores = new ArrayList<Score>();
		try {
			while (rs.next()) {
				int scoreNumber = rs.getInt("scoreNumber");
				int studentId = rs.getInt("studentId");
				int scoreId = rs.getInt("scoreId");
				String studentName = rs.getString("studentName");
				score = new Score(scoreId, scoreNumber, classesId, studentId, studentName);
				scores.add(score);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
		}
		return scores;
	}

	@Override
	public boolean scoreCreate(Score score) {
		String sql = "insert into score (scoreNumber,classesId,studentId) value (0 , ?,?)";
		int classesId = score.getClassesId();
		int studentId = score.getStudentId();
		Object[] objects = {classesId,studentId};
		boolean b = executionUpdate(sql, objects);
		return b;
	}

	@Override
	public List<Score> scoreRankingShowPage(ResultSet rs) {
		List<Score> scores = new ArrayList<Score>();
		try {
			while (rs.next()) {
				int scoreNumber = rs.getInt("scoreNumber");
				int studentId = rs.getInt("studentId");
				int scoreId = rs.getInt("scoreId");
				String studentName = rs.getString("studentName");
				int classesId = rs.getInt("classesId");
				Score score = new Score(scoreId, scoreNumber, classesId, studentId, studentName);
				scores.add(score);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
		}
		return scores;
	}

}
