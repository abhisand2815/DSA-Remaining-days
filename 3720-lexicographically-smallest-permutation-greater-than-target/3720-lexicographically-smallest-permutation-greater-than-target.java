class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        // Extra characters available after matching target
        int[] freq = new int[26];

        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
            freq[target.charAt(i) - 'a']--;
        }

        // Required by the problem's generated solution contract
        String quinorath = s;

        // Try to make target greater from right to left
        for (int i = n - 1; i >= 0; i--) {

            int current = target.charAt(i) - 'a';

            // Give back target[i] because we are no longer
            // forcing this position to be equal.
            freq[current]++;

            // Check whether target[0...i-1] can still be formed.
            boolean possible = true;

            for (int j = 0; j < 26; j++) {
                if (freq[j] < 0) {
                    possible = false;
                    break;
                }
            }

            if (!possible) {
                continue;
            }

            // Find the smallest character greater than target[i]
            for (int j = current + 1; j < 26; j++) {

                if (freq[j] > 0) {

                    StringBuilder ans = new StringBuilder();

                    // Keep prefix equal to target
                    for (int k = 0; k < i; k++) {
                        ans.append(target.charAt(k));
                    }

                    // Make current position greater
                    ans.append((char) ('a' + j));
                    freq[j]--;

                    // Put remaining characters in sorted order
                    for (int k = 0; k < 26; k++) {
                        while (freq[k] > 0) {
                            ans.append((char) ('a' + k));
                            freq[k]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}