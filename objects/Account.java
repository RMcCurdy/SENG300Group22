package objects;

public class Account {
	
	public String email;		// Also login username
	private String firstName;	// 
	private String lastName;	// 
	private int ID;				// 8 digit int
	private int type;		// 0 = student, 1 = prof, 2 = head
	private String faculty; // Faculty chosen from options
	public String password;
	
	public static boolean checkUsername(String username) {
		if (username.length() <= 16) {
			return true;
		}
		
		return false;
	}
	
	public static boolean checkPassword(String password) {
		if (password.length() >= 16) {
			return true;
		}
		
		return false;
	}

	public Account(String email, String firstName, String lastName, int id, int type, String faculty) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.ID = id;
		this.type = type;
		this.faculty = faculty;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	public String toString() {
		return email+","+firstName+","+lastName+","+Integer.toString(ID)+","+Integer.toString(type)+","+faculty;
	}
	
}
