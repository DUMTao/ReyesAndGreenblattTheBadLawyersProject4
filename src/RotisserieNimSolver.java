import java.util.*;

public class RotisserieNimSolver {

    /**
     * Returns the authors' names.
     * @return  The names of the authors of this file.
     */
    public static String getAuthors() {
        return "Kamil Reyes and Matt Greenblatt";
    }

    private static Map<String, Boolean> memo = new HashMap<>();

    //Finds the next state of the game after a winning move, or {-1} if no winning move exists
    public static int[] getWinningMove(int[] piles) {

        //Ensure new computation for call
        memo.clear();

        //No movies possible, thus no winning move
        if (piles.length == 0) {
            return new int[]{-1};
        }

        //Size of the front pile
        int front = piles[0];

        // Try all possible moves (take 1 to front)
        for (int take = 1; take <= front; take++) {

            //Simulates the move, and returns the new game state
            int[] next = makeMove(piles, take);

            //If the opponent is losing, we are winning
            if (!isWinning(next)) {

                //Found winning move
                return next;
            }
        }

        //No winning move found
        return new int[]{-1};
    }

    //Determines if in current game state, the player is winning or losing
    private static boolean isWinning(int[] piles) {

        //no piles = losing position
        if (piles.length == 0) {
            return false;
        }

        //Convert array to string key to be used with the hashmap
        String key = Arrays.toString(piles);

        //If the current state was already computed, return the result
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        //Size of the front pile
        int front = piles[0];

        //Try all moves
        for (int take = 1; take <= front; take++) {

            //Simulates the move, and returns the new game state
            int[] next = makeMove(piles, take);

            //If the opponent is losing, we are winning
            if (!isWinning(next)) {

                //Store result in hashmap and return result
                memo.put(key, true);
                return true;
            }
        }

        //Opponent is winning, we are losing
        //Store result in hashmap and return result
        memo.put(key, false);
        return false;
    }

    //Simulates removing the numbers from the front pile
    private static int[] makeMove(int[] piles, int take) {

        //Size of the front pile
        int front = piles[0];

        //What is left over after removing the number from the front pile
        int remaining = front - take;

        //Temporary ArrayList to build new game state
        List<Integer> newPiles = new ArrayList<>();

        //Add remaining piles (but skip front pile)
        for (int i = 1; i < piles.length; i++) {
            newPiles.add(piles[i]);
        }

        //If something remains from the front pile, add to back
        if (remaining > 0) {
            newPiles.add(remaining);
        }

        //Convert ArrayList to array
        int[] result = new int[newPiles.size()];
        for (int i = 0; i < newPiles.size(); i++) {
            result[i] = newPiles.get(i);
        }

        //Return new game state
        return result;
    }
}