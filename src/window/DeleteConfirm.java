package window;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import window.callbacks.Callback;

import javax.swing.JLabel;
import javax.swing.JButton;

public class DeleteConfirm extends JFrame {

	private JPanel contentPane;
	
	DBHelper dbh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteConfirm frame = new DeleteConfirm("test path",new Callback() {
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
	public DeleteConfirm(String path,Callback callback) {
	
		dbh = new DBHelper();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAreYouSure = new JLabel("Are You Sure You Want To Delete The Path");
		lblAreYouSure.setBounds(72, 92, 292, 15);
		contentPane.add(lblAreYouSure);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(42, 169, 114, 25);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
			
		});
		contentPane.add(btnCancel);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(277, 169, 114, 25);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dbh.deleteRow(path);
				callback.call();
				dispose();
			}
			
		});
		contentPane.add(btnDelete);
	}

}
