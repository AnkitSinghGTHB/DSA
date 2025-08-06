const int INF = -1000000000;

class Solution {
public:
    vector<int> tree;
    vector<int> arr;

    void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = arr[l];
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node + 1, l, mid);
        build(2 * node + 2, mid + 1, r);
        tree[node] = max(tree[2 * node + 1], tree[2 * node + 2]);
    }

    void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            tree[node] = val;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) {
            update(2 * node + 1, l, mid, idx, val);
        } else {
            update(2 * node + 2, mid + 1, r, idx, val);
        }
        tree[node] = max(tree[2 * node + 1], tree[2 * node + 2]);
    }

    int query(int node, int l, int r, int fruit) {
        if (tree[node] < fruit) 
            return -1;
        if (l == r) 
            return l;
        int mid = (l + r) / 2;
        if (tree[2 * node + 1] >= fruit) {
            int left_ans = query(2 * node + 1, l, mid, fruit);
            if (left_ans != -1) 
                return left_ans;
        }
        if (tree[2 * node + 2] >= fruit) {
            int right_ans = query(2 * node + 2, mid + 1, r, fruit);
            if (right_ans != -1) 
                return right_ans;
        }
        return -1;
    }

    int numOfUnplacedFruits(vector<int>& fruits, vector<int>& baskets) {
        //this is lowkey depressing ngl
        int n = baskets.size();
        if (n == 0) 
            return 0;
        arr = baskets;
        tree.resize(4 * n, INF);
        build(0, 0, n - 1);
        int unplaced = 0;
        for (int f : fruits) {
            int idx = query(0, 0, n - 1, f);
            if (idx == -1) {
                unplaced++;
            } else {
                update(0, 0, n - 1, idx, INF);
            }
        }
        return unplaced;
    }
};
