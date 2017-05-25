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
	JPanel data;
	JLayeredPane background;
    Dimension boardSize = new Dimension(1150, 850);
    Dimension paneSize = new Dimension(1300, 950);
    JLabel[] playerNames = {new JLabel("Player 1"), new JLabel("Player 2"), new JLabel("Player 3"), new JLabel("Player 4"), new JLabel("Player 5"), new JLabel("Player 6"), new JLabel("Player 7"), new JLabel("Player 8")};
    //JLabel dayNum = new JLabel("Day Number: " + DeadWood.day + "/4");
    
    
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
		
		//Adding turn based action buttons
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
		JButton endTurn = new JButton("End Turn");
		endTurn.setPreferredSize(new Dimension(40, 40));
		buttons.add(move);
		buttons.add(act);
		buttons.add(rehearse);
		buttons.add(endTurn);
		buttons.add(upgradeMoney);
		buttons.add(upgradeCredits);
		background.add(buttons, JLayeredPane.DEFAULT_LAYER);
		
		//Adding the data display
		data = new JPanel();
		data.setLayout(new GridLayout(9,1));
		data.setPreferredSize(new Dimension(100, 900));
		data.setBounds(boardSize.width, 0, 100, 900);
		//data.add(dayNum); needs to be fixed
		for (int i = 0; i < 8; i++){
			data.add(playerNames[i]);
		}
		background.add(data, JLayeredPane.DEFAULT_LAYER);
		
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
		
		public void playerIcons(Player[] players){
			numPlayers = players.length;
			String[] playerNames = new String[numPlayers];
			playerPics = new ImageIcon[numPlayers];	
			
			String[] diceNames = {"blueDice", "cyanDice", "greenDice", "orangeDice", "pinkDice", "redDice", "violetDice", "yellowDice"};
			String[] blueDice = {"b1.png", "b2.png", "b3.png", "b4.png", "b5.png", "b6.png"}; 
			String[] cyanDice = {"c1.png", "c2.png", "c3.png", "c4.png", "c5.png", "c6.png"}; 
			String[] greenDice = {"g1.png", "g2.png", "g3.png", "g4.png", "g5.png", "g6.png"}; 
			String[] orangeDice = {"o1.png", "o2.png", "o3.png", "o4.png", "o5.png", "o6.png"}; 
			String[] pinkDice = {"p1.png", "p2.png", "p3.png", "p4.png", "p5.png", "p6.png"}; 
			String[] redDice = {"r1.png", "r2.png", "r3.png", "r4.png", "r5.png", "r6.png"}; 
			String[] violetDice = {"v1.png", "v2.png", "v3.png", "v4.png", "v5.png", "v6.png"}; 
			String[] yellowDice = {"y1.png", "y2.png", "y3.png", "y4.png", "y5.png", "y6.png"}; 
			//Setup a dice for each player
			for (int i = 0; i < numPlayers; i++){
				playerNames[i] = "Player" + i;
				diceColor = diceNames[i];
				playerIcon = diceColor[1];
				playerLabels = new JLabel[diceColor];
				//put icons on board
				background.add(playerLabels[i]);
				//needs work		
			}
						
		}
		
		public int displayData(Player[] players, int playerNum){
			int numPlayers = players.length;
			//dayNum.setText("Day Number: " + Deadwood.day);
			for (int i = 0; i < numPlayers; i++){
				playerNames[i].setVerticalTextPosition(JLabel.TOP);
				playerNames[i].setHorizontalTextPosition(JLabel.RIGHT);
				String[] playerColors = {"Blue", "Cyan", "Green", "Orange", "Pink", "Red", "Violet", "Yellow"};
				if (i == playerNum){
					playerNames[i].setText("Player: " +_(i + 1) + "You are color: " + playerColors[i]) + "It's your turn!" + 
				"Credits: " + players[i].getCredits() + "Dollars: " players[i].getDollars() + "Rank: " + players[i].getRank());
				} else {
					playerNames[i].setText("Player: " +_(i + 1) + "You are color: " + playerColors[i]) + 
							"Credits: " + players[i].getCredits() + "Dollars: " players[i].getDollars() + "Rank: " + players[i].getRank());
				}
			}
			
		}
			
}