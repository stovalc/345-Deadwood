
import java.util.*;

public class Area {
	private String roomName;
	private int shotsLeft;
	private int currentShots;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private ArrayList<Role> roleList = new ArrayList<Role>();
	private ArrayList<Area> adjacentRooms = new ArrayList<Area>();
	private Scene roomScene;
	
	//Self-explanatory, constructors + getters/setters
	public Area(String name, ArrayList<Role> roles, int shots) {
		roomName = name;
		roleList = roles;
		shotsLeft = shots;
		currentShots = shotsLeft;
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
