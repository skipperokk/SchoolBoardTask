package org.gis;

import org.gis.Drawing.UserThread;
import org.gis.View.FramePanel;
import org.gis.View.UserGUI;

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
