import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostGreConnectFull {
	public static void main(String[] args) throws ClassNotFoundException, IOException {

//		String[] arr = { "2f", "3f", "4f", "5f", "6f", "7f", "8f", "9f", "10f", "11f" };
		String[] arr = { "6f" };
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

			for (int i = 0; i < arr.length; i++) {
				String stsSize = arr[i];

				final String FILENAME = "C:" + File.separator + "Users" + File.separator + "JENIUS" + File.separator
						+ "git" + File.separator + "repository" + File.separator + "com.branvi.polarion.gantt"
						+ File.separator + "webapp" + File.separator + "mxgraph" + File.separator + "javascript"
						+ File.separator + "examples" + File.separator + "grapheditor" + File.separator + "www"
						+ File.separator + "process" + stsSize + ".xml";

				StringBuffer buff = new StringBuffer();

				File file = new File(FILENAME);

				BufferedReader inFile = new BufferedReader(new FileReader(file));
				String sLine = null;
				// System.out.println(buff.toString());
				stmt.executeUpdate("DELETE FROM public.\"WORK_FLOW_STANDARD_FORMAT\" WHERE \"ID\" = '" + "process"
						+ stsSize + "'");

				boolean minusFlag = false;
				int cnt = 1;
				while ((sLine = inFile.readLine()) != null) {
					String insSql = "INSERT INTO public.\"WORK_FLOW_STANDARD_FORMAT\"(";
					insSql = insSql + "\"ID\", \"SEQ\", \"PROCESS_CONTENT\")";
					insSql = insSql + "VALUES ('" + "process" + stsSize + "','" + cnt++ + "','" + sLine + "')";

					System.out.println(insSql);
					stmt.executeUpdate(insSql);
				}

//				if (globalAdminFlag == true) {



			}


			stmt.close();
			connection.close();
		} catch (SQLException e) {
            e.printStackTrace();
        }
		return connection;
	}
}