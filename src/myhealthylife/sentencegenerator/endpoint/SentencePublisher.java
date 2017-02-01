package myhealthylife.sentencegenerator.endpoint;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Endpoint;

import myhealthylife.sentencegenerator.soap.SentencesImpl;

public class SentencePublisher {
    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        String PROTOCOL = "http://";
        String HOSTNAME = InetAddress.getLocalHost().getHostAddress();
        if (HOSTNAME.equals("127.0.0.1"))
        {
            HOSTNAME = "localhost";
        }
        String PORT = "6902";
        String BASE_URL = "/ws/sentences";

        if (String.valueOf(System.getenv("PORT")) != "null"){
            PORT=String.valueOf(System.getenv("PORT"));
        }
        
        String endpointUrl = PROTOCOL+HOSTNAME+":"+PORT+BASE_URL;
        System.out.println("Starting Sentences Service...");
        System.out.println("--> Published. Check out "+endpointUrl+"?wsdl");
        Endpoint.publish(endpointUrl, new SentencesImpl());
    }
}
