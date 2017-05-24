
public class Role {
	private String name;
	private String details;
	private int Rank;
	private boolean extra; //whether or not the role is on or off the board, false = on card
	private Player person;
	
	//Basic constructor/getters/setters
	public Role(String n, String d, int R, boolean e)
	{
		name = n;
		details = d;
		Rank = R;
		extra = e;
		person = null;
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
