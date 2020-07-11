/**I declare that all material in this assessment task is my own work except where there is clear acknowledgement or reference to the work of others. I further declare that I have complied with, and agree to
abide by, the UIS Academic Integrity Policy at the University website. http://www.uis.edu/academicintegrity
Author’s Name: Gerri Ezeocha  Date: 03/16/2019*/
package ezeochaTask5;
/**
 * Goal of Task:  to create a Hangman Game with several specifications
 * Such as reading the word entries from a text file and creating an ASCII
 * hangman art to go along with progress in the game.
 * 
 * Text-file used for game: hangman.txt
 * Random method: I used Math.random() on my words array to randomly select a word
 * for the user to guess.
 */
import java.util.*;
import java.io.*;
public class ezeochaTask5 {

	public static void main(String[] args) {
		//Opened a scanner to have user enter file name.
		System.out.println("* * Let's Play a Hangman Game * *");
		System.out.println();
		System.out.println();
		Scanner input = new Scanner(System.in);
		String txtFile = " ";
		System.out.print("Enter the text file name to begin: ");
		txtFile = input.nextLine().trim(); //
		//input.close();
		System.out.println();
		
		//Creating an array to hold each word in text file
		String [] words = new String[854];
		
		//Creating a  method that accepts file and created array.  in order to fill String array with words from textfile
		//method is surrounded with a try-catch block for error handling.
		try {
			createArr(words, txtFile);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No such file or directory. please check and retry");
		}
		
		//Variables that hold the win, loss, bad-guess count.
		String playAgain;
		int winCount = 0;
		int lossCount = 0;		
		int badGuess = 0;
		//Arraylist to hold each used word in the game.
		ArrayList<String> usedlist = new ArrayList<String>();
		
		//do-while loop that controls play of game.
		do {
		//method to get and return random word
		String pickedWord = pickWord(words);
		
		usedlist = usedWords(usedlist, pickedWord);
		
		//changing/hiding picked word with asterisks
		char[] asterisk = new char[pickedWord.length()];
		asterisk = rePlaceAst(pickedWord.toCharArray());
		System.out.println(asterisk); 
		System.out.println();
		
		//do-while loop that controls and monitors each turn/guesses of game.
		do {
			
		//Method to get and process player guess
		char guess = getGuess();
		
		//if statement to compare word and guess, while calculating misses.
		if (!checkGuess(pickedWord, asterisk, guess)) {
			badGuess++;
			System.out.println("(You've missed " + badGuess + "!)");
			hangMan(badGuess);
		
			if(badGuess > 11) {
				System.out.println("Game Over: You loose");
				System.out.println("The Word was: " + pickedWord);
				lossCount++;
				break;
			}
		}
	} while(!completeWord(asterisk));
	
		//checking completed guess by player
		if(completeWord(asterisk)) {
			System.out.println("Great Job: You win!");
			System.out.println("The Word was: " + pickedWord);
			winCount++;
		}
		System.out.println();
		System.out.print("Would you like to Guess another word ? Enter Y or N:  "); //Checking if player wants to keep playing.
		playAgain = input.next().toUpperCase();
		System.out.println();
		badGuess = 0;
		
		} while (playAgain.charAt(0) == 'Y');
		
		//If player decides not to continue: display stats: wins, loss & words guessed.
		if(playAgain.charAt(0) == 'N') {
			System.out.println();
			System.out.println("Thanks for Playing!");
			System.out.println("Wins by you: "+ winCount + "\nWins by Program: "+ lossCount );
			System.out.println("Words guessed:" + usedlist);
			input.close();
		}
		
	}
	
	
	//Method to fill array with words from text file
	public static void createArr(String [] words, String txtFile)throws java.io.IOException {

		Scanner in = new Scanner(new File(txtFile));
			while(in.hasNextLine()) {
				for(int i = 0; i < words.length; i++) {
					words[i] = in.nextLine();
				}				
			}
			in.close();
	}
	
	//Method to pick and return random word from array
	public static String pickWord(String[] words) {
		String pickedWord = words[(int) (Math.random() * words.length)];
		return pickedWord;		
	}
	
	//Method to encrypt and return picked random word with asterisks 
	public static char[] rePlaceAst(char[] asterisk) {
		for(int i = 0; i < asterisk.length; i++) {
			asterisk[i] = '*';
		}
		return asterisk;
	}
	
	//Method to get and return player letter guess
	public static char getGuess() {
		Scanner input = new Scanner(System.in);
		System.out.print("Guess a letter in the word: ");
		String gss = input.nextLine();
		return gss.charAt(0);
	}
	
	//Method to compare picked word and guess and return correct or false.
	public static boolean checkGuess(String pickedWord, char[] asterisk, char guess) {
		boolean correct = false;
		
		for (int i = 0; i < pickedWord.length(); i++) {
			if(pickedWord.charAt(i) == guess) {
				asterisk[i] = guess;
				correct = true;
			}	
		}
	
		if(!pickedWord.contains(String.valueOf(guess))) {
			correct = false;
			System.out.println("Not a letter in the Word. Try Again: ");
			System.out.println();
			
		}
		System.out.println();
		System.out.println(asterisk);
		return correct;
	}
	
	//Method to checked if encrypted word is completely guessed.
	public static boolean completeWord (char[] gsEmpt) {
		for (char e: gsEmpt) {
			if(e == '*')
				return false;
		}
		return true;
	}
	
	//Method to keep track of and return guessed words.
	public static  ArrayList<String> usedWords(ArrayList<String> usedlist, String pickedWord) {
		usedlist.add(pickedWord);	
		
		return usedlist;
	}
	
	//Method to print Hang-man ASCII art
	public static void hangMan(int badGuess) {
		switch(badGuess) {
		case 1: System.out.println(" |");
		break;
		case 2: System.out.println(" |");
				System.out.println(" |");
		break;
		case 3: System.out.println(" |");
				System.out.println(" |");
				System.out.println(" |");
		break;
		case 4: System.out.println(" |");
				System.out.println(" |");
				System.out.println(" |");
				System.out.println(" |");
		break;
		case 5: System.out.println("__");
				System.out.println("  |");
				System.out.println("  |");
				System.out.println("  |");
				System.out.println("  |");
		break;
		case 6: System.out.println(" __");
				System.out.println("|  |");
				System.out.println( "   |");
				System.out.println( "   |");
				System.out.println( "   |");
		break;
		case 7: System.out.println(" __");
				System.out.println("|  |");
				System.out.println("O  |");
				System.out.println( "   |");
				System.out.println( "   |");
		break;
		case 8: System.out.println(" __");
				System.out.println("|  |");
				System.out.println("O  |");
				System.out.println(" \\ |");
				System.out.println( "   |");
		break;
		case 9: System.out.println("  ___ ");
				System.out.println(" |   |");
				System.out.println(" O   |");
			    System.out.println(" |\\  |");
				System.out.println( "     |");
		break;	
		case 10: System.out.println("   ___ ");
				System.out.println("  |   |");
				System.out.println("  O   |");
				System.out.println(" /|\\  |");
				System.out.println( "      |");
		break;	
		case 11: System.out.println("   ___ ");
				System.out.println("  |   |");
				System.out.println("  O   |");
				System.out.println(" /|\\  |");
				System.out.println( "   \\ |");
		break;
		case 12: System.out.println("   ___ ");
				System.out.println("  |   |");
				System.out.println("  O   |");
				System.out.println(" /|\\  |");
				System.out.println( " / \\  |"); 
		break;
		}
	}
	
	}


