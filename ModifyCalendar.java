package calendar;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModifyCalendar {


	public ModifyCalendar(){
		init("modify");
	}
	public void init(String title){

			
		CardLayout cl = null;
		//JFrame ;
		
		JFrame f1 = new JFrame(title);
		f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f1.pack();
		f1.setSize(300, 300);
		f1.setVisible(true);
		

		JButton modifyButton1 = new javax.swing.JButton();
		JButton stopButton1 = new javax.swing.JButton();
		JPanel textPanel1 = new javax.swing.JPanel();
		JPanel buttonPanel1 = new javax.swing.JPanel();
		
		

		buttonPanel1.add(modifyButton1);
		buttonPanel1.add(stopButton1);
	
		modifyButton1.addActionListener((ActionListener) this);
		
		JPanel pnl1 = new javax.swing.JPanel();
		JPanel pnl2 = new javax.swing.JPanel();
	    JPanel panelCard = new JPanel();
		

		panelCard.setLayout(cl);
		panelCard.add(pnl1);
        panelCard.add(pnl2);
		
		
		JTextField[] fields1;
		fields1 = new JTextField[1];
		JPanel labelPanel1 = new JPanel(new GridLayout(1, 1));
		//TextField(입력필드)를 위한 Panel 생성 
		JPanel fieldPanel1 = new JPanel(new GridLayout(1, 1));
		//반복문사용하여 label이랑 값 입력 받기위한 textfield 추가 
		fields1[0] = new JTextField();
		fields1[0].setColumns(5);
		JLabel lab1 = new JLabel("modify num?", JLabel.RIGHT);
		lab1.setLabelFor(fields1[0]);
		labelPanel1.add(lab1);
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p1.add(fields1[0]);
		fieldPanel1.add(p1);
		
		pnl1.add(labelPanel1, java.awt.BorderLayout.WEST);
		pnl1.add(fieldPanel1, java.awt.BorderLayout.EAST);
		pnl1.add(buttonPanel1, java.awt.BorderLayout.PAGE_END);
		

		ArrayList input = new ArrayList<>();
		
		JButton modifyButton = new javax.swing.JButton();
		JButton stopButton = new javax.swing.JButton();
		JPanel textPanel = new javax.swing.JPanel();
		JPanel buttonPanel = new javax.swing.JPanel();

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
				fields[i].setText((String) input.get(i));
			JLabel lab = new JLabel(labels[i], JLabel.RIGHT);
			lab.setLabelFor(fields[i]);
			labelPanel.add(lab);
			JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
			p.add(fields[i]);
			fieldPanel.add(p);
		}
		modifyButton.setText("modify");
		
		pnl2.add(labelPanel, java.awt.BorderLayout.WEST);
		pnl2.add(fieldPanel, java.awt.BorderLayout.EAST);
		pnl2.add(buttonPanel, java.awt.BorderLayout.PAGE_END);
		int getnum=0;
		modifyButton1.setText("check");
		modifyButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				
				if(evt.getActionCommand().equals("check"))
		        {
					System.out.println("num checked!!");
					int getnum = Integer.parseInt(fields1[0].getText());
					System.out.println(getnum);
					String num = Calendars.calendar_list.get(getnum).getNum();
					String part = Calendars.calendar_list.get(getnum).getPart();
					String title = Calendars.calendar_list.get(getnum).getTitle();
					int target_grade = Calendars.calendar_list.get(getnum).getTargetGrade();
					String target_major = Calendars.calendar_list.get(getnum).getTargetMajor();
					String period_start = Calendars.calendar_list.get(getnum).getPeriodStart();
					String period_end = Calendars.calendar_list.get(getnum).getPeriodEnd();
					String publisher = Calendars.calendar_list.get(getnum).getPublisher();
					
					input.add(num);
					input.add(part);
					input.add(title);
					input.add(target_grade);
					input.add(target_major);
					input.add(period_start);
					input.add(period_end);
					input.add(publisher);
				
		            cl.next(panelCard); //자신을 소유한 부모 컨테이너에게 변화를 알려야 함
		        }
		        else if(evt.getActionCommand().equals("close"))
		        {
		            System.exit(0);
		        }
				
				

			}
		});
		modifyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				List<String> list = new ArrayList<String>();
				for(int i = 0;i<fields.length;i++){
					String getTxt = fields[i].getText();
					list.add(getTxt);
				}
				String num_ = list.get(0);
				String part_= list.get(1);//장학,취업,인턴,공모,공지 등
				String title_= list.get(2);// 글제목
				int target_grade_= Integer.parseInt(list.get(3));// 타겟 학년
				String target_major_=list.get(4);// 타겟 단대
				String period_start_= list.get(5);// 접수시작일
				String period_end_= list.get(6);// 접수마감일
				String publisher_= list.get(7);// 글쓴이
				Calendar new_cal_ = new Calendar(num_, part_, title_, target_grade_, target_major_, period_start_, period_end_, publisher_);
				if (part_.equals("") || title_.equals("") || target_major_.equals("") || period_start_.equals("") || period_end_.equals("") || publisher_.equals("")){
					System.out.println("입력값이 없습니다.");
				} else {
					System.out.println("add!! : "+new_cal_.toString());
					Calendars.calendar_list.set(getnum,new_cal_);
				}
				f1.dispose();
			}
		});
		stopButton.setText("cancel");
		stopButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				stopButtonActionPerformed(evt);
				
				f1.dispose();
			}
		});
		stopButton1.setText("cancel");
		stopButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				stopButtonActionPerformed(evt);
				f1.dispose();
			}
		});
		
		buttonPanel1.add(modifyButton1);
		buttonPanel1.add(stopButton1);
	
				
	}
	private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt){
		System.out.println("modify!!");
	}
	private void stopButtonActionPerformed(java.awt.event.ActionEvent evt){
		System.out.println("stop!!");
	}
}
