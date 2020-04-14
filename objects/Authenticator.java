package objects;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Authenticator {

	//declaring the hash-maps to be used thorughoutthe system  
	public static HashMap<String, String> accounts =  new HashMap<String, String>();
	public static HashMap<String, String> depts = new HashMap<String, String>();
	public static HashMap<String, String> roles = new HashMap<String, String>();
	static HashMap<String, String> admins = new HashMap<String, String>();
	static HashMap<String, String> names = new HashMap<String, String>();

	//methods which return the contents of the hash-maps
	public HashMap<String, String> getPeopleMap() {
		return accounts;
	}
	
	public HashMap<String, String> getDeptMap() {
		return depts;
	}
	
	public HashMap<String, String> getAdminMap() {
		return admins;
	}

	public HashMap<String, String> getRolesMap() {
		// TODO Auto-generated method stub
		return roles;
	}
	
	public HashMap<String, String> getNamesMap() {
		return names;
	}
	
	
	//methods declare each hash-map as a serialized object
	//writing their contents to a .ser file every time the hash-map
	//is updated and loads it every time the software starts 
	public static void saveStud() {
		
		try { 
			FileOutputStream fileOut = new FileOutputStream("studentUsers.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(accounts);
			out.close();
			fileOut.close();
		} catch(IOException i) {
		}
	}
	
	public static void loadStud() {
		
		HashMap<String, String> map = null;
		
		try {
			FileInputStream fileIn = new FileInputStream("studentUsers.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			map = (HashMap) in.readObject();
			System.out.println("from reading " + map);
			in.close();
			fileIn.close();
			accounts.putAll(map);
			System.out.println("all " + accounts);
		} catch (IOException i) {
		
		} catch (ClassNotFoundException c) {
			
		}	
	}
	
	public static void saveDep() {
		
		try { 
			FileOutputStream fileOut = new FileOutputStream("departmentUsers.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(depts);
			out.close();
			fileOut.close();
		} catch(IOException i) {
		}
	}
	
	public static void loadDep() {
		
		HashMap<String, String> map2 = null;
		
		try {
			FileInputStream fileIn = new FileInputStream("departmentUsers.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			map2 = (HashMap) in.readObject();
			System.out.println("from reading2 " + map2);
			in.close();
			fileIn.close();
			depts.putAll(map2);
			System.out.println("all2 " + map2);
		} catch (IOException i) {
		
		} catch (ClassNotFoundException c) {
			
		}	
	}
	
	public static void saveRoles() {
		
		try { 
			FileOutputStream fileOut = new FileOutputStream("usersFaculties.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(roles);
			out.close();
			fileOut.close();
		} catch(IOException i) {
		}
	}

	public static void loadRoles() {
		
		HashMap<String, String> map3 = null;
		
		try {
			FileInputStream fileIn = new FileInputStream("usersFaculties.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			map3 = (HashMap) in.readObject();
			in.close();
			fileIn.close();
			roles.putAll(map3);
		} catch (IOException i) {
		
		} catch (ClassNotFoundException c) {
			
		}	
	}
	
	public static void saveAdmin() {
		
		try { 
			FileOutputStream fileOut = new FileOutputStream("adminUsers.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(admins);
			out.close();
			fileOut.close();
		} catch(IOException i) {
		}
	}
	
	public static void loadAdmin() {
		
		HashMap<String, String> map4 = null;
		
		try {
			FileInputStream fileIn = new FileInputStream("adminUsers.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			map4 = (HashMap) in.readObject();
			in.close();
			fileIn.close();
			admins.putAll(map4);
		} catch (IOException i) {
		
		} catch (ClassNotFoundException c) {
			
		}	
	}
	
	public static void saveNames() {
		
		try { 
			FileOutputStream fileOut = new FileOutputStream("studNames.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(names);
			out.close();
			fileOut.close();
		} catch(IOException i) {
		}
	}
	
	public static void loadNames() {
		
		HashMap<String, String> map5 = null;
		
		try {
			FileInputStream fileIn = new FileInputStream("studNames.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			map5 = (HashMap) in.readObject();
			in.close();
			fileIn.close();
			names.putAll(map5);
		} catch (IOException i) {
		
		} catch (ClassNotFoundException c) {
			
		}	
	}
}