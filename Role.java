
public class Role {
	private String name;
	private String details;
	private int Rank;
	private boolean extra; //whether or not the role is on or off the board, false = on card
	private Player person;
	private int[] xyLoc;
	private int size;
	
	//Basic constructor/getters/setters
	public Role(String n, String d, int R, boolean e, int[] xy)
	{
		name = n;
		details = d;
		Rank = R;
		extra = e;
		person = null;
		xyLoc = xy;
		if(e)
		{
			size = 46;
		}
		else
		{
			size = 40;
		}
	}
	public Player getPlayer()
	{
		return person;
	}
	public String getName()
	{
		return name;
	}
	public String getDetails()
	{
		return details;
	}
	public int getRank()
	{
		return Rank;
	}
	public void setPlayer(Player p)
	{
		person = p;
	}
	public boolean getStatus()
	{
		return extra;
	}
}
