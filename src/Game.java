import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


public class Game {
	static SingleLinkedList Player1 = new SingleLinkedList();
	static SingleLinkedList Player2 = new SingleLinkedList();
	static int Player1Score = 0;	
	static int Player2Score = 0;
	static int countOfYahtzee = 0;	
	static int countOfLarge = 0;
	static SortSingleLinkedList HighScore = new SortSingleLinkedList();
	static SingleLinkedList HighScoreTable = new SingleLinkedList();
	static int lines;
	public static void main(String[] args) throws FileNotFoundException {		
		System.out.println("Welcome to Yahtzee Game!!");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		for(int i=1;i<11;i++) {
			System.out.println("TURN : "+i);
			NewTurn();			
			System.out.print("Player1: ");
			Player1.display();
			System.out.println("  Score: "+Player1.score);			
			System.out.print("Player2: ");
			Player2.display();
			System.out.println("  Score: "+Player2.score);
			checkLargeStraight(Player1);
			checkLargeStraight(Player2);
			checkYahtzee(Player1);
			checkYahtzee(Player2);
			SetScore(Player1);
			SetScore(Player2);
			System.out.println("");
			System.out.print("Player1: ");
			Player1.display();
			System.out.println("  Score: "+Player1.score);			
			System.out.print("Player2: ");
			Player2.display();
			System.out.println("  Score: "+Player2.score);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Player1.setSscore(Player1.score);
		Player2.setSscore(Player2.score);
		countLineBufferedReader("highscore.txt");
		readHighScore("highScore.txt",lines);
		turnHighScore(HighScoreTable);
		HighScore.sortList();
		System.out.println("HIGH SCORE TABLE");
		HighScore.display();
	}
	public static long countLineBufferedReader(String fileName) {

	      lines = 0;
	      try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
	          while (reader.readLine() != null) lines++;
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	      return lines;

	  }
	public static void readHighScore(String file,int numberOfLine) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(file));
		for(int i = 0;i <lines/2;i++) {
			 Object line = scanner.nextLine();
			 Object score = scanner.nextLine();
			 HighScoreTable.add(line);
			 HighScoreTable.add(score);
		}


	}
	public static void turnHighScore(SingleLinkedList List) {
		Node temp = List.head;
		for(int i = 0;i<List.size()/2;i++) {
			String name="" ;
			String score="";
			if(temp.getData()!=null) {
				name = (String)temp.getData();
				if(temp.getLink()!=null) {
					temp = temp.getLink();
					score = (String)temp.getData();
					HighScore.addNode(Integer.valueOf(score), name);
					temp = temp.getLink();
				}
				else
					break;	
			}									
							
		}
		HighScore.addNode(Player1.score, "Player1");
		HighScore.addNode(Player2.score, "Player2");


	}
	public static void NewTurn() {
		for(int i = 0; i<6; i++) {
			int number = ThreadLocalRandom.current().nextInt(1, 7);//generates random number
			if(i<=2) {
				Player1.add(number);				
			}
			else
				Player2.add(number);								
		}
	}
	public static SingleLinkedList checkYahtzee(SingleLinkedList Player) {
					
		for(int i =1;i <7;i++) {
			int count = 0;
			Node temp = Player.head;
			while (temp!= null) {
				if ((Integer)temp.getData()==i) {
					count++;
					temp = temp.getLink();
				}
				else
					temp = temp.getLink();
				
			}
			if (count >=4) {
				Player.countOfYahtzee++;
				for (int j = 0; j < count; j++) {
					Player.removeJustOneElement((Object)i);
				}
			}
			
		}
		Player.setYahtze(Player.countOfYahtzee);
		return Player;

	}
	public static SingleLinkedList checkLargeStraight(SingleLinkedList Player) {
		int count = 0;
		
		for (int i = 1; i < 7; i++) {
			if(Player.search((Object)i))
				count++;					
		}
		if(count==6) {
			Player.countOfLarge++;
			for(int j =1;j<7;j++) {
				Player.removeJustOneElement((Object)j);			
			   }
			}
		Player.setLarge(Player.countOfLarge);
		return Player;
	}
	public static void SetScore(SingleLinkedList Player) {
		Player.setScore(10*Player.countOfYahtzee+30*Player.countOfLarge);
		
	}
	

}
