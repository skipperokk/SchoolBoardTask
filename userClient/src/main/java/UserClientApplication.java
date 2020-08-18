import Drawing.UserThread;
import View.FramePanel;
import View.UserGUI;

public class UserClientApplication {
    public static void main(String[] args) {
//         Окно юзера
        UserGUI userGUI = new UserGUI();
//         Встраиваем панель в окно
        FramePanel framePanel = new FramePanel();
        userGUI.add(framePanel);
//         Запускаем поток
        new Thread(new UserThread(framePanel)).start();
    }
}
