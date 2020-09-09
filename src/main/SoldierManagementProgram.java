package main;

import java.util.Scanner;

import Functions.Functions;
import List.Benefit;
import List.Classes;
import List.Company;
import List.Duty;
import List.Equipment;
import List.Facility;
import List.Family;
import List.Medical;
import List.Oil;
import List.Physical;
import List.Soldier;
import List.Train;
import List.Vacation;
import java.sql.*;

public class SoldierManagementProgram {

	Scanner scan = new Scanner(System.in);
	public static String solNum;
	public static String name;
	
	Connection con = null;
	CallableStatement cstmt = null;

	void myMain() {
		
		try {
			System.out.println("<병 관리 프로그램을 시작 합니다.>");
			System.out.println();

			con = ConMrg.getCon();
			System.out.println("<로그인>");
			System.out.print("군번을 입력하세요 : ");
			solNum = scan.next();
			System.out.print("이름을 입력하세요 : ");
			name = scan.next();

			String callname = "{call soldier_login(?,?,?)}";
			cstmt = con.prepareCall(callname);
			cstmt.setString(1, solNum);
			cstmt.setString(2, name);
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmt.execute();

			String result_out = cstmt.getString(3);
			System.out.println();
			System.out.println(result_out);
			System.out.println();

			while (true) {
				Functions f = Functions.getInstance();
				System.out.println();
				System.out.println(">>관련 키워드를 선택 하세요.");
				System.out.println("1.부대   2.군인   3.휴가   4.보호자   5.의료   6.체력   7.훈련");
				System.out.print("8.근무   9.장비   10.계급   11.편의시설   12.복지   13.유류   14.종료\n>>");
				int menu = scan.nextInt();
				switch (menu) {
				case 1:
					f = new Company();
					break;
				case 2:
					f = new Soldier();
					break;
				case 3:
					f = new Vacation();
					break;
				case 4:
					f = new Family();
					break;
				case 5:
					f = new Medical();
					break;
				case 6:
					f = new Physical();
					break;
				case 7:
					f = new Train();
					break;
				case 8:
					f = new Duty();
					break;
				case 9:
					f = new Equipment();
					break;
				case 10:
					f = new Classes();
					break;
				case 11:
					f = new Facility();
					break;
				case 12:
					f = new Benefit();
					break;
				case 13:
					f = new Oil();
					break;
				case 14:
					System.out.println("프로그램을 종료합니다.");
					break;
				default:
					System.out.println("잘못된 입력입니다. 다시 입력하세요.");
					continue;
				}
				if (menu==14)
					break;
				f.showMenu(scan);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		SoldierManagementProgram pro = new SoldierManagementProgram();
		pro.myMain();
	}
}
