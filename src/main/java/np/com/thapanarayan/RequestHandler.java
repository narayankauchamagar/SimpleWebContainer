package np.com.thapanarayan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;

public class RequestHandler implements Runnable{

    private Socket socket;
    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in  = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = in.readLine();
            while(!line.isEmpty()){
                System.out.println(line);
                line = in.readLine();
            }

            out = new PrintWriter(socket.getOutputStream());
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
            out.println("<html><body>Hello World from Basic Container");
            out.println();
            out.println("Local Time: " + LocalDateTime.now());
            out.println("</body></html>");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(in != null) in.close();
                if(out != null) out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
