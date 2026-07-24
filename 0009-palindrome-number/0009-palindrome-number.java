class Solution {

    public boolean isPalindrome(int x) {

        // Negative numbers and numbers ending with 0
        // (except 0 itself) can't be palindromes
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;

        int reversed = 0;

        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }

        // Even digits: x == reversed
        // Odd digits: x == reversed / 10
        return x == reversed || x == reversed / 10;
    }
}