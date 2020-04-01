package org.soft.base.ctrl.factory;

import org.soft.base.ctrl.dao.AdminDao;
import org.soft.base.ctrl.dao.ClassesDao;
import org.soft.base.ctrl.dao.ScoreDao;
import org.soft.base.ctrl.dao.SplitPageDao;
import org.soft.base.ctrl.dao.StudentDao;
import org.soft.base.ctrl.impl.AdminDaoImplements;
import org.soft.base.ctrl.impl.ClassesDaoImplements;
import org.soft.base.ctrl.impl.ScoreDaoImplements;
import org.soft.base.ctrl.impl.SplitPageDaoImplements;
import org.soft.base.ctrl.impl.StudentDaoImplements;

public class FactoryServer {
	private AdminDao adminDao;
	private ClassesDao classesDao;
	private ScoreDao scoreDao;
	private StudentDao studentDao;
	private SplitPageDao splitPageDao ;

	private static FactoryServer factoryServer;

	public static FactoryServer instance() {
		if (factoryServer == null) {
			factoryServer = new FactoryServer();
		}
		return factoryServer;
	}

	public AdminDao AdminDaoInstance() {
		if (adminDao == null) {
			adminDao = new AdminDaoImplements();
		}
		return adminDao;
	}

	public ClassesDao classesDaoInstance() {
		if (classesDao == null) {
			classesDao = new ClassesDaoImplements();
		}
		return classesDao;
	}

	public ScoreDao scoreDaoInstance() {
		if (scoreDao == null) {
			scoreDao = new ScoreDaoImplements();
		}
		return scoreDao;
	}
	
	public StudentDao studentDaoInstance() {
		if(studentDao == null) {
			studentDao = new StudentDaoImplements();
		}
		return studentDao;
	}
	
	public SplitPageDao splitPageDaoInstance() {
		if(splitPageDao == null) {
			splitPageDao = new SplitPageDaoImplements();
		}
		return splitPageDao;
	}

}
