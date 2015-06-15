package calendar;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteCalendar {
	
	public static int del;

	public DeleteCalendar(){
		init("Delete");
	}
	public void init(String title){

			
		JFrame f = new JFrame(title);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.pack();
		f.setSize(300, 300);
		f.setVisible(true);
		
		JButton addButton = new javax.swing.JButton();
		JButton stopButton = new javax.swing.JButton();
		JPanel textPanel = new javax.swing.JPanel();
		JPanel buttonPanel = new javax.swing.JPanel();
		
		

		buttonPanel.add(addButton);
		buttonPanel.add(stopButton);
	
		JTextField[] fields;
		fields = new JTextField[1];
		JPanel labelPanel = new JPanel(new GridLayout(1, 1));
		//TextField(입력필드)를 위한 Panel 생성 
		JPanel fieldPanel = new JPanel(new GridLayout(1, 1));
		//반복문사용하여 label이랑 값 입력 받기위한 textfield 추가 
		fields[0] = new JTextField();
		fields[0].setColumns(5);
		JLabel lab = new JLabel("delete num?", JLabel.RIGHT);
		lab.setLabelFor(fields[0]);
		labelPanel.add(lab);
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p.add(fields[0]);
		fieldPanel.add(p);
		
		f.add(labelPanel, java.awt.BorderLayout.WEST);
		f.add(fieldPanel, java.awt.BorderLayout.EAST);
		f.add(buttonPanel, java.awt.BorderLayout.PAGE_END);
		
		
		addButton.setText("delete");
		addButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println("delete!!");
				int getnum = Integer.parseInt(fields[0].getText());
				System.out.println(getnum);
				Calendars.calendar_list.remove(getnum-1);
				f.dispose();
				del=1;
			}
		});
		stopButton.setText("cancel");
		stopButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				stopButtonActionPerformed(evt);
				f.dispose();
			}
		});
	}
	private void stopButtonActionPerformed(java.awt.event.ActionEvent evt){
		System.out.println("stop!!");
	}
}
