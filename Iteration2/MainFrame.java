import java.awt.EventQueue;
import javax.swing.JFrame;

public class MainFrame {

	private JFrame frame;
	private Authenticator auth = new Authenticator();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public MainFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Login panel = new Login(frame, auth);
		frame.setContentPane(panel);
		frame.revalidate();
	}

}
