package pkg;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.*;


public class Game{
	Scanner sc = new Scanner(System.in);
	
    static int timesPlayed = 0;
    String currentWord;
    String centerLetter;
    String guess;
    InfoBoard board;
    ArrayList<String> answerList = new <String>ArrayList();
    ArrayList<String> unguessedList;
    
	public Game(String currentWord, String centerLetter){
		timesPlayed++;
		this.currentWord = currentWord;
		this.centerLetter = centerLetter;
		board = new InfoBoard(currentWord);
		loadAnswers();
	}
	
	// public void loadAnswers() throws FileNotFoundException {
		
	// 	File file = new File(currentWord+".txt");
 //       Scanner scan = new Scanner(file);
        
 //       scan.nextLine(); //to skip over the 1st line of rankings
 //       while(scan.hasNextLine()){
 //       	answerList.add(scan.nextLine());
 //       }
	// }
	public void loadAnswers(){
		try{
	    	Scanner scan = new Scanner(new File(currentWord+".txt"));
		    scan.nextLine(); //to skip over the 1st line of rankings
	    	while(scan.hasNextLine()){
        		answerList.add(scan.nextLine());
    		}
		} catch(FileNotFoundException e){
			System.out.println("caught");
		}
		unguessedList = answerList;
	}
	public void play(){
	    while(true){
	    	System.out.println("your game word: " + currentWord);
	    	System.out.println("the center letter is: " + centerLetter.toUpperCase());
	        System.out.println("enter 's' to skip this game, 'i' to see info board, 'r' to reveal all answers \n or, play by entering a word");
    	    guess = sc.nextLine();
    	    System.out.println();
    	    
    	    switch(guess){
    	    	case "s": 
    	    		System.out.println("moving on to the next game!");
    				return;
    			case "i":
    				board.checkBoard();
		    		System.out.println("enter 's' to skip this game, or play by entering a word");
		    		guess = sc.nextLine();
    	    		if(guess.equals("s")){
    				    System.out.println("moving on to the next game!");
    					return;
    				}
    				break;
    			case "r":
    				System.out.println("all answers:");
    				System.out.println("--- unguessed: ");
    				for(String ans : unguessedList){
    					System.out.print(ans + " ");
    				}
    				System.out.println("\n --- guessed: ");
    				board.printWordsFound();
    				System.out.println("\n moving on to the next game!");
    				return;
    			default: //nothing
    	    }
			
    	    //takes in a guess, checks if it's valid, scores it, and updates the info board
	        boolean valid = isValidWord();
	        while(!valid){
    	        guess = sc.nextLine();
    	        if(guess.equals("i")){
    	        	board.checkBoard();
    	        	System.out.println("----------------");
    	        	System.out.println("play by entering a word");
    	        	guess = sc.nextLine();
    	        }
	            valid = isValidWord();
	        }
	    
	       
	    }
	}
	//also updates infoboard for me
	public boolean isValidWord(){
	    if(!(guess.length() >= 4)){
	        System.out.println("too short");
	        return false;
	    }
	    if(guess.indexOf(centerLetter) == -1){
	        System.out.println("missing center letter");
	        return false;
	    }
	    for(int i = 0; i < answerList.size(); i++){
	    	if(guess.equals(answerList.get(i))){
	    		points();
	    		board.updateWordsFound(guess);
	    		unguessedList.remove(guess);
	    		return true;
	    	}
	    }
	    System.out.println("not a valid word"); 
	    return false;
	}
	public void points(){
	    int p = 0;
	    if(guess.length() == 4){
	        p = 1;
	    }else{
	        p = guess.length();
	        if(isPangram()){
	            p += 5;
	        }
	    }
	    System.out.println("+"+p+" points!");
	    board.updateTotalPoints(p);
	    //return p;
	}
	//only checks if it uses all the letters, not if it's a valid word
	public boolean isPangram(){
	    for(int i = 0; i < currentWord.length(); i++){
	        if(guess.indexOf(currentWord.substring(i, i+1)) == -1){
	            return false;
	        }   
	    }
	    System.out.println("Pangram!");
	    return true;
	}

}