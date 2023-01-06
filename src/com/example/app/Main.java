package com.example.app;

import java.util.List;

import com.example.dao.impl.UserDaoImpl;
import com.example.models.User;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
//		
//		System.out.println("Saisir votre first-name");
//		String firstName = scanner.next();
//		
//		System.out.println("Saisir votre last-name");
//		String lastName = scanner.next();
//		
//		System.out.println("Saisir votre salaire");
//		double salaire = scanner.nextDouble();
//		
//		User user1 = new User (firstName, lastName, salaire);
		
		
		
		//User userToSave = userDaoImpl.save(user1);
		//System.out.println(user1);

		
		System.out.println("--------------------------Afficher la liste de Users---------------------");
		
		// Avant JAVA 8
		//List<User> users = userDaoImpl.findAll();
		
		//for (User user : users) {
			//System.out.println(user);
		//}
		/* System.out.println("--------------------------Inserer un nouveau users---------------------");
		User userToSave = userDaoImpl.save(new User("John", "Doe", 2000));
		System.out.println(userToSave);
		
		 //JAVA 8
		userDaoImpl.findAll().forEach((u) -> System.out.println(u));
				
		System.out.println("--------------------------Modifier un user selon son identifiant---------------------");
				
		User user = new User("Bat", "Man", 2000); // nouveau objet
		
		User userToUpdate = userDaoImpl.update(user, 1); // id que l'on veut modifier et remplacer par usertoUpdate(nom de l'objet)
		
		userDaoImpl.findAll().forEach((u) -> System.out.println(u));
		System.out.println(userToUpdate);
		
		System.out.println("--------------------------Modifier un user selon son identifiant---------------------");
		
		*/
		//userDaoImpl.delete(1);
		//System.out.println(userDaoImpl.delete(1));
		
		//userDaoImpl.delete(12);
		//System.out.println(userDaoImpl.delete(12));
		
		
		//userDaoImpl.delete(6);
		//System.out.println(userDaoImpl.delete(11));
	
		// Ecrire un programme qui demande a l'utilisateur de rentrer une valeur pour
		// Recupere un element selon son identifiant, puis qui supprime un element selon son identifiant
				
//		Scanner scanner = new Scanner(System.in);
//		
//		System.out.println("Saisir un id");
//		int id = scanner.nextInt(); 
//		
//		
//		//System.out.println(userDaoImpl.delete(id));
//		System.out.println(userDaoImpl.findbyId(id));
//		
//		System.out.println("Saisir un prénom");
//		String firstName = scanner.next();
//		System.out.println(userDaoImpl.findbyFirstName(firstName));
		
//		System.out.println("Saisir un salaire");
//		double salaire = scanner.nextDouble();
//		System.out.println(userDaoImpl.findbySalaire(salaire));
		
		//User user = userDaoImpl.findById(i);
		
	
		
		
		//User userToSave = userDaoImpl.save(user1);
		
		
		
		//scanner.close();
		
		List<User> users = userDaoImpl.insertAndFindAll(new User("bob", "doe", 2000));
		
		users.forEach((u) -> System.out.println(u));
				
				

	}

}
