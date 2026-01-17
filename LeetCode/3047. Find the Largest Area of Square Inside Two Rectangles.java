class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long maxArea = 0;        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                //compute intersection rectangle
                int left = Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                int right = Math.min(topRight[i][0], topRight[j][0]);
                int bottom = Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                int top = Math.min(topRight[i][1], topRight[j][1]);                
                if (left < right && bottom < top) {
                    //intersection exists
                    int width = right - left;
                    int height = top - bottom;
                    int side = Math.min(width, height);
                    maxArea = Math.max(maxArea, (long)side * side);
                }
            }
        }        
        return maxArea;
    }
}
