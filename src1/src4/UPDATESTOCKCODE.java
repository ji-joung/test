

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class UPDATESTOCKCODE {

	// Request 메소드 정의
	public enum HttpMethod {
		GET, POST
	}

	public static void main(String[] args) throws Exception {
		String API_KEY = "ccdb30d961951742ff7beb146a651d3c3367e694";
		Connection conn = OracleConnect.connect();
		// 바이트 단위로 파일읽기
		String filePath = "C:/vsworkspace/CORPCODE.xml"; // 대상 파일

		// 파일 객체 생성
		File file = new File(filePath);
		// 입력 스트림 생성
		FileReader filereader = new FileReader(file);
		// 입력 버퍼 생성
		BufferedReader bufReader = new BufferedReader(filereader);
		String fileline = "";
		HashMap map = new HashMap();
		HashMap namemap = new HashMap();
		int cnt = 0;
		String corpCode = "";
		while ((fileline = bufReader.readLine()) != null) {

			if (fileline.indexOf("corp_code") >= 0) {
				corpCode = fileline.split("\\>")[1].split("\\<")[0];
				map.put(corpCode, corpCode);
//				System.out.println(line.split("\\>")[1].split("\\<")[0]);
			}

			if (corpCode.length() > 0 && fileline.indexOf("corp_name") >= 0) {

				namemap.put(corpCode, fileline.split("\\>")[1].split("\\<")[0]);
//				System.out.println(line.split("\\>")[1].split("\\<")[0]);
				corpCode = "";
			}

		}
		// .readLine()은 끝에 개행문자를 읽지 않는다.
		bufReader.close();

		Set set = map.keySet();
		Iterator iter = set.iterator();
		while (iter.hasNext()) {

			corpCode = (String) iter.next();
			StringBuffer buff = new StringBuffer();
			buff.append("UPDATE T_ST_COMPANY SET STOCK_CODE = '" + corpCode + "' WHERE corp_name = '"
					+ namemap.get(corpCode) + "' ");
			System.out.println(buff.toString());
			Statement upst = conn.createStatement();
			upst.executeUpdate(buff.toString());
			upst.close();
			;

		}
		conn.close();
	}





}
