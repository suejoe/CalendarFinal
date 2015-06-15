package calendar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingWorker;


public class Calendars extends javax.swing.JFrame  {

	protected static ArrayList<Calendar> calendar_list = new ArrayList<Calendar>();
	int a=0;
	public Calendars() {
		initComponents();
	}

	private void initComponents() {
	
		scrollpane = new javax.swing.JScrollPane();
		tableBook = new javax.swing.JTable();
		loadButton = new javax.swing.JButton();
		deleteButton  = new javax.swing.JButton();
		saveButton = new javax.swing.JButton();
		buttonPanel = new javax.swing.JPanel();
		addButton = new javax.swing.JButton();
		JButton modifyButton = new javax.swing.JButton();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Calendar Management System");

		tableBook.setModel(new CalendarTableModel(new ArrayList<Calendar>()));
		scrollpane.setViewportView(tableBook);

		addButton.setText("add");
		addButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addButtonActionPerformed(evt);
			}
		});
		
		getContentPane().add(scrollpane, java.awt.BorderLayout.CENTER);
		saveButton.setText("save");
		saveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (a != 1){
					file_take(calendar_list);
				}
				saveButtonActionPerformed(evt);
				a=0;
				DeleteCalendar.del = 0;
			}
		});
		loadButton.setText("Load");
		loadButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loadButtonActionPerformed(evt);
			}
		});
		
		deleteButton.setText("delete");
		deleteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (DeleteCalendar.del == 0){
					file_take(calendar_list);
				}
				deleteButtonActionPerformed(evt);
				a=1;
			}
		});
		
		modifyButton.setText("modify");
		modifyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				file_take(calendar_list);
				modifyButtonActionPerformed(evt);
				a=1;
			}
		});
		
		///buttons
		buttonPanel.add(addButton);
		buttonPanel.add(modifyButton);
		buttonPanel.add(loadButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(deleteButton);
		
		
		
		getContentPane().add(buttonPanel, java.awt.BorderLayout.PAGE_END);
		
		pack();
	}
	
	static void file_take(ArrayList calendar_list){
		FileInputStream fin = null;
		ObjectInputStream ois = null;
	
		try {
			fin = new FileInputStream("calendarlist.txt");
			ois = new ObjectInputStream(fin);
			ArrayList list = (ArrayList) ois.readObject();
			for (int i = 0; i < list.size(); i++)
				calendar_list.add((Calendar) list.get(i));
	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				ois.close();
				fin.close();
			} catch (IOException ioe) {
			}
		}
	}
	
	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt){
		System.out.println("delete button!!!!");
		DeleteCalendar delete = new DeleteCalendar();
	}

	private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt){
		System.out.println("modify button!!!!");
		ModifyCalendar modify = new ModifyCalendar();
	}
	private void addButtonActionPerformed(java.awt.event.ActionEvent evt){
		System.out.println("add button!!!!");
		AddCalendar add = new AddCalendar();
	}
	private void saveButtonActionPerformed(java.awt.event.ActionEvent evt){
		System.out.println("save button!!!!");
		SwingWorker worker = new SwingWorker() {

			
			@Override
			protected Object doInBackground() throws Exception {
				// test for books
				FileOutputStream fout = null;
				ObjectOutputStream oos = null;
				
				try{
					fout = new FileOutputStream("calendarlist.txt");
					oos = new ObjectOutputStream(fout);
					oos.writeObject(calendar_list);
					oos.reset();
					oos.writeObject(calendar_list);
					oos.reset();
					
					System.out.println("저장되었습니다.\n");
					
				}catch(Exception ex){
				}finally{
					try{
						oos.close();
						fout.close();
					}catch(IOException ioe){}
				} // finally
				//6) 파일저장


				calendar_list.clear();
				return null;
			}
		};
		worker.execute();
	}
	
	private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_okButtonActionPerformed
		System.out.println("load button!!!!");
		SwingWorker worker = new SwingWorker() {

			@Override
			protected Object doInBackground() throws Exception {
				// test for books
				List<Calendar> calendars = new ArrayList<Calendar>();
				FileInputStream fin = null;
				ObjectInputStream ois = null;

				try {
					fin = new FileInputStream("calendarlist.txt");
					ois = new ObjectInputStream(fin);
					ArrayList list = (ArrayList) ois.readObject();
					for (int i = 0; i < list.size(); i++)
						calendars.add((Calendar) list.get(i));

				} catch (Exception e) {
					System.out.println(e.getMessage());
				} finally {
					try {
						ois.close();
						fin.close();
					} catch (IOException ioe) {
					}
				}
				tableBook.setModel(new CalendarTableModel(calendars));
				return null;
			}
		};
		worker.execute();
	}

	public static void main(String args[]) {

		/* Create and display the form */
		
		String a = "10";
		int b = Integer.parseInt(a);
		System.out.println(b);
		
		new Calendars().setVisible(true);


	}
	
	private javax.swing.JButton addButton;
	private javax.swing.JButton loadButton;
	private javax.swing.JButton saveButton;
	private javax.swing.JButton deleteButton;
	private javax.swing.JScrollPane scrollpane;
	private javax.swing.JTable tableBook;
	private javax.swing.JPanel buttonPanel;
	

}
