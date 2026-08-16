class Solution {
    public String reverseVowels(String s) {
        char[] a = s.toCharArray();
        int l = 0, r = a.length - 1;

        while (l < r) {
            while (l < r && !vowel(a[l])) l++;
            while (l < r && !vowel(a[r])) r--;

            char temp = a[l];
            a[l++] = a[r];
            a[r--] = temp;
        }

        return new String(a);
    }

    private boolean vowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
               c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}