package code;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UrlShortenServiceTest {

    UrlShortenService service;

    @Before
    public void setUp() {
        service = new UrlShortenService(100);
    }

    @Test
    public void testUrlShorten(){
        String shortUrl = service.shorten("https://www.revolut.com/rewards-personalised-cashback-and-discounts/");
        Assert.assertTrue(shortUrl.startsWith("https://www.rev.me/"));
        Assert.assertEquals("https://www.rev.me/1aHR0cHM6", shortUrl);
    }

    @Test
    public void testUrlUnshorten(){
        service.shorten("https://www.revolut.com/rewards-personalised-cashback-and-discounts/");
        String longUrl = service.unShorten("https://www.rev.me/1aHR0cHM6");
        Assert.assertEquals("https://www.revolut.com/rewards-personalised-cashback-and-discounts/", longUrl);
    }

    @Test
    public void testUrlShortenException(){
        String longUrl = "www.revolut.com/rewards";
        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            service.shorten(longUrl);
        });
        Assert.assertEquals("Invalid URL", exception.getMessage());
    }

    @Test
    public void testMultipleShorten(){
        String shortUrl = service.shorten("https://www.revolut.com/rewards-personalised-cashback-and-discounts/");
        String shortUrl2 =service.shorten("https://www.revolut.com/rewards-personalised-rewards");
        Assert.assertTrue(shortUrl.startsWith("https://www.rev.me/"));
        Assert.assertEquals("https://www.rev.me/1aHR0cHM6", shortUrl);
        Assert.assertEquals("https://www.rev.me/2aHR0cHM6", shortUrl2);
    }

    @Test
    public void testMaxShortenException(){
        for(int i= 0; i< 100; i++){
            service.shorten("https://www.revolut.com/rewards-personalised-cashback-and-discounts/" + i);
        }
        Assert.assertEquals(100, service.getLongUrlMap().size());
        IllegalStateException exception = Assert.assertThrows(IllegalStateException.class, () -> {
            service.shorten("https://www.revolut.com/rewards-personalised-cashback-and-discounts/" + "another-url");
        });
        Assert.assertEquals("Memory buffer full", exception.getMessage());
    }
}
