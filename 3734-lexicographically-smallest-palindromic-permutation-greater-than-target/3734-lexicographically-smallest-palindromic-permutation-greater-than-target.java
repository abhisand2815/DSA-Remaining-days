class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();
        int half = n / 2;

        int[] freq = new int[26];

        // Count characters
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // Check whether palindrome is possible
        int odd = 0;
        int middle = -1;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middle = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        // Characters available for left half
        int[] halfFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        /*
         * First, try using exactly the target's left half.
         * This can still be greater because of:
         * 1. Middle character (odd length)
         * 2. Right half
         */
        int[] temp = halfFreq.clone();
        boolean possible = true;

        for (int i = 0; i < half; i++) {
            int c = target.charAt(i) - 'a';

            if (temp[c] == 0) {
                possible = false;
                break;
            }

            temp[c]--;
        }

        if (possible) {

            StringBuilder left = new StringBuilder();

            for (int i = 0; i < half; i++) {
                left.append(target.charAt(i));
            }

            String candidate = buildPalindrome(left, middle, n);

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        /*
         * Change one character in the left half.
         * Start from the rightmost position so that
         * the resulting palindrome is lexicographically smallest.
         */
        for (int pos = half - 1; pos >= 0; pos--) {

            int[] remaining = halfFreq.clone();

            // Match target's prefix
            boolean valid = true;

            for (int i = 0; i < pos; i++) {

                int c = target.charAt(i) - 'a';

                if (remaining[c] == 0) {
                    valid = false;
                    break;
                }

                remaining[c]--;
            }

            if (!valid) {
                continue;
            }

            int current = target.charAt(pos) - 'a';

            // Choose the smallest character greater than target[pos]
            for (int bigger = current + 1; bigger < 26; bigger++) {

                if (remaining[bigger] == 0) {
                    continue;
                }

                StringBuilder left = new StringBuilder();

                // Prefix same as target
                for (int i = 0; i < pos; i++) {
                    left.append(target.charAt(i));
                }

                // Make palindrome greater here
                left.append((char) ('a' + bigger));

                remaining[bigger]--;

                // Fill remaining characters in sorted order
                for (int c = 0; c < 26; c++) {
                    while (remaining[c] > 0) {
                        left.append((char) ('a' + c));
                        remaining[c]--;
                    }
                }

                return buildPalindrome(left, middle, n);
            }
        }

        return "";
    }

    private String buildPalindrome(StringBuilder left, int middle, int n) {

        StringBuilder result = new StringBuilder(n);

        // Left half
        result.append(left);

        // Middle character for odd length
        if (n % 2 == 1) {
            result.append((char) ('a' + middle));
        }

        // Right half = reverse(left)
        for (int i = left.length() - 1; i >= 0; i--) {
            result.append(left.charAt(i));
        }

        return result.toString();
    }
}