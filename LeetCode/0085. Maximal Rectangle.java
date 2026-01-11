class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols + 1]; //include an extra element for easier calculation
        int maxArea = 0;        
        for (int i = 0; i < rows; i++) {            
            for (int j = 0; j < cols; j++) {//update heights array for current row
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                } 
                else {
                    heights[j] = 0;
                }
            }
            Stack<Integer> stack = new Stack<>();//calculate maximum rectangle area for current histogram
            for (int j = 0; j <= cols; j++) {
                while (!stack.isEmpty() && heights[stack.peek()] > heights[j]) {
                    int height = heights[stack.pop()];
                    int width = stack.isEmpty() ? j : j - stack.peek() - 1;
                    maxArea = Math.max(maxArea, height * width);
                }
                stack.push(j);
            }
        }        
        return maxArea;
    }
}
