
public class TotalExeCute {
	public static void main(String[] args) throws Exception {
		execute();
	}

	public static void execute() throws Exception {
		try {
			System.out.println("KosDartCorpCode start");
			KosDartCorpCode corp = new KosDartCorpCode();
			String[] arr = new String[1];
			corp.main(arr);
			System.out.println("KosDartCorpCode end");
		} catch (Exception e) {
			e.printStackTrace();
			// conn.rollback();
		} finally {
			try {
				System.out.println("KosDartDocFileGet start");
				KosDartDocFileGet_notuse corp = new KosDartDocFileGet_notuse();
				String[] arr = new String[1];
				corp.main(arr);
				System.out.println("KosDartDocFileGet end");
			} catch (Exception e) {
				e.printStackTrace();
				// conn.rollback();
			} finally {
				System.out.println("KosDartDocFileGetNew start");
				KosDartDocFileGetNew_notuse corp = new KosDartDocFileGetNew_notuse();
				String[] arr = new String[1];
				corp.main(arr);
				System.out.println("KosDartDocFileGetNew start");
			}

		}
	}
}
