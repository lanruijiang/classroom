package org.soft.base.ctrl.dao;

import java.sql.ResultSet;

public interface SplitPageDao {

	/**
	 * 	查询指定表的总行数
	 * @param tableName
	 * @return
	 */
	public int totalRow(String sql);
	
	/**
	 * 	求总页数
	 * @param totalRow
	 * @param size
	 * @return
	 */
	public int totalPage(int totalRow , int size);
	
	/**
	 * 	求当前页需要访问数据库的起始行数
	 * select * from table limit ?,?
	 * @return
	 */
	public int currentRow(int currentPage , int size);
	
	/**
	 * 	查询当前页需要显示的内容
	 * @param currentRow
	 * @param size
	 * @return
	 */
	public ResultSet currentPageContent(Object[] objects , String sql);
}
