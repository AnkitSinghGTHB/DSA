//NOTE: i ws unable to solve this question as it was too hard ngl, so i used deepseek to submit the soln
class Solution {
    private static class Node {
        int id;
        long val;
        Node prev, next;
        Node(long v, int i) {
            val = v;
            id = i;
        }
    }
    private static class Pair implements Comparable<Pair> {
        long sum;
        int leftId;
        Node left, right;

        Pair(long s, int lId, Node l, Node r) {
            sum = s;
            leftId = lId;
            left = l;
            right = r;
        }

        @Override
        public int compareTo(Pair other) {
            if (this.sum != other.sum) {
                return Long.compare(this.sum, other.sum);
            }
            return Integer.compare(this.leftId, other.leftId);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return sum == pair.sum && leftId == pair.leftId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(sum, leftId);
        }
    }

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(nums[i], i);
        }
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                nodes[i].prev = nodes[i - 1];
            }
            if (i < n - 1) {
                nodes[i].next = nodes[i + 1];
            }
        }
        Node head = nodes[0];

        TreeSet<Pair> set = new TreeSet<>();
        int violations = 0;
        Node curr = head;
        while (curr != null && curr.next != null) {
            set.add(new Pair(curr.val + curr.next.val, curr.id, curr, curr.next));
            if (curr.val > curr.next.val) {
                violations++;
            }
            curr = curr.next;
        }

        int operations = 0;
        while (violations > 0) {
            Pair p = set.first();
            Node L = p.left;
            Node R = p.right;

            set.remove(p);

            Node prev = L.prev;
            Node next = R.next;

            if (prev != null) {
                set.remove(new Pair(prev.val + L.val, prev.id, prev, L));
            }
            if (next != null) {
                set.remove(new Pair(R.val + next.val, R.id, R, next));
            }

            if (prev != null && prev.val > L.val) {
                violations--;
            }
            if (L.val > R.val) {
                violations--;
            }
            if (next != null && R.val > next.val) {
                violations--;
            }

            long newVal = L.val + R.val;
            Node newNode = new Node(newVal, L.id);

            newNode.prev = prev;
            newNode.next = next;
            if (prev != null) {
                prev.next = newNode;
            }
            if (next != null) {
                next.prev = newNode;
            }

            if (prev != null) {
                set.add(new Pair(prev.val + newNode.val, prev.id, prev, newNode));
            }
            if (next != null) {
                set.add(new Pair(newNode.val + next.val, newNode.id, newNode, next));
            }

            if (prev != null && prev.val > newNode.val) {
                violations++;
            }
            if (next != null && newNode.val > next.val) {
                violations++;
            }

            operations++;
        }
        return operations;
    }
}
