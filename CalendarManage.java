package calendar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CalendarManage {
	ArrayList<Calendar> calendar_list = new ArrayList<Calendar>();
	Scanner s = new Scanner(System.in);
	public void start(){
		while(true){
			System.out.println("1) �����߰�\t2) ���� ����\t3) ��������\t4) �����˻�\t5) �����б�\t6) ��������\t7) �Է°� Ȯ��\t8) ����");
			int input = s.nextInt();
			
			if (input == 8){
				break;
			} else if (input==7){
				for(int i =0; i< calendar_list.size(); i++){
					System.out.println(calendar_list.get(i).toString());
				}
				//7) �Է°� Ȯ��
			} else if (input==6){
				FileOutputStream fout = null;
				ObjectOutputStream oos = null;

				try{
					fout = new FileOutputStream("calendarlist.txt");
					oos = new ObjectOutputStream(fout);
					oos.writeObject(calendar_list);
					oos.reset();
					oos.writeObject(calendar_list);
					oos.reset();
					
					System.out.println("����Ǿ����ϴ�.\n");
					
				}catch(Exception ex){
				}finally{
					try{
						oos.close();
						fout.close();
					}catch(IOException ioe){}
				} // finally
				//6) ��������
			} else if (input==5){
				FileInputStream fin = null;
				ObjectInputStream ois = null;

				try{
					fin = new FileInputStream("calendarlist.txt");
					ois = new ObjectInputStream(fin);
					
					ArrayList list = (ArrayList)ois.readObject();
					calendar_list.clear();
					for (int i = 0; i<list.size(); i++){
						Calendar c1 = (Calendar)list.get(i);
						calendar_list.add(c1);
						System.out.println(c1.toString());
					}
					
				}catch(Exception ex){
				}finally{
					try{
						ois.close();
						fin.close();
					}catch(IOException ioe){}
				} // finally
				//5) �����б�
			} else if (input==4){
				System.out.println("��ĥ ���� num, part, title, target_grade, target_major, period_start, period_end, publisher �Է�");
				String num= s.next();//�۹�ȣ
				String part= s.next();//����,���,����,����,���� ��
				String title= s.next();// ������
				int target_grade= s.nextInt();// Ÿ�� �г�
				String target_major= s.next();// Ÿ�� �ܴ�
				String period_start= s.next();// ����������
				String period_end= s.next();// ����������
				String publisher= s.next();// �۾���
				int to_modify = find(num, part, title, target_grade, target_major,
						period_start, period_end, publisher);
				if (to_modify != -1) {
					System.out.println((to_modify+1)+"��° ���� : "+calendar_list.get(to_modify).toString());
				} else{
					System.out.println("�˻� ����� �����ϴ�.");
				}
				//4) �����˻� (���ڸ�, �ܰ�, ���ǻ�, isbn, ���ǳ⵵ ��)
			} else if (input==3){
				delete();
				//3) ��������
			} else if (input==2){
				modify();
				//2) ���� ����
			} else if (input==1){
				add_calendar();
				//1) �����߰�
			} else {
				
			}
		}
	}
	public void delete(){
		System.out.println("�� ��° ������ �����Ͻðڽ��ϱ�?");
		int remove = s.nextInt();
		calendar_list.remove(remove-1);
	}
	public int find(String num, String part, String title, int target_grade, String target_major,
			String period_start, String period_end, String publisher){
		int found = -1;
		for(int i =0 ; i<calendar_list.size();i++){
			Calendar temp = calendar_list.get(i);
			if (temp.getNum() == num &&
					temp.getPart().equals(part)	&&
					temp.getTitle() == title &&
					temp.getTargetGrade() == target_grade &&
					temp.getTargetMajor().equals(target_major) &&
					temp.getPeriodStart().equals(period_start) &&
					temp.getPeriodEnd().equals(period_end) &&
					temp.getPublisher().equals(publisher)){
				found = i;
			}
		}
		return found;
	}
	
	public void modify(){
		
		System.out.println("ã�� ���� num, part, title, target_grade, target_major, period_start, period_end, publisher ���� �Է�");
		String num_= s.next();//�۹�ȣ
		String part_= s.next();//����,���,����,����,���� ��
		String title_= s.next();// ������
		int target_grade_= s.nextInt();// Ÿ�� �г�
		String target_major_= s.next();// Ÿ�� �ܴ�
		String period_start_= s.next();// ����������
		String period_end_= s.next();// ����������
		String publisher_= s.next();// �۾���
		int to_modify = find(num_,part_, title_, target_grade_, target_major_, period_start_, period_end_, publisher_);
		
		if (to_modify != -1 ){
			System.out.println("��ĥ ���� num, part, title, target_grade, target_major, period_start, period_end, publisher �Է�");
			String num= s.next();//�۹�ȣ
			String part= s.next();//����,���,����,����,���� ��
			String title= s.next();// ������
			int target_grade= s.nextInt();// Ÿ�� �г�
			String target_major= s.next();// Ÿ�� �ܴ�
			String period_start= s.next();// ����������
			String period_end= s.next();// ����������
			String publisher= s.next();// �۾���
			
			Calendar new_cal = new Calendar(num, part, title, target_grade, target_major, period_start, period_end, publisher);
			calendar_list.set(to_modify, new_cal);
		} else {
			System.out.println("�˻� ����� �����ϴ�.");
		}
	}
	public void add_calendar(){
		System.out.println("�߰� �� ������ �Է��ϼ���, num, part, title, target_grade, target_major, period_start, period_end, publisher");
		String num_= s.next();//�۹�ȣ
		String part_= s.next();//����,���,����,����,���� ��
		String title_= s.next();// ������
		int target_grade_= s.nextInt();// Ÿ�� �г�
		String target_major_= s.next();// Ÿ�� �ܴ�
		String period_start_= s.next();// ����������
		String period_end_= s.next();// ����������
		String publisher_= s.next();// �۾���
		Calendar new_cal = new Calendar(num_, part_, title_, target_grade_, target_major_, period_start_, period_end_, publisher_);
		calendar_list.add(new_cal);
	}
}
