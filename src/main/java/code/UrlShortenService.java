package code;

import lombok.Value;

import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

@Value
public class UrlShortenService {

    private Map<String, String> longUrlMap = new ConcurrentHashMap<>();
    private Map<String, String> shortUrlMap = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    private final int maxBufferSize;

    public UrlShortenService(int maxBufferSize){
        this.maxBufferSize = maxBufferSize;
    }

    public String shorten(String longUrl) {
        if (!validateUrl(longUrl)) {
            throw new IllegalArgumentException("Invalid URL");
        }
        if (longUrlMap.size() ==  this.maxBufferSize) {
            throw new IllegalStateException("Memory buffer full");
        }
        String shortUrl = longUrlMap.computeIfAbsent(longUrl, url -> {
            //Can be carved into an interface based shortening strategy
            String encodedStr = Base64.getEncoder().encodeToString(url.getBytes()).substring(0, 8);
            String shortenedUrl = "https://www.rev.me/" + counter.incrementAndGet() + encodedStr;
            shortUrlMap.putIfAbsent(shortenedUrl, longUrl);
            return shortenedUrl;
        });
        //System.out.println(shortUrl);
        return shortUrl;
    }

    public String unShorten(String shortUrl) {
        if (!validateUrl(shortUrl)) {
            throw new IllegalArgumentException("Invalid URL");
        }
        return shortUrlMap.get(shortUrl);
    }

    private boolean validateUrl(String url) {
        Pattern p = Pattern.compile("^https://.*");
        return p.matcher(url).matches();
    }
}



