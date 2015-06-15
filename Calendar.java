package calendar;

import java.io.Serializable;

public class Calendar implements Serializable {
	private String num;//�۹�ȣ
	private String part;//����,���,����,����,���� ��
	private String title;// ������
	private int target_grade;// Ÿ�� �г�
	private String target_major;// Ÿ�� �ܴ�
	private String period_start;// ����������
	private String period_end;// ����������
	private String publisher;// �۾���
	
	public Calendar(String num, String part, String title, int target_grade, String target_major,
			String period_start, String period_end, String publisher){
		this.num = num;
		this.part = part;
		this.title = title;
		this.target_grade = target_grade;
		this.target_major = target_major;
		this.period_start = period_start;
		this.period_end = period_end;
		this.publisher = publisher;
	}
//////getter
	public String getNum() {
		return num;
	}

	public String getPart() {
		return part;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getTargetGrade() {
		return target_grade;
	}
	
	public String getTargetMajor() {
		return target_major;
	}
	
	public String getPeriodStart() {
		return period_start;
	}
	
	public String getPeriodEnd() {
		return period_end;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
//////setter
	public void setNum(String num) {
		this.num = num;
	}

	public void setPart() {
		this.part=part;
	}
	
	public void setTitle() {
		this.title=title;
	}
	
	public void setTargetGrade() {
		this.target_grade=target_grade;
	}
	
	public void setTargetMajor() {
		this.target_major=target_major;
	}
	
	public void setPeriodStart() {
		this.period_start=period_start;
	}
	
	public void setPeriodEnd() {
		this.period_end=period_end;
	}
	
	public void setPublisher() {
		this.publisher=publisher;
	}
	
	public String toString(){
		return getNum() + "," + getPart() + "," + getTitle() + "," + getTargetGrade() + "," 
	+ getTargetMajor()+","+ getPeriodStart()+","+ getPeriodEnd()+","+getPublisher();
	}
	
	
}
