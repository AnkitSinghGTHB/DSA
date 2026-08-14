class Solution {
    public int maximumLengthSubstring(String s) {
        //another sliding window ques?
        //ayo wait it just asks the length, lol
        //nah substring is contiguous elements
        //yes i was making it way too complex, deepseek helped
        int[] freq = new int[26];
        int left = 0, maxLen = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq[c - 'a']++;
            // If any character exceeds 2, shrink window from left
            while (freq[c - 'a'] > 2) {
                char leftChar = s.charAt(left);
                freq[leftChar - 'a']--;
                left++;
            }
            // Update max length
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
