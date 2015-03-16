package com.excilys.computerdatabase.main;

import java.util.Scanner;

import com.excilys.computerdatabase.dao.CompanyDAO;
import com.excilys.computerdatabase.dao.ComputerDAO;
import com.excilys.computerdatabase.service.ComputerDatabaseService;

public class ComputerDatabase {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String LOGIN = "admincdb";
	private static final String PASSWORD = "qwerty1234";

	public static void main(String[] args) {
		System.out.println("Welcome to the computer database program !\n");
		System.out.println("List of commands : \n");
		System.out.println("List all elements : computer|company displayall");
		System.out.println("Show one element : computer|company display [id]");
		System.out.println("Add one element : computer add [id] [name] [introduced date] [discontinued date] [company id]");
		System.out.println("Remove one element : computer remove [id]");
		System.out.println("Update one element : computer update [id] [name] [introduced date] [discontinued date] [company id]\n");
		System.out.println("Write your command :\n");
		
		boolean stop = false;
		
		ComputerDAO.INSTANCE.setDbUrl(DB_URL);
		ComputerDAO.INSTANCE.setLogin(LOGIN);
		ComputerDAO.INSTANCE.setPassword(PASSWORD);
		
		CompanyDAO.INSTANCE.setDbUrl(DB_URL);
		CompanyDAO.INSTANCE.setLogin(LOGIN);
		CompanyDAO.INSTANCE.setPassword(PASSWORD);

		while (!stop) {
			Scanner sc = new Scanner(System.in);
			switch (sc.next()) {
			case "computer":
				switch (sc.next()) {
				case "displayall":
					ComputerDatabaseService.displayAllComputers();
					break;
				case "display":
					ComputerDatabaseService.displayComputer(sc.nextLong());
					break;
				case "add":
					ComputerDatabaseService.addComputer(sc.nextLong(),
														sc.next(),
														sc.next(),
														sc.next(),
														sc.nextLong());
					break;
				case "delete":
					ComputerDAO.INSTANCE.deleteComputer(sc.nextLong());
					break;
				case "update":
					ComputerDatabaseService.updateComputer(sc.nextLong(),
														sc.next(),
														sc.next(),
														sc.next(),
														sc.nextLong());
					break;
				default:
					System.out.println("Unknown command.");
					break;
				}
				break;
			case "company":
				String mode = sc.next();
				if (mode.equals("display")) {
					long id = sc.nextLong();
					System.out.println(id);
					ComputerDatabaseService.displayCompany(id);
				} else if (mode.equals("displayall")) {
					ComputerDatabaseService.displayAllCompanies();
				} else {
					System.out.println("Unknown command.");
				}
				break;
			case "exit":
				stop = true;
				break;
			default:
				System.out.println("Unknown command.");
				break;
			}
		}

		System.out.println("Good bye !");
	}

}
