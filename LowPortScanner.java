import java.net.*;
import java.io.*;
public class LowPortScanner {
  public static void main(String[] args) {
  String host = args.length > 0 ? args[0] : "localhost";
    for(int i=1; i <1024; i++){
      try {
        Socket socket = new Socket(host,i);
        System.out.println("There is a server on port" + i + " of " + host);
        socket.close();
      } catch (UnknowHostExecption e) {
        System.err.println(e);
        break;
      }catch(IOException ex){
        //TODO: 
      }
    }
  }
}
