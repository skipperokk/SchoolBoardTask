import Drawing.BezierMethod;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;

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

    }

    @Test(expected = IllegalArgumentException.class)
    public void getPointForBezierMethodWithIncorrectParam() {
        bezierMethod.getListPoints().add(point1);
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
        bezierMethod.getListPoints().add(point1);
        bezierMethod.getPointsForBezierCurves(bezierMethod.getListPoints(), param);

        assertEquals(point1, bezierMethod.getPointsForBezierCurves(bezierMethod.getListPoints(), param));
        assertEquals(1, bezierMethod.getListPoints().size());
    }

    @Test
    public void kek(){
        bezierMethod.getListPoints().add(point1);
        bezierMethod.getListPoints().add(point2);

        assertEquals(new Point2D.Double(1.01, 1.01).getX(),
                bezierMethod.getPointsForBezierCurves(bezierMethod.getListPoints(), 0.01).getX(), 0);

        assertEquals(new Point2D.Double(1.01, 1.01).getY(),
                bezierMethod.getPointsForBezierCurves(bezierMethod.getListPoints(), 0.01).getY(), 0);
    }


}
