package com.excilys.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.excilys.dao.CompanyDAO;
import com.excilys.exception.DAOException;
import com.excilys.exception.ServiceException;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.validation.ComputerDatabaseValidator;

/**
 * Pattern command for the processing of actions.
 */
public enum Command {
	/**
	 * Show help
	 */
	HELP("help") {
		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			System.out.println("List of commands : \n");
			System.out.println("List all computers    : getAllComputers");
			System.out.println("Display one computer  : getByIdComputer");
			System.out.println("Update a computer     : updateComputer");
			System.out.println("Create a new computer : createComputer");
			System.out.println("Delete a computer     : deleteComputer");
			System.out.println("List all companies    : getAllCompanies");
			System.out.println("Quit : exit\n");
			System.out.println("Date format : yyyy-MM-dd");
		}
	},
	
	/**
	 * Retrieve all computers.
	 */
	GET_ALL_COMPUTERS("getAllComputers") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			ctx.setComputers(ComputerService.INSTANCE.getAll());
			for (Computer c : ctx.getComputers()) {
				System.out.println(c);
			}
		}

	},
	/**
	 * Retrieve all companies.
	 */
	GET_ALL_COMPANIES("getAllCompanies") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			ctx.setCompanies(CompanyService.INSTANCE.getAll());
			for (Company c : ctx.getCompanies()) {
				System.out.println(c);
			}
		}

	},
	/**
	 * Retrieve a computer.
	 */
	GET_BY_ID_COMPUTER("getByIdComputer") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			System.out.print("Identifier : ");
			ctx.setComputerId(Long.valueOf(ctx.getScanner().getNextToken()));
			ctx.setComputers(Arrays.asList(ComputerService.INSTANCE.getById(ctx.getComputerId())));
			for (Computer c : ctx.getComputers()) {
				System.out.println(c);
			}
		}

	},
	/**
	 * Create a new computer.
	 */
	CREATE_COMPUTER("createComputer") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			final Computer computer = new Computer();
			if (populate(ctx, computer)) {
				ctx.setNewComputer(computer);
				ComputerService.INSTANCE.create(ctx.getNewComputer());
				System.out.println("Computer created.");
			} else {
				System.out.println("Computer creation failed.");
			}
			
		}

	},
	/**
	 * Update a computer.
	 */
	UPDATE_COMPUTER("updateComputer") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			System.out.println("Identifier : ");
			final Computer computer = ComputerService.INSTANCE.getById(Long
					.valueOf(ctx.getScanner().getNextToken()));
			if (populate(ctx, computer)) {
				ctx.setNewComputer(computer);
				ComputerService.INSTANCE.update(ctx.getNewComputer());
				System.out.println("Computer updated.");
			} else {
				System.out.println("Update failed.");
			}
		}

	},
	/**
	 * Delete a computer.
	 */
	DELETE_COMPUTER("deleteComputer") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			System.out.print("Identifier : ");
			ctx.setComputerId(Long.valueOf(ctx.getScanner().getNextToken()));
			ComputerService.INSTANCE.delete(ctx.getComputerId());
			System.out.println("Deleted");
		}

	},
	/**
	 * Delete a company with all associated computers.
	 */
	DELETE_COMPANY("deleteCompany") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			System.out.print("Identifier : ");
			ctx.setCompanyId(Long.valueOf(ctx.getScanner().getNextToken()));
			CompanyService.INSTANCE.delete(ctx.getCompanyId());
			System.out.println("Deleted");
		}

	},
	/**
	 * Command to terminate a program.
	 */
	EXIT("exit") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			ctx.getScanner().setExit(true);
			System.out.println("\nGood bye !\n");
		}

	};

	private static Map<String, Command> commands;
	static {
		commands = new HashMap<>();
		for (Command com : Command.values()) {
			commands.put(com.commandLabel, com);
		}
	}

	private final String commandLabel;

	private Command(String commandLabel) {
		this.commandLabel = commandLabel;
	}
	
	/*
	 * Populate a computer model from answers given by the user.
	 */
	private static boolean populate(ComputerDatabaseContext ctx, Computer computer) {
		System.out.println("Name : ");
		if (ctx.getScanner().hasNextToken()) {
			computer.setName(ctx.getScanner().getNextToken());
		}
		System.out.println("Introduced :");
		if (ctx.getScanner().hasNextToken()) {
			final String tok = ctx.getScanner().getNextToken();
			final StringBuilder sb = new StringBuilder();
			sb.append(tok).append(" ").append("00:00:00");
			
			if (ComputerDatabaseValidator.validateDate(sb.toString())) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dateTime = LocalDateTime.parse(sb.toString(),
						formatter);
				computer.setIntroduced(dateTime);
			} else {
				System.out.println("Invalid date.");
				return false;
			}
		}
		System.out.println("Discontinued :");
		if (ctx.getScanner().hasNextToken()) {
			final String tok = ctx.getScanner().getNextToken();
			final StringBuilder sb = new StringBuilder();
			sb.append(tok).append(" ").append("00:00:00");
			
			if (ComputerDatabaseValidator.validateDate(sb.toString())) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dateTime = LocalDateTime.parse(sb.toString(), formatter);
				computer.setDiscontinued(dateTime);
			} else {
				System.out.println("Invalid format.");
				return false;
			}
		}
		System.out.println("Company : ");
		if (ctx.getScanner().hasNextToken()) {
			try {
				computer.setCompany(CompanyDAO.INSTANCE.getById(Long.valueOf(ctx.getScanner().getNextToken())));
			} catch (NumberFormatException | DAOException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}

	/**
	 * Return a command from its textual value.
	 * 
	 * @param command Textual command
	 * @return The matching command
	 */
	public static Command getCommand(String command) {
		return commands.get(command);
	}

	public abstract void execute(ComputerDatabaseContext ctx)
			throws ServiceException;
}
