package code;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientExample {

    /*
        Call this url using Httpclient and return list of email ids of the people who have commented on the given post.
        https://jsonplaceholder.typicode.com/comments?postId=1
        Process the response using JSONArray and JSONObject, or using ObjectMapper
        Note important imports above.
     */
    public static void main(String[] args) {

        try {
            URI uri = new URI("https", "jsonplaceholder.typicode.com", "/comments", "postId=1", null);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println("Status = " + response.statusCode() + "\nResponse: " + response.body());
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(response.body()); //use JSONObject instead of JSONArray if single object is being returned.
            for (Object jsonObj : jsonArray) {
                System.out.println(((JSONObject) jsonObj).get("email"));
            }

            //Read into pojo
            ObjectMapper mapper = new ObjectMapper(); //Import jackson bind
            Comment[] comments = mapper.readValue(response.body(), Comment[].class);
            System.out.println(comments[0]);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}

@Value
@Builder
@Jacksonized
class Comment {
    private Long postId;
    private Long id;
    private String name;
    private String email;
    private String body;
}

/*
Note if Lombok annotations are not available, then you have to write boiler plate code in pojo constructor:
@JsonCreator
    public Comment(
        @JsonProperty("postId") Long postId,
        @JsonProperty("id") Long id,
        @JsonProperty("name") String name,
        @JsonProperty("email") String email,
        @JsonProperty("body") String body
    ) {
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }
 */