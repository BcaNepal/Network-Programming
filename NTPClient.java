
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Date;

public class NTPClient {

    private static final String TIME_SERVER = "time.nist.gov";
    private static final int NTP_PORT = 123;
    private static final long DIFF_1900_TO_1970 = 2208988800L;

    public static void main(String[] args) {
        try {
            // Get the address of the time server
            InetAddress serverAddress = InetAddress.getByName(TIME_SERVER);

            // Create a socket to communicate with the server
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(10000);

            // Create the NTP request packet (48 bytes)
            byte[] buffer = new byte[48];
            buffer[0] = 0b00100011; // NTP version 4, client mode

            // Create the packet and send it to the server
            DatagramPacket request = new DatagramPacket(buffer, buffer.length, serverAddress, NTP_PORT);
            socket.send(request);

            // Receive the response from the server
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);

            // Extract the time from the response
            ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);

            // Skip to the Transmit Timestamp field (offset 40 bytes)
            byteBuffer.position(40);
            long secondsSince1900 = byteBuffer.getInt() & 0xFFFFFFFFL;

            // Convert seconds since 1900 to milliseconds since 1970
            long msSince1970 = (secondsSince1900 - DIFF_1900_TO_1970) * 1000;

            // Create a Date object
            Date time = new Date(msSince1970);

            // Print the time
            System.out.println("Current time from " + TIME_SERVER + ": " + time);

            // Close the socket
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
