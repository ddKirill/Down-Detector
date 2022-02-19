package downdetector.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

@Service
public class DownDetector  {

    @Value("${url-check.timeout-millis:1000}")
    private int timeOut;



    public boolean checkUrl(URI url){

        try {
            InetAddress address = InetAddress.getByName(url.getHost());
            return address.isReachable(timeOut);

        } catch (UnknownHostException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
