import java.util.*;

public class Scene {
	private String name;
	private String fileName;
	private int Difficulty;
	private ArrayList<Role> roleList = new ArrayList<Role>();
	private String details;
	private boolean isFlipped;
	private int sceneNum;
	private Area location;
	
	//Constructor for the scene object
	public Scene(String n, int num, String deets, int budget, String fName)
	{
		name = n;
		Difficulty = budget;
		sceneNum = num;
		details = deets;
		fileName = fName;
		isFlipped = false;
	}
	public int getNum()
	{
		return sceneNum;
	}
	public void setLoc(Area a)
	{
		location = a;
	}
	public void addRoles(Role r)
	{
		roleList.add(r);
	}
	public ArrayList<Role> getRoles()
	{
		return roleList;
	}
	public Area getLoc()
	{
		return location;
	}
	public String getName()
	{
		return name;
	}
	
	//Starts the shoot for the current movie in the location
	//Also displays available roles
	public void startShoot()
	{
		System.out.println(name + ", scene " + sceneNum);
		System.out.println(details);
		System.out.println("With roles:");
		for(int i = 0 ; i < roleList.size(); i++)
		{
			Role r = roleList.get(i);
			System.out.println(r.getName() + ": \"" + r.getDetails() + "\", rank " + r.getRank());
		}
		System.out.println();
		isFlipped = true;
	}
	
	//Budget of the movie
	public int getDiff()
	{
		return Difficulty;
	}
	
	//Wraps the movie when the shot counter = 0
	public void finalCut()
	{
		List<Integer> diceRolls = new ArrayList<Integer>();
		for(int i = 0; i < Difficulty; i++)
		{
			diceRolls.add((int)(Math.random()*6.0) + 1);
		}
		Collections.sort(diceRolls);
		Collections.reverse(diceRolls);

		for(int i = 0; i < Difficulty; i++)
		{
			Role r = roleList.get(i%roleList.size());
			Player p = r.getPlayer();
			if(p != null)
			{
				p.changeDollars(diceRolls.get(i));
				System.out.println("Player " + p.getName() + "gets " + diceRolls.get(i) + " dollars");
			}
		}
		
		location.setScene(null);
		ArrayList<Player> pList = location.getPlayers();
		location = null;
		for(int i = 0; i < pList.size(); i++)
		{
			Player pTemp = pList.get(i);
			Role r= pTemp.getRole();
			if(r != null)
			{
				r.setPlayer(null);
			}
			else
			{
				pTemp.changeCredits(r.getRank());
				System.out.println("Player " + pTemp.getName() + " gets " + r.getRank() + " credits");
			}
			pTemp.removeRole();
			pTemp.resetCounter();
		}
		
	}
	
	//Flips the card, will be used in GUI
	public void flip(boolean f)
	{
		isFlipped = f;
	}
}
