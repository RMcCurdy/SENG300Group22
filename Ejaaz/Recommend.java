
public class Recommend {
	
	private String firstName;	// 
	private String lastName;	// 
	private int ID;				// 8 digit int
    private String faculty; // Faculty chosen from options
    private String notes;
	

	public Recommend(String firstName, String lastName, int id, String faculty, String notes) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ID = id;
        this.faculty = faculty;
        this.notes = notes;
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
	 * @return the ID
	 */
	public int getID() {
		return ID;
    }

    /**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
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
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String toString() {
		return firstName+","+lastName+","+Integer.toString(ID)+","+faculty+","+notes;
	}
	
}
