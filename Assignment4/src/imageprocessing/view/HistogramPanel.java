package imageprocessing.view;

import java.awt.*;
import java.util.Map;

import javax.swing.*;

import imageprocessing.model.ManipulableImage;
import imageprocessing.model.Pixel;

public class HistogramPanel extends JPanel {
  private ManipulableImage img;
  private final String type;

  public HistogramPanel(ManipulableImage img, String type) {
    super();
    this.img = img;
    this.type = type;
    this.setPreferredSize(new Dimension(266, 200));
  }

  public void setImage(ManipulableImage img) {
    this.img = img;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (this.img != null) {
      switch (this.type) {
        case "red":
          g.setColor(new Color(237, 15, 15));
          drawHistogram(img.histogram(pixel -> pixel.getChannels()[0]), g);
          break;
        case "green":
          g.setColor(new Color(17, 234, 49));
          drawHistogram(img.histogram(pixel -> pixel.getChannels()[1]), g);
          break;
        case "blue":
          g.setColor(new Color(12, 54, 220));
          drawHistogram(img.histogram(pixel -> pixel.getChannels()[2]), g);
          break;
        case "intensity":
          g.setColor(new Color(117, 85, 20));
          drawHistogram(img.histogram(Pixel::getIntensity), g);
          break;
      }
    }
  }

  private void drawHistogram(Map<Integer, Integer> histogram, Graphics g) {
    double maxFreq = 0;
    for (int i = 0; i < 256; i += 1) {
      double freq = histogram.getOrDefault(i, 0);
      maxFreq = Math.max(freq, maxFreq);
    }
    for (int i = 10; i < 266; i += 1) {
      double freq = histogram.getOrDefault(i - 10, 0);
      g.drawLine(i - 5, getHeight() - (int) (getHeight() * (freq / (maxFreq * 1.2))),
              i - 5, getHeight());
    }
  }
}
