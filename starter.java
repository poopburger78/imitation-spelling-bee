/*
 *	Author: Maddy Ludes
 *  Date: 5/13/24
*/

import pkg.*;
import java.util.Scanner;
import java.util.Random;
import java.io.*;

//goals:

// small?:
// - add shuffle option
// - make the game set easier to look at
// - add tutorial option
// - label each set's date

// big?:
// - add hints
// - allow user to select specific game sets
// - add logins to identify user, and stats
// - add queen bee rank, and keep track of all points available
// - fully debug game ;(

class starter {
	public static void main(String args[]) {
		//Scanner sc = new Scanner(System.in);

		SpellingBee bee = new SpellingBee();
		bee.play();

	}
}
