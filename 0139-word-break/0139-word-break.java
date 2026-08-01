import java.util.*;

class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> set = new HashSet<>(wordDict);

        int maxLen = 0;
        for (String word : wordDict)
            maxLen = Math.max(maxLen, word.length());

        int n = s.length();

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {

            for (int len = 1; len <= maxLen && len <= i; len++) {

                if (!dp[i - len])
                    continue;

                if (set.contains(s.substring(i - len, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}