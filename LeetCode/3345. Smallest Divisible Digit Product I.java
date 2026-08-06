class Solution {
    public int smallestNumber(int n, int t) {
        //um
        //x>=n
        //multiplication(digits of x) %t==0
        //idk wht optimization can i do?
        int x=n;
        while(x<200){
            int temp=x;
            int prod=1;
            while(temp>0){
                int r = temp%10;
                prod*=r;
                temp/=10;
            }
            if (prod%t==0){
                return x;
            }
            x++;
        }
        return -1;
    }
}
