package code;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

/*
 Read and parse JSON using json-simple (included in POM)
 */
public class JsonParser {

    public static void main(String[] args) {
        createJson();
        readAndParseJson();
    }

    private static void createJson() {
        System.out.println("Creating the json object and storing in a json file:");

        JSONObject obj = new JSONObject();
        obj.put("name", "ramit21");
        obj.put("age", 100);

        JSONArray list = new JSONArray();
        list.add("msg 1");
        list.add("msg 2");
        list.add("msg 3");
        obj.put("messages", list);

        JSONObject innerObj = new JSONObject();
        innerObj.put("deptName", "IT");
        innerObj.put("costCenter", 100);
        obj.put("department", innerObj);

        try (FileWriter file = new FileWriter("C:\\workspace\\test.json")) {
            file.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(obj);
    }

    private static void readAndParseJson() {
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader("C:\\workspace\\test.json")) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            System.out.println("\nReading and parsing JSON object:" + jsonObject);

            Iterator<String> keys = jsonObject.keySet().iterator();

            while(keys.hasNext()) {
                String key = keys.next();
                if (jsonObject.get(key) instanceof String) {
                    String strVal = (String) jsonObject.get(key);
                    System.out.println("Type String: " + key + " : " + strVal);
                } else if (jsonObject.get(key) instanceof Long) {
                    Long longVal = (Long) jsonObject.get(key);
                    System.out.println("Type Long: " + key + " : " + longVal);
                } else if (jsonObject.get(key) instanceof JSONObject) {
                    JSONObject objVal = (JSONObject) jsonObject.get(key);
                    System.out.println("Type Object: " + key + " : " + objVal);
                } else if (jsonObject.get(key) instanceof JSONArray) {
                    JSONArray objArr = (JSONArray) jsonObject.get(key);
                    System.out.println("Type Array: " + key + " : " + objArr + ", with value at first index = "+objArr.get(0));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
