package studentRelated;

// Import statements
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


/*
 * Allows for a student to upload and update their GPA, as well as their official transcript
 * Will be treated as a pop-up, not its own full page. This allows for a better user experience
 * Must be exported as a runnable JAR file, due to the use of SWT
 * @Author Richard Gingrich
 */
public class Upload {

	protected Shell shlUofcScholarshipPortal;
	protected Shell display;

	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Upload window = new Upload(); // create window
			window.open(); // open the window
		} catch (Exception e) {
			e.printStackTrace(); // in case of errors
		}
	}
	

	/**
	 * Open the window
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents(); // create the various objects of the window
		shlUofcScholarshipPortal.open();
		shlUofcScholarshipPortal.layout();
		while (!shlUofcScholarshipPortal.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window
	 * Handles all button presses, user inputs, etc
	 */
	protected void createContents() {
		//shlUofcScholarshipPortal = new Shell();
		shlUofcScholarshipPortal = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN); // adds the close and min buttons, but no fullscreen option
		shlUofcScholarshipPortal.setSize(450, 300);
		shlUofcScholarshipPortal.setText("UofC Scholarship Portal"); // name of the program
		String currDir = System.getProperty("user.dir"); // get the current working directory
		
		// Create a txt file which stores the user's GPA
		String newFileDir = currDir + "\\gpa.txt"; // include gpa.txt
		File check = new File(newFileDir); // check if the file exists
		if (check.exists()) { // if the file already exists...
			System.out.println("Found file gpa.txt in path " + newFileDir); // show where the file was found
		} else { // if the file does not exist...
			try {
				check.createNewFile(); // create the file
				System.out.println("Created gpa.txt, with path " + newFileDir); // state where the file was created
			} catch (IOException e) {
				System.out.println("Something went wrong"); // error caught
			}
		}
		
		// A pop-up message that confirms if the user's file was successfully uploaded
		Label lblSucc = new Label(shlUofcScholarshipPortal, SWT.NONE);
		lblSucc.setAlignment(SWT.CENTER);
		lblSucc.setBounds(38, 109, 75, 34);
		lblSucc.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		lblSucc.setBackground(SWTResourceManager.getColor(245, 194, 66));
		
		// Title label
		Label lblUpload = new Label(shlUofcScholarshipPortal, SWT.CENTER);
		lblUpload.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblUpload.setBackgroundImage(SWTResourceManager.getImage(currDir + "\\background_res.jpg"));
		lblUpload.setFont(SWTResourceManager.getFont("Arial", 16, SWT.BOLD));
		lblUpload.setBounds(146, 10, 142, 25);
		lblUpload.setText("UPLOAD GPA");
		
		// Allows for uploading of the user's transcript
		Button btnChooseFile = new Button(shlUofcScholarshipPortal, SWT.NONE);
		btnChooseFile.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnChooseFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) { // when this button is selected...
				JFileChooser select = new JFileChooser(); // create file chooser
				int picked = select.showOpenDialog(null);
				if (picked == JFileChooser.APPROVE_OPTION) {
					File fileToSave = select.getSelectedFile(); // grab the submitted file
				    System.out.println("Saved file in " + currDir);
				    lblSucc.setText("Uploaded\n Successfully");
				} else {
					System.out.println("No file to save");
					lblSucc.setText("No file selected");
				}
			}
		});
		
		// Format for the button
		btnChooseFile.setBounds(38, 147, 75, 25);
		btnChooseFile.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		btnChooseFile.setText("Choose File");
		
		// Text field where the user is able to manually submit their GPA number in the form of X.XX
		Text txtEnter = new Text(shlUofcScholarshipPortal, SWT.BORDER);
		txtEnter.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		txtEnter.setText("GPA"); // set text within input field to let user know what should be entered there
		txtEnter.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtEnter.setFont(SWTResourceManager.getFont("Arial", 20, SWT.NORMAL));
		txtEnter.setBounds(317, 122, 76, 38);
		
		// A label that displays the user's current GPA
		Label lblDisplay = new Label(shlUofcScholarshipPortal, SWT.NONE);
		lblDisplay.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblDisplay.setBackground(SWTResourceManager.getColor(245, 194, 66));
		lblDisplay.setAlignment(SWT.CENTER);
		lblDisplay.setBounds(173, 122, 87, 80);
		
		// Read the contents of gpa.txt if it is found
		try {
			Scanner outp = new Scanner(new File("gpa.txt"));
			if (outp.hasNext()) {
				String text = outp.useDelimiter("\\A").next();
				if (text.length() == 1) { // empty file
					lblDisplay.setText("--"); // primary generic text, to be replaced with the user's current GPA
				} else {
					lblDisplay.setText(text); // found an existing GPA, so display that
				}
			} else {
				lblDisplay.setText("--");
			}
			outp.close();
		} catch (IOException e) {
			System.out.println("An error has occured. The file was not found"); // something went wrong
		}
		
		// Handles grabbing the user's manually inputed GPA
		Button btnSubmit = new Button(shlUofcScholarshipPortal, SWT.NONE);
		btnSubmit.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnSubmit.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) { // when the submit button is pressed...
				String enteredGpa = txtEnter.getText(); // ...grab the entered text
		
				if (enteredGpa.length() == 4) { // length check for proper amount of numbers entered
					if (enteredGpa.charAt(1) == '.' && enteredGpa.charAt(0) <= '4' && enteredGpa.charAt(0) >= '0') { // second check, ensure there is a decimal, range, etc
						if (enteredGpa.charAt(0) == '4' && (enteredGpa.charAt(2) > '0' || enteredGpa.charAt(3) > '0')) { // gpa higher than 4.0 is not possible
							lblDisplay.setText("Your GPA cannot be greater than 4.00");
						} else {
							lblDisplay.setText(enteredGpa + "\n(Saved \nSuccessfully)"); // display on screen for user to see
							try {
								FileWriter inp = new FileWriter("gpa.txt"); // create new writer for gpa.txt
								inp.write("Joe Tester" + "\n" + enteredGpa); // write name and the user-entered gpa to the file
								inp.close(); // close the writer
							} catch (IOException i) {
								System.out.println("An error has occured"); // something went wrong
							}
						}
					} else { // incorrect format
						lblDisplay.setText("This is not a valid format, please enter your GPA in the form of X.XX");
					}
				} else { // correct format has not been entered
					lblDisplay.setText("This is not a valid format, please enter your GPA in the form of X.XX");
				}
			}
		});
		btnSubmit.setBounds(317, 181, 75, 25);
		btnSubmit.setText("Submit"); // button label
		
		// Section for manual input, visual only
		Label manual = new Label(shlUofcScholarshipPortal, SWT.NONE);
		manual.setFont(SWTResourceManager.getFont("Arial", 13, SWT.NORMAL));
		manual.setAlignment(SWT.CENTER);
		manual.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		manual.setBackground(SWTResourceManager.getColor(245, 194, 66));
		manual.setText("Enter Manually (Form of X.XX)"); // section label
		manual.setBounds(282, 62, 142, 160);
		
		// Displays the current user's GPA if valid
		Label lblCurr = new Label(shlUofcScholarshipPortal, SWT.NONE);
		lblCurr.setAlignment(SWT.CENTER);
		lblCurr.setBounds(173, 91, 87, 15);
		lblCurr.setBackground(SWTResourceManager.getColor(245, 194, 66));
		lblCurr.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblCurr.setText("Current GPA");
		
		// Section for document upload, visual only
		Label lblUploadOfficialTranscript = new Label(shlUofcScholarshipPortal, SWT.NONE);
		lblUploadOfficialTranscript.setText("Upload Official Transcript");
		lblUploadOfficialTranscript.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblUploadOfficialTranscript.setFont(SWTResourceManager.getFont("Arial", 13, SWT.NORMAL));
		lblUploadOfficialTranscript.setBackground(SWTResourceManager.getColor(245, 194, 66));
		lblUploadOfficialTranscript.setAlignment(SWT.CENTER);
		lblUploadOfficialTranscript.setBounds(10, 62, 142, 160);
		
		// Background image, resized to fit window
		Label bck = new Label(shlUofcScholarshipPortal, SWT.NONE);
		bck.setImage(SWTResourceManager.getImage(currDir + "\\background_res.jpg")); // image will be saved inside the lib package
		bck.setBackground(SWTResourceManager.getColor(240, 240, 240));
		bck.setBounds(0, 0, 452, 277);

	}
}
