package com.tag.world;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class DatabaseConnection {

	private static DatabaseConnection instance = new DatabaseConnection();

	private BasicDataSource dataSource;

	private static final String LOOK_UP_PATH = "java:/comp/env/jdbc/TagMyWorldDB";

	private DatabaseConnection() {
		InitialContext cxt;
		try {
			cxt = new InitialContext();
			dataSource = (BasicDataSource) cxt.lookup(LOOK_UP_PATH);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			return instance.dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
