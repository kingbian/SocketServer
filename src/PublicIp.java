import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**

 * class used to abtain a public ip
 * testing a remote connection
 */
public class PublicIp {
    // create field
    private static String ip;
    public static void main(String[] args) throws Exception{
        getPublicIp();
    }
    public static void getPublicIp() throws IOException {
        URL url = new URL("http://checkip.amazonaws.com/");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        ip = br.readLine();
        System.out.println("Public IP address of the server is: " + ip);

    }
}
