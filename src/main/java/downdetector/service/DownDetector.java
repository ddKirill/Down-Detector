package downdetector.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

@Service
public class DownDetector  {



    public boolean checkUrl(URI url){

        try {
            InetAddress address = InetAddress.getByName(url.getHost());
            return address.isReachable(1000);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
