package calendar;

import java.util.List;

import javax.swing.table.AbstractTableModel;


public class CalendarTableModel extends AbstractTableModel  {

	private final static String[] COLUMNS = {"num", "index", "part", "title", "target_grade", "target_major", "period_start", "period_end", "publisher" };

	private List<Calendar> Calendars;
	
	public CalendarTableModel(List<Calendar> Calendars) {
		this.Calendars = Calendars;
	}
	
	@Override
	public int getRowCount() {
		return Calendars.size();
	}
	
	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return COLUMNS[column];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Calendar calendar = Calendars.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return rowIndex+1;
		case 1:	
			return calendar.getNum();
		case 2:
			return calendar.getPart();
		case 3:
			return calendar.getTitle();
		case 4:
			return calendar.getTargetGrade();
		case 5:
			return calendar.getTargetMajor();
		case 6:
			return calendar.getPeriodStart();
		case 7:
			return calendar.getPeriodEnd();
		case 8:
			return calendar.getPublisher();	
		}
		return null;
	}
}
