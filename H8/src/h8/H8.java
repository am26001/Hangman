// program allows the user to play the game Hangman, it utilizes a random number generator to pick a guessable word from an input file, loops, and if else blocks  
// @author Alan Martinez-Lopez

package h8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class H8 {
    
    public static void main(String[] args) {

        // create file var and import the file using its exact name - call it "inputFile"
        File inputFile = new File("words_no_duplicates.txt");

        // declare null scanner var to scan the input file - call it "fileInput"
        Scanner fileInput = null;

        // create empty string var to store a word from the input file - call it "word"
        String word = "";

        // create random var to generate random numbers - call it "randGen"
        Random randGen = new Random();

        // create integer var to generate random numbers 1 to 4581 - call it "randNum" 
        int randNum = randGen.nextInt(4581) + 1;
        // System.out.println(randNum); // test random number generated (for programmer only)
        
        // create try - catch, TRY to scan every word from the input file
        try {
            fileInput = new Scanner(inputFile);
        } // end try
        
        // execute error statements if the input file is not found, CATCHES the error
        catch (FileNotFoundException err) {
            System.out.println("The error is " + err);
            System.out.println("The File was not found!");
        } // end catch

        //grab one word until it reaches its random number spot on the input file list
        for (int i = 0; i < randNum; i++) {
            word = fileInput.nextLine();
        } // System.out.println(word); // test word from the input file (for programmer only)

        // create integer var to get the word's length - call it "len"
        int len = word.length();
        // System.out.println(len); // test word's length (for programmer only)
        
        // create empty string var - call it "unknown"
        String unknown = "";

        // create for loop to hide the word from the input file with underscores, must be the same length as the word that was grabbed from the input file
        for (int i = 0; i < len; i++) {
            unknown = unknown + "_";
        }

        // create for loop to add spaces in the hidden word, this is for readiblity
        for (int i = 0; i < len; i++) {
            System.out.print(unknown.charAt(i) + " ");
        }
        
        System.out.println(""); // for readiblity

        // create integer var for the player, counter is set to 0 - call it "guesses" 
        int guesses = 0;

        // create scanner so the player can guess the word letter by letter - call it "scnr"
        Scanner scnr = new Scanner(System.in);
        
        // create boolean var to determine if game should continue, set to true - call it "continueGame"
        boolean continueGame = true;
        
        System.out.println(""); // for readiblity
        
        // create while loop for a max number of 10 guesses, if one of these conditions is false, the whole condition itself is false and the game stops
        while ((guesses < 10) && (continueGame)) {
            
            // prompt player to enter a letter (letter can be a hyphen since the word may have one)    
            System.out.println("Enter a letter, word may have a hyphen");
            
            // create ch var, grabs player input at index 0 - call it "ch"
            char ch = scnr.next().charAt(0);
            
            // convert char to string, grabs the char at the 0th index in the string (takes care if the user enters a string and allows comparison in if block)
            String letter = Character.toString(ch);
            
            // create if block, if letter is not in the word, increase guesses by 1, print incorrect statement and remaining guesses left for the player
            if (!word.contains(letter)) {
                guesses++;
                System.out.println("That's incorrect! Guesses left: " + (10 - guesses));
                
                // create for loop to keep track of the guessed word if incorrect character is entered, includes readiblity
                for (int i = 0; i < len; i++) {
                    System.out.print(unknown.charAt(i) + " ");
                }
                
                System.out.println(""); // for readiblity
            } 
            
            // execute else statements when if block condition is false
            else {
                
                // create for loop to compare the player's entered letter to the letters from the input file's word
                for (int i = 0; i < len; i++) {

                    // create if block, if letter is in word, then find the letter(s) at index "i" in word and replace "_" in the guessed word with the letter(s) in its correct index 
                    if (word.charAt(i) == ch) {
                        
                        // update the guessed word when a correct character is entered
                        unknown = unknown.substring(0, i) + ch + unknown.substring(i + 1);
                    }

                    // create if block, if guessed word equals to the word from the input file, set the boolean to false and stop while loop
                    if (word.equals(unknown)) {

                        // set boolean to false, stops while loop and ends the game
                        continueGame = false;
                    }
                    
                } // end second loop

                // print the guessed word so far, helps the player keep track of it
                for (int i = 0; i < len; i++) {
                    System.out.print(unknown.charAt(i) + " ");
                }
                
                System.out.println(""); // for readiblity

            } // end else

            System.out.println(""); // for readiblity
            
            // create if else-if block, if boolean is false: print congrats statement, else if the amount of guesses equals to 10: print you lose statement and reveal the word
            if (continueGame == false) {
                System.out.println("Congratulations, you win! You correctly guessed the word " + '\"' + word + '\"');
            } else if (guesses == 10) {
                System.out.println("You lose, the correct word was " + '\"' + word + '\"');
            }
            
        } // end first loop

    } // end main
} // end class

