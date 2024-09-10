import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class SecureServer {
    public static void main(String[] args) {
        int port = 12345;
        String keyStoreFile = "serverkeystore.jks";
        String keyStorePassword = "IsP@ssw0rd"; 
        try {
            // Load the KeyStore
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream(keyStoreFile), keyStorePassword.toCharArray());
            // Create and initialize the KeyManagerFactory
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());
            // Create and initialize the SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), null, null);
            // Create the SSLServerSocketFactory
            SSLServerSocketFactory sslServerSocketFactory = sslContext.getServerSocketFactory();
            SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(port);
            System.out.println("Secure server started and waiting for client connection...");
            // Accept client connections
            SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();
            // Communicate with the client
            BufferedReader input = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            PrintWriter output = new PrintWriter(sslSocket.getOutputStream(), true);
            String clientMessage = input.readLine();
            System.out.println("Received from client: " + clientMessage);
            output.println("Hello, client!");
            // Close the connection
            sslSocket.close();
            sslServerSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
