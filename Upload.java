//package uploadGPA;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
		shlUofcScholarshipPortal.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		shlUofcScholarshipPortal.setSize(450, 300);
		shlUofcScholarshipPortal.setText("UofC Scholarship Portal"); // name of the program
		
		String currDir = System.getProperty("user.dir"); // get the current working directory
		String newFileDir = currDir + "\\gpa.txt"; // include gpa.txt
		File check = new File(newFileDir); // check if the file exists
		if (check.exists()) { // if the file already exists...
			System.out.println("Found file in path " + newFileDir); // show where the file was found
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
		btnChooseFile.setBounds(40, 209, 75, 25);
		btnChooseFile.setText("Choose File");
		
		txtEnter = new Text(shlUofcScholarshipPortal, SWT.BORDER);
		txtEnter.setText("GPA"); // set text within input field to let user know what should be entered there
		txtEnter.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		txtEnter.setFont(SWTResourceManager.getFont("Arial", 20, SWT.NORMAL));
		txtEnter.setBounds(316, 95, 76, 38);
		
		Label lblUpload = new Label(shlUofcScholarshipPortal, SWT.CENTER);
		lblUpload.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblUpload.setFont(SWTResourceManager.getFont("Arial", 16, SWT.BOLD));
		lblUpload.setBounds(146, 10, 142, 46);
		lblUpload.setText("UPLOAD GPA"); // section label
		
		Label lblDropBox = new Label(shlUofcScholarshipPortal, SWT.NONE);
		lblDropBox.setText("Drop Transcript Here"); // section label
		lblDropBox.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblDropBox.setFont(SWTResourceManager.getFont("Arial", 13, SWT.NORMAL));
		lblDropBox.setAlignment(SWT.CENTER);
		lblDropBox.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		lblDropBox.setBounds(10, 62, 142, 187);
		
		//DropTarget dropTarget = new DropTarget(lblNewLabel, DND.DROP_MOVE);
		
		Label lblDisplay = new Label(shlUofcScholarshipPortal, SWT.NONE);
		lblDisplay.setAlignment(SWT.CENTER);
		lblDisplay.setBounds(196, 133, 52, 80);
		lblDisplay.setText("--"); // primary generic text, to be replaced with the user's current gpa
		
		Button btnSubmit = new Button(shlUofcScholarshipPortal, SWT.NONE);
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) { // when the submit button is pressed...
				String enteredGpa = txtEnter.getText(); // grab the entered text
				//float gpa = Float.parseFloat(enteredGpa); // attempt to convert to float FIX THIS, HAVE A CATCH!!!
				// DO ALL TESTS HERE (0.0 < gpa < 4.0, |gpa| = 3)
				// IF TESTS PASS,
				lblDisplay.setText(enteredGpa);
				try {
					FileWriter inp = new FileWriter("gpa.txt");
					inp.write(enteredGpa);
				} catch (IOException i) {
					System.out.println("An error has occured"); // something went wrong
				} //finally {
					//inp.close();
				//}
			}
		});
		btnSubmit.setBounds(317, 167, 75, 25);
		btnSubmit.setText("Submit"); // button label
		
		Label manual = new Label(shlUofcScholarshipPortal, SWT.NONE);
		manual.setFont(SWTResourceManager.getFont("Arial", 13, SWT.NORMAL));
		manual.setAlignment(SWT.CENTER);
		manual.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		manual.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		manual.setText("Enter Manually"); // section label
		manual.setBounds(282, 62, 142, 187);
		
		Label lblCurr = new Label(shlUofcScholarshipPortal, SWT.NONE);
		lblCurr.setAlignment(SWT.CENTER);
		lblCurr.setBounds(173, 112, 87, 15);
		lblCurr.setText("Current GPA");
		

	}
}
