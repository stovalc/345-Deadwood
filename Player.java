import java.util.*;

public class Player {
	private int Dollars;
	private int Credits;
	private int Rank;
	private Area Location;
	private Role playerRole;
	private String Name;
	private int rCounter;
	
	public Player(String n, Area start, int r)
	{
		Dollars = 0;
		Credits = 0;
		Rank = r;
		Location = start;
		Name = n;
	}
	public String getName()
	{
		return Name;
	}
	//Ensures that scene is not wrapped
	public void takeRole(Role r)
	{
		if(Location.getScene() != null)
		{
			if(r.getRank() <= Rank)
			{
				if(r.getPlayer() == null)
				{
						playerRole = r;
						r.setPlayer(this);
				}
			}
		}
	}
	public void resetCounter()
	{
		rCounter = 0;
	}
	//Moves player, displays information about room moved to
	public void move(Area newLocation)
	{
		if(Location.getAdjacent().contains(newLocation))
		{
			System.out.println();
			Location.moveOff(this);
			Location = newLocation;
			Location.moveOn(this);
			System.out.println("Player " + Name + "'s location is " + Location.getName());
			ArrayList<Area> adj = Location.getAdjacent();
			System.out.println("This room is adjacent to");
			System.out.print(adj.get(0).getName());
			for(int i = 1; i < adj.size(); i++)
			{
				System.out.print(", " + adj.get(i).getName());
			}
			System.out.println();
			System.out.println("With roles:");
			ArrayList<Role> roleList = Location.getRoles();
			for(int i = 0 ; i < roleList.size(); i++)
			{
				Role r = roleList.get(i);
				System.out.println(r.getName() + ": \"" + r.getDetails() + "\", rank " + r.getRank());
			}
			
			System.out.println();
		}
	}
	public void changeDollars(int amt)
	{
		Dollars = Dollars + amt;
	}
	public void changeCredits(int amt)
	{
		Dollars = Dollars + amt;
	}
	public void rehearse()
	{
		rCounter ++;
	}
	
	//Also ensures that player has a role, determines gains from acting
	public void act(Scene s)
	{
		if(playerRole != null)
		{
			int dice = (int)(Math.random()*6.0) + 1;
			if(dice + rCounter >= s.getDiff())
			{
				System.out.println("Success!");
				if(!playerRole.getStatus())
				{
					Credits = Credits + 2;
					System.out.println("Player gets two credits");
				}
				else
				{
					System.out.println("Player gets one credit and one dollar");
					Credits = Credits + 1;
					Dollars = Dollars + 1;
				}
			}
			else
			{
				System.out.println("Failure!");
				if(playerRole.getStatus())
				{
					System.out.println("Player gets one dollar");
					Dollars = Dollars + 1;
				}
			}
			Location.decrementShots();
			System.out.println("Location " + Location.getName() + " has " + Location.getShots() + " remaining");
		}
	}
	public int getCredits()
	{
		return Credits;
	}
	public int  getDollars()
	{
		return Dollars;
	}
	public void removeRole()
	{
		playerRole = null;
	}
	public Role setRole(Role r)
	{
		if(Rank >= r.getRank())
		{
			System.out.println("Player's role is now " + r.getName());
			playerRole = r;
			r.setPlayer(this);
		}
		else
		{
			System.out.println("Player cannot take this role, rank is too high");
		}
		return playerRole;
	}
	public Role getRole()
	{
		return playerRole;
	}
	//Determines whether player can upgrade rank, if so does such
	public boolean Upgrade(int newR, boolean type)
	{
		if(newR == 2)
		{
			if(Dollars >= 4 && type)
			{
				Rank = 2;
				return true;
			}
			else if(Credits >= 5 && !type)
			{
				Rank = 2;
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(newR == 3)
		{
			if(Dollars >= 10 && type)
			{
				Rank = 3;
				return true;
			}
			else if(Credits >= 10 && !type)
			{
				Rank = 3;
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(newR == 4)
		{
			if(Dollars >= 18 && type)
			{
				Rank = 4;
				return true;
			}
			else if(Credits >= 15 && !type)
			{
				Rank = 4;
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(newR == 5)
		{
			if(Dollars >= 28 && type)
			{
				Rank = 5;
				return true;
			}
			else if(Credits >= 20 && !type)
			{
				Rank = 5;
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(newR == 6)
		{
			if(Dollars >= 40 && type)
			{
				Rank = 6;
				return true;
			}
			else if(Credits >= 25 && !type)
			{
				Rank = 6;
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public Area getLocation() {
		return Location;
	}
	public int getRank(){
		return Rank;
	}
	public int getMoney()
	{
		int count = Dollars + Credits + 5*Rank;
		return count;
	}
	
	//resets the players on area
	public void reset(Area a){
		Location.moveOff(this);
		Location = a;
	}
}
