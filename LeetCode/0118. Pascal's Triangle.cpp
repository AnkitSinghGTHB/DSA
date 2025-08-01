class Solution {
public:
    vector<vector<int>> generate(int numRows) {
        //o wao, this looks so fun, lol
        //uh so basically 11^29 is a bit too big <who could have thought T-T>
        /*vector<vector<int>> result;
        vector<int> current = {1};//11^0 = 1
        for (int i = 0; i < n; ++i) {
            result.push_back(current);
            if (i == n - 1) break; //no need to compute next after last iteration
            vector<int> next;
            int carry = 0;
            for (int digit : current) {//multiply current by 11 to get next
                int product = digit * 11 + carry;
                next.push_back(product % 10);
                carry = product / 10;
            }
            while (carry > 0) {
                next.push_back(carry % 10);
                carry /= 10;
            }
            //gidits are stored in reverse order during multiplication
            reverse(next.begin(), next.end());
            current = next;
        }
        return result;*/
        //i might be dumb nvm
        int n=numRows;
        vector<vector<int>> triangle;
        for (int i = 0; i < n; ++i) {
            vector<int> row(i + 1, 1);//init row with 1s
            for (int j = 1; j < i; ++j) {
                row[j] = triangle[i-1][j-1] + triangle[i-1][j];//sum
            }
            triangle.push_back(row);
        }
        return triangle;
    }
};
