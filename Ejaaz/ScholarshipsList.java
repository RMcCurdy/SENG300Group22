import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class ScholarshipsList {

	List<Scholarship> scholarships = new ArrayList<Scholarship>();
	
	public ScholarshipsList() {
		this.loadFromFile();
	}

	public void addScholarship(Scholarship toAdd) {

		this.scholarships.add(toAdd);
		System.out.println("Scholarships " + toAdd.toString() + " has been added.");
		saveToFile();
	}

	public void removeScholarship(int index) {
		
		System.out.println("Removing " + this.scholarships.get(index).toString());
		this.scholarships.remove(index);
		saveToFile();
	}
	
	public void editScholarship(int index, Scholarship toEdit) {
		
		// TODO
	}
	
	public void loadFromFile(){
		
		try {
			FileInputStream fileIn = new FileInputStream("scholarships.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.scholarships = (List<Scholarship>) in.readObject();
			in.close();
			fileIn.close();
			
			System.out.println("Loaded scholarships from file.");
			
			for (Scholarship i: this.scholarships) {
				System.out.println("Scholarship in list: "+i.toString());
			}
		} 
		catch (Exception i) 
		{
			// Only happens when no scholarships are stored, just ignore
		}
	}
	
	public void saveToFile() {
		
		try {
			FileOutputStream fileOut = new FileOutputStream("scholarships.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this.scholarships);
			out.close();
			fileOut.close();
			
			System.out.println("Saved.");
		} 
		catch(Exception ex) 
		{
			//Exception thrown if the above code can't proceed
			ex.printStackTrace();
		}
	}
	
	/**
	 * @return the scholarships
	 */
	public List<Scholarship> getScholarships() {
		return scholarships;
	}

	/**
	 * @param scholarships the scholarships to set
	 */
	public void setScholarships(List<Scholarship> scholarships) {
		this.scholarships = scholarships;
	}
	
}
