package org.gis.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

@Data
@Getter
@Setter
public class LineModel extends Line2D.Double {
    private Color color;

    public LineModel(Point2D x, Point2D y, Color color) {
        super(x, y);
        this.color = color;
    }
}
