/**
        * File: Problem Set Unit 4
        * Author: Alfred Yang
        * Date Created: April 21, 2026
        * Date Last Modified: April 27, 2026
        */
import java.util.Random;
import java.util.Scanner;
public class ProblemSet {
	static int minimumValue = 0;
	static int maximumValue = 0;
	static Scanner input = new Scanner(System.in);
	static Boolean rangeNumber = false;
	static Random random = new Random();

	public static void main(String args[]) {
		int score = 0;
		System.out.println("Welcome to the High Low Guessing Game.\n");
		System.out.print("Input a number of rounds to play: ");

	//number of rounds
	int numberOfAttempts = 0;
	numberOfAttempts = inputNumber("Input a number of rounds to play: ");

	//THE RANGE
	rangeChecker();
		while (!(rangeNumber) || !(minimumValue+2 <= maximumValue)){
			System.out.println("\nInvilid Range!");
			rangeChecker();
		}	
		//High Low or Even text messages 
		String evenTextMessage = "";
		String highTextMessage = "";
		String lowTextMessage = "";
		int upperMiddleNumber = (int)Math.ceil(((double)maximumValue-(double)minimumValue)/2) + minimumValue;
		int lowerMiddleNumber = (int)Math.floor(((double)maximumValue-(double)minimumValue)/2) + minimumValue;
		if (upperMiddleNumber == lowerMiddleNumber){
			evenTextMessage = "3. Even(" + upperMiddleNumber +")\n"; 
		}
		else{
			evenTextMessage = "3. Even(" + lowerMiddleNumber + " to " + upperMiddleNumber +")\n";
		}
		if (maximumValue == upperMiddleNumber+1){
			highTextMessage = "1. High (" + maximumValue + ")";
		}
		else {
			highTextMessage = "1. High (" + (upperMiddleNumber+1) + " to " + maximumValue + ")";
		}
		if (minimumValue == lowerMiddleNumber-1){
			lowTextMessage = "2. Low (" + minimumValue + ")";
		}
		else {
			lowTextMessage = "2. Low (" + minimumValue + " to " + (lowerMiddleNumber-1) + ")";
		}
		String highLowEvenTextMessage = "Please select High, Low or Even:\n" + highTextMessage + "\n" + lowTextMessage + "\n" + evenTextMessage + "\n";
		for (int roundNumber = 1; roundNumber < numberOfAttempts+1; roundNumber++){
			System.out.println("\nRound " + roundNumber + ":\n");
			System.out.print(highLowEvenTextMessage);
	//picking 1, 2, 3
		int highLowOrEvenInput = inputNumber(highLowEvenTextMessage);
		while (highLowOrEvenInput < 1 || highLowOrEvenInput > 3){
			System.out.println("\nInvalid Input!\n");
			System.out.print(highLowEvenTextMessage);
			highLowOrEvenInput = inputNumber(highLowEvenTextMessage);
		}
	//picking random number
		int randomNumber = random.nextInt(maximumValue - minimumValue) + minimumValue;
		if ((randomNumber > upperMiddleNumber && highLowOrEvenInput == 1) || (randomNumber < lowerMiddleNumber && highLowOrEvenInput == 2) || (highLowOrEvenInput == 3 && (randomNumber == upperMiddleNumber || randomNumber == lowerMiddleNumber))){
			score++;
			System.out.println("\nThe number was " + randomNumber + ". You were correct.");
			System.out.println("Current score: " + score + "\n");
		}
		else {
			System.out.println("\nThe number was " + randomNumber + ". You were incorrect.");
			System.out.println("Current score: " + score);
		}
	}
	// Printing score number
		System.out.println("Total Score: " + score);
		if (score >= ((double)numberOfAttempts/2)){
			System.out.println("Congratulations you got " + score + " out of " + numberOfAttempts + " rounds right!");
		}
		else{System.out.println("You got " + score + " out of " + numberOfAttempts + ". Better Luck next time.");}
	}
//------------------------------FUNCTIONS BELOW------------------------------------------------------------------------
	//Checking If The Value That Was Given Was A Number
	public static boolean ifNumber(String number) {
		if (number.length() == 0){return false;}
		//Checking The Ascii Values Of The Numbers 0-9 For Every Index
		for (int index = 0; index < number.length(); index++) {
			if (!((int) number.charAt(index) >= 48 && (int) number.charAt(index) <= 57) || number.contains(" ")) {
				return false;
			}
		}
		return true;
	}
	public static int inputNumber(String repeatErrorMessage){
		String userInput = input.nextLine();
		while (!ifNumber(userInput) || userInput.startsWith("0") || userInput.length() == 0){
			System.out.println("\nInvalid Input!\n");
			System.out.print(repeatErrorMessage);
			userInput = input.nextLine();
		}
		return Integer.parseInt(userInput);
	}
	public static String rangeChecker(){
		System.out.println("\nWhat Range would you like to play between (#-#)?");
		String range = input.nextLine();
		rangeNumber = false;
		minimumValue = 0;
		maximumValue = 0;
		String min = "";
		String max = "";
		int indexOfDash = 0;
		if (range.length() < 3){return range;}
		//Splitting The Range To Two Numbers
		else if (range.substring(1).contains("-")){
			indexOfDash = range.substring(1).indexOf("-")+1;
			min = range.substring(0, indexOfDash);
			max = range.substring(indexOfDash+1);
		} 
		if ((min.length() >= 2 && min.startsWith("-") && ifNumber(min.substring(1)))){
			minimumValue = Integer.parseInt(min.substring(1)) * -1;
		}
		else if (ifNumber(min)){
			minimumValue = Integer.parseInt(min);
		}
		else{
			rangeNumber = false;
			return range;
		}
		if ((max.length() >= 2 && max.startsWith("-") && ifNumber(max.substring(1)))){
			maximumValue = Integer.parseInt(max.substring(1)) * -1;
		}
		else if (ifNumber(max)){
			maximumValue = Integer.parseInt(max);
		}
		else{
			rangeNumber = false;
			return range;
		}
		rangeNumber = true;
		return range;	
			}
	}