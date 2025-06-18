// User function Template for C++
class Solution {
  public:
    double power(double b, int e) {
        //erm what the sigma
        //we do in O(log e)?
        if (e == 0)
        return 1;
        if (e < 0)
            return 1 / power(b, -e); //lets call it again
        double t = power(b, e/2); //again!
        if (e % 2 == 0)
            return t*t;
        else
            return b*t*t;
    }
};
