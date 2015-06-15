package calendar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CalendarProgram{
    static JLabel lblMonth, lblYear;
    static JButton btnPrev, btnNext, btnAll, btnNotice, btnFund, btnJob, btnDelete, btnApply;
    static JTable tblCalendar;
    static JTable tblApply;
    static JTable tblRecommand;
    static JComboBox cmbYear;
    static JFrame frmMain;
    static Container pane;
    static DefaultTableModel mtblCalendar; //Table model
    static JScrollPane stblCalendar; //The scrollpane
    static DefaultTableModel mtblApply; //Table model
    static JScrollPane stblApply; //The scrollpane
    static DefaultTableModel mtblRecommand; //Table model
    static JScrollPane stblRecommand; //The scrollpane
    static JPanel pnlCalendar;
    static JPanel pnlApply;
    static JPanel pnlRecommand;
    //static JPanel pnlBtn;
    static int realYear, realMonth, realDay, currentYear, currentMonth;
    protected static ArrayList<Calendar> apply_list = new ArrayList<Calendar>();
    protected static ArrayList<Calendar> calendar_pro_list = new ArrayList<Calendar>();
    
    public static void main (String args[]){
    	
    	Calendars.file_take(calendar_pro_list);
    	
        //Look and feel
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        catch (UnsupportedLookAndFeelException e) {}
        
        //Prepare frame
        frmMain = new JFrame ("일정 관리 프로그램"); //Create frame
        frmMain.setSize(500, 430); //Set size to pixels
        pane = frmMain.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked
        
        //Create controls
        lblMonth = new JLabel ("January");
        lblYear = new JLabel ("Change year:");
        cmbYear = new JComboBox();
        btnAll = new JButton ("전체");
        btnNotice = new JButton ("공지");
        btnFund = new JButton ("장학");
        btnJob = new JButton ("취업");
        btnPrev = new JButton ("<-");
        btnNext = new JButton ("->");
        btnApply = new JButton ("별표");
        mtblCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        tblCalendar = new JTable(mtblCalendar);
        stblCalendar = new JScrollPane(tblCalendar);
        mtblApply = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        tblApply = new JTable(mtblApply);
        stblApply = new JScrollPane(tblApply);
        mtblRecommand = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        tblRecommand = new JTable(mtblRecommand);
        stblRecommand = new JScrollPane(tblRecommand);
        pnlCalendar = new JPanel(null);
        pnlApply = new JPanel(null);
        btnDelete = new JButton ("삭제");
        pnlRecommand = new JPanel(null);
        //pnlBtn = new JPanel(null);
        
        //Set border
        pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));
        pnlApply.setBorder(BorderFactory.createTitledBorder("Apply"));
        pnlRecommand.setBorder(BorderFactory.createTitledBorder("Recommand"));
        //pnlBtn.setBorder(BorderFactory.createTitledBorder("Btn"));
        
        //Register action listeners
        btnPrev.addActionListener(new btnPrev_Action());
        btnNext.addActionListener(new btnNext_Action());
        cmbYear.addActionListener(new cmbYear_Action());
        btnAll.addActionListener(new btnAll_Action());
        btnNotice.addActionListener(new btnNotice_Action());
        btnFund.addActionListener(new btnFund_Action());
        btnJob.addActionListener(new btnJob_Action());
        btnDelete.addActionListener(new btnDelete_Action());
        btnApply.addActionListener(new btnApply_Action());
        
        //Add controls to pane
        pane.add(pnlCalendar);
        pnlCalendar.add(lblMonth);
        pnlCalendar.add(btnAll);
        pnlCalendar.add(btnNotice);
        pnlCalendar.add(btnFund);
        pnlCalendar.add(btnJob);
        pnlCalendar.add(lblYear);
        pnlCalendar.add(cmbYear);
        pnlCalendar.add(btnPrev);
        pnlCalendar.add(btnNext);
        pnlCalendar.add(btnApply);
        pnlCalendar.add(stblCalendar);
        
        /*pnlBtn.add(btnAll);
        pnlBtn.add(btnNotice);
        pnlBtn.add(btnFund);
        pnlBtn.add(btnJob);
        pnlCalendar.add(pnlBtn);*/
        
        //Set bounds
        pnlCalendar.setBounds(0, 2, 320, 380);
        lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 60, 100, 25);
        //pnlBtn.setBounds(160-lblMonth.getPreferredSize().width/2-105,20, 240,20);
        btnAll.setBounds(160-lblMonth.getPreferredSize().width/2-105, 20, 60, 20);
        btnNotice.setBounds(160-lblMonth.getPreferredSize().width/2-35, 20, 60, 20);
        btnFund.setBounds(160-lblMonth.getPreferredSize().width/2+35, 20, 60, 20);
        btnJob.setBounds(160-lblMonth.getPreferredSize().width/2+105, 20, 60, 20);
        lblYear.setBounds(10, 340, 80, 20);
        cmbYear.setBounds(100, 340, 80, 20);
        btnPrev.setBounds(10, 60, 50, 25);
        btnNext.setBounds(260, 60, 50, 25);
        btnApply.setBounds(230, 340, 65, 23);
        stblCalendar.setBounds(10, 85, 300, 250);
        
       
        
        tblApply = new JTable(mtblApply);
        stblApply = new JScrollPane(tblApply);
        
        pane.add(pnlApply);
        pnlApply.add(stblApply);
        pnlApply.add(btnDelete);
        
        pnlApply.setBounds(320, 2, 160, 185);
        stblApply.setBounds(325, 5, 150, 175);
        btnDelete.setBounds(360, 155, 30, 20);
        
        tblRecommand = new JTable(mtblRecommand);
        stblRecommand = new JScrollPane(tblRecommand);
        
        pane.add(pnlRecommand);
        pnlApply.add(stblApply);
        pnlRecommand.setBounds(320, 202, 160, 180);
        stblApply.setBounds(325, 205, 150, 175);
        
        
        //Make frame visible
        frmMain.setResizable(false);
        frmMain.setVisible(true);
        
        //Get real month/year
        GregorianCalendar cal = new GregorianCalendar(); //Create calendar
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        realMonth = cal.get(GregorianCalendar.MONTH); //Get month
        realYear = cal.get(GregorianCalendar.YEAR); //Get year
        currentMonth = realMonth; //Match month and year
        currentYear = realYear;
        
        //Add headers
        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
        for (int i=0; i<7; i++){
            mtblCalendar.addColumn(headers[i]);
        }
        
        tblCalendar.getParent().setBackground(tblCalendar.getBackground()); //Set background
        
        //No resize/reorder
        tblCalendar.getTableHeader().setResizingAllowed(false);
        tblCalendar.getTableHeader().setReorderingAllowed(false);
        
        //Single cell selection
        tblCalendar.setColumnSelectionAllowed(true);
        tblCalendar.setRowSelectionAllowed(true);
        tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //Set row/column count
        tblCalendar.setRowHeight(38);
        mtblCalendar.setColumnCount(7);
        mtblCalendar.setRowCount(6);
        
        //Populate table
        for (int i=realYear-100; i<=realYear+100; i++){
            cmbYear.addItem(String.valueOf(i));
        }
        
        //Refresh calendar
        refreshCalendar (realMonth, realYear); //Refresh calendar
        
        
/////////////
        //Add headers
        String[] headers_a = {"year","month","day","year_end","month_end","day_end", "Keyword"}; //All headers
        for (int i=0; i<7; i++){
            mtblApply.addColumn(headers_a[i]);
        }
        
        tblApply.getParent().setBackground(tblApply.getBackground()); //Set background
        
        //No resize/reorder
        tblApply.getTableHeader().setResizingAllowed(false);
        tblApply.getTableHeader().setReorderingAllowed(false);
        
        //Single cell selection
        tblApply.setRowSelectionAllowed(true);
        tblApply.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //Set row/column count
        tblApply.setRowHeight(18);
        mtblApply.setColumnCount(7);
        mtblApply.setRowCount(3);
        
        refreshApply ();
        
        
        
        /*String[] headers_r = {"year","month","day","year_end","month_end","day_end", "종료", "Keyword"}; //All headers
        for (int i=0; i<8; i++){
            mtblRecommand.addColumn(headers_r[i]);
        }*/
        
        
    }
    
    public static void refreshCalendar(int month, int year){
        //Variables
        String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int nod, som; //Number Of Days, Start Of Month
        
        //Allow/disallow buttons
        btnPrev.setEnabled(true);
        btnNext.setEnabled(true);
        if (month == 0 && year <= realYear-10){btnPrev.setEnabled(false);} //Too early
        if (month == 11 && year >= realYear+100){btnNext.setEnabled(false);} //Too late
        lblMonth.setText(months[month]); //Refresh the month label (at the top)
        lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 60, 180, 25); //Re-align label with calendar
        cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box
        
        //Clear table
        for (int i=0; i<6; i++){
            for (int j=0; j<7; j++){
                mtblCalendar.setValueAt(null, i, j);
            }
        }
        
        //Get first day of month and number of days
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);
        
        //Draw calendar
        for (int i=1; i<=nod; i++){
            int row = new Integer((i+som-2)/7);
            int column  =  (i+som-2)%7;
            mtblCalendar.setValueAt(i, row, column);
        }
        
        //Apply renderers
        tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
    }
    
    static class tblCalendarRenderer extends DefaultTableCellRenderer{
        public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 0 || column == 6){ //Week-end
                setBackground(new Color(255, 220, 220));
            }
            else{ //Week
                setBackground(new Color(255, 255, 255));
            }
            if (value != null){
                if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear){ //Today
                    setBackground(new Color(220, 220, 255));
                }
            }
            setBorder(null);
            setForeground(Color.black);
            return this;
        }
    }
    
    ///////
    
    public static void refreshApply(){    	
        //Clear table
        for (int i=0; i<3; i++){
            for (int j=0; j<7; j++){
                mtblApply.setValueAt(null, i, j);
            }
        }
        for (int i=0; i<3; i++){
            for (int j=0; j<7; j++){
                mtblApply.setValueAt(i*8+j, i, j);
            }
        }
        
	    //Apply renderers
	    tblApply.setDefaultRenderer(tblApply.getColumnClass(0), new tblApplyRenderer());
	}
    
    static class tblApplyRenderer extends DefaultTableCellRenderer{
        public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 0){ //Week-end
                setBackground(new Color(255, 220, 220));
            }
            ////
            setBorder(null);
            setForeground(Color.black);
            return this;
        }
    }
    static class tblRecommandRenderer extends DefaultTableCellRenderer{
        public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (value != null){
                if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear){ //Today
                    setBackground(new Color(220, 220, 255));
                }
            }
            setBorder(null);
            setForeground(Color.black);
            return this;
        }
    }
    
    static class btnPrev_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (currentMonth == 0){ //Back one year
                currentMonth = 11;
                currentYear -= 1;
            }
            else{ //Back one month
                currentMonth -= 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }
    static class btnNext_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (currentMonth == 11){ //Foward one year
                currentMonth = 0;
                currentYear += 1;
            }
            else{ //Foward one month
                currentMonth += 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }
    static class btnDelete_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            ////
        	int selectedRow = tblApply.getSelectedRow();
        	apply_list.remove(selectedRow);
        }
    }
    static class btnAll_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            ////
        }
    }
    static class btnNotice_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            ////
        }
    }
    static class btnFund_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            ////
        }
    }
    static class btnJob_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            ////
        	for (int i=0; i<Calendars.calendar_list.size();i++){
        		Calendar temp = calendar_pro_list.get(i);
        		//String part = temp.getPart().equals();
        	}
        }
    }
    static class btnApply_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            ////
        }
    }
    static class cmbYear_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (cmbYear.getSelectedItem() != null){
                String b = cmbYear.getSelectedItem().toString();
                currentYear = Integer.parseInt(b);
                refreshCalendar(currentMonth, currentYear);
            }
        }
    }
}