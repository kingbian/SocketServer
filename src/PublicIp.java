import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * class used to obtain a public ip
 *
 */
public class PublicIp {
    // create field
    private static String ip;
    public static void main(String[] args) throws Exception{
        getPublicIp("google.com");
    }
    public static void getPublicIp(String ip) throws IOException {
        URL url = new URL(ip);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        ip = br.readLine();
        System.out.println("Public IP address of the server is: " + ip);

    }
}
