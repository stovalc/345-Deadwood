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
	JPanel info;
	JLayeredPane background;
    Dimension boardSize = new Dimension(1150, 850);
    Dimension paneSize = new Dimension(1300, 950);
    
    ImageIcon[] playerImages;
    
    static int numPlayers;
	
	public GameDisplay() {
		background = new JLayeredPane();
		getContentPane().add(background);
		background.setPreferredSize(paneSize);
		
		//Board setup from different sections
		board = new JPanel();
		background.add(board, JLayeredPane.DEFAULT_LAYER);
		board.setLayout(new GridLayout(2, 2));
		board.setPreferredSize(boardSize);
		board.setBounds(0, 0, boardSize.width, boardSize.height);
		String[] backgrounds = {"deadwoodboardstopleft.jpg", "deadwoodboardstopright.jpg", "deadwoodboardsbotleft.jpg", "deadwoodboardsbotright.jpg"};
		for (int i = 0; i < 4; i++){
			JLabel backgroundImages = new JLabel(new ImageIcon(backgrounds[i]));
			board.add(backgroundImages);
		}
		
		//Adding buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(4, 1));
		buttons.setBounds(0, boardSize.height, boardSize.width, 68);
		JButton move = new JButton("Move");
		move.setPreferredSize(new Dimension(40, 40));
		JButton act = new JButton("Act");
		act.setPreferredSize(new Dimension(40, 40));
		JButton rehearse = new JButton("Rehearse");
		rehearse.setPreferredSize(new Dimension(40, 40));		
		JButton upgradeMoney = new JButton("Upgrade using money");
		upgradeMoney.setPreferredSize(new Dimension(40, 40));
		JButton upgradeCredits = new JButton("Upgrade using credits");
		upgradeCredits.setPreferredSize(new Dimension(40, 40));
		buttons.add(move);
		buttons.add(act);
		buttons.add(rehearse);
		buttons.add(upgradeMoney);
		buttons.add(upgradeCredits);
		background.add(buttons, JLayeredPane.DEFAULT_LAYER);
	}
		
		//Drop down options for number of players
		public static int numOfPlayers(){
			Object[] options = {"2", "3", "4", "5", "6", "7", "8"};
			String pick = (String)JOptionPane.showInputDialog(null, "Please choose the number of players.\n", "Number of players?", JOptionPane.DEFAULT_OPTION, null, options, "0");
			Integer numPlayers = Integer.parseInt(pick);
			return numPlayers;		
		}		
		public int getNumOfPlayers(){
			return numPlayers;
		}		
}