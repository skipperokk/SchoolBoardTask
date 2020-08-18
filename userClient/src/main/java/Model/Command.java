package Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Point2D;

@Data
@Getter
@Setter
public class Command {
    private String ID;
    private String action;
    private Point2D.Double points;
    private Color color;
}
