package org.gis.Drawing;

import org.gis.View.FramePanel;

public class DrawCurves {
    private ParseCommand command;
    private FramePanel panel;
    private BezierMethod bezierMethod;

    public DrawCurves(ParseCommand command, FramePanel panel, BezierMethod bezierMethod) {
        this.command = command;
        this.panel = panel;
        this.bezierMethod = bezierMethod;
    }
    public void drawCurves(String com, int width, int height) {
        command.parseCommand(com, width, height);
        if (command.getCommandLine().getAction().equals("start")) {
            panel.getLineModels().addAll(panel.getTemporaryLineModels());
            panel.getTemporaryLineModels().clear();
            bezierMethod.getListPoints().clear();
            panel.repaint();
            bezierMethod.setStartPoint(command.getCommandLine().getPoints());
            bezierMethod.getListPoints().add(command.getCommandLine().getPoints());
        } else if (command.getCommandLine().getAction().equals("move")) {
            bezierMethod.getListPoints().add(command.getCommandLine().getPoints());
            panel.getTemporaryLineModels().clear();
            panel.getTemporaryLineModels().addAll(bezierMethod.getBezierCurves(command.getCommandLine().getColor()));
            panel.repaint();
        }
    }
}
