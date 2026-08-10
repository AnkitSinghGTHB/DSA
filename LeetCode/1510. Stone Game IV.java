class Solution {
    public boolean winnerSquareGame(int n) {
        //1,4,9,16,... true and only sub values
        //taking the biggest square number out is optimal? nop
        //39 -36 a= 3 -1 b=2 - 1a= 1 - 1b= 0a false
        //how about we find losing values for b?
        //there can be some way
        //they are asking to use dp in the hint, what else can i do?
        boolean[] solv = new boolean[n+1];
        solv[0]=false;
        //solv[i] is true if solv[i-s]=false else solv[i] is false
        //i mess up the s*s < i thing most of the times, i was thinking to do something else
        for (int i=1;i<=n;i++){
            boolean a = false;
            for(int s=1;s*s<=i;s++){
                if (!solv[i-s*s]){
                    a = true;
                    break;
                }
            }
            solv[i]=a;
        }
        return solv[n];
    }
}
