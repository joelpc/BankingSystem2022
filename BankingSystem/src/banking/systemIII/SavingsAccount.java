package banking.systemIII;

/**
 * Description: Child class extends Accounts and implements interface Polices
 * with method to update balance to calculate monthly charges for savings
 * account <br>
 * Date: April 3, 2022 <br>
 * Revised: July 8, 2022 <br>
 * Lab 6, CST8132_303
 * 
 * @author Author: Joel Black
 * @version 1.1
 * @since 1.8
 */
//beginning of savings class
public class SavingsAccount extends Account implements Policies {

	public SavingsAccount() {// no argument constructor
	}

	@Override
	public void updateBalance() {
		balance = super.balance + (super.balance * (INTEREST_RATE / 100) / MONTHS);// calculates the new balance after
																					// monthly process
	}
}
