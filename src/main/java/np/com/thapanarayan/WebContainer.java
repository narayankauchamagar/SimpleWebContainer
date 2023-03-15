package np.com.thapanarayan;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class WebContainer {

    private int port;
    private ExecutorService executorService;

    public WebContainer(int port) {
        this.port = port;
        this.executorService = Executors.newCachedThreadPool();
    }

    public static void main(String[] args) throws IOException {
        WebContainer webContainer = new WebContainer(9999);

        webContainer.start();
    }


    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            Runnable thread = new RequestHandler(socket);
            executorService.submit(thread);
        }
    }
}
