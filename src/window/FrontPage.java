package window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import window.callbacks.Callback;
import window.model.PathVar;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class FrontPage {

	private JFrame frame;
	private JTable table;
	
	DBHelper dbh;
	
	String[][] data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontPage window = new FrontPage();
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
	public FrontPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		dbh = new DBHelper();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 725, 464);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("System Variables");
		lblNewLabel.setBounds(226, 12, 118, 15);
		frame.getContentPane().add(lblNewLabel);
		
		

		
		// show two column list
		
		
		

		
		JButton addPathBtn = new JButton("Add PATH");
		addPathBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddPathVar apv = new AddPathVar(new Callback() {
					@Override
					public void call() {
						data = genArrForTable(dbh.getList());
						table.repaint();
					}
				});
				apv.setVisible(true);
			}
		});
		addPathBtn.setBounds(552, 111, 161, 25);
		frame.getContentPane().add(addPathBtn);
		
		JButton addVarBtn = new JButton("Add Variable");
		addVarBtn.setBounds(552, 148, 161, 25);
		addVarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddVar av = new AddVar(new Callback() {

					@Override
					public void call() {
						// TODO Auto-generated method stub
						
						data = genArrForTable(dbh.getList());
						table.repaint();
						
						
					}
					
				});
				av.setVisible(true);
			}
		});
		frame.getContentPane().add(addVarBtn);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(552, 185, 161, 25);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeleteConfirm dc = new DeleteConfirm(table.getValueAt(table.getSelectedRow(), 1).toString(),new Callback() {
					@Override
					public void call() {
						data = genArrForTable(dbh.getList());
						table.repaint();
					}
				});
			}
		});
		frame.getContentPane().add(deleteBtn);
		
		
		// refresh on data change
			// how to change table data
		
		String[] headers = {"Variable Name","Path"};
		
		data = genArrForTable(dbh.getList());
		
		table = new JTable(data,headers);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setMinWidth(150);
		table.getColumnModel().getColumn(1).setMinWidth(450);
		table.setPreferredScrollableViewportSize(new Dimension(500,50));
		table.setBounds(75, 77, 197, 96);
		
		
		
		JScrollPane jsp = new JScrollPane(table);
		jsp.setLocation(0, 41);
		jsp.setSize(540, 381);
		frame.getContentPane().add(jsp);
		
	}
	
	
	String[][] genArrForTable(ArrayList<PathVar> list){
		
		String[][] array = new String[list.size()][2];
		
		for(int i=0;i<array.length;i++) {
			array[i][0] = list.get(i).getvName();
			array[i][1] = list.get(i).getPath();
		}
		
		return array;
	}
	
}
