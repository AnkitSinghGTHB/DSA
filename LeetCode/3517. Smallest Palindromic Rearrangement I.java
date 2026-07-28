//um i got stuck while solving
class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int half = n / 2;
        StringBuilder firstHalf = new StringBuilder();
        int remaining = half;

        // Build the smallest possible first half
        for (int i = 0; i < 26 && remaining > 0; i++) {
            int available = freq[i] / 2;   // how many pairs of this character
            int take = Math.min(available, remaining);
            for (int j = 0; j < take; j++) {
                firstHalf.append((char)('a' + i));
            }
            remaining -= take;
        }

        // Find the middle character (for odd length)
        char middle = 0;
        if (n % 2 == 1) {
            for (int i = 0; i < 26; i++) {
                if (freq[i] % 2 == 1) {
                    middle = (char)('a' + i);
                    break;
                }
            }
        }

        // Build the palindrome: firstHalf + middle + reverse(firstHalf)
        StringBuilder result = new StringBuilder(firstHalf);
        if (middle != 0) result.append(middle);
        result.append(new StringBuilder(firstHalf).reverse());

        return result.toString();
    }
}
