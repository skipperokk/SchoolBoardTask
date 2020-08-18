package Drawing;

import Model.Command;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Point2D;

@Data
@Getter
@Setter
public class ParseCommand {

    private Command commandLine;

    public ParseCommand() {
        this.commandLine = new Command();
    }

    public void parseCommand(String com, int width, int height) {
        if (com == null) {
            throw new IllegalArgumentException("Command is empty");
        }
        String[] str = com.split(";");
        if (str.length != 5) {
            throw new IllegalArgumentException("Incorrect commands format");
        }
        if (str[0].length() == 17) {
            commandLine.setID(str[0]);
        } else throw new IllegalArgumentException("Unknown ID format");

        switch (str[1]) {
            case "start":
                commandLine.setAction("start");
                break;
            case "move":
                commandLine.setAction("move");
                break;
            default:
                throw new IllegalArgumentException("Incorrect action");
        }
        commandLine.setPoints(new Point2D.Double(Double.parseDouble(str[2]) * width, Double.parseDouble(str[3]) * height));
        commandLine.setColor(Color.decode(str[4]));
    }
}
