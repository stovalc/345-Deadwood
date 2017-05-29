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
	private int playNum;
	private ArrayList<Player> pList;
	private JButton rehearse;
	private JLayeredPane background;
    private Dimension boardSize = new Dimension(1200, 900);
    private Dimension paneSize = new Dimension(1300, 1000);
    private JLabel[] playerInfo = {new JLabel("Player 1"), new JLabel("Player 2"), new JLabel("Player 3"), new JLabel("Player 4"), new JLabel("Player 5"), new JLabel("Player 6"), new JLabel("Player 7"), new JLabel("Player 8")};
    //JLabel dayNum = new JLabel("Day Number: " + DeadWood.day + "/4");
  private JLabel[] playerNames;


    public static int numPlayers;

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
		buttons.setLayout(new GridLayout(4, 1));
		buttons.setBounds(0, boardSize.height, boardSize.width, 68);
		move = new JButton("Move");
		move.setPreferredSize(new Dimension(40, 40));
		act = new JButton("Act");
		act.setPreferredSize(new Dimension(40, 40));
		rehearse = new JButton("Rehearse");
		rehearse.setPreferredSize(new Dimension(40, 40));
		JButton upgradeMoney = new JButton("Upgrade using money");
		upgradeMoney.setPreferredSize(new Dimension(40, 40));
		JButton upgradeCredits = new JButton("Upgrade using credits");
		upgradeCredits.setPreferredSize(new Dimension(40, 40));
		JButton endTurn = new JButton("End Turn");
		endTurn.setPreferredSize(new Dimension(40, 40));
		JButton work = new JButton("Work");
		work.setPreferredSize(new Dimension(40, 40));
		buttons.add(work);
		buttons.add(move);
		buttons.add(act);
		buttons.add(rehearse);
		buttons.add(endTurn);
		buttons.add(upgradeMoney);
		buttons.add(upgradeCredits);
		background.add(buttons, JLayeredPane.DEFAULT_LAYER);

		//button functionality
		move.addActionListener(this);
		act.addActionListener(this);
		rehearse.addActionListener(this);


		//Adding the data display
		data = new JPanel();
		data.setLayout(new GridLayout(9,1));
		data.setPreferredSize(new Dimension(100, 900));
		data.setBounds(boardSize.width, 0, 100, 900);
		//data.add(dayNum); needs to be fixed
		for (int i = 0; i < 8; i++){
			data.add(playerInfo[i]);
		}
		background.add(data, JLayeredPane.DEFAULT_LAYER);

	}
	public  void actionPerformed(ActionEvent e) {
		 JButton source = (JButton)e.getSource();
		 Player p = pList.get(playNum);
		if(source.equals(move))
		{

			ArrayList<Area> locs = p.getLocation().getAdjacent();
			ArrayList<String> locName = new ArrayList<String>();
			for(int i = 0; i < locs.size(); i++)
			{
				locName.add(locs.get(i).getName());
			}
			Area moveTo = null;
			Object[] options = locName.toArray(new Object[locName.size()]);
			if(p.getPhase()!= 0)
			{
				options = null;
			}
			String pick = (String)JOptionPane.showInputDialog(null, "Please choose desired area", "Areas", JOptionPane.DEFAULT_OPTION, null, options, "0");
			for(int i = 0; i < locs.size(); i++)
			{
				if(locs.get(i).getName().equals(pick))
				{
					moveTo = locs.get(i);
				}
			}
			p.move(moveTo);

			//update the player location
			Area newA = p.getLocation();
			int[] xy = newA.getXY()[0];
			playerNames[playNum].setLocation(xy[0] - 600, xy[1] - 450);

		}
		if(source.equals(act))
		{
			if(p.getRole()!= null)
			{
				p.act(p.getLocation().getScene());
			}
		}
		if(source.equals(rehearse))
		{
			if(p.getRole()!= null)
			{
				p.rehearse();
			}
		}
	}
	public void setCard(Scene s)
	{
		JLabel newS = new JLabel(new ImageIcon(s.getFile()));
		newS.setBounds(0, 0, boardSize.width, boardSize.height);
		newS.setLocation(s.getXY()[0] - 600 + 205/2, s.getXY()[1] - 450 + 115/2);
		background.add(newS, JLayeredPane.DRAG_LAYER );
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
			ArrayList<String[]> diceNames = new ArrayList<>(Arrays.asList(blueDice, cyanDice, greenDice, orangeDice, pinkDice, redDice, violetDice, yellowDice));
			//Setup a dice for each player
			for (int i = 0; i < numPlayers; i++){
				playerPics[i] = new ImageIcon(diceNames.get(i)[0]);
				playerNames[i] = new JLabel(playerPics[i]);
				//put icons on board
				JLabel temp = null;
				temp = playerNames[i];
				temp.setBounds(0, 0, boardSize.width, boardSize.height);
				temp.setLocation(991-600 + 194/2, 248 - 450 + 201/2);

				background.add(temp, JLayeredPane.DRAG_LAYER);
				//needs work
			}

		}

		public int displayData(ArrayList<Player> players, int playerNum){
			playNum = playerNum;
			int numPlayers = players.size();
			//dayNum.setText("Day Number: " + Deadwood.day);
			for (int i = 0; i < numPlayers; i++){
				playerInfo[i].setVerticalTextPosition(JLabel.TOP);
				playerInfo[i].setHorizontalTextPosition(JLabel.RIGHT);
				String[] playerColors = {"Blue", "Cyan", "Green", "Orange", "Pink", "Red", "Violet", "Yellow"};
				if (i == playerNum){
					playerInfo[i].setText("<html>Player: " + (i + 1) + "<br>It's your turn!" +
				"<br>Credits: " + players.get(i).getCredits() + "<br>Dollars: " + players.get(i).getDollars() + "<br>Rank: " + players.get(i).getRank() +"</html>");
				} else {
					playerInfo[i].setText("<html>Player: " + (i + 1) + "<br>Credits: " + players.get(i).getCredits() + "<br>Dollars: " + players.get(i).getDollars() + "<br>Rank: " + players.get(i).getRank()+"</html>");
				}
			}
			return 0;
		}



}
