class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length;      // no. of rows
        int n = boxGrid[0].length;   // no. of columns
        //rotate 90deg clockwise
        char[][] rotated = new char[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][m - 1 - i] = boxGrid[i][j];
            }
        }
        //apply gravity to each column of the rotated box
        char[][] result = new char[n][m];
        //initialize result with '.'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = '.';
            }
        }
        for (int c = 0; c < m; c++) {          //each column of the rotated box
            int bottom = n - 1;                //next free row from the bottom
            for (int r = n - 1; r >= 0; r--) { //scan column from bottom to top
                char cell = rotated[r][c];
                if (cell == '*') {
                    result[r][c] = '*';        //obstacle stays
                    bottom = r - 1;            //stones cannot fall below it
                } 
                else if (cell == '#') {
                    result[bottom][c] = '#';   //stone falls to the current bottom
                    bottom--;
                }
                //'.' is ignored
            }
        }
        return result;
    }
}
