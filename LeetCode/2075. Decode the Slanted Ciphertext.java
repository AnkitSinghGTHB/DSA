class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int len = encodedText.length();
        int cols = len / rows; // number of columns in the matrix
        
        //build the matrix row by row from encodedText
        char[][] mat = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = encodedText.charAt(i * cols + j);
            }
        }
        
        //read the original text by following the diagonal order
        StringBuilder sb = new StringBuilder();
        for (int startCol = 0; startCol < cols; startCol++) {
            for (int r = 0; r < rows; r++) {
                int c = startCol + r;
                if (c >= cols) break;
                sb.append(mat[r][c]);
            }
        }
        
        //remove trailing spaces (originalText has no trailing spaces)
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        
        return sb.toString();
    }
}
