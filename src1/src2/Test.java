import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Test {

	public static void main(String[] args) throws Exception {
		
		// Create a HashMap
		java.util.Date date = new Date("Sat Dec 01 00:00:00 GMT 2012");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String format = formatter.format(date);
		System.out.println(format);

	}

	public static <K, V> Map<K, V> convertToTreeMap(Map<K, V> hashMap) {
		Map<K, V> treeMap = hashMap
				// Get the entries from the hashMap
				.entrySet()

				// Convert the map into stream
				.stream()

				// Now collect the returned TreeMap
				.collect(Collectors

						// Using Collectors, collect the entries
						// and convert it into TreeMap
						.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> newValue, TreeMap::new));

		// Return the TreeMap
		return treeMap;
	}

}
