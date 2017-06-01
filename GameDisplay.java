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




public class GameDisplay extends JFrame implements ActionListener {
		private JPanel board;
		private JPanel data;
		private JButton move;
		private JButton act;
		private JPanel lastAction;
		private JButton endTurn;
		private ArrayList<ArrayList<JLabel>> shotCounts = new ArrayList<ArrayList<JLabel>>();
		private ArrayList<String[]> diceNames;
		private int turnOpt;
		private ArrayList<JLabel> cards = new ArrayList<JLabel>();
		private JButton work;
		private JButton upgradeMoney;
		private JButton upgradeCredits;
		private int playNum;
		private Player currP;
		private ArrayList<Player> pList;
		private JButton rehearse;
		private JLayeredPane background;
		private int[] day = new int[2];
	  private Dimension boardSize = new Dimension(1200, 900);
	  private Dimension paneSize = new Dimension(1300, 1000);
	  private JLabel[] playerInfo = {new JLabel("Player 1"), new JLabel("Player 2"), new JLabel("Player 3"), new JLabel("Player 4"), new JLabel("Player 5"), new JLabel("Player 6"), new JLabel("Player 7"), new JLabel("Player 8")};
	  private JLabel dayNum = new JLabel();
		private JLabel lastA = new JLabel();
	  private JLabel[] playerNames;


    public static int numPlayers;
    public void setDay(int t, int max)
    {
    	day[0] = t;
    	day[1] = max;
    }
	public GameDisplay() {
		background = new JLayeredPane();
		getContentPane().add(background);
		background.setPreferredSize(paneSize);

		//Board setup from different sections
		board = new JPanel();
		background.add(board, JLayeredPane.DEFAULT_LAYER);
		board.setPreferredSize(boardSize);
		board.setBounds(0, 0, boardSize.width, boardSize.height);
		JLabel backgroundImage = new JLabel(new ImageIcon("board.jpg"));
		board.add(backgroundImage);
		

		//Adding turn based action buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(2, 3));
		buttons.setBounds(0, boardSize.height, boardSize.width-300, 68);
	  move = new JButton("Move");
		move.setPreferredSize(new Dimension(40, 40));
		act = new JButton("Act");
		act.setPreferredSize(new Dimension(40, 40));
	  rehearse = new JButton("Rehearse");
		rehearse.setPreferredSize(new Dimension(40, 40));
	  upgrade = new JButton("Upgrade");
		upgrade.setPreferredSize(new Dimension(40, 40));
    endTurn = new JButton("End Turn");
		endTurn.setPreferredSize(new Dimension(40, 40));
		work = new JButton("Work");
		work.setPreferredSize(new Dimension(40, 40));
		buttons.add(work);
		buttons.add(move);
		buttons.add(act);
		buttons.add(rehearse);
		buttons.add(endTurn);
		buttons.add(upgrade);
		background.add(buttons, JLayeredPane.DEFAULT_LAYER);

		//button functionality
		move.addActionListener(this);
		act.addActionListener(this);
		rehearse.addActionListener(this);
		endTurn.addActionListener(this);
		work.addActionListener(this);
		upgrade.addActionListener(this);

		//Adding the data display
		data = new JPanel();
		data.setLayout(new GridLayout(9,1));
		data.setPreferredSize(new Dimension(100, 900));
		data.setBounds(boardSize.width, 0, 100, 900);
		data.add(dayNum);
		for (int i = 0; i < 8; i++){
			data.add(playerInfo[i]);
		}
		lastAction = new JPanel();

		lastAction.setPreferredSize(new Dimension(100, 100));
		lastAction.setBounds(900, 900, 300, 100);
		lastAction.add(lastA);

		background.add(lastAction, JLayeredPane.DEFAULT_LAYER);
		background.add(data, JLayeredPane.DEFAULT_LAYER);

		
	}
	public void setLastAction(String s1)
	{
		lastA.setText("<html> Last Action: <br><html>" + s1);
	}
	public String showOpt(String s1, String s2, Object[] list)
	{
		return (String)JOptionPane.showInputDialog(null, s1, s2, JOptionPane.DEFAULT_OPTION, null, list, "0");
	}
	public int getOpt()
	{
		return turnOpt;
	}
	public void setOpt()
	{
		turnOpt = 0;
	}
	public void showMsg(String s1, String s2)
	{
		JOptionPane.showMessageDialog(null, s1, s2, JOptionPane.PLAIN_MESSAGE);
	}
	public void removeCounts()
	{
		while(shotCounts.size() > 0)
		{
			shotCounts.remove(0);
		}
	}
	public void addCount(Scene s)
	{
		ArrayList<JLabel> shots = new ArrayList<JLabel>();
		for(int i = 0; i < s.getLoc().shots(); i ++)
		{
			JLabel addS = new JLabel(new ImageIcon("shotcounter-1.png"));
			addS.setBounds(0, 0, boardSize.width, boardSize.height);
			addS.setLocation(s.getLoc().getXY()[i+1][0] - 600 + 24, s.getLoc().getXY()[i+1][1] -450 + 29);
			shots.add(addS);

			background.add(addS, JLayeredPane.MODAL_LAYER );
		}
		shotCounts.add(shots);
	}
	public void changeIcon(int i, int rank)
	{
		playerNames[i].setIcon(new ImageIcon(diceNames.get(i)[rank - 1]));
	}
	public void decCount(int s, int left)
	{
		JLabel shot = shotCounts.get(s).get(left - 1);
		shotCounts.get(s).remove(shot);
		shot.setLocation(-500, -500);
	}
	//Button event listener
	public  void actionPerformed(ActionEvent e) {
		 JButton source = (JButton)e.getSource();

		if(source.equals(move))
		{
			turnOpt = 1;
		}
		if(source.equals(act))
		{
			turnOpt = 2;
		}
		if(source.equals(rehearse))
		{
			turnOpt = 3;
		}
		if(source.equals(endTurn))
		{
			turnOpt = 4;
		}
		if(source.equals(upgrade))
	  {
			turnOpt = 5;
		}
		if(source.equals(work))
		{
			turnOpt = 7;
		}
	}
public void removeCards()
{
	while(cards.size() > 0)
	{
		JLabel card = cards.get(0);
		background.remove(card);
		cards.remove(0);
	}
	
}
	//Initializes cards on the board
	public void setCard(ArrayList<Scene> sList)
	{
		for(int i = 0; i < sList.size(); i++)
		{
			Scene s = sList.get(i);
		System.out.println(s.getXY()[0] +", " + s.getXY()[1]);
		JLabel newS = new JLabel(new ImageIcon("back.png"));
		newS.setBounds(0, 0, boardSize.width, boardSize.height);
		newS.setLocation(s.getXY()[0] - 600 + 205/2, s.getXY()[1] - 450 + 62);
		background.add(newS, JLayeredPane.MODAL_LAYER );
		cards.add(newS);
		}
	}
	public void startShoot(int s, String file)
	{
		cards.get(s).setIcon(new ImageIcon(file));
	}
	public void removeCard(int i)
	{
		JLabel card = cards.get(i);

		card.setLocation(-500, -500);
	}
		//Drop down options for number of players
		public static int numOfPlayers(){
			Object[] options = {"2", "3", "4", "5", "6", "7", "8"};
			String pick = (String)JOptionPane.showInputDialog(null, "Please choose the number of players.\n", "Number of players?", JOptionPane.DEFAULT_OPTION, null, options, "0");
			numPlayers = Integer.parseInt(pick);
			return numPlayers;
		}

		public static int getNumOfPlayers(){
			return numPlayers;
		}
		public void clearPlayers()
		{
			for(int i = 0; i < playerNames.length; i++)
			{
				background.remove(playerNames[i]);
			}
			for(int i = 0 ; i < playerInfo.length; i++)
			{
				data.remove(playerInfo[i]);
				playerInfo[i] = new JLabel("Player " + (i+1));
				data.add(playerInfo[i]);
			}
		}

		public void playerIcons(ArrayList<Player> players){

			pList = players;
			numPlayers = players.size();

			playerNames = new JLabel[numPlayers];
			ImageIcon[] playerPics = new ImageIcon[numPlayers];

			String[] blueDice = {"b1.png", "b2.png", "b3.png", "b4.png", "b5.png", "b6.png"};
			String[] cyanDice = {"c1.png", "c2.png", "c3.png", "c4.png", "c5.png", "c6.png"};
			String[] greenDice = {"g1.png", "g2.png", "g3.png", "g4.png", "g5.png", "g6.png"};
			String[] orangeDice = {"o1.png", "o2.png", "o3.png", "o4.png", "o5.png", "o6.png"};
			String[] pinkDice = {"p1.png", "p2.png", "p3.png", "p4.png", "p5.png", "p6.png"};
			String[] redDice = {"r1.png", "r2.png", "r3.png", "r4.png", "r5.png", "r6.png"};
			String[] violetDice = {"v1.png", "v2.png", "v3.png", "v4.png", "v5.png", "v6.png"};
			String[] yellowDice = {"y1.png", "y2.png", "y3.png", "y4.png", "y5.png", "y6.png"};
			diceNames = new ArrayList<String[]>(Arrays.asList(blueDice, cyanDice, greenDice, orangeDice, pinkDice, redDice, violetDice, yellowDice));
			//Setup a dice for each player
			for (int i = 0; i < numPlayers; i++){
				playerPics[i] = new ImageIcon(diceNames.get(i)[players.get(i).getRank()-1]);
				playerNames[i] = new JLabel(playerPics[i]);
				playerNames[i].setBounds(0, 0, boardSize.width, boardSize.height);
				//playerNames[i].setLocation(991-600 + 194/2, 248 - 450 + 201/2);

				background.add(playerNames[i], JLayeredPane.DRAG_LAYER);
				//needs work
			}

		}
		public void setPlayer(int t)
		{
			playNum = t;
		}
		public void changeLayer(int pNum)
		{
			background.moveToBack(playerNames[pNum]);
		}
		public int displayData(ArrayList<Player> players, int playerNum){
			playNum = playerNum;
			pList = players;
			numPlayers = players.size();
			dayNum.setText("Day " + day[0] + " / " + day[1]);
			for (int i = 0; i < numPlayers; i++){
				Player p = pList.get(i);
				playerInfo[i].setVerticalTextPosition(JLabel.TOP);
				playerInfo[i].setHorizontalTextPosition(JLabel.RIGHT);
				String[] playerColors = {"Blue", "Cyan", "Green", "Orange", "Pink", "Red", "Violet", "Yellow"};
				String[] colorCodes= {"#0000FF", "#00FFFF", "#008000", "#FFA500", "#FF69B4", "#FF0000", "#9400D3", "FFFF00"};
				if (i == playerNum){
					playerInfo[i].setText("<html>Player: " + (i + 1) + "<br>It's your turn!" +
				"<br>Credits: " + players.get(i).getCredits() + "<br>Dollars: " + players.get(i).getDollars() + "<br>Rank: " + players.get(i).getRank() + "<br><font color = \"" + colorCodes[i] +"\">Color: " + playerColors[i] + "</font></html>");
				} else {
					playerInfo[i].setText("<html>Player: " + (i + 1) + "<br>Credits: " + players.get(i).getCredits() + "<br>Dollars: " + players.get(i).getDollars() + "<br>Rank: " + players.get(i).getRank() + "<br><font color = \"" + colorCodes[i] +"\">Color: " + playerColors[i] + "</font></html>");
				}
				playerNames[i].setLocation(p.getXY()[0], p.getXY()[1]);
			}
			return 0;
		}

		
		public void displayWinner(Player p)
		{
			JOptionPane.showMessageDialog(null, p.getName() + ", with a score of " + p.getMoney(), "WINNER!", JOptionPane.PLAIN_MESSAGE);
		}


}
