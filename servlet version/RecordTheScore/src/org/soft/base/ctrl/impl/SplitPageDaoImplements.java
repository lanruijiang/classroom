package org.soft.base.ctrl.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.soft.base.ctrl.dao.SplitPageDao;
import org.soft.base.ctrl.db.CRUDDB;

public class SplitPageDaoImplements extends CRUDDB implements SplitPageDao{

	@Override
	public int totalRow(String sql) {
		ResultSet rs = executiongQuery(sql, null);
		int rows = 0;
		try {
			if(rs.next()) {
				rows = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
		}
		return rows;
	}

	@Override
	public int totalPage(int totalRow, int size) {
		int pages = 0;
		if(totalRow % size != 0) {
			pages = totalRow / size + 1;
		}else {
			pages = totalRow / size;
		}
		return pages;
	}

	@Override
	public int currentRow(int currentPage, int size) {
		int beginRow = (currentPage - 1) * size;
		
		return beginRow;
	}

	@Override
	public ResultSet currentPageContent(Object[] objects , String sql) {
		
		ResultSet rs = executiongQuery(sql, objects);
		return rs;
	}
	
}
