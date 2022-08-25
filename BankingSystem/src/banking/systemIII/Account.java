package banking.systemIII;
import java.util.Scanner;
/**
 * Description: This class adds account number and account balance to
 * person/accHolder <br>
 * Date: April 3, 2022 <br>
 * Revised: July 8, 2022 <br>
 * Lab 6, CST8132_303
 * 
 * @author Author: Joel Black
 * @version 1.1
 * @since 1.8
 */
/**Beginning of abstract class account**/
public abstract class Account {

	//declarations
	protected long accountNumber;
	protected Person accHolder;
	protected double balance;
	private String typeOfAccount;

	/**
	 * Constructor for accHolder creating new person object
	 * 
	 */
	public Account() {
		
		accHolder = new Person();
		accountNumber = 0;
		balance = 0;
		typeOfAccount = null;

	}

	/**
	 * Method read Account to take input for the user for account number and
	 * balance. Do while loop used to prevent user from entering a negative balance.
	 * 
	 * @param input Takes user input
	 */
	public void readAccountDetails(Scanner input) {
		boolean count = false;
		do {
			System.out.println("Enter your Account Number: ");// user output statement

			try {
				accountNumber = input.nextLong();// Sets account number to accountNumber
				input.nextLine();// Reset next line for output
				try {
					if (accountNumber < 0)// condition and statement for negative number
						throw new NotAllowedException("Account number cannot be negative..");
					if (accountNumber > 0) {// condition to exit loop if accountNumber positive
						count = true;//changes condition to exit do-while loop
						break;
					}
				} catch (NotAllowedException e) {//catches exception thrown for negative number
					System.out.println(e.getMessage());
					continue;
				}
			} catch (Exception e) {// catches all other exceptions like input mismatch
				System.out.println("Invalid number...");
				input.next();
				continue;
			}
			break;
		} while (count == false);//end of do-while loop

		accHolder.readPersonalDetails(input);//calls method to read person details adds as an account holder

		boolean count2 = false;// Instantiation
		do {
			System.out.println("Enter balance (must be a positive number): ");// user output

			try {
				balance = input.nextDouble();// Sets account balance to balance

				try {
					if (balance < 0)// balance condition negative
						throw new NotAllowedException("Balance amount cannot be negative...");// throws exception
					if (balance > 0) {// balance condition positive
						count2 = true;// change boolean to allow exit from do-while loop
						break;
					}

				} catch (NotAllowedException e) {// catch exception thrown from negative entry
					System.out.println(e.getMessage());
					continue;
				}
			} catch (Exception e) {// catch any other exception including mismatch
				System.out.println("Invalid amount...");
				input.next();
				continue;
			}
			break;
		} while (count2 == false);// end of do-while loop
	}

	public String getTypeOfAccount() {
		return typeOfAccount;//accessor method for account type
	}

	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;//mutator method to set type of account
	}

	public void printAccountDetails() {// formatted print method for all account details to print in table
		System.out.printf("%11d | %30s | %20s | %15d | %10.2f | \n", accountNumber, accHolder.getName(),
				accHolder.getEmail(), accHolder.getPhoneNumber(), balance);
	}

	public abstract void updateBalance();// abstract method the update balance for child classes when monthly process is run

	//method to set file details
	public void setFile(String newAccountType, int newAccountNumber, String newFirstName, String newLastName,
			String newEmail, long newPhoneNumber, double newBalance) {

		typeOfAccount = newAccountType;
		accountNumber = newAccountNumber;
		accHolder = new Person(newFirstName, newLastName, newEmail, newPhoneNumber);
		balance = newBalance;

	}
	//method to write all accounts to file
	public String writeToFile(Account A) {
		String writeAccount = (typeOfAccount + " " + accountNumber + accHolder.getName() + " " + accHolder.getEmail()
				+ " " + accHolder.getPhoneNumber() + " " + String.format("%.2f", balance));
		
		return writeAccount;
	}
}
