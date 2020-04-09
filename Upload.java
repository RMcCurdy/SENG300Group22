package uploadGPA;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import java.io.File;
//import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


/*
 * Allows for a student to 
 */
public class Upload {

	protected Shell shlUofcScholarshipPortal;
	private Text txtEnter;

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
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlUofcScholarshipPortal = new Shell();
		shlUofcScholarshipPortal.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shlUofcScholarshipPortal.setSize(450, 300);
		shlUofcScholarshipPortal.setText("UofC Scholarship Portal"); // name of the program
		
		String currDir = System.getProperty("user.dir"); // get the current working directory
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
		
		Button btnChooseFile = new Button(shlUofcScholarshipPortal, SWT.NONE);
		btnChooseFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) { // when this button is selected...
				JFileChooser select = new JFileChooser(); // create file chooser
				select.showOpenDialog(null); // open file chooser
				//File uploadFile = select.getSelectedFile(); // file will be stored eventually
			}
		});
		btnChooseFile.setBounds(38, 149, 75, 25);
		btnChooseFile.setText("Choose File");
		
		Text txtEnter = new Text(shlUofcScholarshipPortal, SWT.BORDER);
		
		txtEnter.setText("GPA"); // set text within input field to let user know what should be entered there
		txtEnter.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		txtEnter.setFont(SWTResourceManager.getFont("Arial", 20, SWT.NORMAL));
		txtEnter.setBounds(317, 137, 76, 38);
		
		Label lblUpload = new Label(shlUofcScholarshipPortal, SWT.CENTER);
		lblUpload.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblUpload.setFont(SWTResourceManager.getFont("Arial", 16, SWT.BOLD));
		lblUpload.setBounds(146, 10, 142, 46);
		lblUpload.setText("UPLOAD GPA");
		
		Label lblDisplay = new Label(shlUofcScholarshipPortal, SWT.NONE);
		lblDisplay.setAlignment(SWT.CENTER);
		lblDisplay.setBounds(173, 133, 87, 80);
		
		try {
			Scanner outp = new Scanner(new File("gpa.txt"));
			String text = outp.useDelimiter("\\A").next();
			if (text.length() == 1) { // empty file
				lblDisplay.setText("--"); // primary generic text, to be replaced with the user's current gpa
			} else {
				System.out.println(text);
				lblDisplay.setText(text);
			}
			outp.close();
		} catch (IOException e) {
			System.out.println("An error has occured. The file was not found"); // something went wrong
		}
		
		// CHECK CONTENTS OF GPA.txt HERE
		// IF NOT BLANK, lblDisplay.setText("first 4 characters" + "name in file")
		// ELSE,
		
		Button btnSubmit = new Button(shlUofcScholarshipPortal, SWT.NONE);
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) { // when the submit button is pressed...
				String enteredGpa = txtEnter.getText(); // grab the entered text
		
				if (enteredGpa.length() == 4) { // length check for proper amount of numbers entered
					if (enteredGpa.charAt(1) == '.' && enteredGpa.charAt(0) <= '4' && enteredGpa.charAt(0) >= '0') { // second check, ensure there is a decimal, range, etc
						if (enteredGpa.charAt(0) == '4' && (enteredGpa.charAt(2) > '0' || enteredGpa.charAt(3) > '0')) { // gpa higher than 4.0 is not possible
							lblDisplay.setText("Your GPA cannot be greater than 4.00");
						} else {
							lblDisplay.setText(enteredGpa + "\n(Saved \nSuccessfully)"); // display on screen for user to see
							try {
								FileWriter inp = new FileWriter("gpa.txt"); // create new writer for gpa.txt
								// GRAB CURRENT USER'S LAST AND FIRST NAME HERE
								inp.write("First Last\n" + "First Last"); // write the user-entered gpa to the file
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
		btnSubmit.setBounds(318, 209, 75, 25);
		btnSubmit.setText("Submit"); // button label
		
		Label manual = new Label(shlUofcScholarshipPortal, SWT.NONE);
		manual.setFont(SWTResourceManager.getFont("Arial", 13, SWT.NORMAL));
		manual.setAlignment(SWT.CENTER);
		manual.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		manual.setBackground(SWTResourceManager.getColor(255, 51, 102));
		manual.setText("Enter Manually (Form of X.XX)"); // section label
		manual.setBounds(282, 62, 142, 187);
		
		Label lblCurr = new Label(shlUofcScholarshipPortal, SWT.NONE);
		lblCurr.setAlignment(SWT.CENTER);
		lblCurr.setBounds(173, 112, 87, 15);
		lblCurr.setText("Current GPA");
		
		Label lblUploadOfficialTranscript = new Label(shlUofcScholarshipPortal, SWT.NONE);
		lblUploadOfficialTranscript.setText("Upload Official Transcript");
		lblUploadOfficialTranscript.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblUploadOfficialTranscript.setFont(SWTResourceManager.getFont("Arial", 13, SWT.NORMAL));
		lblUploadOfficialTranscript.setBackground(SWTResourceManager.getColor(255, 51, 102));
		lblUploadOfficialTranscript.setAlignment(SWT.CENTER);
		lblUploadOfficialTranscript.setBounds(10, 62, 142, 187);
		

	}
}
