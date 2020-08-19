package org.gis;

import org.gis.Drawing.BezierMethod;
import org.gis.Model.LineModel;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BezierMethodTest {
    private BezierMethod bezierMethod;
    private final double param = 0.01;
    private final Point2D.Double point1 = new Point2D.Double(1, 1);
    private final Point2D.Double point2 = new Point2D.Double(2, 2);
    private final Point2D.Double point3 = new Point2D.Double(3, 3);
    private final Point2D.Double point4 = new Point2D.Double(4, 4);


    @Before
    public void initBezierMethod() {
        this.bezierMethod = new BezierMethod();
        bezierMethod.getListPoints().add(point1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void getPointForBezierMethodWithIncorrectParam() {
        bezierMethod.getListPoints().add(point2);
        bezierMethod.getListPoints().add(point3);
        bezierMethod.getListPoints().add(point4);

        bezierMethod.getPointsForBezierCurves(bezierMethod.getListPoints(), 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPointForBezierMethodWithEmptyPointsList() {
        bezierMethod.getPointsForBezierCurves(new ArrayList<>(), param);
    }

    @Test(expected = NullPointerException.class)
    public void getPointForBezierMethodWithNullPointsList() {
        bezierMethod.getPointsForBezierCurves(null, param);
    }

    @Test
    public void getPointForBezierMethodWithListWithOneElem() {
        bezierMethod.getPointsForBezierCurves(bezierMethod.getListPoints(), param);

        assertEquals(point1, bezierMethod.getPointsForBezierCurves(bezierMethod.getListPoints(), param));
        assertEquals(1, bezierMethod.getListPoints().size());
    }

    @Test
    public void getCorrectCalculatedPoints() {
        bezierMethod.getListPoints().add(point2);

        assertEquals(new Point2D.Double(1.01, 1.01).getX(),
                bezierMethod.getPointsForBezierCurves(bezierMethod.getListPoints(), 0.01).getX(), 0);

        assertEquals(new Point2D.Double(1.01, 1.01).getY(),
                bezierMethod.getPointsForBezierCurves(bezierMethod.getListPoints(), 0.01).getY(), 0);
    }

    @Test
    public void getCurveFromTwoPoints() {
        bezierMethod.getListPoints().add(point2);

        List<LineModel> curve = new ArrayList<>();
        curve.add(new LineModel(point1, point2, Color.decode("-16777216")));

        assertEquals(curve, bezierMethod.getBezierCurves(Color.decode("-16777216")));
        assertEquals(2, bezierMethod.getListPoints().size());
    }

    @Test
    public void getCorrectCalculatedPointFromThreePoints() {
        Point2D.Double pointCalculated = new Point2D.Double(1.02, 1.02);

        bezierMethod.getListPoints().add(point2);
        bezierMethod.getListPoints().add(point3);

        LineModel expected = new LineModel(point1, pointCalculated, Color.decode("-16777216"));

        assertEquals(expected, bezierMethod.getBezierCurves(Color.decode("-16777216")).get(1));
        assertEquals(3, bezierMethod.getListPoints().size());
    }
}
