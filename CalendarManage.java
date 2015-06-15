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
			System.out.println("1) 일정추가\t2) 일정 변경\t3) 일정삭제\t4) 일정검색\t5) 일정읽기\t6) 일정저장\t7) 입력값 확인\t8) 종료");
			int input = s.nextInt();
			
			if (input == 8){
				break;
			} else if (input==7){
				for(int i =0; i< calendar_list.size(); i++){
					System.out.println(calendar_list.get(i).toString());
				}
				//7) 입력값 확인
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
					
					System.out.println("저장되었습니다.\n");
					
				}catch(Exception ex){
				}finally{
					try{
						oos.close();
						fout.close();
					}catch(IOException ioe){}
				} // finally
				//6) 파일저장
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
				//5) 파일읽기
			} else if (input==4){
				System.out.println("고칠 일정 num, part, title, target_grade, target_major, period_start, period_end, publisher 입력");
				String num= s.next();//글번호
				String part= s.next();//장학,취업,인턴,공모,공지 등
				String title= s.next();// 글제목
				int target_grade= s.nextInt();// 타겟 학년
				String target_major= s.next();// 타겟 단대
				String period_start= s.next();// 접수시작일
				String period_end= s.next();// 접수마감일
				String publisher= s.next();// 글쓴이
				int to_modify = find(num, part, title, target_grade, target_major,
						period_start, period_end, publisher);
				if (to_modify != -1) {
					System.out.println((to_modify+1)+"번째 일정 : "+calendar_list.get(to_modify).toString());
				} else{
					System.out.println("검색 결과가 없습니다.");
				}
				//4) 일정검색 (저자명, 단가, 출판사, isbn, 출판년도 등)
			} else if (input==3){
				delete();
				//3) 일정삭제
			} else if (input==2){
				modify();
				//2) 일정 변경
			} else if (input==1){
				add_calendar();
				//1) 일정추가
			} else {
				
			}
		}
	}
	public void delete(){
		System.out.println("몇 번째 일정을 삭제하시겠습니까?");
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
		
		System.out.println("찾을 일정 num, part, title, target_grade, target_major, period_start, period_end, publisher 각각 입력");
		String num_= s.next();//글번호
		String part_= s.next();//장학,취업,인턴,공모,공지 등
		String title_= s.next();// 글제목
		int target_grade_= s.nextInt();// 타겟 학년
		String target_major_= s.next();// 타겟 단대
		String period_start_= s.next();// 접수시작일
		String period_end_= s.next();// 접수마감일
		String publisher_= s.next();// 글쓴이
		int to_modify = find(num_,part_, title_, target_grade_, target_major_, period_start_, period_end_, publisher_);
		
		if (to_modify != -1 ){
			System.out.println("고칠 일정 num, part, title, target_grade, target_major, period_start, period_end, publisher 입력");
			String num= s.next();//글번호
			String part= s.next();//장학,취업,인턴,공모,공지 등
			String title= s.next();// 글제목
			int target_grade= s.nextInt();// 타겟 학년
			String target_major= s.next();// 타겟 단대
			String period_start= s.next();// 접수시작일
			String period_end= s.next();// 접수마감일
			String publisher= s.next();// 글쓴이
			
			Calendar new_cal = new Calendar(num, part, title, target_grade, target_major, period_start, period_end, publisher);
			calendar_list.set(to_modify, new_cal);
		} else {
			System.out.println("검색 결과가 없습니다.");
		}
	}
	public void add_calendar(){
		System.out.println("추가 할 일정을 입력하세요, num, part, title, target_grade, target_major, period_start, period_end, publisher");
		String num_= s.next();//글번호
		String part_= s.next();//장학,취업,인턴,공모,공지 등
		String title_= s.next();// 글제목
		int target_grade_= s.nextInt();// 타겟 학년
		String target_major_= s.next();// 타겟 단대
		String period_start_= s.next();// 접수시작일
		String period_end_= s.next();// 접수마감일
		String publisher_= s.next();// 글쓴이
		Calendar new_cal = new Calendar(num_, part_, title_, target_grade_, target_major_, period_start_, period_end_, publisher_);
		calendar_list.add(new_cal);
	}
}
