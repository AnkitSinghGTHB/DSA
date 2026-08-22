class Solution {
    public boolean checkDivisibility(int n) {
        int m=n;
        int s=0;
        int p=1;
        while(m>0){
            int d= m%10;
            p*=d;
            s+=d;
            m/=10;
        }
        if(n%(s+p)==0){
            return true;
        }
        return false;
    }
}
