import java.awt.Color;
import java.awt.Image;
import javax.swing.Icon;

public interface TicTacToeStyle {
	public Icon getXIcon(int x, int y);
	
	public Icon getOIcon(int x, int y);
	
	public Icon getBackgroundIcon(int x, int y);
	
	public Color getBorderColor();
	
}
