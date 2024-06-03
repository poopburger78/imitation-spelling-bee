/*
 *	Author: Maddy Ludes
 *  Date: 5/13/24
*/

package pkg;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.*;


public class SpellingBee{
	Scanner sc = new Scanner(System.in);
	
    ArrayList<String> setList = new <String>ArrayList();

	public SpellingBee(){
	    loadSets();
	}
	
	public void loadSets(){
		try{
	    	Scanner scan = new Scanner(new File("sets.txt"));
	    	while(scan.hasNextLine()){
        		setList.add(scan.nextLine());
    		}
		} catch(FileNotFoundException e){
    	// Do something with `e` or handle it accordingly.
		}
	}
	
	public Game newGame(){  //sets up new playing word
		int i = (int)(Math.random()*setList.size());
		String word = setList.get(i);
		String letter = word.substring(0, 1);
		//System.out.println("word= "+word +", letter= "+letter);
	    
	    setList.remove(i);  //makes sure the same word doesn't come up again
	    return new Game(word, letter);
	}
	public void play(){
    	while(!(setList.size() == 0)){ //while there're still fresh sets left to play
    		System.out.println("enter 'q' to end session, or press enter to continue");
			if(sc.nextLine().equals("q")){
    		    System.out.println("bye!");
    			return;
    		}
    		//System.out.println("blo");
    		newGame().play();
    		//^ ?
    	}
	}
	
	
	

}