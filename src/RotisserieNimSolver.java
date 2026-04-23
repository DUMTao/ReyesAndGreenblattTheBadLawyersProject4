import java.util.*;

public class RotisserieNimSolver {

    /**
     * Returns the authors' names.
     * @return  The names of the authors of this file.
     */
    public static String getAuthors() {
        return "Kamil Reyes and Matt Greenblatt";
    }

    //Finds the winning move for Rotisserie Nim
    public static int[] getWinningMove(int[] piles) {

        //No winning move possible
        if (piles.length == 0) {
            return new int[]{-1};
        }

        //Computes the total number of things across all piles
        int sum = 0;
        for (int p : piles) {
            sum += p;
        }

        //Puts the piles array into state
        int[] state = new int[piles.length + sum];
        System.arraycopy(piles, 0, state, 0, piles.length);

        //Sets up the queue-like structure
        int head = 0;
        int tail = piles.length;

        //Gets the first pile
        int front = state[head];

        //Move 1: take whole pile
        if (!isWinning(state, head + 1, tail)) {
            return slice(state, head + 1, tail);
        }

        //Move 2: take 1 from front pile (front-1 goes to back)
        if (front > 1) {
            state[tail] = front - 1;
            if (!isWinning(state, head + 1, tail + 1)) {
                return slice(state, head + 1, tail + 1);
            }
        }

        //Move 3: take all but 1 from front pile (1 goes to back)
        if (front > 1) {
            state[tail] = 1;
            if (!isWinning(state, head + 1, tail + 1)) {
                return slice(state, head + 1, tail + 1);
            }
        }

        //If none of these moves work, no winning move exists
        return new int[]{-1};
    }

    //Determines if this position is winning for the current player
    private static boolean isWinning(int[] state, int head, int tail) {

        //No piles left, thus the current player loses
        if (head == tail) {
            return false;
        }

        //Get the current front pile
        int front = state[head];

        //Move 1: take all from front
        if (!isWinning(state, head + 1, tail)) {
            return true;
        }

        //Move 2: take 1 from front (front-1 goes to back)
        if (front > 1) {
            state[tail] = front - 1;
            if (!isWinning(state, head + 1, tail + 1)) {
                return true;
            }
        }

        //Move 3: take all but 1 from front (1 goes to back)
        if (front > 1) {
            state[tail] = 1;
            if (!isWinning(state, head + 1, tail + 1)) {
                return true;
            }
        }

        //This position is losing for this player
        return false;
    }

    //Creates the new array representing the current slice of the game state
    private static int[] slice(int[] state, int head, int tail) {

        //Copies that portion of the game into a new array
        int[] result = new int[tail - head];
        System.arraycopy(state, head, result, 0, tail - head);

        //Returns the current game state
        return result;
    }
}