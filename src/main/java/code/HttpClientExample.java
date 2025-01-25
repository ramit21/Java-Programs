package code;


import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.http.*;

public class HttpClientExample {

    /*
        Call this url using Httpclient and return list of email ids of the people who have commented on the given post.
        https://jsonplaceholder.typicode.com/comments?postId=1
        Process the response using JSONArray and JSONObject.
        Note important imports above.
     */
    public static void main(String[] args) {

        try {
            HttpClient client = HttpClient.newHttpClient();
            URI uri = new URI("https", "jsonplaceholder.typicode.com", "/comments", "postId=1", null);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println("Status = " + response.statusCode() + "\nResponse: " + response.body());
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(response.body()); //use JSONObject instead of JSONArray if single object is being returned.
            for (Object jsonObj : jsonArray) {
                System.out.println(((JSONObject) jsonObj).get("email"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
