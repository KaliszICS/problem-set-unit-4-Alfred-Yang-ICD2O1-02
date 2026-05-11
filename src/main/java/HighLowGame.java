
/**
        * File: Problem Set Unit 4
        * Author: Alfred Yang
        * Date Created: April 21, 2026
        * Date Last Modified: April 27, 2026
        */
import java.util.Random;
import java.util.Scanner;

public class HighLowGame {
	static int minimumValue = 0;
	static int maximumValue = 0;
	// static Scanner input = new Scanner(System.in);
	static Random random = new Random();

	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		int score = 0;
		System.out.println("Welcome to the High Low Guessing Game.\n");
		System.out.print("Input a number of rounds to play: ");

		// number of rounds
		int numberOfAttempts = 0;
		numberOfAttempts = inputNumber("Input a number of rounds to play: ");

		// THE RANGE
		while (!(rangeChecker()) || !(minimumValue + 2 <= maximumValue)) {
			System.out.println("\nInvalid Range!");
		}
		// High Low or Even text messages
		String evenText = "";
		String highText = "";
		String lowText = "";
		int upperMiddleNum = (int) Math.ceil(((double) maximumValue - (double) minimumValue) / 2) + minimumValue;
		int lowerMiddleNum = (int) Math.floor(((double) maximumValue - (double) minimumValue) / 2) + minimumValue;
		if (upperMiddleNum == lowerMiddleNum) {
			evenText = "3. Even(" + upperMiddleNum + ")\n\n";
		} else {
			evenText = "3. Even(" + lowerMiddleNum + " to " + upperMiddleNum + ")\n\n";
		}
		if (maximumValue == upperMiddleNum + 1) {
			highText = "1. High (" + maximumValue + ")\n";
		} else {
			highText = "1. High (" + (upperMiddleNum + 1) + " to " + maximumValue + ")\n";
		}
		if (minimumValue == lowerMiddleNum - 1) {
			lowText = "2. Low (" + minimumValue + ")\n";
		} else {
			lowText = "2. Low (" + minimumValue + " to " + (lowerMiddleNum - 1) + ")\n";
		}
		String gameMenu = "Please select High, Low or Even:\n" + highText + lowText + evenText;
		for (int roundNumber = 1; roundNumber < numberOfAttempts + 1; roundNumber++) {
			System.out.println("\nRound " + roundNumber + ":\n");
			System.out.print(gameMenu);
			// picking 1, 2, 3
			int userInput = inputNumber(gameMenu);
			while (userInput < 1 || userInput > 3) {
				System.out.println("\nInvalid Input!\n");
				System.out.print(gameMenu);
				userInput = inputNumber(gameMenu);
			}
			// picking random number
			int randomNumber = random.nextInt(maximumValue - minimumValue) + minimumValue;
			boolean higher = randomNumber > upperMiddleNum;
			boolean lower = randomNumber < lowerMiddleNum;
			boolean middle = randomNumber == upperMiddleNum || randomNumber == lowerMiddleNum;
			if ((higher && userInput == 1) || (lower && userInput == 2) || (userInput == 3 && (middle))) {
				score++;
				System.out.println("\nThe number was " + randomNumber + ". You were correct.");
				System.out.println("Current score: " + score + "\n");
			} else {
				System.out.println("\nThe number was " + randomNumber + ". You were incorrect.");
				System.out.println("Current score: " + score);
			}
		}
		// Printing score number
		System.out.println("\nTotal Score: " + score);
		if (score >= ((double) numberOfAttempts / 2)) {
			System.out.println("Congratulations you got " + score + " out of " + numberOfAttempts + " rounds right!");
		} else {
			System.out.println("You got " + score + " out of " + numberOfAttempts + ". Better Luck next time.");
		}
	}

	// ------------------------------FUNCTIONS
	// BELOW------------------------------------------------------------------------
	// Checking If The Value That Was Given Was A Number
	public static boolean ifNumber(String number) {
		if (number.length() == 0) {
			return false;
		}
		// Checking The Ascii Values Of The Numbers 0-9 For Every Index
		for (int index = 0; index < number.length(); index++) {
			if (!((int) number.charAt(index) >= 48 && (int) number.charAt(index) <= 57) || number.contains(" ")) {
				return false;
			}
		}
		return true;
	}

	public static int inputNumber(String repeatErrorMessage) {
		Scanner input = new Scanner(System.in);
		String userInput = input.nextLine();
		while (!ifNumber(userInput) || userInput.startsWith("0") || userInput.length() == 0) {
			System.out.println("\nInvalid Input!\n");
			System.out.print(repeatErrorMessage);
			userInput = input.nextLine();
		}
		return Integer.parseInt(userInput);
	}

	public static boolean rangeChecker() {
		Scanner input = new Scanner(System.in);
		System.out.println("\nWhat Range would you like to play between (#-#)?");
		String range = input.nextLine();
		Boolean rangeNumber = false;
		minimumValue = 0;
		maximumValue = 0;
		String min = "";
		String max = "";
		int indexOfDash = 0;
		if (range.length() < 3) {
			return rangeNumber;
		}
		// Splitting The Range To Two Numbers
		else if (range.substring(1).contains("-")) {
			indexOfDash = range.substring(1).indexOf("-") + 1;
			min = range.substring(0, indexOfDash);
			max = range.substring(indexOfDash + 1);
		}
		if ((min.length() >= 2 && min.startsWith("-") && ifNumber(min.substring(1)))) {
			minimumValue = Integer.parseInt(min.substring(1)) * -1;
		} else if (ifNumber(min)) {
			minimumValue = Integer.parseInt(min);
		} else {
			return rangeNumber;
		}
		if ((max.length() >= 2 && max.startsWith("-") && ifNumber(max.substring(1)))) {
			maximumValue = Integer.parseInt(max.substring(1)) * -1;
		} else if (ifNumber(max)) {
			maximumValue = Integer.parseInt(max);
		} else {
			return rangeNumber;
		}
		return rangeNumber = true;
	}
}