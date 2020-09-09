package List;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import Functions.Functions;
import main.ConMrg;
import main.SoldierManagementProgram;

public class Soldier extends Functions {
	Scanner scan = new Scanner(System.in);

	Connection con = null;
	CallableStatement cstmt = null;

	@Override
	protected void infor() {
		try {
			con = ConMrg.getCon();

			String callname = "{call sol_infor(?,?)}";
			cstmt = con.prepareCall(callname);
			cstmt.setString(1, SoldierManagementProgram.solNum);
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			cstmt.execute();
			
			String result_out = cstmt.getString(2);
			System.out.println(result_out);
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void add() {
		try {
			con = ConMrg.getCon();
			
			System.out.print("군번 : ");
			String s_id = scan.nextLine();
			System.out.print("이름 : ");
			String s_name = scan.nextLine();
			System.out.print("계급 : ");
			String s_class = scan.nextLine();
			System.out.print("성별 : ");
			String s_sex = scan.nextLine();
			System.out.print("생일 : ");
			String s_birth = scan.nextLine();
			System.out.print("H.P : ");
			String s_pnum = scan.nextLine();
			System.out.print("주소 : ");
			String s_addr = scan.nextLine();
			System.out.print("부대번호 : ");
			String s_co = scan.nextLine();
			System.out.print("보직번호 : ");
			String s_po = scan.nextLine();
			System.out.print("의무구분 : ");
			String s_duty = scan.nextLine();
			System.out.print("전입일 : ");
			String s_transfer = scan.nextLine();
			System.out.print("전역일 : ");
			String s_discharge = scan.nextLine();
			System.out.print("체력 문서번호 : ");
			int s_p_docunum = scan.nextInt();
			System.out.print("의료 문서번호 : ");
			int s_m_docunum = scan.nextInt();
			
			String callname2 = "{call enroll_soldat(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"; 
			cstmt = con.prepareCall(callname2);
			cstmt.setString(1, s_id);
			cstmt.setString(2, s_name);
			cstmt.setString(3, s_class);
			cstmt.setString(4, s_sex);
			cstmt.setString(5, s_birth);
			cstmt.setString(6, s_pnum);
			cstmt.setString(7, s_addr);
			cstmt.setString(8, s_co);
			cstmt.setString(9, s_po);
			cstmt.setString(10, s_duty);
			cstmt.setString(11, s_transfer);
			cstmt.setString(12, s_discharge);
			cstmt.setInt(13,s_p_docunum);
			cstmt.setInt(14,s_m_docunum);
//			cstmt.registerOutParameter(13, java.sql.Types.VARCHAR);
			
//			String result_out2 = cstmt.getString(15);
//			System.out.println(result_out2);
			System.out.println("신병 등록이 완료되었습니다.");
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showMenu(Scanner scan) {
		while (true) {
			System.out.printf("1.군인정보 조회   2.신병 추가   3.돌아가기\n>>");
			menu = scan.nextInt();
			switch (menu) {
			case 1:
				this.infor();
				break;
			case 2:
				this.add();
				break;
			case 3:
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
				continue;
			}
		}
	}
}
