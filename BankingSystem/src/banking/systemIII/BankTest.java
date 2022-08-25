package banking.systemIII;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Description: BankTest with main method contains the main menu and scanner
 * call Takes the name of bank and the number of account holders and performs
 * all the main operations <br>
 * Date: April 3, 2022 <br>
 * Revised: July 8, 2022 <br>
 * Lab 6, CST8132_303
 * 
 * @author Author: Joel Black
 * @version 1.1
 * @since 1.8
 */
//Beginning of Class bankTest
public class BankTest {

	/**Start of main method
	 * @param args takes arguments
	 * 
	 *  **/
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);// creates a new scanner object
		Scanner fileReader;// declaration of file reader
		boolean check = false;// Initializations for check and number of accounts
		int numAccount = 0;

		System.out.println("Enter the name of the bank: ");// print statement
		String name = input.nextLine();// takes the name of the bank from user sets to name variable

		do {// do while loop to process number of account holders while user has no
			// specified number
			System.out.println("how many account holders do you have: ");// print statement

			try {// Try catch block to handle wrong user input of negative numbers or letters
				numAccount = input.nextInt();// takes number of accounts from user and sets to numAccount variable
				if (numAccount < 0) {// if user inputs negative number prints statement returns to do
					System.out.println("Number of accounts can not be negative... ");// print line statement
				} else if (numAccount > 0) {// if user inputs a positive number
					check = true;// changes check to true allows the exit of do while loop
					break;
				}
			} catch (InputMismatchException e) {// catches letter input exception
				System.out.println("Please enter number in digits.. not words");// print statement
				continue;// continue loop
			} catch (Exception e) {// catches all other exceptions including negative number
				System.out.println("Invalid number... please enter a positive integer");// print line statement
				input.nextLine();// resets line to take user input
				continue;// continue loop
			}
		} while (check == false);// prevents the loop from exit while user has entered invalid input

		Bank bank = new Bank(name, numAccount);// creates new object Bank passing the bank name, and the number of
												// accounts

		// Initializations
		int tracker = 0;
		int menuChoice = 0;

		do {// do while loop to take user selection for which method/methods to run will
			// return to this menu(main menu) unless user chooses 6 to exit

			System.out.println("1. Read Account");// print line statements to display menu
			System.out.println("2. Run monthly process");
			System.out.println("3. Print all Accounts");
			System.out.println("4. Read data from file");
			System.out.println("5. Write data to file");
			System.out.println("6. Exit");

			System.out.println("Enter your option:");

			try {// try catch block to prevent the user from moving on with invalid entry
					// (numbers 1 to 6)
				menuChoice = input.nextInt();// takes the users menu choice and sets it to variable menuChoice
				if (menuChoice < 1 || menuChoice > 6) {// if the user selects a number
					System.out.println("invalid menu option.  Try again...");// prints when number is not 1 to 6
					continue;// continue loop
				}

			} catch (Exception e) {// catches all other invalid input
				System.out.println("Invalid menu option.  Try again...");// prints when user enters invalid input mostly
																			// letters
				input.next();// resets scanner to take input on the next line
				continue;// continue loop
			}

			switch (menuChoice) {// switch case to organize users valid selection (numbers 1 to 6)

			case 1: {// user selects menuChoice 1 to read account
				bank.readAccount(input);// calls method readAccount from class Bank to add an account and take
										// information
				tracker++;// increments the tracking counter for added account
				break;// continue main menu loop
			}
			case 2: {// user selects menuChoice 2 to run monthly process
				if (tracker == 0) {// prevents running process when no accounts have been entered
					System.out.println("No accounts to process");// prints when there are no accounts
					continue;// continue main menu loop
				} else if (tracker != 0) {// runs method when there are accounts to process
					bank.runMonthly();// calls runMonthly method from Bank calculates monthly account balance
										// adjustments
					break;// continue main menu loop
				}
			}
			case 3: {// user selects menuChoice 3 to display accounts
				if (tracker == 0) {// if no accounts have been entered
					System.out.println("No accounts to display");// print statement
					System.out.println();// line space
					continue;// continue main menu loop
				} else if (tracker != 0) {// if accounts have been added
					System.out.println();// line space
					bank.printTitle();// calls method printTile from class Bank which prints the table header with bank name
					bank.printAccount();// calls method printAccount which prints account information formatted into a table
					break;// continue main menu loop
				}
			}
			case 4: {// user selects menuChoice 4 to read data from file and add accounts
				try {// try catch to catch file mismatch or empty file
					File fileName = new File("AccountsSource.txt");// creates a new file object using the file location AccountSource.txt
					fileReader = new Scanner(fileName);// new object fileReader to store scanner information from file/fileName
					while (fileReader.hasNextLine()) {// scanner continues until there is no more lines in file
						bank.readFromFile(fileReader.nextLine(), input);// calls method readFromFile (Bank class) to interpret account information
						System.out.println();// line space
						System.out.println("Account read from file: " + (tracker + 1));// prints for each account read from file incrementing
						System.out.println();// line space
						tracker++;// increases the tracker for number of accounts
					}
				} catch (FileNotFoundException e) {// catches file not found exception
					System.out.println("The specified file was not found.  Try again...");// print statement
					input.next();// reset the scanner to take input on next line
					continue;// continues the main menu
				}
				fileReader.close();// closes the file reader
				break;// continues the main menu
			}
			case 5: {// user selects menuChoice 5 write to file
				if (tracker == 0) {// if no accounts have been added
					System.out.println("***No accounts to save to file...Please try again***");// print statement
					continue;// continues the main menu

				} else if (tracker != 0) {// if there are accounts
					try {// try catch to write to file
						bank.printToFile();// calls the printToFile method (Bank class)
						System.out.println("****all accounts written to file****");// print statement
						break;// continues to main menu
					} catch (IOException e) {// catch exceptions 
						System.out.println("The specified file was not found.  Try again...");// print statement
						input.next();// reset scanner to take input on next line
						continue;// continues to main menu
					}
				}
			}
			case 6: {// user selects option 6 to exit program
				System.out.println();// line space
				System.out.println("Exiting banking system.  Have a nice day!");// print statement
				break;
			}
			default: {// prints for invalid selection from main menu
				System.out.println("Invalid option... Please re-enter");// print statement
				break;
			}
			}
		} while (menuChoice != 6);// Exits only when option 6 is selected from main menu

		input.close();// closes the scanner

	}// end of main method

}// end of BankTest class