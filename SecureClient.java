import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;
public class SecureClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;
        String trustStoreFile = "clienttruststore.jks";
        String trustStorePassword = "IsP@ssw0rd";
        try {
            // Load the TrustStore
            KeyStore trustStore = KeyStore.getInstance("JKS");
            trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword.toCharArray());
            // Create and initialize the TrustManagerFactory
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX510");
            trustManagerFactory.init(trustStore);
            // Create and initialize the SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
            // Create the SSLSocketFactory
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(host, port);
            // Communicate with the server
            PrintWriter output = new PrintWriter(sslSocket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            output.println("Hello, server!");
            String serverMessage = input.readLine();
            System.out.println("Received from server: " + serverMessage);
            // Close the connection
            sslSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
