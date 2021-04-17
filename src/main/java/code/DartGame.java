package code;

public class DartGame {
	
	//private static String[] throwList = {"8", "D10", "T6", "SB", "DB", "20", "D17", "DB", "DB", "D17","8", "D10", "T6"};
	//private static String[] throwList = {"8", "D10", "T6", "SB", "DB", "20", "D17", "DB", "DB", "34", "8", "D10"};
	//private static String[] throwList = {"8", "D10", "T6", "SB", "DB", "20", "D17", "DB", "DB", "34", "DB", "DB", "D10", "10", "D2"};
	private static String[] throwList = {"8", "D10", "T6", "SB", "DB", "20", "D17", "DB", "DB", "34", "DB", "DB", "D10", "10", "D3"};
	private static int score = 301;
	private static int curScore = 0;
	private static boolean isGameStarted;
	private static int throwCtr = 0;
	
	public static void main(String[] args) {
		System.out.println("Final score is: "+gameScore());
	}
	
	private static int gameScore() {
		
		for(String curthrow : throwList) {
			if(!isGameStarted && curthrow.startsWith("D")) {
				isGameStarted = true;
				//System.out.println("Game started with throw "+curthrow);
			}
			if(!isGameStarted) {
				continue;
			}
			
			throwCtr++;
			int curThrowScore = calculateScore(curthrow);
			curScore+= curThrowScore;
			
			if(throwCtr == 3) {
				score-= curScore;
				if(isBust(score, curthrow)) {
					System.out.println("BUST at throw "+curthrow);
					score+= curScore;
				}else {
					if(score == 0) {
						return score;
					}
				}
				//System.out.println("Round completed with a score of " + score);
				curScore = 0;
				throwCtr = 0;
			}
			
		}
		
		if(throwCtr != 0) {
			score-= curScore;
			if(isBust(score, throwList[throwList.length-1])) {
				System.out.println("BUST at last throw");
				score+= curScore;
			}
		}
		return score;
	}
	
	private static int calculateScore(String curthrow) {
		if("DB".equals(curthrow)) {
			return 50;
		}
		if("SB".equals(curthrow)) {
			return 25;
		}
		if(curthrow.startsWith("D")) {
			return 2*Integer.valueOf(curthrow.substring(1));
		}
		if(curthrow.startsWith("T")) {
			return 3*Integer.valueOf(curthrow.substring(1));
		}
		return Integer.valueOf(curthrow);
	}
	
	private static boolean isBust(int scoreSoFar, String curThrow) {
		if(scoreSoFar == 0 && !curThrow.startsWith("D")) {
			return true;
		}else if(scoreSoFar == 1 || scoreSoFar<0) {
			return true;
		}
		return false;
	}

}

/*
Coding Problem: Dart Scoring

Write a scoring function for the darts game 301.
Game Rules
The player's score starts at 301. Darts are thrown in rounds of three. Each round's score is removed from the player's total until it is exactly zero.

Players must "double-in" and "double-out": any darts thrown before a double is scored are ignored, and only a double can bring the total to zero.

If the total arrives at 1, a negative number, or zero but not via a double, the player is bust and the total from the previous round is maintained. (Any superfluous throws in a bust round will be recorded as zeroes.)
 
 Instructions
You are provided an array of dart throws. Write a function to return the current score as a number from 301 (a scoreless game) to 0 (a winning condition) or any number in between (a game in progress). You may assume that the input is well-formed and valid.

Dart throws are represented as the following strings:
 Singles: "1"-"20" (worth 1-20), "SB" (worth 25)
Doubles: "D1"-"D20" (worth 2-40), "DB" (worth 50)
Triples: "T1"-"T20" (worth 3-60)

Sample i/p:

["8", "D10", "T6", "SB", "DB", "20", "D17"]
*/

