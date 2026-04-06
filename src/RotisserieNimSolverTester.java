import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.lang.*;
import java.util.concurrent.Callable;
import java.util.Arrays;

/**
 * Code to test submitted solutions for the sorting project.
 *
 * @author Kyle Burke
 */
public class RotisserieNimSolverTester extends SpeedGrader.CodeTester {

    private static final int MAX_SCORE = 100;

    private static final double MULTIPLIER_TO_TIME_OUT = 8.00;

    //private static final int[] SIZES = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    private static final int[] SIZES = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 40, 50, 60, 70, 80, 90, 100};
    private static final int N = 40;

    public RotisserieNimSolverTester() {
        //no state
    }

    public String getCodeAuthors() {
        return RotisserieNimSolver.getAuthors();

    }


    // Tests whether two arrays of integers are equal.
    private static boolean arrayEquals(int[] a, int[] b) {
        try {
            for (int i = 0; i < a.length || i < b.length; i++) {
                if (a[i] != b[i]) return false;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    //Prints out an array in a line.
    private static String arrayToString(int[] a) {
        String s = "[ ";
        for (int i = 0; i < a.length; i++) {
            s += a[i] + " ";
        }
        return s + "]";
    }


    /**
     * Runs the tests and prints feedback if asserts are enabled.
     */
    public Long call() {


        //System.out.println("IN CALL!!!!");
        //assert false : "In call!";


        boolean test;
        String message;

        //test some base cases:
        int[][][] givenTestCases = new int[6][][];
        int caseNum = 0;
        int[][] testCase;
        int[] noWin = new int[] {-1};

        testCase = new int[2][];
        testCase[0] = new int[] {};
        testCase[1] = noWin;
        givenTestCases[caseNum] = testCase;
        caseNum++;

        testCase = new int[2][];
        testCase[0] = new int[] {1};
        testCase[1] = new int[] {};
        givenTestCases[caseNum] = testCase;
        caseNum++;

        testCase = new int[2][];
        testCase[0] = new int[] {1, 1};
        testCase[1] = noWin;
        givenTestCases[caseNum] = testCase;
        caseNum++;

        testCase = new int[2][];
        testCase[0] = new int[] {1, 1, 1};
        testCase[1] = new int[] {1, 1};
        givenTestCases[caseNum] = testCase;
        caseNum++;

        testCase = new int[2][];
        testCase[0] = new int[] {2, 1, 1};
        testCase[1] = new int[] {1, 1};
        givenTestCases[caseNum] = testCase;
        caseNum++;

        testCase = new int[2][];
        testCase[0] = new int[] {5, 10, 7};
        testCase[1] = new int[] {10, 7, 1};
        givenTestCases[caseNum] = testCase;
        caseNum++;

        /*  There are two winning solutions to this one!
        testCase = new int[2][];
        testCase[0] = new int[] {5, 10, 17};
        testCase[1] = new int[] {10, 17};
        givenTestCases[caseNum] = testCase;
        caseNum++;
        */

        for (int i = 0; i < givenTestCases.length; i++) {
            testCase = givenTestCases[i];
            int[] question = testCase[0];
            int[] correct = testCase[1];
            int[] answer = RotisserieNimSolver.getWinningMove(question);
            test = arrayEquals(correct, answer);
            message = "You found the wrong answer for: " + arrayToString(question) + ".   You guessed: " + arrayToString(answer) + ", but the correct answer is: " + arrayToString(correct);
            assert test : message;
        }


        int numPiles = (this.problemSize / 3) + 1;





        //specificTestCases = new HashMap<>();
        if (numPiles == SIZES[0]) {
            caseNum = 0;
            givenTestCases = new int[4][][];
            givenTestCases[caseNum] = new int[][] {new int[] {10, 10}, noWin};
            caseNum++;
            givenTestCases[caseNum] = new int[][] {new int[] {6, 5}, new int[] {5, 5}};
            caseNum++;
            givenTestCases[caseNum] = new int[][] {new int[] {1, 8}, noWin};
            caseNum++;
            givenTestCases[caseNum] = new int[][] {new int[] {594, 593}, new int[] {593, 593}};
            caseNum++;
        }




        //run the tests with given answers!
        long startTime = this.getSystemMillis();


        for (int i = 0; i < givenTestCases.length; i++) {
            testCase = givenTestCases[i];
            int[] question = testCase[0];
            int[] correct = testCase[1];
            int[] answer = RotisserieNimSolver.getWinningMove(question);
            test = arrayEquals(correct, answer);
            message = "You found the wrong answer for: " + arrayToString(question) + ".   You guessed: " + arrayToString(answer) + ", but the correct answer is: " + arrayToString(correct);
            assert test : message;
        }
        long stopTime = this.getSystemMillis();






        //run the random tests!
        int maxPileSize = ((this.problemSize % 3) + 2) * 5;
        int minPileSize = maxPileSize - 5;


        long time = stopTime - startTime;

        Random rand = new Random();
        int[] piles;
        for (int j = 0; j < 1; j++) {
            List<Integer> pileList = new ArrayList<>();
            for (int i = 0; i < numPiles; i++) {
                int next = rand.nextInt(maxPileSize - minPileSize) + minPileSize;
                pileList.add(next);
            }

            piles = new int[pileList.size()];
            for (int i = 0; i < piles.length; i++) {
                piles[i] = pileList.get(i);
                System.out.print(piles[i] + " ");
            }
            startTime = this.getSystemMillis();
            int[] answer = RotisserieNimSolver.getWinningMove(piles);
            stopTime = this.getSystemMillis();
            time += Math.max(time, stopTime - startTime);
            //System.out.println("? " + answer);  // uncomment this line to see what your code does
        }


        return time;

    }



    public static void main(String[] args) {
        SpeedGrader grader = new SpeedGrader();
        SpeedGrader.CodeTester tester = new RotisserieNimSolverTester();
        grader.runTests(tester, MULTIPLIER_TO_TIME_OUT, MAX_SCORE, SIZES);
    }
}