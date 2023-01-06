package com.example.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Idao.IDao;
import com.example.models.User;
import com.example.utils.MyConnection;
import com.mysql.cj.conf.BooleanPropertyDefinition.AllowableValues;

public class UserDaoImpl implements IDao<User> {

	// Ici, on retoune une collection de type User de la db
	public List<User> findAll() throws ClassNotFoundException {
		Connection c = MyConnection.getConnection();
		List<User> users = new ArrayList<User>();
		try {

			// Preparation de la requete
			String request = "SELECT * FROM user";
			// creation de la requete (PreparedStatement =>
			// contre les injections SQL)
			PreparedStatement ps = c.prepareStatement(request);
			// Execution de la requete
			ResultSet r = ps.executeQuery();
			// Recuperation des donnees
			while (r.next()) {
				User u = new User();
				// on indique chaque fois le nom de la colonne et le type
				u.setId(r.getInt("id"));
				u.setFirstName(r.getString("firstname"));
				u.setLastName(r.getString("lastname"));
				u.setSalary(r.getDouble("salary"));

				users.add(u);
			}
			return users;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public User save(User o) throws ClassNotFoundException {
		Connection c = MyConnection.getConnection();
		try {
			// Preparation de la requête
			String request = "INSERT INTO user (firstname, lastname, salary) VALUES(?,?,?)";
			// creation de la requete (PreparedStatement => contre les injections SQL)
			PreparedStatement ps = c.prepareStatement(request, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, o.getFirstName());
			ps.setString(2, o.getLastName());
			ps.setDouble(3, o.getSalary());

			// Execution de la requete
			ps.executeUpdate();

			ResultSet resultat = ps.getGeneratedKeys();
			if (resultat.next()) {
				o.setId(resultat.getInt(1));
				return o;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public User update(User o, int id) throws ClassNotFoundException{
		Connection c = MyConnection.getConnection();
		try {
			// Preparation de la requête
			String request = ("UPDATE user SET firstname = ?,"
					+ "lastname = ?, salary = ? WHERE id = ?");
			
			// creation de la requete (PreparedStatement => contre les injections SQL)
			PreparedStatement ps = c.prepareStatement(request);
			ps.setString(1, o.getFirstName());
			ps.setString(2, o.getLastName());
			ps.setDouble(3, o.getSalary());
			ps.setInt(4, id);
			
			// Execution de la requete
			int count = ps.executeUpdate();
			if(count == 1) {
				System.out.println("Update queries" + count);
				o.setId(id);
				return o;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(int id) throws ClassNotFoundException{
		Connection c = MyConnection.getConnection();
		String request = ("DELETE FROM user WHERE id = ?");
		try {
		// creation de la requete (PreparedStatement => contre les injections SQL)
		PreparedStatement ps = c.prepareStatement(request);
		ps.setInt(1, id);
		
		// Execution de la requete
		int count = ps.executeUpdate();
		if(count == 1) {
			System.out.println("Delete queries " + count);
			return true;
		}
		}
		 catch (Exception e){
		e.printStackTrace();
	}
		 return false;
}

	@Override
	public User findbyId(int id) throws ClassNotFoundException {
		Connection c = MyConnection.getConnection();
		try {
			String request = "SELECT * FROM user WHERE id = ? "; 
			
			PreparedStatement ps = c.prepareStatement(request);
			ps.setInt(1, id);
			
			ResultSet r = ps.executeQuery();
			
			if (r.next()) {
				User u = new User();
				
				u.setId(r.getInt("id"));
				u.setFirstName(r.getString("firstname"));
				u.setLastName(r.getString("lastname"));
				u.setSalary(r.getDouble("salary"));
				
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public User findbyFirstName(String firstname) throws ClassNotFoundException {
		Connection c = MyConnection.getConnection();
		try {
			String request = "SELECT * FROM user WHERE firstName = ? "; 
			
			PreparedStatement ps = c.prepareStatement(request);
			ps.setString(1, firstname);
			
			ResultSet r = ps.executeQuery();
			
			if (r.next()) {
				User u = new User();
				
				u.setId(r.getInt("id"));
				u.setFirstName(r.getString("firstname"));
				u.setLastName(r.getString("lastname"));
				u.setSalary(r.getDouble("salary"));
				
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}


	public List<User> findbySalaire(double salaire) throws ClassNotFoundException {
		Connection c = MyConnection.getConnection();
		List<User> users = new ArrayList<User>();
		try {
			String request = "SELECT * FROM user WHERE salaire = ?";
			
			PreparedStatement ps = c.prepareStatement(request);
			//ps.setDouble(1, salaire);
			
			
			ResultSet r = ps.executeQuery();
			while (r.next()) {
				User u = new User();
				
				u.setId(r.getInt("id"));
				u.setFirstName(r.getString("firstname"));
				u.setLastName(r.getString("lastname"));
				u.setSalary(r.getDouble("salary"));
				
				users.add(u);
			} return users;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return null;
	}
	
		// Creation d'une transaction 
		// Une transaction traite une seule instruction SQL ou un groupe 
		// d’instructions SQL comme une unité logique, et si une instruction échoue, 
		// la transaction entière échoue.
		@Override
		public List<User> insertAndFindAll(User o) throws ClassNotFoundException {
			List<User> users = new ArrayList<User>();
			Connection c = null;
			try{			
				c = MyConnection.getConnection();			
				// définir la gestion des transactions manuelles
				c.setAutoCommit(false);
				
				// Preparation de la requete
				String request1 = "INSERT INTO user (firstname, lastname, salary) VALUES (?,?,?)";
				// creation de la requete (PreparedStatement => contre les injections SQL)
				PreparedStatement ps = c.prepareStatement(request1);

				ps.setString(1, o.getFirstName());
				ps.setString(2, o.getLastName());
				ps.setDouble(3, o.getSalary());

				// Execution de la requete
				ps.executeUpdate();

				// Preparation de la requete
				String request2 = "SELECT * FROM user";
				// creation de la requete (PreparedStatement =>
				// contre les injections SQL)
				PreparedStatement ps2 = c.prepareStatement(request2);
				// Execution de la requete
				ResultSet r = ps2.executeQuery();
				// Recuperation des donnees
				while (r.next()) {
					User u = new User();
					// on indique chaque fois le nom de la colonne et le type
					u.setId(r.getInt("id"));
					u.setFirstName(r.getString("firstname"));
					u.setLastName(r.getString("lastname"));
					u.setSalary(r.getDouble("salary"));

					users.add(u);
				}
				
				// S'il n'y a pas d'erreur.
				c.commit();
				
				return users;
			} catch (SQLException e) {
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return null;
		}
		
}
	

