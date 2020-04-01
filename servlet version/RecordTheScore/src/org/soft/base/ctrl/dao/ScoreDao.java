package org.soft.base.ctrl.dao;

import java.sql.ResultSet;
import java.util.List;

import org.soft.base.model.Score;

/**
 * 	计分管理
 * @author Administrator
 *
 */
public interface ScoreDao {

	/**
	 * 	加分
	 * @param studentId
	 * @return
	 */
	public boolean scoreAppend(int num , int scoreId);
	
	/**
	 * 	减分
	 * @param studentId
	 * @return
	 */
	public boolean scoreReduce(int num , int scoreId);
	
	/**
	 * 	查看指定学生分数
	 * @param studentId
	 * @return
	 */
	public Score scorebyStudentId(int studentId);
	
	/**
	 *	 班级分数排行
	 * @param classesId
	 * @return
	 */
	List<Score>  scoreRanking(int classesId);
	
	/**
	 * 	创建用户时创建分数，默认分数为0
	 * @param score
	 * @return
	 */
	public boolean scoreCreate(Score score);
	
	/**
	 * 每页显示的信息
	 * @param rs
	 * @return
	 */
	List<Score>  scoreRankingShowPage(ResultSet rs);
	
	
}
