package Drawing;

import View.FramePanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class UserThread extends Thread {
    FramePanel panel;

    public UserThread(FramePanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        String host = "localhost";
        int port = 29288;
        try (Socket socket = new Socket(host, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String inCommand;
            ParseCommand command = new ParseCommand();
            BezierMethod bezierMethod = new BezierMethod();
            DrawCurves drawCurves = new DrawCurves(command, panel, bezierMethod);
            while (socket.isConnected() && (inCommand = reader.readLine()) != null) {
                System.out.println(inCommand);
                drawCurves.drawCurves(inCommand, panel.getParent().getWidth(), panel.getParent().getHeight());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
