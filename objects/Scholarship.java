package objects;
import java.util.Arrays;

public class Scholarship {

	private String name;			// Name
	private String donor;			// Who gave out the scholarship
	private int value;				// How much that scholarship gives
	private String term;			// Fall/Winter/String/Summer/Full Year
	private String type;			// Graduate/Undergraduate
	private String department;		// i.e. computer science students only
	private String[] applicants;	// List of student emails
	private String deadline;			// YYYYMMDD
	
	public Scholarship(String name, String donor, int value, String term, String type, String department, String deadline) {
		this.name = name;
		this.donor = donor;
		this.value = value;
		this.term = term;
		this.type = type;
		this.department = department;
		this.deadline = deadline;
	}

	public String toString() {
		return name+","+donor+","+value+","+term+","+type+","+department+","+Arrays.toString(applicants)+","+deadline;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the donor
	 */
	public String getDonor() {
		return donor;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @return the applicants
	 */
	public String[] getApplicants() {
		return applicants;
	}

	/**
	 * @return the deadline
	 */
	public String getDeadline() {
		return deadline;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param donor the donor to set
	 */
	public void setDonor(String donor) {
		this.donor = donor;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @param applicants the applicants to set
	 */
	public void setApplicants(String[] applicants) {
		this.applicants = applicants;
	}

	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

}
