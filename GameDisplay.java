import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage; 
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.Graphics;
import java.lang.Object; 
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.ImageIO;




public class GameDisplay extends JFrame {
	JPanel board;
	JPanel desc;
	JLayeredPane background;
    Dimension boardSize = new Dimension(1170, 882);
    Dimension paneSize = new Dimension(1300, 950);
    
    ImageIcon[] playerImages;
	
	public void boardBackground() {
		background = new JLayeredPane();
		background.setPreferredSize(paneSize);
		
		
		board = new JPanel();
		background.add(board, JLayeredPane.DEFAULT_LAYER);
		board.setLayout(new GridLayout(2, 2));
		board.setPreferredSize(boardSize);
		board.setBounds(0, 0, boardSize.width, boardSize.height);
		String[] backgrounds = {"deadwoodboardstopleft.jpg", "deadwoodboardstopright.jpg", "deadwoodboardsbotleft.jpg", "deadwoodboardsbotright.jpg"};
		for (int i = 0; i < 4; i++){
			JLabel background = new JLabel(new ImageIcon(backgrounds[i]));
			board.add(background);
		}
	}
}
