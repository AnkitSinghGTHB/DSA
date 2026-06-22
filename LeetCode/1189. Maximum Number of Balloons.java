class Solution {
    public int maxNumberOfBalloons(String text) {
        //shall i hardcode it?
        //basically i make a hashmap, take b a l o n
        //just traverse the text, when b>=1, a>=1, l>=2, o>=2, n>=1,
        //count++ and reduce the values, b-1, a-1, l-2, o-2, n-1
        //finally we return count
        int[] freq = new int[26];
        for (char c : text.toCharArray()) {
            freq[c - 'a']++;
        }
        
        //for "balloon", we need:
        //b:1, a:1, l:2, o:2, n:1
        int b = freq['b' - 'a'];
        int a = freq['a' - 'a'];
        int l = freq['l' - 'a'] / 2;
        int o = freq['o' - 'a'] / 2;
        int n = freq['n' - 'a'];
        
        //the maximum number is limited by the minimum of these requirements
        //okay deepseek just told me to do this, i am unable to comprehend still
        int max = Math.min(
                    Math.min(
                        b, 
                        a
                    ), 
                    Math.min(
                        l, 
                        Math.min(
                            o, 
                            n
                        )
                    )
                );
        return max;
    }
}
