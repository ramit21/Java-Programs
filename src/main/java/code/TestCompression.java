package code;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

import javax.swing.text.DateFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*
 * prog to show that deflator is better than zip
 */
public class TestCompression {

	static final int BUFFER = 2048;
	private static final DateFormatter formatter = new DateFormatter();

	public static void main(String[] args) {
		try {
			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream("src/main/resources/temp.txt");
			BufferedInputStream bufinStream = new BufferedInputStream(fis);
			// String string = bufinStream.
			// System.out.println("string >> "+string);

			FileOutputStream outStream = new FileOutputStream("src/main/resources/temp2.txt");
			// GZIPOutputStream goutStream = new GZIPOutputStream(outStream);
			byte[] contents = new byte[1024];

			int bytesRead = 0;
			StringBuffer strbuf = new StringBuffer();
			while ((bytesRead = bufinStream.read(contents)) != -1) {
				strbuf.append(new String(contents, 0, bytesRead));
			}
			System.out.println("param >> " + strbuf);
			String param = strbuf.toString();
			Map map = (Map) deserialize(param);
			// CONVERTING A MAP TO BYTE ARRAY
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
			os.flush();
			os.writeObject(map);
			os.flush();
			byte[] sendBuf = byteStream.toByteArray();

			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry) it.next();
				System.out.println(pairs.getKey() + " = " + pairs.getValue());
			}

			Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
			DeflaterOutputStream dos = new DeflaterOutputStream(outStream, deflater);
			dos.write(sendBuf);
			/*
			 * byte[] buf = new byte[1024]; int i; while ((i =
			 * bufinStream.read(buf)) >= 0) { // goutStream.write(buf, 0, i); //
			 * zip compression dos.write(buf, 0, i); // deflator compression }
			 */

			bufinStream.close();
			// goutStream.close();
			dos.close();
			System.out.println("completed succesfully");

		} catch (Exception e) {
			System.out.println("Exception is" + e.getMessage());
		}
	}

	public static Map deserialize(Object param) throws ParseException {
		return parseAndConstructMap(param);
	}

	// Parsing JSON String
	private static Map parseAndConstructMap(Object jsonString) throws ParseException {

		try {
			return constructMap((JSONObject) new JSONParser().parse((String) jsonString));
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Map constructMap(Object object) {
		System.out.println("inside func");
		JSONObject json = (JSONObject) object;
		Map newMap = new HashMap();
		Object tempObj = null;

		Iterator iter = json.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry) iter.next();
			String key = entry.getKey();
			System.out.println("#### " + key + ", " + entry.getValue());
			if (entry.getValue() instanceof JSONObject) {
				tempObj = constructMap(entry.getValue());
			} else if (entry.getValue() instanceof JSONArray) {
				System.out.println("for the first time");
				tempObj = constructBRVect(entry.getValue());
			} else {
				Date Date = null;

				if (entry.getValue() instanceof String) {
					String dateExpected = (String) entry.getValue();

					// if string value contains date then parse to get Date
					Date = checkAndConvertToDate(dateExpected.toLowerCase());
				}

				if (Date == null) {
					tempObj = entry.getValue();
					System.out.println("obj!!");
				} else {
					tempObj = Date;
				}
			}
			if (newMap.containsKey(key)) {
				System.out.println("Contains " + key);
			}
			newMap.put(key, tempObj);
		}

		return newMap;
	}

	@SuppressWarnings("rawtypes")
	private static Map<String, List> constructBRVect(Object object) {
		Map<String, List> map = new HashMap<String, List>();

		int index = 0;

		List arrayList = (List) object;
		System.out.println("!!!!!" + arrayList.size());
		while (index < arrayList.size()) {
			Object obj = arrayList.get(index++);
			JSONObject json = (JSONObject) obj;
			Iterator iter = json.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, Object> entry = (Map.Entry) iter.next();
				String key = entry.getKey();
				System.out.println("$$$$ " + key + ", " + entry.getValue());
				List temp;
				if (!map.containsKey(key)) {
					temp = new ArrayList();
				} else {
					temp = (List) map.get(key);
				}
				temp.add(entry.getValue());
				map.put(key, temp);
			}
		}

		return map;
	}

	private static Date checkAndConvertToDate(String dateExpected) {
		Date date = null;
		try {
			date = DateFormat.getInstance().parse(dateExpected);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

}
