import java.awt.*;

import javax.swing.*;

public class OceanStyle implements TicTacToeStyle {
  
  private Icon getScaledImageIcon(int x, int y, String url) {
    ImageIcon icon = new ImageIcon(url);
    Image image = icon.getImage();
    image = image.getScaledInstance(x, y, Image.SCALE_DEFAULT);
    icon.setImage(image);
    return icon;
  }
  
  public Icon getXIcon(int x, int y) {
    return getScaledImageIcon(x, y, "src/basicX.png");
  }

  public Icon getOIcon(int x, int y) {
    return getScaledImageIcon(x, y, "src/bubbleIcon.png");
  }

  public Icon getBackgroundIcon(int x, int y) {
    return getScaledImageIcon(x, y, "src/ocean.jpg");
  }

  public Color getBorderColor() {
    return Color.BLACK;
  }

}