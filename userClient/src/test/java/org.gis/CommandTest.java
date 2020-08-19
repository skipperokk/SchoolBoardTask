package org.gis;

import org.gis.Drawing.ParseCommand;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;


public class CommandTest {
    private ParseCommand command;

    private final int width = 800;
    private final int height = 600;

    String comStart = "60:21:C0:2A:E0:33;start;0.15703125;0.28644067;-16777216";
    String comMove = "60:21:C0:2A:E0:33;move;0.2;0.6983051;-16777216";


    @Before
    public void createCommand() {
        command = new ParseCommand();
        command.parseCommand(comStart, width, height);
    }


    @Test
    public void getCorrectParseIDUser() {
        assertEquals(command.getCommandLine().getID(), "60:21:C0:2A:E0:33");
    }

    @Test
    public void getCorrectParseAction() {
        assertEquals(command.getCommandLine().getAction(), "start");

        command.parseCommand(comMove, width, height);
        assertEquals(command.getCommandLine().getAction(), "move");
    }

    @Test
    public void getCorrectParsePoints() {
        assertEquals(command.getCommandLine().getPoints(),
                new Point2D.Double(0.15703125 * width, 0.28644067 * height));
    }

    @Test
    public void getCorrectParseColor() {
        assertEquals(command.getCommandLine().getColor(), new Color(-16777216));
        assertEquals(command.getCommandLine().getColor(), Color.BLACK);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseIncorrectCommandFormat() {
        command.parseCommand("start;0.15703125;-16777216", width, height);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseIncorrectCommandFormatID() {
        command.parseCommand("60:21:C0:2AAA;move;0.2;0.6983051;-16777216", width, height);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseIncorrectCommandFormatAction() {
        command.parseCommand("60:21:C0:2A:E0:33;let's_go;0.2;0.6983051;-16777216", width, height);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseNullCommand() {
        command.parseCommand(null, width, height);
    }
}
