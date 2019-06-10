package window;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import window.activities.Popup;
import window.callbacks.Callback;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddPathVar extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	DBHelper dbh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPathVar frame = new AddPathVar(new Callback() {
						@Override
						public void call() {

						}
					});
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
	public AddPathVar(Callback callback) {

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
		lblPath.setBounds(31, 95, 66, 15);
		contentPane.add(lblPath);

		textField = new JTextField();
		textField.setBounds(119, 91, 223, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(180, 122, 114, 25);

		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				if (Validator.validatePath(textField.getText())) {

					dbh.insertPath(textField.getText());
					callback.call();
					dispose();

				} else {
					//new Popup("Variable names cannot contain spaces").setVisible(true);
				}

			}

		});

		contentPane.add(btnSubmit);
	}
}
