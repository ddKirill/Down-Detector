import downdetector.DownDetector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;

public class DownDetectorTest {


    @Test
    void checkShouldBeTrue() {
        //GIVEN
        DownDetector downDetector = new DownDetector();
        URI uri = URI.create("http://localhost");
        // WHEN
        boolean result = downDetector.checkUrl(uri);
        //downDetector.checkUrl(URI.create("http://localhol"));
        //THEN
        Assertions.assertEquals(true, result);
    }

    @Test
    void checkShouldBeFalse() {
        DownDetector downDetectorFalse = new DownDetector();
        URI uri = URI.create("http://128.0.0.1");
        boolean result = downDetectorFalse.checkUrl(uri);
        Assertions.assertEquals(false, result);
    }

    @Test
    void checkShouldBe() {
        DownDetector downDetector = new DownDetector();
        URI uri = URI.create("http://localloh");
        boolean result = downDetector.checkUrl(uri);
        Assertions.assertEquals(false, result);
    }




}
