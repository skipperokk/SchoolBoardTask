package View;

import Model.LineModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Getter
@Setter
public class FramePanel extends JPanel {
    private List<LineModel> lineModels = new ArrayList<>();
    private List<LineModel> temporaryLineModels = new ArrayList<>();
    private Graphics2D graphics2D;

    @Override
    protected void paintComponent(Graphics g) {
        List<LineModel> currentLines = new ArrayList<>();
        super.paintComponent(g);
        graphics2D = (Graphics2D) g;
        currentLines.addAll(lineModels);
        currentLines.addAll(temporaryLineModels);
        currentLines.forEach(graphics2D::draw);
    }
}
