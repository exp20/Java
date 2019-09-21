package t1.buildings.net.server.parallel;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//Сервер для параллельной обработки запросов
public class BinaryParallelServer {
    private static int PORT = 8090;
    public static void main(String... ars){
        try {
            ServerSocket serv_sock = new ServerSocket(PORT);
            while(true){
                Socket client_socket = serv_sock.accept();
                Thread client_thread = new Thread(new ClientThread(client_socket));
                client_thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
