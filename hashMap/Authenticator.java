import java.util.HashMap;

public class Authenticator {

	private HashMap<String, Account> accounts =  new HashMap<String, Account>();
	
	public String register(String username, String password) {
		boolean correctuser = Account.checkUsername(username);
		boolean correctpass = Account.checkPassword(password);
		boolean notsameuser = !accounts.containsKey(username);
		
		if (correctuser && correctpass && notsameuser) {
			accounts.put(username, new Account( username, password));
			return "works";
		}
		
		if (!correctuser) {
			return "Invalid username";
		}
		
		if (!correctpass) {
			return "Invalid password";
		}
		
		if (!notsameuser) {
			return "Username taken";
		}
		
		return "Unkown issue";
	}
	
	public Account login(String username, String password) {
		if(accounts.containsKey(username) ) {
			System.out.println("found users");
			
			if (accounts.get(username).getPassword().contentEquals(password)) {
				return accounts.get(username);
				
			}
			
			else {
				return null;
			}
			
			
		}
		
		else {
			return null;
		}
		
	}

}
