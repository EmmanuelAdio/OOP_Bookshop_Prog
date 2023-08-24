

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public MainFrame() throws FileNotFoundException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 655, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//get the contents inside the user file.
		ArrayList<User> Users = UserFileManager.getUsers();
		
		String UsType[] = {"admin","customer"};
		JComboBox UserType = new JComboBox(UsType);
		UserType.setBounds(277, 151, 89, 22);
		contentPane.add(UserType);
		
		JComboBox<String> UserChoice = new JComboBox<String>();
		UserChoice.setBounds(234, 184, 174, 22);
		contentPane.add(UserChoice);
		
		UserType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (UserType.getSelectedItem().equals("admin")) {
					//remove the contenst already on the user choice combo box
					UserChoice.removeAllItems();
					
					//add the new content to the combo box. 
					for(User us : Users) {
						if (us instanceof Admin) {
							UserChoice.addItem(us.getUsername());
						}
						
					}
				}else {
					//remove the contenst already on the user choice combo box
					UserChoice.removeAllItems();
					
					//add the new content to the combo box. 
					for(User us : Users) {
						if (us instanceof Customer) {
							UserChoice.addItem(us.getUsername());
						}
					}
				}
			}
		});
		
		JButton LoginBtn = new JButton("Login");
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (UserChoice.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Please chose a User", "ERROR:", JOptionPane.ERROR_MESSAGE);
				} else {
					for(User us : Users) {
						if (us.getUsername().equals(UserChoice.getSelectedItem())) {
							if (us instanceof Customer) {
								//create a new instance of the customer frame and open it.
								CustomerFrame Frame = new CustomerFrame((Customer) us);
								Frame.setVisible(true);
								dispose();
							}else {
								AdminFrame Frame = null;
								try {
									//create a new instance of the Admin frame and open it.
									Frame = new AdminFrame((Admin) us);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								Frame.setVisible(true);
								dispose();
							}//displos of the main menu frame after you have open the new Customer or Admin frame.
						}
					}
				}
			}
		});
		LoginBtn.setBounds(277, 217, 89, 23);
		contentPane.add(LoginBtn);
		
		
		
		
	}
}
