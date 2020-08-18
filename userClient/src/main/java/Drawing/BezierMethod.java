package Drawing;

import Model.LineModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class BezierMethod {

    private List<Point2D.Double> listPoints = new ArrayList<>();
    private Point2D.Double startPoint = new Point2D.Double();

    public List<LineModel> getBezierCurves(Color color) {
        List<LineModel> curve = new ArrayList<>();
        Point2D.Double nextPoint;
        if (listPoints.size() < 3) {
            curve.add(new LineModel(listPoints.get(0), listPoints.get(1), color));
        } else {
            startPoint = listPoints.get(0);
            for (double param = 0.0; param < 1.0; param += 0.01) {
                nextPoint = getPointsForBezierCurves(listPoints, param);
                curve.add(new LineModel(startPoint, nextPoint, color));
                startPoint = nextPoint;
            }
        }
        return curve;
    }

    public Point2D.Double getPointsForBezierCurves(List<Point2D.Double> points, double param) {
        List<Point2D.Double> pointsForCurveDraw = new ArrayList<>();
        if (param < 0 || param > 1) {
            throw new IllegalArgumentException("Parameter > 0 and < 1");
        } else if (points.isEmpty()) {
            throw new IllegalArgumentException("Empty points list");
        } else if (points.size() == 1) {
            return points.get(0);
        } else {
            for (int i = 0; i < points.size() - 1; i++) {
                pointsForCurveDraw.add(calculatePoints(points, param, i));
            }
            points = pointsForCurveDraw;
            return getPointsForBezierCurves(points, param);
        }
    }

    // вычисление точек для отрисовки кривой Безье без использования факториала
    private Point2D.Double calculatePoints(List<Point2D.Double> points, double param, int i) {
        double x = points.get(i + 1).getX() * param - points.get(i).getX() * (param - 1);
        double y = points.get(i + 1).getY() * param - points.get(i).getY() * (param - 1);
        return new Point2D.Double(x, y);
    }

    // вычисление точек для отрисовки кривой Безье с помощью факториала

//    private Point2D.Double calculatePointsWithFactorial( List<Point2D.Double> points, double t) {
//        double x = 0;
//        double y = 0;
//
//        int n = points.size() - 1;
//        for (int i = 0; i <= n; i++) {
//            x += fact(n) / (fact(i) * fact(n - i)) * points.get(i).getX() * Math.pow(t, i) * Math.pow(1 - t, n - i);
//            y += fact(n) / (fact(i) * fact(n - i)) * points.get(i).getY() * Math.pow(t, i) * Math.pow(1 - t, n - i);
//        }
//        return new Point2D.Double(x,y);
//    }
//
//    private double fact(double arg) {
//        if (arg < 0) throw new RuntimeException("Incorrect arg!");
//        if (arg == 0) return 1;
//        double result = 1;
//        for (int i = 1; i <= arg; i++)
//            result *= i;
//        return result;
//    }

}
