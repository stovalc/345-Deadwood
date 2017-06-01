import java.util.*;
import javax.swing.*;

public class DeadWood extends GameDisplay {
	public static int day;
	private static int turn;
	private static ArrayList<Scene> deck = new ArrayList<Scene>();
	private static ArrayList<Scene> discard = new ArrayList<Scene>();
	private static ArrayList<Scene> board = new ArrayList<Scene>();
	private static GameDisplay backgroundBoard;
	private static ArrayList<Player> playerList = new ArrayList<Player>();
	private static ArrayList<Area> locations = new ArrayList<Area>();
	private static Area start;
	private static int maxDay = 4;
	public static int playerNum;
	public static int numPlayers;
	private static int boardSize = 0;

	//Controls basic game flow
	public static void main(String[] args) throws InterruptedException
	{

//		Scanner scan = new Scanner(System.in);
//		if(args.length != 0)
//		{
//			playerNum = Integer.parseInt(args[0]);
//		}
//		else
//		{
//			System.out.println("Please enter the number of players (must be 2-8)");
//			playerNum = scan.nextInt();
//		}

		backgroundBoard = new GameDisplay();
		numPlayers = backgroundBoard.getNumOfPlayers();
		backgroundBoard.setDefaultCloseOperation(EXIT_ON_CLOSE);
		backgroundBoard.pack();
		backgroundBoard.setLocationRelativeTo(null);
		backgroundBoard.setResizable(true);
		backgroundBoard.setVisible(true);

		boolean game = true;
		startGame();
		//backgroundBoard.displayData(playerList, playerNum);

		while(game)
		{
			day = 0;
			turn = 0;
			while(day < maxDay)
			{
				backgroundBoard.setDay(day + 1, maxDay);
				resetDay();
				while(boardSize > 1)
				{
					backgroundBoard.displayData(playerList, turn);

					takeTurn(turn);
					turn++;

					if(turn == playerList.size())
					{
						turn = 0;
					}
					System.out.println();

				}
				
			}
			endGame();

			boolean exit = true;
			//determine whether want to continue playing
		}
		System.out.println("GG");
	}

	//Initializes everything
	public static void startGame()
	{
		int[] threeLoc = {20, 83, 145};
		int[] twoLoc = {53, 115};


		Role rrWorker = new Role("Railroad Worker", "I'm a steel driving man!", 1, true, new int[]{637, 22});
		Role fRoof = new Role("Falls off Roof", "Aaaaiiiigggghh!", 2, true, new int[]{720, 22});
		Role wBDress = new Role("Woman in Black Dress", "Well, I'll be!", 2, true, new int[]{637, 105});
		Role Mayor = new Role("Mayor McGinty", "People of Deadwood!", 4, true, new int[]{720, 105});
		ArrayList<Role> mRoles= new ArrayList<Role>();
		mRoles.add(rrWorker);
		mRoles.add(fRoof);
		mRoles.add(wBDress);
		mRoles.add(Mayor);

		Area mST = new Area("Main Street", mRoles, 3, new int[][]{new int[]{969, 28}, new int[]{912, 23}, new int[]{858, 23}, new int[]{804,23}});

		locations.add(mST);
		int[][] options1 = {{775, 75}, {820, 75}, {865, 75}, {910, 75}, {775, 125}, {820, 125}, {865, 125}, {910, 125}};
		mST.setMove(options1);
		
		Role wRDress = new Role("Woman in Red Dress", "Well, I'll be!", 2, true, new int[]{877, 276});
		Role rFarmer = new Role("Reluctant Farmer", "I ain't so sure about that!", 1, true, new int[]{877, 352});
		ArrayList<Role> salRoles= new ArrayList<Role>();
		salRoles.add(wRDress);
		salRoles.add(rFarmer);
		Area Saloon = new Area("Saloon", salRoles, 2, new int[][]{new int[]{632, 280}, new int[]{626, 216}, new int[]{679, 216}});
		 
		locations.add(Saloon);
		Saloon.setMove(new int[][]{new int[]{725, 225}, new int[]{770, 225}, new int[]{815, 225}, new int[]{860, 225}, new int[]{905, 225}, new int[]{925, 280}, new int[]{925, 340}, new int[]{925, 400}});

		Role mUHorse = new Role("Man Under Horse", "A little help here!", 3, true, new int[]{488, 525});
		Role sLeg = new Role("Shot in Leg", "Ow! Me leg!", 1, true, new int[]{412, 608});
		Role sFred = new Role("Saucy Fred", "That's what she said!", 2, true, new int[]{488, 608});
		ArrayList<Role> ranRoles= new ArrayList<Role>();
		ranRoles.add(mUHorse);
		ranRoles.add(sLeg);
		ranRoles.add(sFred);
		Area Ranch = new Area("Ranch", ranRoles, 2, new int[][]{new int[]{252, 478}, new int[]{472, 473}, new int[]{525, 473}});
		Ranch.setMove(new int[][]{{535, 515},{535, 560},{535, 605},{240, 590},{240, 635},{282, 635},{324, 635},{366, 635}});
		locations.add(Ranch);

		Role cPFighter = new Role("Clumsy Pit Fighter", "Hit me!", 1, true, new int[]{435, 719});
		Role tKnife = new Role("Thug with Knife", "Meet Suzy, my murderin' knife.", 2, true, new int[]{521, 719});
		Role dTom = new Role("Dangerous Tom", "There's two ways we can do this....", 3, true, new int[]{435, 808});
		Role pLost = new Role("Penny, who is Lost", "Oh, woe! For I am lost!", 4, true, new int[]{521, 808});
		ArrayList<Role> sHRoles= new ArrayList<Role>();
		sHRoles.add(cPFighter);
		sHRoles.add(tKnife);
		sHRoles.add(dTom);
		sHRoles.add(pLost);
		Area sHideout = new Area("Secret Hideout", sHRoles, 3, new int[][]{new int[]{27, 732}, new int[]{244, 764}, new int[]{299, 764}, new int[]{354, 764}});
		sHideout.setMove(new int[][]{{245, 815},{290, 815},{335, 815},{380, 815},{10, 685},{55, 685},{100, 685},{145, 685}});
		locations.add(sHideout);

		Role fTeller = new Role("Flustered Teller", "Would you like a large bill, sir?", 3, true, new int[]{911, 470});
		Role sGent = new Role("Suspicious Gentleman", "Can you be more specific", 2, true, new int[]{911, 554});
		ArrayList<Role> bankRoles= new ArrayList<Role>();
		bankRoles.add(fTeller);
		bankRoles.add(sGent);
		Area Bank = new Area("Bank", bankRoles, 1, new int[][]{new int[]{623, 475}, new int[]{840, 549}});
		Bank.setMove(new int[][]{{815, 460},{860, 460},{815, 505},{860, 505},{600, 595},{645, 595},{775, 595},{820, 595}});
		locations.add(Bank);

		Role dMan = new Role("Dead Man", "....", 1, true, new int[]{857, 730});
		Role cWoman = new Role("Crying Woman", "Oh, the humanity!", 1, true, new int[]{858, 809});
		ArrayList<Role> churchRoles= new ArrayList<Role>();
		churchRoles.add(dMan);
		churchRoles.add(cWoman);
		Area Church = new Area("Church", churchRoles, 2, new int[][]{new int[]{623, 734}, new int[]{623, 675}, new int[]{682, 675}});
		Church.setMove(new int[][]{{725, 675},{770, 675},{815, 675},{860, 675},{595, 845},{640, 845},{765, 845},{810, 845}});

		locations.add(Church);

		Role fPlayer = new Role("Faro Player", "Hit me!", 1, true, new int[]{1044, 509});
		Role sDrunk = new Role("Sleeping Drunkard", "Zzzzzz.. Whiskey!", 1, true, new int[]{1111, 469});
		Role aBart = new Role("Australian Bartender", "What'll it be, mate?", 3, true, new int[]{1046, 596});
		Role fBalconey = new Role("Falls from Balconey", "Arrrgghh!!", 2, true, new int[]{1111, 557});
		ArrayList<Role> hotelRoles= new ArrayList<Role>();
		hotelRoles.add(fPlayer);
		hotelRoles.add(sDrunk);
		hotelRoles.add(aBart);
		hotelRoles.add(fBalconey);
		Area Hotel = new Area("Hotel", hotelRoles, 3, new int[][]{new int[]{969, 740}, new int[]{1005, 683}, new int[]{1058, 683}, new int[]{1111, 683}});
		Hotel.setMove(new int[][]{{995, 460},{1040, 460},{995, 505},{995, 550},{995, 595},{1100, 625},{1145, 625},{950, 685}});
		locations.add(Hotel);

		Role cProsp = new Role("Crusty Prospector", "Aww, peaches!", 1, true, new int[]{114, 227});
		Role dTrain = new Role("Dragged by Train", "Omgeezers!", 1, true, new int[]{51, 268});
		Role pBag = new Role("Preacher with Bag", "The Lord will provide.", 2, true, new int[]{114, 320});
		Role cGun = new Role("Cyrus the Gunfighter", "Git to fightin' or git away!", 4, true, new int[]{49, 356});
		ArrayList<Role> tSRoles= new ArrayList<Role>();
		tSRoles.add(cProsp);
		tSRoles.add(dTrain);
		tSRoles.add(pBag);
		tSRoles.add(cGun);
		Area tStation = new Area("Train Station", tSRoles, 3, new int[][]{new int[]{21, 69}, new int[]{36, 11}, new int[]{89, 11}, new int[]{141, 11}});
		tStation.setMove(new int[][]{{185, 15},{15, 215},{60, 215},{160, 215},{5, 265},{5, 335},{105, 385},{150, 385}});
		locations.add(tStation);

		Role pCell = new Role("Prisoner in Cell	", "Zzzzzzz... Whiskey!", 2, true, new int[]{519, 25});
		Role fIrons = new Role("Feller in Irons", "Ah kilt the wrong man!", 3, true, new int[]{519, 105});
		ArrayList<Role> jailRoles= new ArrayList<Role>();
		jailRoles.add(pCell);
		jailRoles.add(fIrons);
		Area Jail = new Area("Jail", jailRoles, 1, new int[][]{new int[]{281, 27}, new int[]{442, 156}});
		Jail.setMove(new int[][]{{260, 150},{305, 150},{395, 150},{490, 175},{535, 175},{355, 195},{400, 195},{445, 195}});
		locations.add(Jail);

		Role mOver = new Role("Man in Overalls", "Looks like a storm's comin' in.", 1, true, new int[]{236, 276});
		Role mKeach = new Role("Mister Keach", "Howdy, stranger.", 3, true, new int[]{236, 358});
		ArrayList<Role> gSRoles= new ArrayList<Role>();
		gSRoles.add(mOver);
		gSRoles.add(mKeach);
		Area gStore = new Area("General Store", gSRoles, 2, new int[][]{new int[]{370, 282}, new int[]{313, 277}, new int[]{313, 330}});
		gStore.setMove(new int[][]{{275, 245},{350, 245},{395, 245},{440, 245},{485, 245},{530, 245},{285, 390},{330, 390}});
		locations.add(gStore);


		Area Trailers = new Area("Trailers", null, 0, new int[][]{new int[]{991, 248}});
		locations.add(Trailers);
		Trailers.setSize(new int[]{194, 201});
		
		Trailers.setMove(new int[][]{ {990, 240}, {1035, 240}, {1080, 240}, {1125, 240},{990, 285}, {1150, 285}, {1150, 330},{990, 365}});
		Area cOffice = new Area("Casting Office", null, 0, new int[][]{new int[]{9, 459}});
		locations.add(cOffice);
		cOffice.setSize(new int[]{208, 209});
		cOffice.setMove(new int[][]{{2, 475},{2, 520},{2, 565},{2, 610},{170, 475},{170, 520},{170, 565},{170, 610}});
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

		Role dPriest = new Role("Defrocked Priest", "Look out below!", 2, false, new int[]{threeLoc[0], 47});
		Role mCan = new Role("Marshal Canfield", "Hold fast!", 3, false, new int[]{threeLoc[1], 47});
		Role oneEye = new Role("One-Eyed Man", "Balderdash!", 4, false, new int[]{threeLoc[2], 47});

		Scene evilHat = new Scene("Evil Wears a Hat", 7, "Calhoun is separated from the group during a white-knuckle chase near Desperation Bluff", 4, "01.png");
		evilHat.addRoles(oneEye);
		evilHat.addRoles(mCan);
		evilHat.addRoles(dPriest);
		deck.add(evilHat);


		Role sqBoy = new Role("Squeaking Boy", "I'll say!", 2, false, new int[]{threeLoc[0], 47});
		Role pImh = new Role("Pharaoh Imhotep", "Attack, soldiers!", 4, false, new int[]{threeLoc[1], 47});
		Role aMartha = new Role("Aunt Martha", "You got nothin'!", 6, false, new int[]{threeLoc[2], 47});

		Scene sqDeal = new Scene("Square Deal City", 14, "Douglas and Katherine confront Aunt Martha about her missing pies. Devin sulks quietly in a side room.", 6, "06.png");
		sqDeal.addRoles(aMartha);
		sqDeal.addRoles(pImh);
		sqDeal.addRoles(sqBoy);
		deck.add(sqDeal);


		Role rugMerchant = new Role("Rug Merchant", "Don't leave my store!", 1, false, new int[]{threeLoc[0], 47});
		Role banker = new Role("Banker", "Trust me", 2, false, new int[]{threeLoc[1], 47});
		Role talkingMule = new Role("Talking Mule", "Nice work, Johnny!", 5, false, new int[]{threeLoc[2], 47});

		Scene lawOldWest = new Scene("Law and the Old West", 20, "Charlie " + apost + "Three Guns" + apost + " Henderson cooperates with Johnny Law and reluctantly enters the witless protection program.", 3, "02.png");
		lawOldWest.addRoles(talkingMule);
		lawOldWest.addRoles(banker);
		lawOldWest.addRoles(rugMerchant);
		deck.add(lawOldWest);

		Role theDuck = new Role("The Duck", "Waaaak!", 4, false, new int[]{twoLoc[0], 47});
		Role hisBrother = new Role("His Brother", "Waaaaaaaak!", 6, false, new int[]{twoLoc[1], 47});

		Scene davyCrockett31 = new Scene("Davy Crockett: A Drunkard’s Tale", 31, "Robert enlists the aid of several farm animals in order to ascertain the efficacy of his new hangover remedy", 4, "07.png");
		davyCrockett31.addRoles(hisBrother);
		davyCrockett31.addRoles(theDuck);
		deck.add(davyCrockett31);

		Role Auctioneer = new Role("Auctioneer", "Going once!", 5, false, new int[]{twoLoc[0], 47});
		Role generalCuster = new Role("General Custer", "Go West!", 6, false, new int[]{twoLoc[1], 47});

		Scene johnSkywater22 = new Scene("The Life and Times of John Skywater", 22, "Disheartened by his lack of business acumen and his poor choice of investment partners, John Skywater sets off into the Cree Nation to convince them to kidnap his wife.", 5, "03.png");
		johnSkywater22.addRoles(generalCuster);
		johnSkywater22.addRoles(Auctioneer);
		deck.add(johnSkywater22);

		Role townDrunk = new Role("Town Drunk", "Even me!", 2, false, new int[]{threeLoc[0], 47});
		Role squintingMiner = new Role("Squinting Miner", "Sure we can!", 4, false, new int[]{threeLoc[1], 47});
		Role poltergeist = new Role("Poltergeist", "Wooooo!", 5, false, new int[]{threeLoc[2], 47});

		Scene wayWestRun = new Scene("The Way the West Was Run", 34, "Jose explains patiently, but with thinly veiled contempt, the intricacies of Arizona bureaucracy, as though speaking to a simple and distracted child.", 4, "08.png");
		wayWestRun.addRoles(poltergeist);
		wayWestRun.addRoles(squintingMiner);
		wayWestRun.addRoles(townDrunk);
		deck.add(wayWestRun);

		Role drunk = new Role("Drunk", "Where's Willard?", 3, false, new int[]{threeLoc[0], 47});
		Role librarian = new Role("Librarian", "Shhhhh!", 4, false, new int[]{threeLoc[1], 47});
		Role manWithHay = new Role("Man With Hay", "Hey!", 6, false, new int[]{threeLoc[2], 47});

		Scene myYearsOnThePrarie = new Scene("My Years on the Prarie", 32, "Virgil and Stacy set out at midnight to track down the stray cows, unaware that they are being pursued by inch-high aliens from outer space.", 5, "04.png");
		myYearsOnThePrarie.addRoles(manWithHay);
		myYearsOnThePrarie.addRoles(librarian);
		myYearsOnThePrarie.addRoles(drunk);
		deck.add(myYearsOnThePrarie);

		Role angryBarber = new Role("Angry Barber", "Hold him still!", 1, false, new int[]{threeLoc[0], 47});
		Role womanWithBoard = new Role("Woman with Board", "Nonsense, Frank!", 3, false, new int[]{threeLoc[1], 47});
		Role manOnFire = new Role("Man on Fire", "It burns!", 5, false, new int[]{threeLoc[2], 47});

		Scene downInTheValley = new Scene("Down in the Valley", 24, "A tripped waiter is the spark igniting a brawl of cataclysmic proportions. Walter is injured in the neck.", 3, "09.png");
		downInTheValley.addRoles(manOnFire);
		downInTheValley.addRoles(womanWithBoard);
		downInTheValley.addRoles(angryBarber);
		deck.add(downInTheValley);

		Role holleringBoy = new Role("Hollering Boy", "Over here, mister!", 2, false, new int[]{threeLoc[0], 47});
		Role drunkFarmer = new Role("Drunk Farmer", " Git outta me barn!", 3, false, new int[]{threeLoc[1], 47});
		Role meekLittleSarah = new Role("Meek Little Sarah", "He's so cute!", 5, false, new int[]{threeLoc[2], 47});

		Scene buffaloBill = new Scene("Buffalo Bill: The Lost Years", 12, "Buffalo Bill’s companion Marty disappears in a freak electrical storm. Bill enlists the aid of the Sidekick Friends Network.", 4, "05.png");
		buffaloBill.addRoles(meekLittleSarah);
		buffaloBill.addRoles(drunkFarmer);
		buffaloBill.addRoles(holleringBoy);
		deck.add(buffaloBill);

		Role sleepingMan = new Role("Sleeping Man", "Snnkkk snnkk snnkk", 1, false, new int[]{threeLoc[0], 47});
		Role manWithPig = new Role("Man With Pig", "Tally-Hooo!", 2, false, new int[]{threeLoc[1], 47});
		Role shooter = new Role("Shooter", "Wehre's my britches", 4, false, new int[]{threeLoc[2], 47});

		Scene olShooter = new Scene("Ol' Shooter and Little Doll", 14, "Shooter discovers that he has been proceeding for days with no trousers. This causes him no small embarrassment as he searches for them with Little Doll.", 4, "10.png");
		olShooter.addRoles(shooter);
		olShooter.addRoles(manWithPig);
		olShooter.addRoles(sleepingMan);
		deck.add(olShooter);

		Role buster = new Role("Buster", "One two three go!", 1, false, new int[]{threeLoc[0], 47});
		Role manReadingPaper = new Role("Man Reading Paper", "Ouchie!", 4, false, new int[]{threeLoc[1], 47});
		Role fatPete = new Role("Fat Pete", "Nice kick, boss!", 5, false, new int[]{threeLoc[2], 47});

		Scene robbersOfTrains = new Scene("The Robbers of Trains", 19, "Coogan confronts the toughest thug in his gang, Big Jake, in an abbreviated knife fight. Coogan settles the dispute with fearless guile and a kick in the family jewels.", 4, "11.png");
		robbersOfTrains.addRoles(fatPete);
		robbersOfTrains.addRoles(manReadingPaper);
		robbersOfTrains.addRoles(buster);
		deck.add(robbersOfTrains);

		Role shotInBack = new Role("Shot in Back", "Arrrggh!", 2, false, new int[]{threeLoc[0], 47});
		Role shotInLeg = new Role("Shot in Leg", "Ooh, lordy!", 4, false, new int[]{threeLoc[1], 47});
		Role leapsIntoCake = new Role("Leaps into Cake", "Dangit, Jesse!", 5, false, new int[]{threeLoc[2], 47});

		Scene jesseJames8 = new Scene("Jesse James: Man of Action", 8, "Jesse’s brothers Jed and Henry throw him a surprise birthday party. Jesse’s nerves get the better of him when the birthday cake explodes.", 5, "16.png");
		jesseJames8.addRoles(leapsIntoCake);
		jesseJames8.addRoles(shotInLeg);
		jesseJames8.addRoles(shotInBack);
		deck.add(jesseJames8);

		Role martin = new Role("Martin", "Have you tried soy cheese?", 6, false, new int[]{threeLoc[1], 47});

		Scene beyondThePail = new Scene("Beyond the Pail: Life without Lactose", 12, "Henry discovers for the first time that his ability to digest cream has disappeared along with his hair. Other cowboys attempt to console him.", 2, "12.png");
		beyondThePail.addRoles(martin);
		deck.add(beyondThePail);

		Role pianoPlayer = new Role("Piano Player", "It's a nocturne!", 2, false, new int[]{threeLoc[0], 47});
		Role manInTurban = new Role("Man in Turban", "My Stars!", 3, false, new int[]{threeLoc[1], 47});
		Role fallsOnHoe = new Role("Falls on Hoe", "Ow!", 4, false, new int[]{threeLoc[2], 47});

		Scene disasterAtFlyingJ = new Scene("Disaster at Flying J", 6, "After the mine explosion, the traveling circus takes time out to get drunk and start a fight.", 5, "17.png");
		disasterAtFlyingJ.addRoles(fallsOnHoe);
		disasterAtFlyingJ.addRoles(manInTurban);
		disasterAtFlyingJ.addRoles(pianoPlayer);
		deck.add(disasterAtFlyingJ);

		Role preacher = new Role("Preacher", "My Word!", 3, false, new int[]{twoLoc[0], 47});
		Role amusedWitness = new Role("Amused Witness", "Tee hee hee!", 6, false, new int[]{twoLoc[0], 47});

		Scene aManCalledCow = new Scene("A Man Called \"Cow\"", 16, "Nothing will settle the debates among the skeptical locals, short of a demonstration of Hector’s special talents.", 3, "13.png");
		aManCalledCow.addRoles(amusedWitness);
		aManCalledCow.addRoles(preacher);
		deck.add(aManCalledCow);

		Role fallsFromTree = new Role("Falls from Tree", "What ho!", 1, false, new int[]{threeLoc[0], 47});
		Role laughingWoman = new Role("Laughing Woman", "Tis to laugh!", 3, false, new int[]{threeLoc[1], 47});
		Role manWithWhistle = new Role("Man with Whistle", "Tweeeet!", 4, false, new int[]{threeLoc[2], 47});

		Scene shakespeareInLubbock = new Scene("Shakespeare in Lubbock", 23, "William decides that it is time to be movin’ on. Julia convinces him to stick around just long enough to get into big trouble", 3, "18.png");
		shakespeareInLubbock.addRoles(manWithWhistle);
		shakespeareInLubbock.addRoles(laughingWoman);
		shakespeareInLubbock.addRoles(fallsFromTree);
		deck.add(shakespeareInLubbock);

		Role curiousGirl = new Role("Curios Girl", "Are you sure?", 3, false, new int[]{twoLoc[0], 47});
		Role ghostOfPlato = new Role("Ghost of Plato", "It happened to me!", 4, false, new int[]{twoLoc[1], 47});

		Scene taffyCommercial = new Scene("Taffy Commercial", 2, "Jackson encourages the children to eat only taffy, because gum can kill them stone dead.", 2, "14.png");
		taffyCommercial.addRoles(ghostOfPlato);
		taffyCommercial.addRoles(curiousGirl);
		deck.add(taffyCommercial);

		Role exConvict = new Role("Ex-Convict", "Never again!", 4, false, new int[]{twoLoc[0], 47});
		Role manWithOnions = new Role("Man with Onions", "Fresh Onions!", 6, false, new int[]{twoLoc[1], 47});

		Scene goWestYou = new Scene("Go West, You!", 30, "Susan and Peter encounter some of the perils of the Badlands: rutted mud roads, torrential rain storms, and a bad case of \"grumble tummy.\"", 3, "19.png");
		goWestYou.addRoles(manWithOnions);
		goWestYou.addRoles(exConvict);
		deck.add(goWestYou);

		Role suprisedBison = new Role("Suprised Bison", "Mmrrrrrph!", 2, false, new int[]{twoLoc[0], 47});
		Role manWithHorn = new Role("Man with Horn", "Ta daaaa!", 4, false, new int[]{twoLoc[0], 47});

		Scene gumCommercial = new Scene("Gum Commercial", 3, "Inspector Pete speaks to a riveted audience about the many hidden dangers of taffy, not the least of which is that taffy can kill you stone dead.", 2, "15.png");
		gumCommercial.addRoles(manWithHorn);
		gumCommercial.addRoles(suprisedBison);
		deck.add(gumCommercial);

		Role staggeringMan = new Role("Staggering Man", "You never know!", 3, false, new int[]{threeLoc[0], 47});
		Role womanWithBeer = new Role("Woman with Beer", "Howdy, stranger!", 5, false, new int[]{threeLoc[1], 47});
		Role marcie = new Role("Marcie", "Welcome home!", 6, false, new int[]{threeLoc[2], 47});

		Scene johnSkywater = new Scene("The Life and Times of Skywater", 15, "John discovers his long-lost sister Marcie, and instructs her in the ways of gunfighting and whiskey distillation.", 5, "20.png");
		johnSkywater.addRoles(marcie);
		johnSkywater.addRoles(womanWithBeer);
		johnSkywater.addRoles(staggeringMan);
		deck.add(johnSkywater);

		Role looksLikeElvis = new Role("Looks Like Elvis", "Thankyouverymuch.", 4, false, new int[]{threeLoc[0], 47});
		Role singingDeadMan = new Role("Singing Dead Man", "Yeah!", 5, false, new int[]{threeLoc[1], 47});
		Role apothecary = new Role("Apothecary", "Such drugs I have.", 6, false, new int[]{threeLoc[2], 47});

		Scene gunTheMusical = new Scene("Gun! The Musical", 25, "A song and dance extravaganza, \"Hunka Hunka Burnin’ Lead.\"", 6, "21.png");
		gunTheMusical.addRoles(apothecary);
		gunTheMusical.addRoles(singingDeadMan);
		gunTheMusical.addRoles(looksLikeElvis);
		deck.add(gunTheMusical);

		Role flusteredMan = new Role("Flustered Man", "Well, I never!", 1, false, new int[]{threeLoc[0], 47});
		Role spaceMonkey = new Role("Space Monkey", "Ook!", 2, false, new int[]{threeLoc[1], 47});
		Role cowbotDan = new Role("Cowbot Dan", "Bzzzzzt!", 5, false, new int[]{threeLoc[2], 47});
		Scene oneFalseStepForMankind = new Scene("One False Step for Mankind", 21, "After a dozen failed attempts, one rocket carries Horatio and his six children to the Moon, where they enjoy a picnic and a spirited game of badminton.", 6, "26.png");
		oneFalseStepForMankind.addRoles(cowbotDan);
		oneFalseStepForMankind.addRoles(spaceMonkey);
		oneFalseStepForMankind.addRoles(flusteredMan);
		deck.add(oneFalseStepForMankind);

		Role jailer = new Role("Jailer", "You there!", 2, false, new int[]{threeLoc[0], 47});
		Role mephistopheles = new Role("Mephistopheles", "Be not afraid!", 4, false, new int[]{threeLoc[1], 47});
		Role breaksWindow = new Role("Breaks a Window", "Oops!", 5, false, new int[]{threeLoc[2], 47});

		Scene humorAtTheExpenseOfOthers = new Scene("Humor at the Expense of Others", 21, "After a dozen failed attempts, one rocket carries Horatio and his six children to the Moon, where they enjoy a picnic and a spirited game of badminton.", 6, "22.png");
		humorAtTheExpenseOfOthers.addRoles(breaksWindow);
		humorAtTheExpenseOfOthers.addRoles(mephistopheles);
		humorAtTheExpenseOfOthers.addRoles(jailer);
		deck.add(humorAtTheExpenseOfOthers);

		Role manInPoncho = new Role("Man in Poncho", "Howdy, Jones!", 1, false, new int[]{threeLoc[0], 47});
		Role ecstaticHousewife = new Role("Ecstatic Housewife", "This is fine!", 3, false, new int[]{threeLoc[1], 47});
		Role isaac = new Role("Isaac", "The mail!", 5, false, new int[]{threeLoc[2], 47});

		Scene thirteenTheHardWay = new Scene("Thirteen the Hard Way", 15, "After some delay, the Pony Express arrives. Isaac, Gwen, Francis, Terry, Conrad, Brooke, Jerry, Howard, MacNeill, Jones, Spike, Cornwall and Crawford are all there.", 5, "27.png");
		thirteenTheHardWay.addRoles(isaac);
		thirteenTheHardWay.addRoles(ecstaticHousewife);
		thirteenTheHardWay.addRoles(manInPoncho);
		deck.add(thirteenTheHardWay);

		Role filmCritic = new Role("Film Critic", "Implausible", 5, false, new int[]{twoLoc[0], 47});
		Role hoboWithBat = new Role("Hobo with Bat", "Nice house!", 6, false, new int[]{twoLoc[1], 47});
		Scene theSearchForMaggieWhite = new Scene("The Search for Maggie White", 12, "Alone in the wilderness, Maggie makes the best of her situation. In what seems like no time at all, she constructs a sturdy two-story house from branches and mud.", 6, "23.png");
		theSearchForMaggieWhite.addRoles(hoboWithBat);
		theSearchForMaggieWhite.addRoles(filmCritic);
		deck.add(theSearchForMaggieWhite);

		Role cow = new Role("Cow", "Moo.", 2, false, new int[]{threeLoc[0], 47});
		Role stClementOfAlexandria = new Role("St. Clement of Alexandria", "Peace be with you, child!", 3, false, new int[]{threeLoc[1], 47});
		Role josie = new Role("Josie", "Yikes!", 4, false, new int[]{threeLoc[2], 47});

		Scene howTheyGetMilk = new Scene("How They Get Milk", 2, "Josie asks the Milkman how they get milk. After a thoughtful pause, he begins. \"Not like you’d expect!\"", 4, "28.png");
		howTheyGetMilk.addRoles(josie);
		howTheyGetMilk.addRoles(stClementOfAlexandria);
		howTheyGetMilk.addRoles(cow);
		deck.add(howTheyGetMilk);

		Role bewhiskerdCowpoke = new Role("Bewhisker'd Cowpoke", "Oh, sweet Lord!", 3, false, new int[]{twoLoc[0], 47});
		Role dog = new Role("Dog", "Wurf!", 5, false, new int[]{twoLoc[1], 47});

		Scene picanteSauceCommercial = new Scene("Picante Sauce Commercial", 1, "A dozen grizzled cowboys surround a fire. Suddenly, they exclaim, " + apost + "That’s not mayonnaise!" + apost, 2, "24.png");
		picanteSauceCommercial.addRoles(dog);
		picanteSauceCommercial.addRoles(bewhiskerdCowpoke);
		deck.add(picanteSauceCommercial);

		Role willard = new Role("Willard", "Ain't that a sight?", 2, false, new int[]{threeLoc[0], 47});
		Role leprechaun = new Role("Lerpechaun", "Begorrah!", 3, false, new int[]{threeLoc[1], 47});
		Role startledOx = new Role("Startled Ox", "Mrr?", 5, false, new int[]{threeLoc[2], 47});

		Scene myYearsOnThePrarie27 = new Scene("My years on the Prarie", 27, "Louise takes instruction from Henry, the neighbor boy, in an absurdly suggestive explanation of how to plow a field.", 5, "29.png");
		myYearsOnThePrarie27.addRoles(startledOx);
		myYearsOnThePrarie27.addRoles(leprechaun);
		myYearsOnThePrarie27.addRoles(willard);
		deck.add(myYearsOnThePrarie27);

		Role shotInHead = new Role("Shot in Head", "Arrrgh!", 1, false, new int[]{threeLoc[0], 47});
		Role leapsOutOfCake = new Role("Leaps out of Cake", "Oh, for Pete's sake!", 4, false, new int[]{threeLoc[1], 47});
		Role shotThreeTimes = new Role("Shot Three Times", "Ow! Ow! Ow!", 6, false, new int[]{threeLoc[2], 47});

		Scene jesseJames14 = new Scene("Jesse James: Man of Action", 14, "A hail of gunfire results when Jesse’s friend Barton marries Jesse’s childhood sweetheart.", 5, "25.png");
		jesseJames14.addRoles(shotThreeTimes);
		jesseJames14.addRoles(leapsOutOfCake);
		jesseJames14.addRoles(shotInHead);
		deck.add(jesseJames14);

		Role voiceOfGod = new Role("Voice of God", "Grab hold, son!", 2, false, new int[]{threeLoc[0], 47});
		Role handsOfGod = new Role("Hands of God", "!", 3, false, new int[]{threeLoc[1], 47});
		Role jackKemp = new Role("Jack Kemp", "America!", 4, false, new int[]{threeLoc[2], 47});

		Scene davyCrockett12 = new Scene("Davy Crockett: A Drunkards Tale", 12, "In an absurd dream sequence, Crockett recalls an episode of fear and chaos in which his childhood friend Timmy was trapped at the bottom of a well.", 4, "30.png");
		davyCrockett12.addRoles(jackKemp);
		davyCrockett12.addRoles(handsOfGod);
		davyCrockett12.addRoles(voiceOfGod);
		deck.add(davyCrockett12);


		Role farm = new Role("Farmer", "Git off a that!", 2, false, new int[]{threeLoc[0], 47});
		Role expHorse = new Role("Exploding Horse", "Boom!", 4, false, new int[]{threeLoc[1], 47});
		Role Jack = new Role("Jack", "Here we go again!", 6, false, new int[]{threeLoc[2], 47});

		Scene cOtherSt = new Scene("Custer's Other Stands", 40, "General George Armstrong Custer clinches another victory at the battle of Little Sands. His trusty steed Cairo is not so lucky.", 5, "40.png");
		cOtherSt.addRoles(Jack);
		cOtherSt.addRoles(expHorse);
		cOtherSt.addRoles(farm);
		deck.add(cOtherSt);

		Role dect = new Role("Detective", "I have a hunch.", 3, false, new int[]{threeLoc[0], 47});
		Role fClerk = new Role("File Clerk", "My stapler!", 4, false, new int[]{threeLoc[1], 47});
		Role cLou = new Role("Cindy Lou", "Dear Lord!", 5, false, new int[]{threeLoc[2], 47});

		Scene grinchTex = new Scene("How the Grinch Stole Texas", 9, "The doe-eyed citizens of El Paso gather together around a warm fire and pray for the safety of those poor souls in Oklahoma. It almost works.", 5, "35.png");
		grinchTex.addRoles(cLou);
		grinchTex.addRoles(fClerk);
		grinchTex.addRoles(dect);
		deck.add(grinchTex);

		Role fratPledge = new Role("Fraternity Pledge", "Beer me!", 2, false, new int[]{twoLoc[0], 47});
		Role manSword = new Role("Man with Sword", "None shall pass!", 6, false, new int[]{twoLoc[1], 47});

		Scene breakPonies = new Scene("Breakin’ in Trick Ponies", 19, "Uncle Stewart reveals what to do when all else fails.", 3, "39.png");
		breakPonies.addRoles(manSword);
		breakPonies.addRoles(fratPledge);
		deck.add(breakPonies);

		Role burnMan = new Role("Burning Man", "Make it stop!", 2, false, new int[]{threeLoc[0], 47});
		Role cheeseVend = new Role("Cheese Vendor", "Opa!", 4, false, new int[]{threeLoc[1], 47});
		Role hitTable = new Role("Hit with Table", "Ow! A table?", 5, false, new int[]{threeLoc[2], 47});

		Scene trialPion = new Scene("Trials of the First Pioneers", 5, "A fire breaks out in the town livery. Before long, the surrounding buildings are engulfed in flame. The world falls into chaos.", 4, "34.png");
		trialPion.addRoles(hitTable);
		trialPion.addRoles(cheeseVend);
		trialPion.addRoles(burnMan);
		deck.add(trialPion);

		Role mark = new Role("Marksman", "Pull", 4, false, new int[]{threeLoc[0], 47});
		Role postWork = new Role("Postal Worker", "It's about time!", 5, false, new int[]{threeLoc[1], 47});
		Role aHorse = new Role("A Horse", "Yes Sir!", 6, false, new int[]{threeLoc[2], 47});

		Scene howMilk = new Scene("How They Get Milk", 8, "Josie is thoroughly off milk at this point. The Milkman shows her one more way that she might not have heard of before.", 4, "38.png");
		howMilk.addRoles(aHorse);
		howMilk.addRoles(postWork);
		howMilk.addRoles(mark);
		deck.add(howMilk);

		Role libNun = new Role("Liberated Nun", "Let me have it!", 3, false, new int[]{threeLoc[0], 47});
		Role witchDoc = new Role("Witch Doctor", "Oogie Boogie!", 5, false, new int[]{threeLoc[1], 47});
		Role voiceReason = new Role("Voice of Reason", "Come on, now!", 6, false, new int[]{threeLoc[2], 47});

		Scene swingWide = new Scene("Swing 'em Wide", 35, "Hector makes a surprising discovery behind the Chinese grocery store.", 6, "33.png");
		swingWide.addRoles(voiceReason);
		swingWide.addRoles(witchDoc);
		swingWide.addRoles(libNun);
		deck.add(swingWide);

		Role veryWet = new Role("Very Wet Man", "Sheesh!", 2, false, new int[]{threeLoc[0], 47});
		Role dejWife = new Role("Dejected Housewife", "Its time had come.", 4, false, new int[]{threeLoc[1], 47});
		Role manBox = new Role("Man with Box", "Progres!", 5, false, new int[]{threeLoc[2], 47});

		Scene thirtWay = new Scene("Thirteen the Hard Way", 17, "After operating for only six minutes, the Pony Express disbands and gives way to the international Telegraph and Railroad systems. Little boys cry.", 5, "37.png");
		thirtWay.addRoles(manBox);
		thirtWay.addRoles(dejWife);
		thirtWay.addRoles(veryWet);
		deck.add(thirtWay);

		Role thriftMike = new Role("Thrifty Mike", "Call!", 1, false, new int[]{threeLoc[0], 47});
		Role soberPhys = new Role("Sober Physician", "Raise!", 3, false, new int[]{threeLoc[1], 47});
		Role manFloor = new Role("Man on Floor", "Fold!", 5, false, new int[]{threeLoc[2], 47});

		Scene swingEm = new Scene("Swing 'em Wide", 19, "Black Jack invites Dixon and The Captain to a late-night poker game. Little do they know that Gertrude and Isabella await them at the table.", 6, "32.png");
		swingEm.addRoles(manFloor);
		swingEm.addRoles(soberPhys);
		swingEm.addRoles(thriftMike);
		deck.add(swingEm);

		Role manRope = new Role("Man with Rope", "Look out below!", 1, false, new int[]{threeLoc[0], 47});
		Role Svet = new Role("Svetlana", "Says who?", 2, false, new int[]{threeLoc[1], 47});
		Role accVictim = new Role("Accidental Victim", "Ow! My spine!", 5, false, new int[]{threeLoc[2], 47});

		Scene manSubstance = new Scene("J. Robert Lucky, Man of Substance", 13, "Horace and Mathilde discover that the mysterious orange powder filling Doctor Lucky’s air vents is neither Agent Orange nor weaponized Tang, but a rare form of cheese mold.", 4, "36.png");
		manSubstance.addRoles(accVictim);
		manSubstance.addRoles(Svet);
		manSubstance.addRoles(manRope);
		deck.add(manSubstance);

		Role Opice = new Role("Opice (Monkey)", "Ukk! (Ook)!", 5, false, new int[]{twoLoc[0], 47});
		Role manGun = new Role("Man with Gun", "Hold it right there!", 6, false, new int[]{twoLoc[1], 47});

		Scene Czechs = new Scene("Czechs in the Sonora", 25, "Bob reverts to his ancestral ways in a short fight over a disembodied hand", 4, "31.png");
		Czechs.addRoles(manGun);
		Czechs.addRoles(Opice);
		deck.add(Czechs);
		Collections.shuffle(deck);

		getPlayers(Trailers);
			/*
			for(int i = 0; i < locations.size() -2; i++)
			{
				Area a = locations.get(i);
				Scene temp;
				temp = deck.get(0);
				deck.remove(temp);
				temp.setLoc(a);
				a.setScene(temp);
				board.add(temp);
				backgroundBoard.addCount(temp);
				boardSize++;
				System.out.println(boardSize);
				System.out.println(a.getName());
				System.out.println(a.getXY()[0][0] + ", " + a.getXY()[0][1]);
				//

			}
			backgroundBoard.setCard(board);
			*/
			
			System.out.println("Game is initialized!");
			System.out.println(deck.size());
	}
	public static void getPlayers(Area Trailers)
	{
		backgroundBoard.numOfPlayers();
		playerNum = backgroundBoard.getNumOfPlayers();

			for(int i = 0; i < playerNum; i++)
			{
				Player p;
				if(playerNum <= 3)
				{
					maxDay = 3;
				}
				if(playerNum >= 7)
				{
					p = new Player("Player " + (i + 1), Trailers, 2);
				}
				else
				{
					p = new Player("Player " + (i + 1), Trailers, 1);
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
				p.setXY(start.getMove()[i][0] - 600, start.getMove()[i][1] - 450);
			}
			backgroundBoard.setDay(1, maxDay);
			backgroundBoard.playerIcons(playerList);
	}
	//Determines winner/prepares for next game
	public static void endGame()
	{
		System.out.println("Game over!");
		while(discard.size() > 0)
		{
			Scene temp;
			temp = discard.get(0);
			temp.flip(false);
			deck.add(temp);
			discard.remove(0);
		}
		while(board.size() > 0)
		{
			Scene temp;
			temp = board.get(0);
			temp.flip(false);
			deck.add(temp);
			board.remove(0);
		}
		boardSize = 0;
		Collections.shuffle(deck);
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
			backgroundBoard.displayWinner(win.get(0));
		}
		while(playerList.size() > 0)
		{
			playerList.remove(0);
		}
		backgroundBoard.clearPlayers();
		getPlayers(start);
	}

	//Replaces the cards left on board + places others, essentially resets day
	public static void resetDay()
	{
		System.out.println("End of day");
		for(int i = 0; i < boardSize; i++)
		{
			Scene temp;
			temp = board.get(i);
			temp.flip(true);
			if(temp.getLoc()!= null)
			{
			temp.getLoc().removeScene();
			temp.setLoc(null);
			}
			discard.add(temp);
			int rem;
			

		}
		while(board.size() > 0)
		{
			board.remove(0);
		}
		boardSize = 0;
		backgroundBoard.removeCards();
		backgroundBoard.removeCounts();
		for(int i = 0; i < playerList.size(); i ++)
		{
			Player p = playerList.get(i);
			p.reset(start);
			p.setLoc(i);
			start.removeLoc();
			p.setXY(p.getLocation().getMove()[i][0] - 600 + 25, p.getLocation().getMove()[i][1] -450 + 30);
			backgroundBoard.displayData(playerList, i);
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
			a.resetShots();
			Scene temp;
			System.out.println(deck.size());
			temp = deck.get(0);
			deck.remove(temp);
			temp.setLoc(a);
			a.setScene(temp);
			board.add(temp);
			
			backgroundBoard.addCount(temp);
			boardSize++;
			System.out.println(a.getName());
			System.out.println(a.getScene().getName() + ", " + a.getScene().getXY()[0]+", "+ a.getScene().getXY()[1]);
			System.out.println(a.getXY()[0][0] + ", " + a.getXY()[0][1]);

		}
		
		backgroundBoard.setCard(board);
		day++;
		
	}

	//turn logic goes here, i.e have player choose to move, rehearse, act, upgrade, or take role
	//if player moves allow them to upgrade or take role after.
	//Uses turn-based cases for decisions
	public static void takeTurn(int player)throws InterruptedException
	{
		String choice = "";
		Player p = playerList.get(player);
		p.setPhase(0);
		Area loc = p.getLocation();
		Scene s = loc.getScene();
		System.out.println(p.getName() + "'s turn");
		int opt = 0;
		if(p.getRole()!= null)
		{
			p.setPhase(2);
		}
		while(p.getPhase() != 3)
		{
			while(opt == 0)
			{
			 	opt = backgroundBoard.getOpt();
				Thread.sleep(3);
			}
			if(opt == 4)
			{
				p.setPhase(3);
			}
			if(opt == 1)
			{
				if(p.getPhase() == 0)
				{
					int place = 0;
					ArrayList<Area> a = loc.getAdjacent();
					ArrayList<String> areaName = new ArrayList<String>();
					for(int i = 0; i < a.size(); i++)
					{
						areaName.add(a.get(i).getName());
					}
					Object[] list = areaName.toArray(new Object[areaName.size()]);
					String pick = backgroundBoard.showOpt("Areas you can move to\n", "Areas", list);
					if(pick != null)
					{
						for(int i = 0; i < a.size(); i++)
						{
							if(a.get(i).getName().equals(pick))
							{
								place = i;
							}
						}
						p.move(a.get(place));
						loc = p.getLocation();
						backgroundBoard.changeLayer(playerList.indexOf(p));
						backgroundBoard.displayData(playerList, turn);
						
						s = loc.getScene();
						p.setPhase(1);
						
						if(s != null && !s.flipped())
						{
							System.out.println(locations.indexOf(loc));
							System.out.println(board.indexOf(s));
							System.out.println(locations.get(locations.indexOf(loc)).getName());
							s.startShoot();
							backgroundBoard.startShoot(locations.indexOf(loc), s.getFile());
						}
					}
				}
				else
				{
					System.out.println("Player has already taken a vital turn action");
				}
			}
			//rehearse
			if(opt == 3)
			{
				if(p.getPhase() == 2)
				{
					p.rehearse();
					p.setPhase(3);
				}
				else
				{
					System.out.println("Player cannot rehearse while not working on role");
				}
			}
			//act
			if(opt == 2)
			{
				if(p.getPhase() == 2)
				{
					int sLeft = loc.getShots();
					int ind = locations.indexOf(loc);
					backgroundBoard.decCount(ind, sLeft);
					p.act(s);
					if(loc.getShots() == 0)
					{
						boardSize--;
						s.finalCut();
						backgroundBoard.removeCard(locations.indexOf(p.getLocation()));
					}
					p.setPhase(3);
				}
				else
				{
					System.out.println("Player cannot act while not working on role");
				}
			}
			/*
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
			*/
			//Give option to either take role or upgrade if applicable
			if(opt == 5 || opt == 6)
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
						String s1 = "Credits";
						String s2 = "Dollars";
						ArrayList<String> ranks = new ArrayList<String>();
						for(int i = p.getRank()+1; i <= 6; i++)
						{
							ranks.add(Integer.toString(i));
						}
						String pick;
						boolean didUp = false;
						if(opt == 6)
						{
							pick = backgroundBoard.showOpt("Ranks that can be upgraded to\n", s1, ranks.toArray(new Object[ranks.size()]));
							if(pick != null)
							{
								didUp = p.upgradeCredits(Integer.parseInt(pick));
							}
						}
						else
						{
							pick =  backgroundBoard.showOpt("Ranks that can be upgraded to\n", s2, ranks.toArray(new Object[ranks.size()]));
							if(pick != null)
							{
								didUp = p.upgradeMoney(Integer.parseInt(pick));
							}
						}
						if(didUp)
						{
							backgroundBoard.changeIcon(turn, p.getRank());
						}
					}
				}
				else
				{
					System.out.println("Player not at casting office");
				}
			}
			//work
			if(opt == 7)
			{
				if(p.getPhase() != 2)
				{
					loc = p.getLocation();
					//display roles player can take, etc.
					s = loc.getScene();
					if(s != null)
					{
						ArrayList<Role> r1 = s.getRoles();
						ArrayList<Role> r2 = loc.getRoles();
						ArrayList<Role> availRoles = new ArrayList<Role>();
						ArrayList<String> roleNames = new ArrayList<String>();
						for(int i = 0; i < r1.size(); i++)
						{
							Role r = r1.get(i);
							if(r.getPlayer() == null && r.getRank() <= p.getRank())
							{
								availRoles.add(r);
								roleNames.add(r.getName());
							}
						}
						for(int i = 0; i < r2.size(); i++)
						{
							Role r = r2.get(i);
							if(r.getPlayer() == null && r.getRank() <= p.getRank())
							{
								availRoles.add(r);
								roleNames.add(r.getName());
							}
						}
						String pick = backgroundBoard.showOpt("Available roles to take \n", "Roles", roleNames.toArray(new Object[roleNames.size()]));
						if(pick != null)
						{
							p.setPhase(3);
							for(int i = 0; i < availRoles.size(); i++)
							{
								Role r = availRoles.get(i);
								if(r.getName().equals(pick))
								{
									int x = 0;
									int y = 0;
									if(!r.getStatus())
									{
										x = loc.getXY()[0][0] + r.getXY()[0] + 20 - 600;
										y = loc.getXY()[0][1] + r.getXY()[1] + 25 - 450;
									}
									else
									{
										x =  r.getXY()[0] + 24 - 600;
										y =  r.getXY()[1] + 28 - 450;
									}
									p.setXY(x, y);
									p.setRole(r);
									loc.addLoc(p.getLoc());
									p.setLoc(8);
									backgroundBoard.displayData(playerList, turn);
								}
							}
						}
					}
				}
			}
			backgroundBoard.setOpt();
			opt = 0;
		}
	}
}
