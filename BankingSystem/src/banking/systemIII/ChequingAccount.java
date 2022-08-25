package banking.systemIII;

/**
 * Description: Child class extends Accounts and implements interface Polices
 * with method to update balance to calculate monthly charges for chequing
 * account <br>
 * Date: April 3, 2022 <br>
 * Revised: July 8, 2022 <br>
 * Lab 6, CST8132_303
 * 
 * @author Author: Joel Black
 * @version 1.1
 * @since 1.8
 */
public class ChequingAccount extends Account implements Policies {

	public ChequingAccount() {// no argument constructor
	}

	@Override
	public void updateBalance() {
		balance = super.balance - MONTHLY_FEES;// calculates the new balance after monthly process
	}
}
