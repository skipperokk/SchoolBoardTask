import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class ServerApplication {
    private static final int port = 29288;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket socket = serverSocket.accept();
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(
                     new FileInputStream(".\\server\\src\\main\\resources\\testCommand.txt")));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            String commandLine;
            while ((commandLine = reader.readLine()) != null) {
                System.out.println(commandLine);
                writer.write(commandLine);
                writer.newLine();
                writer.flush();
                // для наглядности отрисовки
                sleep(50);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
