package code;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implement Select, order by, group by operations
 *
 */
public class DBOperationsProblem {

	private static List<DataRow> readFile(File file1, File file2) {
		List<DataRow> returnList = new ArrayList<DataRow>();
		String strLine = "";
		String[] columnNames = null;
		String[] rowData = null;
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(file2));

			while ((strLine = br1.readLine()) != null) {
				columnNames = strLine.split(",");
			}

			br1 = new BufferedReader(new FileReader(file1));

			while ((strLine = br1.readLine()) != null) {
				rowData = strLine.split(",");
				Map<String, String> rowMap = new LinkedHashMap<String, String>();
				DataRow row = new DataRow();
				for (int i = 0; i < rowData.length; i++) {
					rowMap.put(columnNames[i], rowData[i]);
				}
				row.setDataMap(rowMap);
				returnList.add(row);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnList;
	}

	public static void display(List<DataRow> list) {
		for (DataRow row : list) {
			System.out.println(row);
		}
	}

	public static void main(String[] args) {
		File file1 = new File("src/main/resources/data.txt");
		File file2 = new File("src/main/resources/dataColumns.txt");
		List<DataRow> data = readFile(file1, file2);
		String[] selectColumns = { "fname", "lname" };
		// Operations.OperationType opr = Operations.OperationType.SELECT;
		Operations.OperationType opr = Operations.OperationType.ORDERBY;
		List<DataRow> resultSet = new ArrayList<DataRow>();
		switch (opr) {
		case SELECT:
			resultSet = Operations.select(data, selectColumns);
			break;
		case ORDERBY:
			resultSet = Operations.orderBy(data, selectColumns);
			break;
		case GROUPBY:
			resultSet = Operations.groupBy(data, selectColumns);
			break;
		}
		display(resultSet);
		// display(data);
	}
}

class DataRow {

	Map<String, String> dataMap = new LinkedHashMap<String, String>();

	public Map<String, String> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, String> dataMap) {
		this.dataMap = dataMap;
	}

	@Override
	public String toString() {
		String retString = "[";
		Set<String> keySet = dataMap.keySet();
		for (String key : keySet) {
			retString = retString + key + " : " + dataMap.get(key) + ", ";
		}
		retString = retString.substring(0, retString.length() - 2);
		retString += "]";
		// retString += dataMap.k
		return retString;
	}
}

class Operations {

	public static enum OperationType {
		SELECT, ORDERBY, GROUPBY
	};

	public static List<DataRow> select(List<DataRow> data, String[] columns) {
		List<DataRow> returnList = new ArrayList<DataRow>();
		for (DataRow row : data) {
			DataRow newRow = new DataRow();
			Map<String, String> map = new HashMap<String, String>();
			for (String column : columns) {
				map.put(column, row.getDataMap().get(column));
			}
			newRow.setDataMap(map);
			returnList.add(newRow);
		}
		return returnList;
	}

	public static List<DataRow> orderBy(List<DataRow> data, String[] columns) {
		List<DataRow> returnList = reOrder(data, columns, 0);

		return returnList;
	}

	public static List<DataRow> groupBy(List<DataRow> data, String[] columns) {
		List<DataRow> returnList = new ArrayList<DataRow>();

		return returnList;
	}

	private static List<DataRow> reOrder(List<DataRow> data, String[] columns, int i) {
		if (i >= columns.length) {
			return data;
		}
		List<DataRow> returnList = new ArrayList<DataRow>();
		DataRowCompartor comp = new DataRowCompartor(columns[i]);

		//for (int j = i; j <= columns.length-1; j++) {
			String prev = null;
			String cur = null;
			Collections.sort(data, comp);
			List<DataRow> temp = new ArrayList<DataRow>();
		//	List<DataRow> tempReturn = new ArrayList<DataRow>();
			for (DataRow row : data) {
				cur = row.getDataMap().get(columns[i]);
				if (prev == null) {
					prev = cur;
					temp.add(row);
				} else if (prev.equals(cur)) {
					temp.add(row);
				} else if (!prev.equals(cur)) {
					temp = reOrder(temp, columns, i + 1);
					returnList.addAll(temp);
					temp.clear();
					temp.add(row);
				}
			}
			returnList.addAll(temp);
			temp.clear();
		//}
		return returnList;
	}
}

class DataRowCompartor implements Comparator<DataRow> {
	String currentColumn;

	public DataRowCompartor(String currentColumn) {
		this.currentColumn = currentColumn;
	}

	@Override
	public int compare(DataRow row1, DataRow row2) {
		return row1.getDataMap().get(currentColumn)
				.compareTo(row2.getDataMap().get(currentColumn));
	}
}