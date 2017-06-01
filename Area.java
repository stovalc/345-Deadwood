
import java.util.*;

public class Area {
	private String roomName;
	private int shotsLeft;
	private int currentShots;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private ArrayList<Role> roleList = new ArrayList<Role>();
	private ArrayList<Area> adjacentRooms = new ArrayList<Area>();
	private Scene roomScene;
	private int[][] xyLocations;
	private int[] size = {115, 205};
	private int[][] moveTo;
	private ArrayList<Integer> availLoc;

	//Self-explanatory, constructors + getters/setters
	public Area(String name, ArrayList<Role> roles, int shots, int[][] locs) {
		roomName = name;
		roleList = roles;
		shotsLeft = shots;
		currentShots = shotsLeft;
		xyLocations = locs;
		availLoc = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
	}
	public int removeLoc()
	{
		int temp = availLoc.get(0);
		availLoc.remove(0);
		return temp;
	}
	public void addLoc(int loc)
	{
		if(loc < 8)
		{
			availLoc.add(loc);
			Collections.sort(availLoc);
		}
	}
	public void setMove(int[][] opt)
	{
		moveTo = opt;
	}
	public int[][] getMove()
	{
		return moveTo;
	}
	public void setSize(int[] s)
	{
		size = s;
	}
	public int[] getSize()
	{
		return size;
	}
	public int[][] getXY()
	{
		return xyLocations;
	}
	public void addRooms(Area room)
	{
		adjacentRooms.add(room);
	}
	public void moveOn(Player p)
	{
		playerList.add(p);
	}
	public void moveOff(Player p)
	{
		playerList.remove(p);
	}
	public void resetShots()
	{
		shotsLeft = currentShots;
	}
	public void decrementShots()
	{
		shotsLeft --;
	}
	public void setScene(Scene s)
	{
		roomScene = s;
	}
	public void removeScene()
	{
		roomScene = null;
	}
	public String getName()
	{
		return roomName;
	}
	public int getShots()
	{
		return shotsLeft;
	}
	public int shots()
	{
		return currentShots;
	}
	public ArrayList<Player> getPlayers()
	{
		return playerList;
	}
	public ArrayList<Role> getRoles()
	{
		return roleList;
	}
	public ArrayList<Area> getAdjacent()
	{
		return adjacentRooms;
	}
	public Scene getScene()
	{
		return roomScene;
	}
}
