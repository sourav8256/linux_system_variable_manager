package window;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import window.callbacks.Callback;

public class AddVar extends JFrame {

	private JPanel contentPane;
	private JTextField vnameTF;
	private JTextField pathTF;
	
	DBHelper dbh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddVar frame = new AddVar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddVar(Callback callback) {
		
		dbh = new DBHelper();
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 425, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddNewPath = new JLabel("Add new path to path variable");
		lblAddNewPath.setBounds(119, 12, 208, 15);
		contentPane.add(lblAddNewPath);
		
		JLabel lblPath = new JLabel("PATH");
		lblPath.setBounds(31, 124, 66, 15);
		contentPane.add(lblPath);
		
		vnameTF = new JTextField();
		vnameTF.setBounds(119, 91, 223, 19);
		contentPane.add(vnameTF);
		vnameTF.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(178, 160, 114, 25);
		
		
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				dbh.insertVar(vnameTF.getText(), pathTF.getText());
				callback.call();
				dispose();
				
			}
			
		});
		
		
		contentPane.add(btnSubmit);
		
		pathTF = new JTextField();
		pathTF.setColumns(10);
		pathTF.setBounds(119, 122, 223, 19);
		contentPane.add(pathTF);
		
		JLabel lblVariableName = new JLabel("Variable Name");
		lblVariableName.setBounds(12, 93, 99, 15);
		contentPane.add(lblVariableName);
	}

}
