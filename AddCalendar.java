package calendar;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddCalendar {

	public AddCalendar(){
		init("add");
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
	
		String[] labels = {"num", "part", "title", "target_grade", "target_major", "period_start", "period_end", "publisher"};
		int[] widths = { 10, 10, 10, 10, 10, 10, 10, 10};
	
		JTextField[] fields;
		fields = new JTextField[labels.length];
		JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));
		//TextField(입력필드)를 위한 Panel 생성 
		JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));
		//반복문사용하여 label이랑 값 입력 받기위한 textfield 추가 
		for (int i = 0; i < labels.length; i += 1) {
			fields[i] = new JTextField();
			if (i < widths.length)
				fields[i].setColumns(widths[i]);
			JLabel lab = new JLabel(labels[i], JLabel.RIGHT);
			lab.setLabelFor(fields[i]);
			labelPanel.add(lab);
			JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
			p.add(fields[i]);
			fieldPanel.add(p);
		}
		f.add(labelPanel, java.awt.BorderLayout.WEST);
		f.add(fieldPanel, java.awt.BorderLayout.EAST);
		f.add(buttonPanel, java.awt.BorderLayout.PAGE_END);
		
		
		addButton.setText("add");
		addButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				List<String> list = new ArrayList<String>();
				for(int i = 0;i<fields.length;i++){
					String getTxt = fields[i].getText();
					list.add(getTxt);
				}
				String num = list.get(0);
				String part= list.get(1);//장학,취업,인턴,공모,공지 등
				String title= list.get(2);// 글제목
				int target_grade= Integer.parseInt(list.get(3));// 타겟 학년
				String target_major=list.get(4);// 타겟 단대
				String period_start= list.get(5);// 접수시작일
				String period_end= list.get(6);// 접수마감일
				String publisher= list.get(7);// 글쓴이
				Calendar new_cal = new Calendar(num, part, title, target_grade, target_major, period_start, period_end, publisher);
				if (part.equals("") || title.equals("") || target_major.equals("") || period_start.equals("") || period_end.equals("") || publisher.equals("")){
					System.out.println("입력값이 없습니다.");
				} else {
					System.out.println("add!! : "+new_cal.toString());
					Calendars.calendar_list.add(new_cal);
				}
				f.dispose();
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
	private void addButtonActionPerformed(java.awt.event.ActionEvent evt){
		System.out.println("add!!");
	}
	private void stopButtonActionPerformed(java.awt.event.ActionEvent evt){
		System.out.println("stop!!");
	}
}
