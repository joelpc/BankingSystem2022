package banking.systemIII;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Description: The purpose of this class is to read and print all the personal
 * properties <br>
 * Date: April 3, 2022 <br>
 * Revised: July 8, 2022 <br>
 * Lab 6, CST8132_303
 * 
 * @author Author: Joel Black
 * @version 1.1
 * @since 1.8
 */
/* Beginning of class Person Parent class */
public class Person {

	// Declarations
	private String firstName;
	private String lastName;
	private String email;
	private long phoneNumber;

	public Person() {// no argument constructor
	}

	Person(String firstName, String lastName, String email, long ph) {// parameterized constructor

		// Assignments
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		phoneNumber = ph;
	}

	public String getName() {

		return firstName + " " + lastName;// Accessor method returns firstName and lastName as getName()
	}

	public String getEmail() {// Accessor method returns email
		return email;
	}

	public long getPhoneNumber() {// Accessor method returns phoneNumber
		return phoneNumber;
	}

	public void readPersonalDetails(Scanner input) {// method to take first name last name, email and phone number from
													// user
		System.out.print("Enter your first name: ");// print statement
		firstName = input.nextLine();// Take user input and set to firstName
		
		System.out.print("Enter your last name: ");// print statement
		lastName = input.nextLine();// set user input to lastName
	
		System.out.print("Enter your email address: ");// print statement
		email = input.nextLine();// set user input to email
		
		do {
			System.out.print("Enter phone number: ");// print statement
			try {
				phoneNumber = input.nextLong();// stores phone number in phone
				if (String.valueOf(phoneNumber).length() < 7) {
					throw new Exception("Invalid phone number...");// throw exception if user enters less than 7 digits
				} else {
					break;
				}
			} catch (InputMismatchException badNumber) {// catch for mismatch or letters entered
				System.out.println("Invalid phone number...");//print statement
				input.nextLine();
			} catch (Exception e) {// catch for all other exceptions
				System.out.println("Phone number must have 7 digits");//print statement
				input.nextLine();
			}
		} while (String.valueOf(phoneNumber).length() <= 7);// repeats until user enters 7 or more digits
		Long.valueOf(phoneNumber);// converts number back to long from string
	}

	public void printPersonalDetails() {// method to print all person details formatted for table
		
		System.out.printf("%30s | %30s | %15d |\n", getName(), getEmail(), getPhoneNumber());// formatted print statement
	}
}
