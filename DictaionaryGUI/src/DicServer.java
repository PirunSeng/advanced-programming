import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DicServer {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DicServer window = new DicServer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DicServer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDictionaryServer = new JLabel("Dictionary Server");
		lblDictionaryServer.setBounds(165, 6, 138, 22);
		frame.getContentPane().add(lblDictionaryServer);
		
		JLabel lblClients = new JLabel("Clients:");
		lblClients.setBounds(51, 58, 61, 16);
		frame.getContentPane().add(lblClients);
		
		JLabel label = new JLabel("0");
		label.setBounds(131, 58, 61, 16);
		frame.getContentPane().add(label);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnStart.setBounds(6, 101, 117, 29);
		frame.getContentPane().add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(165, 101, 117, 29);
		frame.getContentPane().add(btnStop);
	}
}
