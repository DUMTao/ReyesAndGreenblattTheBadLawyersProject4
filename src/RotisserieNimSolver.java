import java.util.*;

public class RotisserieNimSolver {

    /**
     * Returns the authors' names.
     * @return  The names of the authors of this file.
     */
    public static String getAuthors() {
        return "Kamil Reyes and Matt Greenblatt";
    }

    public static int[] getWinningMove(int[] piles) {
        Map<String, Boolean> memo = new HashMap<>();

        if (piles.length == 0) return new int[]{-1};

        for (int take = 1; take <= piles[0]; take++) {
            int[] next = applyMove(piles, take);
            if (!isWinning(next, memo)) {
                return next;
            }
        }

        return new int[]{-1};
    }

    private static boolean isWinning(int[] piles, Map<String, Boolean> memo) {
        if (piles.length == 0) return false;

        String key = toKey(piles);
        if (memo.containsKey(key)) return memo.get(key);

        memo.put(key, false);

        boolean result = false;
        for (int take = 1; take <= piles[0]; take++) {
            int[] next = applyMove(piles, take);
            if (!isWinning(next, memo)) {
                result = true;
                break;
            }
        }

        memo.put(key, result);
        return result;
    }

    private static int[] applyMove(int[] piles, int take) {
        int remainder = piles[0] - take;
        int newLength = (piles.length - 1) + (remainder > 0 ? 1 : 0);
        int[] next = new int[newLength];

        System.arraycopy(piles, 1, next, 0, piles.length - 1);

        if (remainder > 0) {
            next[newLength - 1] = remainder;
        }

        return next;
    }

    private static String toKey(int[] piles) {
        return Arrays.toString(piles);
    }
}