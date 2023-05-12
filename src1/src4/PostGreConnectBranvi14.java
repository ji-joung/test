import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostGreConnectBranvi14 {
	public static void main(String[] args) throws ClassNotFoundException, IOException {

//		String[] arr = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" };
		String[] arr = { "5", "6" };
		connect(arr);
	}

	public static Connection connect(String[] arr) throws ClassNotFoundException, IOException {
		Class.forName("org.postgresql.Driver");

		String connurl = "jdbc:postgresql://localhost:5432/postgres";
		String user = "postgres";
		String password = "1234";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(connurl, user, password);
			Statement stmt = connection.createStatement();
			System.out.println("success");
			String valSql = "SELECT \"ID\", \"SNAME\", \"SCOLOR\", \"SCONFIG\", \"SINDEX\", \"SSTATUS\", \"SVISIBLECOL\", \"SVISIBLEROW\", \"SDATA\", \"SVERIFICATION\", \"SHEET_SEQ\" FROM public.\"USER_ABILITY_SHEET\" ";
			ResultSet rs = stmt.executeQuery(valSql);
			boolean loopFlag = false;
			while (rs.next()) {
				String ID = rs.getString("ID");
				String SNAME = rs.getString("SNAME");
				String SCOLOR = rs.getString("SCOLOR");
				String SCONFIG = rs.getString("SCONFIG");
				String SINDEX = rs.getString("SINDEX");
				String SSTATUS = rs.getString("SSTATUS");
				String SVISIBLECOL = rs.getString("SVISIBLECOL");
				String SVISIBLEROW = rs.getString("SVISIBLEROW");
				String SDATA = rs.getString("SDATA");
				String SVERIFICATION = rs.getString("SVERIFICATION");
				String SHEET_SEQ = rs.getString("SHEET_SEQ");

				String isSql = "INSERT INTO public.\"USER_ABILITY_SHEET\" (	\"ID\", \"SNAME\", \"SCOLOR\", \"SCONFIG\", \"SINDEX\", \"SSTATUS\", \"SVISIBLECOL\", \"SVISIBLEROW\", \"SDATA\", \"SVERIFICATION\", \"SHEET_SEQ\") \n"
						+ "	VALUES ('" + ID + "', '" + SNAME + "', '" + SCOLOR + "', '" + SCONFIG + "', '" + SINDEX
						+ "', '" + SSTATUS + "', '" + SVISIBLECOL + "', '" + SVISIBLEROW + "', '" + SDATA + "','"
						+ SVERIFICATION + "', '" + SHEET_SEQ + "');";
				System.out.println(isSql);
			}
			rs.close();

			valSql = "SELECT \"ID\", \"SEQ\", \"PROCESS_CONTENT\" FROM public.\"WORK_FLOW_STANDARD_FORMAT\" ";
			rs = stmt.executeQuery(valSql);

			while (rs.next()) {
				String ID = rs.getString("ID");
				String SEQ = rs.getString("SEQ");
				String PROCESS_CONTENT = rs.getString("PROCESS_CONTENT");

				String isSql = "INSERT INTO public.\"WORK_FLOW_STANDARD_FORMAT\" (	\"ID\", \"SEQ\", \"PROCESS_CONTENT\") \n"
						+ "	VALUES ('" + ID + "', '" + SEQ + "', '" + PROCESS_CONTENT + "');";
				System.out.println(isSql);
			}

			stmt.close();
			connection.close();
		} catch (SQLException e) {
            e.printStackTrace();
        }
		return connection;
	}
}