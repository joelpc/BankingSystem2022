package banking.systemIII;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Description: Bank takes the name of bank and the number of account holders
 * and creates an array list contains menu for type of account
 * (chequing/savings) and methods to print table of account holders with
 * formatted header <br>
 * Date: April 3, 2022 <br>
 * Revised: July 8, 2022 <br>
 * Lab 6, CST8132_303
 * 
 * @author Author: Joel Black
 * @version 1.1
 * @since 1.8
 */
public class Bank {

	//declarations and initialization
	private static String name;
	private ArrayList<Account> accounts;
	private int numAccount;
	boolean count = false;
	int subMenu;

	/** no-argument constructor takes nothing returns nothing */
	public Bank() {
	}

	/**
	 * Parameterized constructor takes name of bank and number of accounts
	 * Initializes an array list accounts to number of accounts declares num for
	 * number of accounts
	 * 
	 * @param bankName             Takes name of Bank
	 * @param numAccount Takes the number of account holders
	 */
	public Bank(String bankName, int numAccount) {
		name = bankName.toUpperCase();
		accounts = new ArrayList<Account>(numAccount);// creates array list accounts
	}

	/**
	 * Method read accounts creates a menu for selecting account type (chequing
	 * /savings) with default print statement and do while loop to protect invalid
	 * selection
	 * 
	 * 
	 * @param input Takes scanner input
	 * 
	 */
	public void readAccount(Scanner input) {

		do {
			System.out.println("1. Chequing");
			System.out.println("2. Savings");
			System.out.println("Enter the type of account you want to create: ");

			try {
				subMenu = input.nextInt();//takes input for account type sets to submenu

				if (subMenu == 1 || subMenu == 2) {//condition for valid user input
					count = true;// condition to exit do-while loop
					break;
				}
				if (subMenu != 1 || subMenu != 2) {// invalid user input prints next line
					System.out.println("Invalid account type... please re-enter 1 or 2");
					continue;
				}
			} catch (Exception e) {// catches all exceptions for invalid user input
				System.out.println("Invalid account type... please re-enter 1 or 2");
				input.nextLine();
				continue;
			}
		} while (count == false);// end of do-while loop

		if (subMenu == 1) {// if sub menu selection 1 chequing 
			accounts.add(new ChequingAccount());//adds a new chequing account to the accounts array
			accounts.get(numAccount).setTypeOfAccount("C");//takes account number and sets type to C
		} else if (subMenu == 2) {// if sub menu selection 2 savings
			accounts.add(new SavingsAccount());//adds a new savings account to the accounts array
			accounts.get(numAccount).setTypeOfAccount("S");//takes account number and sets type to S
		}
		accounts.get(numAccount).readAccountDetails(input);//method call adding to array accounts all account details(see Account)
		numAccount++;//increment the number of accounts
	}

	public void runMonthly() {// method to pass over the accounts array and update all balances from abstract method updateBalance
		for (int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i) != null) {
				accounts.get(i).updateBalance();
			}
		}
	}
	//method to read accounts from file
	public void readFromFile(String addAccounts, Scanner input) {
		
		//initializations
		input = new Scanner(addAccounts);
		String newAccountType = " ";
		int newAccountNumber = 0;
		String newFirstName = " ";
		String newLastName = " ";
		String newEmail = " ";
		long newPhoneNumber = 0;
		double newBalance = 0;

		while (input.hasNext()) {//pulls info from file using the space breaks
			newAccountType = input.next();
			newAccountNumber = input.nextInt();
			newFirstName = input.next();
			newLastName = input.next();
			newEmail = input.next();
			newPhoneNumber = input.nextLong();
			newBalance = input.nextDouble();
		}
		if (addAccounts.startsWith("C")) {//sets all C accounts as chequing
			accounts.add(new ChequingAccount());// adds account to accounts array
			accounts.get(numAccount).setFile(newAccountType, newAccountNumber, newFirstName, newLastName, newEmail,
					newPhoneNumber, newBalance);//pulls from set file to create account
			numAccount++;//increments the number of accounts
		} else if (addAccounts.startsWith("S")) {//sets all S accounts as savings
			accounts.add(new SavingsAccount());// adds account to accounts array
			accounts.get(numAccount).setFile(newAccountType, newAccountNumber, newFirstName, newLastName, newEmail,
					newPhoneNumber, newBalance);//pulls from set file to create account
			numAccount++;//increments the number of accounts
		}
	}

	public void printToFile() throws IOException {// method to write to file listed
		FileWriter writer = new FileWriter("AccountsOutSource.txt");//object writer file AccountsOutSource.txt
		for(Account A: accounts) {
			writer.write(A.writeToFile(A) + System.lineSeparator());//adds a line separator for each account
		}
		writer.close();//closes the file writer
	}

	public static void printStars() {//method to print line of stars for table header
		for (int i = 0; i < 114; i++) {
			System.out.print("*");
		}
	}

	public void printTitle() {//method to print table header
		printStars();
		System.out.printf("\n %46s", name + " " + "BANK \n");//Bank name with spacing
		printStars();
		System.out.printf("\n %10s | %30s | %20s | %15s | %10s |\n", "Acc Number", "Name", "Email", "Phone", "Balance");
		printStars();
		System.out.println();
	}

	public void printAccount() {//method to pass over the accounts array and print all accounts in a table
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i) != null) {
				accounts.get(i).printAccountDetails();
			}
		}
	}

}
