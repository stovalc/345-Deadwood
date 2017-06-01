import java.util.*;

public class Player {
	private int Dollars;
	private int Credits;
	private int Rank;
	private Area Location;
	private Role playerRole;
	private String Name;
	private int rCounter;
	private String fileName;
	private int offsetX;
	private int offsetY;
	private int turnPhase = 0;
	private int[] xyLoc = {991 - 600, 248 - 450};

	public Player(String n, Area start, int r)
	{
		Dollars = 0;
		Credits = 0;
		Rank = r;
		Location = start;
		Name = n;
	}
	public int[] getXY()
	{
		return xyLoc;
	}
	public void setXY(int x, int y)
	{
		xyLoc[0] = x;
		xyLoc[1] = y;
	}
	public int getPhase()
	{
		return turnPhase;
	}
	public void setPhase(int x)
	{
		turnPhase = x;
	}
	public void setFile(String str){
		fileName = str;
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
		if(turnPhase == 0)
		{
			if(playerRole == null)
			{
				if(Location.getAdjacent().contains(newLocation))
				{
					System.out.println();
					Location.moveOff(this);
					ArrayList<Player> listP = Location.getPlayers();
					if(Location.getMove() != null)
					{
						for(int i = 0; i < listP.size(); i++)
						{
							Player pTemp = listP.get(i);
							pTemp.setXY(Location.getMove()[i][0] -600 + 25, Location.getMove()[i][1] - 450 + 25);
						}
					}
					else
					{
						for(int i = 0; i < listP.size(); i++)
						{
							Player pTemp = listP.get(i);
							System.out.println(pTemp.getName());
							if(i < 4)
							{
								pTemp.setOffsetX(40*i);
								pTemp.setOffsetY(0);
							}
							else
							{
								pTemp.setOffsetX(40*(i-4));
								pTemp.setOffsetY(115);
							}
						}
					}

					Location = newLocation;
					Location.moveOn(this);
					listP = Location.getPlayers();
					if(Location.getMove() != null)
					{
						offsetX = 0;
						offsetY = 0;
						xyLoc[0] = Location.getMove()[listP.size()-1][0] -600 + 25;
						xyLoc[1] = Location.getMove()[listP.size()-1][1] -450 + 25;
					}
					else
					{
						for(int i = 0; i < listP.size(); i++)
						{
							Player pTemp = listP.get(i);
							System.out.println(pTemp.getName());
							if(i < 4)
							{
								pTemp.setOffsetX(40*i);
								pTemp.setOffsetY(0);
							}
							else
							{
								pTemp.setOffsetX(40*(i-4));
								pTemp.setOffsetY(115);
							}
						}
						this.setXY(Location.getXY()[0][0] - 600, Location.getXY()[0][1] - 450);
					}
					System.out.println("NEXT");
					
					turnPhase = 1;
					
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
					if(roleList != null)
					{
					for(int i = 0 ; i < roleList.size(); i++)
					{
						Role r = roleList.get(i);
						System.out.println(r.getName() + ": \"" + r.getDetails() + "\", rank " + r.getRank());
					}
				}
					System.out.println();
				}
			}
		}
	}
	public int getOffsetX()
	{
		return offsetX;
	}
	public void setOffsetX(int off)
	{
		offsetX = off;
	}
	public int getOffsetY()
	{
		return offsetY;
	}
	public void setOffsetY(int off)
	{
		offsetY = off;
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
	public boolean upgradeCredits(int newR)
	{
		if(newR == 2)
		{
			if(Credits >= 5)
			{
				Rank = 2;
				Credits -= 5;
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(newR == 3)
		{
			if(Credits >= 10)
			{
				Credits -= 10;
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
			if(Credits >= 15)
			{
				Credits -= 15;
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
			Credits -= 20;
			if(Credits >= 20)
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
			Credits -= 25;
			if(Credits >= 25)
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

	public boolean upgradeMoney(int newR)
	{
		if(newR == 2)
		{
			if(Dollars >= 4)
			{
				Dollars -= 4;
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
			if(Dollars >= 10)
			{
				Dollars -= 10;
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
			if(Dollars >= 18)
			{
				Dollars -= 18;
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
			if(Dollars >= 28)
			{
				Dollars -= 28;
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
			if(Dollars >= 40)
			{
				Dollars -= 40;
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
		Location.moveOn(this);
		xyLoc[0] = a.getXY()[0][0] - 600;
		xyLoc[1] = a.getXY()[0][1] - 450;
	}
}
