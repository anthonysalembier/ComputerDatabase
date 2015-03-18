package com.excilys.main;

import com.excilys.util.Command;
import com.excilys.util.ComputerDatabaseContext;
import com.excilys.util.ComputerDatabaseScanner;

public class Main {
	public static void main(String[] args) throws Exception {
		ComputerDatabaseScanner scanner = new ComputerDatabaseScanner();
		ComputerDatabaseContext ctx = new ComputerDatabaseContext();
		ctx.setScanner(scanner);

		System.out.println("Welcome to the computer database program !\n");
		System.out.println("List of commands : \n");
		System.out.println("List all computers    : getAllComputers");
		System.out.println("Display one computer  : getByIdComputer");
		System.out.println("Update a computer     : updateComputer");
		System.out.println("Create a new computer : createComputer");
		System.out.println("Delete a computer     : deleteComputer");
		System.out.println("List all companies    : getAllCompanies");
		System.out.println("Quit : exit\n");
		System.out.println("Write your command :\n");
		
		while (!scanner.isExit()) {
			final String token = scanner.getNextToken();
			final Command command = Command.getCommand(token);
			if (command != null) {
				command.execute(ctx);
			} else {
				System.out.println("Invalid command.");
			}
		}
	}
}
