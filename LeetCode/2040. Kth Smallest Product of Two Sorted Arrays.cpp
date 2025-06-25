class Solution {
public:
    long long kthSmallestProduct(vector<int>& nums1, vector<int>& nums2, long long k) {
        //nums1 and 2 are pre-sorted, k is 1 based, arrays are 0 based
        //we need to return the smallest prod of nums1[i]*nums2[j] where 0<=i<n1
        //and 0<=j<n2
        //well this looks pretty easy to do but there is a catch
        //how about we split the problem into 4 cases wrt the sign
        //and then binary search the val to make it more optimal
        //note: we cannot bruteforce here, very big values would be there
        vector<int> A_neg, A_pos, B_neg, B_pos;
        for (int a : nums1) (a < 0 ? A_neg : A_pos).push_back(abs(a));
        for (int b : nums2) (b < 0 ? B_neg : B_pos).push_back(abs(b));
        sort(A_neg.begin(), A_neg.end());
        sort(B_neg.begin(), B_neg.end());

        long long neg_count = (long long)A_neg.size() * B_pos.size() + (long long)A_pos.size() * B_neg.size();
        int sign = 1;
        if (k > neg_count) {
            k -= neg_count;
        } else {
            k = neg_count - k + 1;
            sign = -1;
            swap(B_neg, B_pos);
        }

        long long low = 0, high = 1e10;
        while (low < high) {
            long long mid = (low + high) / 2;
            if (countNoGreater(A_neg, B_neg, mid) + countNoGreater(A_pos, B_pos, mid) >= k)
                high = mid;
            else
                low = mid + 1;
        }
        return sign * low;
    }

private:
    long long countNoGreater(const vector<int>& A, const vector<int>& B, long long m) {
        long long count = 0;
        int j = B.size() - 1;
        for (int i = 0; i < A.size(); ++i) {
            int a = A[i];
            while (j >= 0 && (long long)a * B[j] > m) --j;
            count += j + 1;
        }
        return count;
    }
    /*
    The algorithm first separates both input arrays into negative and non-negative parts, treating all values as positive to simplify multiplication logic. It calculates how many total negative products there are, and based on whether the k-th smallest product is negative or positive, it adjusts k and flips the sign if needed. The core idea is to binary search the product value space (from 0 to 1e10) to find the smallest product such that there are at least k products less than or equal to it. For each midpoint value in the binary search, it efficiently counts how many valid products exist by using a two-pointer sweep over the sorted positive components. This eliminates expensive division and branching, making the solution fast and accepted without TLE.
    */
};
