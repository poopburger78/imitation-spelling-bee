
package pkg;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.*;

public class InfoBoard{
	Scanner sc = new Scanner(System.in);
	
    int totalPoints;
    ArrayList<String> wordsFound;
    int[] rankRanges = new int[9];
    int cri; //short for currentRankIndex
    String[] rankNames = new String[]{"beginner", "good start", "moving up", "good", "solid", "nice", "great", "amazing", "genius"};

	public InfoBoard(String currentWord){
		//use currentWord to find the correct file of info 
		//the file has the rank ranges, all the correct words
		totalPoints = 0;
		wordsFound = new <String>ArrayList();
		loadRanks(currentWord);
		cri = 0;
	}
	
	// public void loadRanks(String currentWord) throws FileNotFoundException {
	// 	rankRanges[0] = 0;
		
	// 	File file = new File(currentWord+".txt");
 //       Scanner scan = new Scanner(file);
        
 //       String line = scan.nextLine(); 
 //       int i = 0;
 //       while(line.length() > 0){
 //       	rankRanges[i] = Integer.valueOf(line.substring(0, line.indexOf(" ")));
 //       	//^ ?
 //       	line = line.substring(line.indexOf(" "));
 //       }
        
	// }
	public void loadRanks(String currentWord){
		rankRanges[0] = 0;
		
		try{
	    	Scanner scan = new Scanner(new File(currentWord+".txt"));
		    String line = scan.nextLine();
	    	int i = 1;
    		while(line.length() > 0 && line.indexOf(" ") > -1){	//  && !(line.substring(0, line.indexOf(" ").equals(""))
    			// if(line.substring(0, line.indexOf(" ").equals("")){
    			// 	break;
    			// }
        		rankRanges[i] = Integer.valueOf(line.substring(0, line.indexOf(" ")));
        		//^ ?
        		line = line.substring(line.indexOf(" ")+1);
        		i++;
        	}
		}catch(FileNotFoundException e){
			System.out.println("caught");
		}
	}
	
	public void checkBoard(){

	    System.out.println("total points: " + totalPoints);
	    System.out.println("current rank: " + rankNames[cri]);
	    System.out.println((rankRanges[cri+1]-totalPoints) + " points away from " + rankNames[cri+1]);
	    System.out.println();
	    
	    System.out.println("see all rankings? ('y' to proceed)");
	    if(sc.nextLine().equals("y")){
	    	for(int i = 0; i < rankRanges.length; i++){
	    		System.out.println(rankNames[i] + ": " + rankRanges[i]);
	    	}
	    	System.out.println();
	    
	    }
	    System.out.println("see all words found so far? ('y' to proceed)");
	    if(sc.nextLine().equals("y")){
	    	if(wordsFound.size() == 1){
	        	System.out.println(wordsFound.size() + " word found so far: ");
	    	}else{
		        System.out.println(wordsFound.size() + " words found so far: ");
	    	}
	        printWordsFound();
	        System.out.println();
	    }
	}
	
	public void updateTotalPoints(int p){
	    totalPoints += p;
	    
	    while(totalPoints >= rankRanges[cri+1]){					//also updates CRI
	    	cri++;
	    }
	}
	public void updateWordsFound(String g){
	    wordsFound.add(g);
	}
	public void printWordsFound(){
		for(String w : wordsFound){
	            System.out.print(w + "   ");
	    }
	}
	
// 	public int getTotalPoints(){
// 	    return totalPoints;
// 	}

}