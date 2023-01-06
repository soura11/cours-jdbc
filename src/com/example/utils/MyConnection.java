package com.example.utils;

import java.sql.*;

// Singleton (static) -> Design pattern - Patron de conception
// qui fournit un unique point d'acc�s � notre base de donn�es 

public class MyConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/cours_jdbc?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
    private static Connection connexion = null;

	// constructeur qui vas initailis� une connexion
	private MyConnection() throws ClassNotFoundException{
	{
		try {
			// Chargement du driver 8 mysql
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Etablit la connection avec la base de donn�es
			connexion = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	}
	// methode statique qui vas retourner une unique connexion � la db
	public static Connection getConnection() throws ClassNotFoundException {
		if (connexion == null) {
			new MyConnection();
		}
		return connexion;
	}

	// methode statique qui stop l'unique connexion � la db
	public static void stop() {
		if (connexion != null) {
			try {
				connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
