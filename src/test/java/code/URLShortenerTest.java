package code;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class URLShortenerTest {

    URLShortener urlShortner;

    @Before
    public void setup(){
        urlShortner = new URLShortener(new Base64Strategy());
    }

    @Test
    public void testUrlShorten(){
        String shortenedUrl = urlShortner.shorten("https://google.com/search/latest_in_java");
        Assert.assertEquals("https://google.com/search/latest_in_java", urlShortner.unshorten(shortenedUrl));
    }

    @Test
    public void testInvalidUrl(){
        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            urlShortner.shorten("wrong_url");
        });
        Assert.assertEquals("Invalid URL format", exception.getMessage());
    }
}
