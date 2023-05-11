import java.io.FileReader;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ReadXmlDomParser {
	public static void main(String[] args) {
		// 자바로 xml 파싱하기
		// org.w3c.dom.Document
		Document xml = null;
		try {
			// 절대 경로로 xml 값을 얻어온다.
			InputSource is = new InputSource(new FileReader("C:\\vsworkspace\\CORPCODE.xml"));

			xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

			// root element 취득
			Element element = xml.getDocumentElement();

			// child node 취득
			NodeList list = element.getChildNodes();

			// child node 가 1개 이상인 경우
			if (list.getLength() > 0) {
				for (int i = 0; i < list.getLength(); i++) {

					NodeList childList = list.item(i).getChildNodes();

					if (childList.getLength() > 0) {
						String sql = "";
						for (int j = 0; j < childList.getLength(); j++) {

							// 데이터가 있는 애들만 출려되게 한다.
							if (childList.item(j).getNodeName().equals("#text") == false) {

								if (childList.item(j).getNodeName().equals("corp_code")) {
									sql = "UPDATE T_ST_COMPANY SET STOCK_CODE = '" + childList.item(j).getTextContent()
											+ "' ";
								} else if (childList.item(j).getNodeName().equals("corp_name")) {
									sql = sql + " WHERE CPNAME = '" + childList.item(j).getTextContent() + "';";
									break;
								}
//								
//								System.out.println("\t xml tag name : " + childList.item(j).getNodeName() + ", xml값 : "
//										+ childList.item(j).getTextContent());
							}

						}
//						if (sql.trim().length() > 0) {

							System.out.println(sql);
//						}

					}
					// System.out.println();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}