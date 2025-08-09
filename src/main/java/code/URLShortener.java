package code;

import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

//Thread safe URL shortner
public class URLShortener {
    private static final Pattern URL_PATTERN = Pattern.compile("^https://.*");

    private final Map<String, String> urlMap = new ConcurrentHashMap<>();
    private final Map<String, String> shortToUrl = new ConcurrentHashMap<>();
    private final ShorteningStrategy strategy;

    public URLShortener(ShorteningStrategy strategy) {
        this.strategy = strategy;
    }

    public String shorten(String longUrl) {
        if (!isValidUrl(longUrl)) {
            throw new IllegalArgumentException("Invalid URL format");
        }

        return urlMap.computeIfAbsent(longUrl, url -> {
            String shortUrl = strategy.generateShortUrl(url);
            shortToUrl.put(shortUrl, url);
            return shortUrl;
        });
    }

    public String unshorten(String shortUrl) {
        return shortToUrl.get(shortUrl);
    }

    private boolean isValidUrl(String url) {
        return URL_PATTERN.matcher(url).matches();
    }

    public static void main(String[] args) {
        URLShortener urlShortner = new URLShortener(new Base64Strategy());
        String shortenedUrl = urlShortner.shorten("https://google.com/search/latest_in_java");
        System.out.println(shortenedUrl + " = " + urlShortner.unshorten(shortenedUrl));
    }
}


interface ShorteningStrategy {
    String generateShortUrl(String longUrl);
}

class Base64Strategy implements ShorteningStrategy {
    @Override
    public String generateShortUrl(String longUrl) {
        return Base64.getUrlEncoder()
                .encodeToString(longUrl.getBytes())
                .substring(0, 8);
    }
}