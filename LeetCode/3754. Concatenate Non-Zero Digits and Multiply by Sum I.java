class Solution {
    public long sumAndMultiply(int n) {
        long x=0,i=1,sum=0;
        while(n!=0){
            int val = n%10; //10232032, n%10 -> 2, val =2
            if (val!=0){
                x=val*i+x;
                i*=10;
                sum+=val;
            }
            n/=10; //10232032, n/10 -> 1023203
        }
        return x*sum;
    }
}
