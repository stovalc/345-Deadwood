import java.util.*;
import javax.swing.*;

public class DeadWood extends JFrame {
	private static int day;
	private static int turn;
	private static ArrayList<Scene> deck = new ArrayList<Scene>();
	private static ArrayList<Scene> discard = new ArrayList<Scene>();
	private static ArrayList<Scene> board = new ArrayList<Scene>();
	private static GameDisplay backgroundBoard;
	private static ArrayList<Player> playerList = new ArrayList<Player>();
	private static ArrayList<Area> locations = new ArrayList<Area>();
	private static Area start;
	private static int maxDay = 4;
	private static int playerNum;
	
	//Controls basic game flow
	public static void main(String[] args)
	{		
		Scanner scan = new Scanner(System.in);
		if(args.length != 0)
		{
			playerNum = Integer.parseInt(args[0]);
		}
		else
		{
			System.out.println("Please enter the number of players (must be 2-8)");
			playerNum = scan.nextInt();
		}
		
		backgroundBoard = new GameDisplay();
		backgroundBoard.setDefaultCloseOperation(EXIT_ON_CLOSE);
		backgroundBoard.pack();
		backgroundBoard.setLocationRelativeTo(null);
		backgroundBoard.setResizable(true);
		backgroundBoard.setVisible(true);
		
		boolean game = true;
		startGame();
		
		while(game)
		{
			day = 0;
			turn = 0;
			while(day < maxDay)
			{
				while(board.size() > 1)
				{
					takeTurn(turn);
					turn++;
					if(turn == playerList.size())
					{
						turn = 0;
					}
					System.out.println();
				}
				resetDay();
			}
			endGame();
			System.out.println("Enter 0 if you wish to stop playing, else enter # of players for next game");
			int check = scan.nextInt();
			boolean exit = true;
			while(exit)
			{
				if(check == 0)
				{
					exit =false;
					game = false;
				}
				else if(check > 1 && check <= 8)
				{
					exit = false;
					playerList.clear();
					if(check < 4)
					{
						maxDay = 3;
					}
					for(int i = 0; i < check; i++)
					{
						System.out.println("Please enter the player name");
						Player p;
						String n = scan.next();
						if(check >= 7)
						{
							p = new Player(n, start, 2);
						}
						else
						{
							p = new Player(n, start, 1);
						}
						if(check == 5)
						{
							p.changeCredits(2);
						}
						if(check == 6)
						{
							p.changeCredits(4);
						}
							
						playerList.add(p);
					}
				}
				else
				{
					System.out.println("Improper input, enter 0 to end game, or input # of players bewteen 2-8 for next game");
					check = scan.nextInt();
				}
			}
			//determine whether want to continue playing
		}
		System.out.println("GG");
	}
	
	//Initializes everything
	public static void startGame()
	{
		Role rrWorker = new Role("Railroad Worker", "I'm a steel driving man!", 1, true);
		Role fRoof = new Role("Falls off Roof", "Aaaaiiiigggghh!", 2, true);
		Role wBDress = new Role("Woman in Black Dress", "Well, I'll be!", 2, true);
		Role Mayor = new Role("Mayor McGinty", "People of Deadwood!", 4, true);
		ArrayList<Role> mRoles= new ArrayList<Role>();
		mRoles.add(rrWorker);
		mRoles.add(fRoof);
		mRoles.add(wBDress);
		mRoles.add(Mayor);
		Area mST = new Area("Main Street", mRoles, 3);
		
		locations.add(mST);
		
		Role wRDress = new Role("Woman in Red Dress", "Well, I'll be!", 2, true);
		Role rFarmer = new Role("Reluctant Farmer", "I ain't so sure about that!", 1, true);
		ArrayList<Role> salRoles= new ArrayList<Role>();
		salRoles.add(wRDress);
		salRoles.add(rFarmer);
		Area Saloon = new Area("Saloon", salRoles, 2);
		
		locations.add(Saloon);
		
		Role mUHorse = new Role("Man Under Horse", "A little help here!", 3, true);
		Role sLeg = new Role("Shot in Leg", "Ow! Me leg!", 1, true);
		Role sFred = new Role("Saucy Fred", "That's what she said!", 2, true);
		ArrayList<Role> ranRoles= new ArrayList<Role>();
		ranRoles.add(mUHorse);
		ranRoles.add(sLeg);
		ranRoles.add(sFred);
		Area Ranch = new Area("Ranch", ranRoles, 2);
		
		locations.add(Ranch);
		
		Role cPFighter = new Role("Clumsy Pit Fighter", "Hit me!", 1, true);
		Role tKnife = new Role("Thug with Knife", "Meet Suzy, my murderin' knife.", 2, true);
		Role dTom = new Role("Dangerous Tom", "There's two ways we can do this....", 3, true);
		Role pLost = new Role("Penny, who is Lost", "Oh, woe! For I am lost!", 4, true);
		ArrayList<Role> sHRoles= new ArrayList<Role>();
		sHRoles.add(cPFighter);
		sHRoles.add(tKnife);
		sHRoles.add(dTom);
		sHRoles.add(pLost);
		Area sHideout = new Area("Secret Hideout", ranRoles, 3);
		
		locations.add(sHideout);
		
		Role fTeller = new Role("Flustered Teller", "Would you like a large bill, sir?", 3, true);
		Role sGent = new Role("Suspicious Gentleman", "Can you be more specific", 2, true);
		ArrayList<Role> bankRoles= new ArrayList<Role>();
		bankRoles.add(fTeller);
		bankRoles.add(sGent);
		Area Bank = new Area("Bank", bankRoles, 1);
		
		locations.add(Bank);
		
		Role dMan = new Role("Dead Man", "....", 1, true);
		Role cWoman = new Role("Crying Woman", "Oh, the humanity!", 1, true);
		ArrayList<Role> churchRoles= new ArrayList<Role>();
		churchRoles.add(dMan);
		churchRoles.add(cWoman);
		Area Church = new Area("Church", churchRoles, 2);
		
		locations.add(Church);
		
		Role fPlayer = new Role("Faro Player", "Hit me!", 1, true);
		Role sDrunk = new Role("Sleeping Drunkard", "Zzzzzz.. Whiskey!", 1, true);
		Role aBart = new Role("Australian Bartender", "What'll it be, mate?", 3, true);
		Role fBalconey = new Role("Falls from Balconey", "Arrrgghh!!", 2, true);
		ArrayList<Role> hotelRoles= new ArrayList<Role>();
		hotelRoles.add(fPlayer);
		hotelRoles.add(sDrunk);
		hotelRoles.add(aBart);
		hotelRoles.add(fBalconey);
		Area Hotel = new Area("Hotel", hotelRoles, 3);
		
		locations.add(Hotel);
		
		Role cProsp = new Role("Crusty Prospector", "Aww, peaches!", 1, true);
		Role dTrain = new Role("Dragged by Train", "Omgeezers!", 1, true);
		Role pBag = new Role("Preacher with Bag", "The Lord will provide.", 2, true);
		Role cGun = new Role("Cyrus the Gunfighter", "Git to fightin' or git away!", 4, true);
		ArrayList<Role> tSRoles= new ArrayList<Role>();
		tSRoles.add(cProsp);
		tSRoles.add(dTrain);
		tSRoles.add(pBag);
		tSRoles.add(cGun);
		Area tStation = new Area("Train Station", tSRoles, 3);
		
		locations.add(tStation);
		
		Role pCell = new Role("Prisoner in Cell	", "Zzzzzzz... Whiskey!", 2, true);
		Role fIrons = new Role("Feller in Irons", "Ah kilt the wrong man!", 3, true);
		ArrayList<Role> jailRoles= new ArrayList<Role>();
		jailRoles.add(pCell);
		jailRoles.add(fIrons);
		Area Jail = new Area("Jail", jailRoles, 1);
		
		locations.add(Jail);
		
		Role mOver = new Role("Man in Overalls", "Looks like a storm's comin' in.", 1, true);
		Role mKeach = new Role("Mister Keach", "Howdy, strager.", 1, true);
		ArrayList<Role> gSRoles= new ArrayList<Role>();
		gSRoles.add(mOver);
		gSRoles.add(mKeach);
		Area gStore = new Area("General Store", gSRoles, 2);
		
		locations.add(gStore);
		
		
		Area Trailers = new Area("Trailers", null, 0);
		locations.add(Trailers);
		Area cOffice = new Area("Casting Office", null, 0);
		locations.add(cOffice);
		
		start = Trailers;
		
		mST.addRooms(Jail);
		mST.addRooms(Saloon);
		mST.addRooms(Trailers);
		
		Saloon.addRooms(mST);
		Saloon.addRooms(gStore);
		Saloon.addRooms(Bank);
		Saloon.addRooms(Trailers);
		
		Trailers.addRooms(mST);
		Trailers.addRooms(Saloon);
		Trailers.addRooms(Hotel);
		
		cOffice.addRooms(tStation);
		cOffice.addRooms(sHideout);
		cOffice.addRooms(Ranch);
		
		Ranch.addRooms(gStore);
		Ranch.addRooms(cOffice);
		Ranch.addRooms(sHideout);
		Ranch.addRooms(Bank);
		
		
		sHideout.addRooms(Ranch);
		sHideout.addRooms(cOffice);
		sHideout.addRooms(Church);
		
		Bank.addRooms(Saloon);
		Bank.addRooms(Ranch);
		Bank.addRooms(Church);
		Bank.addRooms(Hotel);
		
		Hotel.addRooms(Trailers);
		Hotel.addRooms(Bank);
		Hotel.addRooms(Church);
		
		Church.addRooms(Bank);
		Church.addRooms(sHideout);
		Church.addRooms(Hotel);
		
		tStation.addRooms(cOffice);
		tStation.addRooms(gStore);
		tStation.addRooms(Jail);
		
		Jail.addRooms(tStation);
		Jail.addRooms(gStore);
		Jail.addRooms(mST);
		
		gStore.addRooms(Jail);
		gStore.addRooms(tStation);
		gStore.addRooms(Ranch);
		gStore.addRooms(Saloon);
		
		String apost = "\"";
		
		Role dPriest = new Role("Defrocked Priest", "Look out below!", 2, false);
		Role mCan = new Role("Marshal Canfield", "Hold fast!", 3, false);
		Role oneEye = new Role("One-Eyed Man", "Balderdash!", 4, false);
		
		Scene evilHat = new Scene("Evil Wears a Hat", 7, "Calhoun is separated from the group during a white-knuckle chase near Desperation Bluff", 4, "01.png");
		evilHat.addRoles(oneEye);
		evilHat.addRoles(mCan);
		evilHat.addRoles(dPriest);
		deck.add(evilHat);
		
		
		Role sqBoy = new Role("Squeaking Boy", "I'll say!", 2, false);
		Role pImh = new Role("Pharaoh Imhotep", "Attack, soldiers!", 4, false);
		Role aMartha = new Role("Aunt Martha", "You got nothin'!", 6, false);
		
		Scene sqDeal = new Scene("Square Deal City", 14, "Douglas and Katherine confront Aunt Martha about her missing pies. Devin sulks quietly in a side room.", 6, "06.png");
		sqDeal.addRoles(aMartha);
		sqDeal.addRoles(pImh);
		sqDeal.addRoles(sqBoy);
		deck.add(sqDeal);
		
		
		Role rugMerchant = new Role("Rug Merchant", "Don't leave my store!", 1, false);
		Role banker = new Role("Banker", "Trust me", 2, false);
		Role talkingMule = new Role("Talking Mule", "Nice work, Johnny!", 5, false);
		
		Scene lawOldWest = new Scene("Law and the Old West", 20, "Charlie " + apost + "Three Guns" + apost + " Henderson cooperates with Johnny Law and reluctantly enters the witless protection program.", 3, "02.png");
		lawOldWest.addRoles(talkingMule);
		lawOldWest.addRoles(banker);
		lawOldWest.addRoles(rugMerchant);
		deck.add(lawOldWest);
		
		Role theDuck = new Role("The Duck", "Waaaak!", 4, false);
		Role hisBrother = new Role("His Brother", "Waaaaaaaak!", 6, false);
		
		Scene davyCrockett31 = new Scene("Davy Crockett: A Drunkard’s Tale", 31, "Robert enlists the aid of several farm animals in order to ascertain the efficacy of his new hangover remedy", 4, "07.png");
		davyCrockett31.addRoles(hisBrother);
		davyCrockett31.addRoles(theDuck);
		deck.add(davyCrockett31);
		
		Role Auctioneer = new Role("Auctioneer", "Going once!", 5, false);
		Role generalCuster = new Role("General Custer", "Go West!", 6, false);
		
		Scene johnSkywater22 = new Scene("The Life and Times of John Skywater", 22, "Disheartened by his lack of business acumen and his poor choice of investment partners, John Skywater sets off into the Cree Nation to convince them to kidnap his wife.", 5, "03.png");
		johnSkywater22.addRoles(generalCuster);
		johnSkywater22.addRoles(Auctioneer);
		deck.add(johnSkywater22);
		
		Role townDrunk = new Role("Town Drunk", "Even me!", 2, false);
		Role squintingMiner = new Role("Squinting Miner", "Sure we can!", 4, false);
		Role poltergeist = new Role("Poltergeist", "Wooooo!", 5, false);
		
		Scene wayWestRun = new Scene("The Way the West Was Run", 34, "Jose explains patiently, but with thinly veiled contempt, the intricacies of Arizona bureaucracy, as though speaking to a simple and distracted child.", 4, "08.png");
		wayWestRun.addRoles(poltergeist);
		wayWestRun.addRoles(squintingMiner);
		wayWestRun.addRoles(townDrunk);
		deck.add(wayWestRun);
		
		Role drunk = new Role("Drunk", "Where's Willard?", 3, false);
		Role librarian = new Role("Librarian", "Shhhhh!", 4, false);
		Role manWithHay = new Role("Man With Hay", "Hey!", 6, false);
		
		Scene myYearsOnThePrarie = new Scene("My Years on the Prarie", 32, "Virgil and Stacy set out at midnight to track down the stray cows, unaware that they are being pursued by inch-high aliens from outer space.", 5, "04.png");
		myYearsOnThePrarie.addRoles(manWithHay);
		myYearsOnThePrarie.addRoles(librarian);
		myYearsOnThePrarie.addRoles(drunk);
		deck.add(myYearsOnThePrarie);
		
		Role angryBarber = new Role("Angry Barber", "Hold him still!", 1, false);
		Role womanWithBoard = new Role("Woman with Board", "Nonsense, Frank!", 3, false);
		Role manOnFire = new Role("Man on Fire", "It burns!", 5, false);
		
		Scene downInTheValley = new Scene("Down in the Valley", 24, "A tripped waiter is the spark igniting a brawl of cataclysmic proportions. Walter is injured in the neck.", 3, "09.png");
		downInTheValley.addRoles(manOnFire);
		downInTheValley.addRoles(womanWithBoard);
		downInTheValley.addRoles(angryBarber);
		deck.add(downInTheValley);
		
		Role holleringBoy = new Role("Hollering Boy", "Over here, mister!", 2, false);
		Role drunkFarmer = new Role("Drunk Farmer", " Git outta me barn!", 3, false);
		Role meekLittleSarah = new Role("Meek Little Sarah", "He's so cute!", 5, false);
		
		Scene buffaloBill = new Scene("Buffalo Bill: The Lost Years", 12, "Buffalo Bill’s companion Marty disappears in a freak electrical storm. Bill enlists the aid of the Sidekick Friends Network.", 4, "05.png");
		buffaloBill.addRoles(meekLittleSarah);
		buffaloBill.addRoles(drunkFarmer);
		buffaloBill.addRoles(holleringBoy);
		deck.add(buffaloBill);
		
		Role sleepingMan = new Role("Sleeping Man", "Snnkkk snnkk snnkk", 1, false);
		Role manWithPig = new Role("Man With Pig", "Tally-Hooo!", 2, false);
		Role shooter = new Role("Shooter", "Wehre's my britches", 4, false);
		
		Scene olShooter = new Scene("Ol' Shooter and Little Doll", 14, "Shooter discovers that he has been proceeding for days with no trousers. This causes him no small embarrassment as he searches for them with Little Doll.", 4, "10.png");
		olShooter.addRoles(shooter);
		olShooter.addRoles(manWithPig);
		olShooter.addRoles(sleepingMan);
		deck.add(olShooter);
		
		Role buster = new Role("Buster", "One two three go!", 1, false);
		Role manReadingPaper = new Role("Man Reading Paper", "Ouchie!", 4, false);
		Role fatPete = new Role("Fat Pete", "Nice kick, boss!", 5, false);
		
		Scene robbersOfTrains = new Scene("The Robbers of Trains", 19, "Coogan confronts the toughest thug in his gang, Big Jake, in an abbreviated knife fight. Coogan settles the dispute with fearless guile and a kick in the family jewels.", 4, "11.png");
		robbersOfTrains.addRoles(fatPete);
		robbersOfTrains.addRoles(manReadingPaper);
		robbersOfTrains.addRoles(buster);
		deck.add(robbersOfTrains);
		
		Role shotInBack = new Role("Shot in Back", "Arrrggh!", 2, false);
		Role shotInLeg = new Role("Shot in Leg", "Ooh, lordy!", 4, false);
		Role leapsIntoCake = new Role("Leaps into Cake", "Dangit, Jesse!", 5, false);
		
		Scene jesseJames8 = new Scene("Jesse James: Man of Action", 8, "Jesse’s brothers Jed and Henry throw him a surprise birthday party. Jesse’s nerves get the better of him when the birthday cake explodes.", 5, "16.png");
		jesseJames8.addRoles(leapsIntoCake);
		jesseJames8.addRoles(shotInLeg);
		jesseJames8.addRoles(shotInBack);
		deck.add(jesseJames8);
		
		Role martin = new Role("Martin", "Have you tried soy cheese?", 6, false);
		
		Scene beyondThePail = new Scene("Beyond the Pail: Life without Lactose", 12, "Henry discovers for the first time that his ability to digest cream has disappeared along with his hair. Other cowboys attempt to console him.", 2, "12.png");
		beyondThePail.addRoles(martin);
		deck.add(beyondThePail);
		
		Role pianoPlayer = new Role("Piano Player", "It's a nocturne!", 2, false);
		Role manInTurban = new Role("Man in Turban", "My Stars!", 3, false);
		Role fallsOnHoe = new Role("Falls on Hoe", "Ow!", 4, false);
		
		Scene disasterAtFlyingJ = new Scene("Disaster at Flying J", 6, "After the mine explosion, the traveling circus takes time out to get drunk and start a fight.", 5, "17.png");
		disasterAtFlyingJ.addRoles(fallsOnHoe);	
		disasterAtFlyingJ.addRoles(manInTurban);
		disasterAtFlyingJ.addRoles(pianoPlayer);
		deck.add(disasterAtFlyingJ);
		
		Role preacher = new Role("Preacher", "My Word!", 3, false);
		Role amusedWitness = new Role("Amused Witness", "Tee hee hee!", 6, false);
		
		Scene aManCalledCow = new Scene("A Man Called \"Cow\"", 16, "Nothing will settle the debates among the skeptical locals, short of a demonstration of Hector’s special talents.", 3, "13.png");
		aManCalledCow.addRoles(amusedWitness);
		aManCalledCow.addRoles(preacher);
		deck.add(aManCalledCow);
		
		Role fallsFromTree = new Role("Falls from Tree", "What ho!", 1, false);
		Role laughingWoman = new Role("Laughing Woman", "Tis to laugh!", 3, false);
		Role manWithWhistle = new Role("Man with Whistle", "Tweeeet!", 4, false);
		
		Scene shakespeareInLubbock = new Scene("Shakespeare in Lubbock", 23, "William decides that it is time to be movin’ on. Julia convinces him to stick around just long enough to get into big trouble", 3, "18.png");
		shakespeareInLubbock.addRoles(manWithWhistle);
		shakespeareInLubbock.addRoles(laughingWoman);
		shakespeareInLubbock.addRoles(fallsFromTree);
		deck.add(shakespeareInLubbock);
		
		Role curiousGirl = new Role("Curios Girl", "Are you sure?", 3, false);
		Role ghostOfPlato = new Role("Ghost of Plato", "It happened to me!", 4, false);
		
		Scene taffyCommercial = new Scene("Taffy Commercial", 2, "Jackson encourages the children to eat only taffy, because gum can kill them stone dead.", 2, "14.png");
		taffyCommercial.addRoles(ghostOfPlato);
		taffyCommercial.addRoles(curiousGirl);
		deck.add(taffyCommercial);
		
		Role exConvict = new Role("Ex-Convict", "Never again!", 4, false);
		Role manWithOnions = new Role("Man with Onions", "Fresh Onions!", 6, false);
		
		Scene goWestYou = new Scene("Go West, You!", 30, "Susan and Peter encounter some of the perils of the Badlands: rutted mud roads, torrential rain storms, and a bad case of \"grumble tummy.\"", 3, "19.png");
		goWestYou.addRoles(manWithOnions);
		goWestYou.addRoles(exConvict);
		deck.add(goWestYou);
		
		Role suprisedBison = new Role("Suprised Bison", "Mmrrrrrph!", 2, false);
		Role manWithHorn = new Role("Man with Horn", "Ta daaaa!", 4, false);
		
		Scene gumCommercial = new Scene("Gum Commercial", 3, "Inspector Pete speaks to a riveted audience about the many hidden dangers of taffy, not the least of which is that taffy can kill you stone dead.", 2, "15.png");
		gumCommercial.addRoles(manWithHorn);
		gumCommercial.addRoles(suprisedBison);
		deck.add(gumCommercial);
		
		Role staggeringMan = new Role("Staggering Man", "You never know!", 3, false);
		Role womanWithBeer = new Role("Woman with Beer", "Howdy, stranger!", 5, false);
		Role marcie = new Role("Marcie", "Welcome home!", 6, false);
		
		Scene johnSkywater = new Scene("The Life and Times of Skywater", 15, "John discovers his long-lost sister Marcie, and instructs her in the ways of gunfighting and whiskey distillation.", 5, "20.png");
		johnSkywater.addRoles(marcie);
		johnSkywater.addRoles(womanWithBeer);
		johnSkywater.addRoles(staggeringMan);
		deck.add(johnSkywater);
		
		Role looksLikeElvis = new Role("Looks Like Elvis", "Thankyouverymuch.", 4, false);
		Role singingDeadMan = new Role("Singing Dead Man", "Yeah!", 5, false);
		Role apothecary = new Role("Apothecary", "Such drugs I have.", 6, false);
		
		Scene gunTheMusical = new Scene("Gun! The Musical", 25, "A song and dance extravaganza, \"Hunka Hunka Burnin’ Lead.\"", 6, "21.png");
		gunTheMusical.addRoles(apothecary);
		gunTheMusical.addRoles(singingDeadMan);
		gunTheMusical.addRoles(looksLikeElvis);
		deck.add(gunTheMusical);
		 
		Role flusteredMan = new Role("Flustered Man", "Well, I never!", 1, false);
		Role spaceMonkey = new Role("Space Monkey", "Ook!", 2, false);
		Role cowbotDan = new Role("Cowbot Dan", "Bzzzzzt!", 5, false);
		Scene oneFalseStepForMankind = new Scene("One False Step for Mankind", 21, "After a dozen failed attempts, one rocket carries Horatio and his six children to the Moon, where they enjoy a picnic and a spirited game of badminton.", 6, "26.png");
		oneFalseStepForMankind.addRoles(cowbotDan);
		oneFalseStepForMankind.addRoles(spaceMonkey);
		oneFalseStepForMankind.addRoles(flusteredMan);
		deck.add(oneFalseStepForMankind);
		
		Role jailer = new Role("Jailer", "You there!", 2, false);
		Role mephistopheles = new Role("Mephistopheles", "Be not afraid!", 4, false);
		Role breaksWindow = new Role("Breaks a Window", "Oops!", 5, false);
		
		Scene humorAtTheExpenseOfOthers = new Scene("Humor at the Expense of Others", 21, "After a dozen failed attempts, one rocket carries Horatio and his six children to the Moon, where they enjoy a picnic and a spirited game of badminton.", 6, "22.png");
		humorAtTheExpenseOfOthers.addRoles(breaksWindow);
		humorAtTheExpenseOfOthers.addRoles(mephistopheles);
		humorAtTheExpenseOfOthers.addRoles(jailer);
		deck.add(humorAtTheExpenseOfOthers);
		
		Role manInPoncho = new Role("Man in Poncho", "Howdy, Jones!", 1, false);
		Role ecstaticHousewife = new Role("Ecstatic Housewife", "This is fine!", 3, false);
		Role isaac = new Role("Isaac", "The mail!", 5, false);
		
		Scene thirteenTheHardWay = new Scene("Thirteen the Hard Way", 15, "After some delay, the Pony Express arrives. Isaac, Gwen, Francis, Terry, Conrad, Brooke, Jerry, Howard, MacNeill, Jones, Spike, Cornwall and Crawford are all there.", 5, "27.png");
		thirteenTheHardWay.addRoles(isaac);
		thirteenTheHardWay.addRoles(ecstaticHousewife);
		thirteenTheHardWay.addRoles(manInPoncho);
		deck.add(thirteenTheHardWay);
		
		Role filmCritic = new Role("Film Critic", "Implausible", 5, false);
		Role hoboWithBat = new Role("Hobo with Bat", "Nice house!", 6, false);
		Scene theSearchForMaggieWhite = new Scene("The Search for Maggie White", 12, "Alone in the wilderness, Maggie makes the best of her situation. In what seems like no time at all, she constructs a sturdy two-story house from branches and mud.", 6, "23.png");
		theSearchForMaggieWhite.addRoles(hoboWithBat);
		theSearchForMaggieWhite.addRoles(filmCritic);
		deck.add(theSearchForMaggieWhite);
		
		Role cow = new Role("Cow", "Moo.", 2, false);
		Role stClementOfAlexandria = new Role("St. Clement of Alexandria", "Peace be with you, child!", 3, false);
		Role josie = new Role("Josie", "Yikes!", 4, false);
		
		Scene howTheyGetMilk = new Scene("How They Get Milk", 2, "Josie asks the Milkman how they get milk. After a thoughtful pause, he begins. \"Not like you’d expect!\"", 4, "28.png");
		howTheyGetMilk.addRoles(josie);
		howTheyGetMilk.addRoles(stClementOfAlexandria);
		howTheyGetMilk.addRoles(cow);
		deck.add(howTheyGetMilk);
		
		Role bewhiskerdCowpoke = new Role("Bewhisker'd Cowpoke", "Oh, sweet Lord!", 3, false);
		Role dog = new Role("Dog", "Wurf!", 5, false);
		
		Scene picanteSauceCommercial = new Scene("Picante Sauce Commercial", 1, "A dozen grizzled cowboys surround a fire. Suddenly, they exclaim, " + apost + "That’s not mayonnaise!" + apost, 2, "24.png");
		picanteSauceCommercial.addRoles(dog);
		picanteSauceCommercial.addRoles(bewhiskerdCowpoke);
		deck.add(picanteSauceCommercial);
		
		Role willard = new Role("Willard", "Ain't that a sight?", 2, false);
		Role leprechaun = new Role("Lerpechaun", "Begorrah!", 3, false);
		Role startledOx = new Role("Startled Ox", "Mrr?", 5, false);
		
		Scene myYearsOnThePrarie27 = new Scene("My years on the Prarie", 27, "Louise takes instruction from Henry, the neighbor boy, in an absurdly suggestive explanation of how to plow a field.", 5, "29.png");
		myYearsOnThePrarie27.addRoles(startledOx);
		myYearsOnThePrarie27.addRoles(leprechaun);
		myYearsOnThePrarie27.addRoles(willard);
		deck.add(myYearsOnThePrarie27);
		
		Role shotInHead = new Role("Shot in Head", "Arrrgh!", 1, false);
		Role leapsOutOfCake = new Role("Leaps out of Cake", "Oh, for Pete's sake!", 4, false);
		Role shotThreeTimes = new Role("Shot Three Times", "Ow! Ow! Ow!", 6, false);
		
		Scene jesseJames14 = new Scene("Jesse James: Man of Action", 14, "A hail of gunfire results when Jesse’s friend Barton marries Jesse’s childhood sweetheart.", 5, "25.png");
		jesseJames14.addRoles(shotThreeTimes);
		jesseJames14.addRoles(leapsOutOfCake);
		jesseJames14.addRoles(shotInHead);
		deck.add(jesseJames14);
		
		Role voiceOfGod = new Role("Voice of God", "Grab hold, son!", 2, false);
		Role handsOfGod = new Role("Hands of God", "!", 3, false);
		Role jackKemp = new Role("Jack Kemp", "America!", 4, false);
		
		Scene davyCrockett12 = new Scene("Davy Crockett: A Drunkards Tale", 12, "In an absurd dream sequence, Crockett recalls an episode of fear and chaos in which his childhood friend Timmy was trapped at the bottom of a well.", 4, "30.png");
		davyCrockett12.addRoles(jackKemp);
		davyCrockett12.addRoles(handsOfGod);
		davyCrockett12.addRoles(voiceOfGod);
		deck.add(davyCrockett12);
		
		
		Role farm = new Role("Farmer", "Git off a that!", 2, false);
		Role expHorse = new Role("Exploding Horse", "Boom!", 4, false);
		Role Jack = new Role("Jack", "Here we go again!", 6, false);
		
		Scene cOtherSt = new Scene("Custer's Other Stands", 40, "General George Armstrong Custer clinches another victory at the battle of Little Sands. His trusty steed Cairo is not so lucky.", 5, "40.png");
		cOtherSt.addRoles(Jack);
		cOtherSt.addRoles(expHorse);
		cOtherSt.addRoles(farm);
		deck.add(cOtherSt);
		
		Role dect = new Role("Detective", "I have a hunch.", 3, false);
		Role fClerk = new Role("File Clerk", "My stapler!", 4, false);
		Role cLou = new Role("Cindy Lou", "Dear Lord!", 5, false);
		
		Scene grinchTex = new Scene("How the Grinch Stole Texas", 9, "The doe-eyed citizens of El Paso gather together around a warm fire and pray for the safety of those poor souls in Oklahoma. It almost works.", 5, "35.png");
		grinchTex.addRoles(cLou);
		grinchTex.addRoles(fClerk);
		grinchTex.addRoles(dect);
		deck.add(grinchTex);
		
		Role fratPledge = new Role("Fraternity Pledge", "Beer me!", 2, false);
		Role manSword = new Role("Man with Sword", "None shall pass!", 6, false);
		
		Scene breakPonies = new Scene("Breakin’ in Trick Ponies", 19, "Uncle Stewart reveals what to do when all else fails.", 3, "39.png");
		breakPonies.addRoles(manSword);
		breakPonies.addRoles(fratPledge);
		
		Role burnMan = new Role("Burning Man", "Make it stop!", 2, false);
		Role cheeseVend = new Role("Cheese Vendor", "Opa!", 4, false);
		Role hitTable = new Role("Hit with Table", "Ow! A table?", 5, false);
		
		Scene trialPion = new Scene("Trials of the First Pioneers", 5, "A fire breaks out in the town livery. Before long, the surrounding buildings are engulfed in flame. The world falls into chaos.", 4, "34.png");
		trialPion.addRoles(hitTable);
		trialPion.addRoles(cheeseVend);
		trialPion.addRoles(burnMan);
		deck.add(trialPion);
		
		Role mark = new Role("Marksman", "Pull", 4, false);
		Role postWork = new Role("Postal Worker", "It's about time!", 5, false);
		Role aHorse = new Role("A Horse", "Yes Sir!", 6, false);
		
		Scene howMilk = new Scene("How They Get Milk", 8, "Josie is thoroughly off milk at this point. The Milkman shows her one more way that she might not have heard of before.", 4, "38.png");
		howMilk.addRoles(aHorse);
		howMilk.addRoles(postWork);
		howMilk.addRoles(mark);
		deck.add(howMilk);
		
		Role libNun = new Role("Liberated Nun", "Let me have it!", 3, false);
		Role witchDoc = new Role("Witch Doctor", "Oogie Boogie!", 5, false);
		Role voiceReason = new Role("Voice of Reason", "Come on, now!", 6, false);
		
		Scene swingWide = new Scene("Swing 'em Wide", 35, "Hector makes a surprising discovery behind the Chinese grocery store.", 6, "33.png");
		swingWide.addRoles(voiceReason);
		swingWide.addRoles(witchDoc);
		swingWide.addRoles(libNun);
		deck.add(swingWide);
		
		Role veryWet = new Role("Very Wet Man", "Sheesh!", 2, false);
		Role dejWife = new Role("Dejected Housewife", "Its time had come.", 4, false);
		Role manBox = new Role("Man with Box", "Progres!", 5, false);
		
		Scene thirtWay = new Scene("Thirteen the Hard Way", 17, "After operating for only six minutes, the Pony Express disbands and gives way to the international Telegraph and Railroad systems. Little boys cry.", 5, "37.png");
		thirtWay.addRoles(manBox);
		thirtWay.addRoles(dejWife);
		thirtWay.addRoles(veryWet);
		deck.add(thirtWay);
		
		Role thriftMike = new Role("Thrifty Mike", "Call!", 1, false);
		Role soberPhys = new Role("Sober Physician", "Raise!", 3, false);
		Role manFloor = new Role("Man on Floor", "Fold!", 5, false);
		
		Scene swingEm = new Scene("Swing 'em Wide", 19, "Black Jack invites Dixon and The Captain to a late-night poker game. Little do they know that Gertrude and Isabella await them at the table.", 6, "32.png");
		swingEm.addRoles(manFloor);
		swingEm.addRoles(soberPhys);
		swingEm.addRoles(thriftMike);
		deck.add(swingEm);
		
		Role manRope = new Role("Man with Rope", "Look out below!", 1, false);
		Role Svet = new Role("Svetlana", "Says who?", 2, false);
		Role accVictim = new Role("Accidental Victim", "Ow! My spine!", 5, false);
		
		Scene manSubstance = new Scene("J. Robert Lucky, Man of Substance", 13, "Horace and Mathilde discover that the mysterious orange powder filling Doctor Lucky’s air vents is neither Agent Orange nor weaponized Tang, but a rare form of cheese mold.", 4, "36.png");
		manSubstance.addRoles(accVictim);
		manSubstance.addRoles(Svet);
		manSubstance.addRoles(manRope);
		deck.add(manSubstance);
		
		Role Opice = new Role("Opice (Monkey)", "Ukk! (Ook)!", 5, false);
		Role manGun = new Role("Man with Gun", "Hold it right there!", 6, false);
		
		Scene Czechs = new Scene("Czechs in the Sonora", 25, "Bob reverts to his ancestral ways in a short fight over a disembodied hand", 4, "31.png");
		Czechs.addRoles(manGun);
		Czechs.addRoles(Opice);
		deck.add(Czechs);
		Collections.shuffle(deck);
		Scanner scan = new Scanner(System.in);
		if(playerNum < 4)
		{
			maxDay = 3;
		}
		if(playerNum < 2 || playerNum > 8)
		{
			System.out.println("Please enter a number between 2 and 8");
		}
		else
		{
			for(int i = 0; i < playerNum; i++)
			{
				System.out.println("Please enter the player name");
				Player p;
				String pName = scan.next();
				if(playerNum >= 7)
				{
					p = new Player(pName, Trailers, 2);
				}
				else
				{
					p = new Player(pName, Trailers, 1);
				}
				if(playerNum == 5)
				{
					p.changeCredits(2);
				}
				if(playerNum == 6)
				{
					p.changeCredits(4);
				}
				playerList.add(p);
			}
			Collections.shuffle(playerList);
			for(int i = 0; i < locations.size() -2; i++)
			{
				Area a = locations.get(i);
				Scene temp;
				temp = deck.get(0);
				deck.remove(temp);
				temp.setLoc(a);
				a.setScene(temp);
				board.add(temp);
			}
		}
		System.out.println("Game is initialized!");
	}
	
	//Determines winner/prepares for next game
	public static void endGame()
	{
		System.out.println("Game over!");
		for(int i = 0; i < discard.size(); i++)
		{
			Scene temp;
			temp = discard.get(i);
			temp.flip(false);
			deck.add(temp);
			discard.remove(i);
		}
	
		int[] winner = new int[playerList.size()];
		ArrayList<Player> win = new ArrayList<Player>();
		int max = 0;
		for(int i = 0; i < playerList.size(); i ++)
		{
			Player p = playerList.get(i);
			winner[i] = p.getMoney();
			if(winner[i] == max)
			{
				win.add(p);
			}
			else if(winner[i] > max)
			{
				win.clear();
				win.add(p);
				max = winner[i];
			}
			System.out.println(p.getName() + " ended the game with a score of: " + winner[i]);
			//calculate score
		}
		System.out.println();
		if(win.size() > 1)
		{
			System.out.println("Players ");
			for(int i = 0; i < win.size() - 1; i++)
			{
				System.out.println(win.get(i).getName() + ", ");
			}
			System.out.println("and " +win.get(win.size()-1).getName() + " tied for the win, with a score of " + win.get(win.size()-1).getMoney());
		}
		else
		{
			System.out.println(win.get(0).getName() + " won the game with a score of " + win.get(0).getMoney());
		}
	}
	
	//Replaces the cards left on board + places others, essentially resets day
	public static void resetDay()
	{
		System.out.println("End of day");
		for(int i = 0; i < board.size(); i++)
		{
			Scene temp;
			temp = board.get(i);
			temp.flip(true);
			temp.getLoc().removeScene();
			temp.setLoc(null);
			deck.add(temp);
			board.remove(i);
		}
		for(int i = 0; i < playerList.size(); i ++)
		{
			Player p = playerList.get(i);
			p.reset(start);
			Role r = p.getRole();
			if(r != null)
			{
				r.setPlayer(null);
			}
			p.resetCounter();
			p.removeRole();
		}
		for(int i = 0; i < locations.size() -2; i++){
			Area a = locations.get(i);
			Scene disc = a.getScene();
			if(disc != null) 
			{
				a.removeScene();
				disc.setLoc(null);
				board.remove(disc);
				discard.add(disc);
			}
			a.resetShots();
			Scene temp;
			temp = deck.get(0);
			deck.remove(temp);
			temp.setLoc(a);
			a.setScene(temp);
			board.add(temp);
		}
		day++;
	}
	
	//turn logic goes here, i.e have player choose to move, rehearse, act, upgrade, or take role
	//if player moves allow them to upgrade or take role after.
	//Uses turn-based cases for decisions
	public static void takeTurn(int player)
	{
		Scanner scan = new Scanner(System.in);
		Player p = playerList.get(player);
		Area loc = p.getLocation();
		Scene s = loc.getScene();
		System.out.println(p.getName() + "'s turn");
		boolean cont = true;
		boolean turn = true;
		boolean extend = true;
		while(turn)
		{
			String line = scan.nextLine();
			Scanner lineScan = new Scanner(line);
			ArrayList<String> input = new ArrayList<String>();
			while(lineScan.hasNext())
			{
				input.add(lineScan.next());
			}
			if(input.get(0).equals("end"))
			{
				turn = false;
			}
			else
			{
				if(input.get(0).equals("move"))
				{
					if(cont && extend)
					{
						if(p.getRole()== null)
						{
							boolean contain = false;
							int place = 0;
							ArrayList<Area> a = loc.getAdjacent();
							String str = input.get(1);
							for(int i = 2; i < input.size(); i++)
							{
								str += " ";
								str += input.get(i);

							}
							System.out.println(str);
							
							for(int i = 0; i < a.size(); i++)
							{
								if(a.get(i).getName().equals(str))
								{
									contain = true;
									place = i;
								}
							}
							if(contain)
							{
								p.move(a.get(place));
								loc = p.getLocation();
								s = loc.getScene();
								if(s != null)
								{
									s.startShoot();
								}
								cont = false;
							}
							else
							{
								System.out.println("Area not adjacent to current room");
							}
						}
						else
						{
							System.out.println("Player cannot move while working on a role");
						}
					}
					else
					{
						System.out.println("Player has already taken a vital turn action");
					}
				}
				if(input.get(0).equals("Rehearse"))
				{
					if(cont && extend)
					{
						if(p.getRole()!= null)
						{
							p.rehearse();
							cont = false;
						}
						else
						{
							System.out.println("Player cannot rehearse while not working on role");
						}
					}
					else
					{
						System.out.println("Player has already taken a vital turn action");
					}
				}
				if(input.get(0).equals("act"))
				{
					if(cont && extend)
					{
						if(p.getRole() != null)
						{
							p.act(s);
							if(loc.getShots() == 0)
							{
								s.finalCut();
								board.remove(s);
								discard.add(s);
							}
							cont = false;
						}
						else
						{
							System.out.println("Player cannot act while not working on role");
						}
					}
					else
					{
						System.out.println("Player has already taken a vital turn action");
					}	
				}
				
				if(input.get(0).equals("who"))
				{
					System.out.println();
					System.out.print(p.getName() + "($" + p.getDollars() + ", " + p.getCredits() + "cr) ");
					Role r = p.getRole();
					if(r != null)
					{
						System.out.println(r.getName() + ", \"" + r.getDetails() + "\"");
					}
					System.out.println();
				}
				if(input.get(0).equals("where"))
				{
					Area a = p.getLocation();
					System.out.println();
					System.out.print("in " + a.getName());
					int sLeft = a.getShots();
					if(sLeft != 0)
					{
						Scene tempS = a.getScene();
						System.out.println(" shooting " + tempS.getName() + " scene " + tempS.getNum());
					}
					else
					{
						System.out.println("wrapped");
					}
					System.out.println();
				}
				
				//Give option to either take role or upgrade if applicable
				if(input.get(0).equals("upgrade"))
				{
					if(extend)
					{
						if(p.getLocation().getName().equals("Casting Office"))
						{
							if(p.getRank() == 6)
							{
								System.out.println("Already at max rank");
							}
							else
							{
								boolean type = false;
								if(input.get(1).equals("$"))
								{
									type = true;
								}
								else if(input.get(1).equals("cr"))
								{
									type = false;
								}
								if(p.Upgrade(Integer.parseInt(input.get(2)), type))
								{
									extend = false;
									System.out.println("Player's role is now " + input.get(2));
								}
								else
								{
									if(type)
									{
										System.out.println("Insufficient dollars");
									}
									else
									{
										System.out.println("Insufficient credits");
									}
								}
							}
						}
						else
						{
							System.out.println("Player not at casting office");
						}
					}
					else
					{
						System.out.println("Player already taken similar action this turn");
					}
					//display possible upgrades for player w/ input
				}
				else if(input.get(0).equals("work"))
				{
					String workName = input.get(1);
					for(int i = 2; i < input.size(); i++)
					{
						workName  += " ";
						workName += input.get(i);
					}
					//display roles player can take, etc.
					if(p.getRole() == null)
					{
						s = loc.getScene();
						if(s != null && loc.getRoles() != null)
						{
							ArrayList<Role> r1 = s.getRoles();
							ArrayList<Role> r2 = loc.getRoles();
							ArrayList<Role> availRoles = new ArrayList<Role>();
							for(int i = 0; i < r1.size(); i++)
							{
								Role r = r1.get(i);
								if(r.getPlayer() == null && r.getRank() <= p.getRank())
								{
									availRoles.add(r);
								}
							}
							for(int i = 0; i < r2.size(); i++)
							{
								Role r = r2.get(i);
								if(r.getPlayer() == null && r.getRank() <= p.getRank())
								{
									availRoles.add(r);
								}
							}
							boolean actable = false;
							int place = 0;
							for(int i = 0; i < availRoles.size(); i++)
							{
								Role r= availRoles.get(i);
								if(r.getName().equals(workName))
								{
									actable = true;
									place = i;
								}
							}
							if(actable)
							{
								extend = false;
								p.setRole(availRoles.get(place));
							}
						}
						else
						{
							System.out.println("No roles to take");
						}
					}
					else
					{
							System.out.println("Player already has a role, cannot take another");
					}
				}
			}
		}
	}
}
