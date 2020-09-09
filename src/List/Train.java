package List;

import Functions.Functions;
import main.ConMrg;
import main.SoldierManagementProgram;

import java.util.*;
import java.sql.*;

public class Train extends Functions {
	CallableStatement cstmt = null;
	Statement st = null;
	ResultSet rs = null;
	Scanner scan = new Scanner(System.in);
	
	protected void infor() { // 체력 정보 출력
		try {
			
			con = ConMrg.getCon();
			
			call = "select * from train";
			st = con.prepareStatement(call);
			rs = st.executeQuery(call);
			
			System.out.println("훈련이름\t\t 부대번호\t\t 훈련시작일\t\t 훈련종료일\t\t 훈련장소");
			while(rs.next()) {
				String t_name = rs.getString(1);
				String t_co = rs.getString(2);
				String t_start = rs.getString(3);
				String t_end = rs.getString(4);
				String t_addr = rs.getString(5);
				
				System.out.printf("%s\t\t %s %s\t\t %s\t\t %s", t_name, t_co, t_start, t_end, t_addr);
				System.out.println();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void add() {		//의료 정보 등록 (날짜 dbms에서 자료형변환요망)
		try {
			
			con = ConMrg.getCon();
			
			String name, co, start, end, addr;
			System.out.print("훈련 이름 : ");
			name = scan.nextLine();
			System.out.print("훈련을 진행하는 부대번호 : ");
			co = scan.nextLine();
			System.out.print("훈련 시작일 : ");
			start = scan.nextLine();
			System.out.print("훈련 종료일 : ");
			end = scan.nextLine();
			System.out.print("훈련을 진행하는 주소 : ");
			addr = scan.nextLine();
			
			call = "{call insert_train(?,?,?,?,?,?)}";
			cstmt = con.prepareCall(call);
			cstmt.setString(1, name);
			cstmt.setString(2, co);
			cstmt.setString(3, start);
			cstmt.setString(4, end);
			cstmt.setString(5, addr);
			cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			cstmt.execute();
			
			String result_out = cstmt.getString(6);
			System.out.println(result_out);
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void delete() {
try {
			
			con = ConMrg.getCon();
			
			String name2, co2;
			System.out.print("삭제할 훈련 이름 : ");
			name2 = scan.nextLine();
			System.out.print("삭제할 훈련을 진행하는 부대번호 : ");
			co2 = scan.nextLine();
			
			call = "{call delete_train(?,?,?)}";
			cstmt = con.prepareCall(call);
			cstmt.setString(1, name2);
			cstmt.setString(2, co2);
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmt.execute();
			
			String result_out = cstmt.getString(3);
			System.out.println(result_out);
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void showMenu(Scanner scan) {
		super.showMenu(scan);
		while (true) {
			System.out.print("1.훈련 일정 조회  2.훈련 추가   3.훈련 삭제   4.돌아가기\n>>");
			menu = scan.nextInt();
			switch (menu) {
			case 1:
				this.infor();
				break;
			case 2:
				this.add();
				break;
			case 3:
				this.delete();
				break;
			case 4:
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
				continue;
			}
		}
	}

}
