class Spreadsheet {
private:
    vector<vector<int>> grid;
    int rows;

    int parseOperand(const string& s) {
        if (s[0] >= 'A' && s[0] <= 'Z') {
            int colIndex = s[0] - 'A';
            int rowIndex = stoi(s.substr(1)) - 1;
            return grid[rowIndex][colIndex];
        } else {
            return stoi(s);
        }
    }
    //yo, why are we coding spreadsheets in leetcode? T-T

public:
    Spreadsheet(int rows) : rows(rows) {
        grid.resize(rows, vector<int>(26, 0));
    }
    
    void setCell(string cell, int value) {
        int colIndex = cell[0] - 'A';
        int rowIndex = stoi(cell.substr(1)) - 1;
        grid[rowIndex][colIndex] = value;
    }
    
    void resetCell(string cell) {
        int colIndex = cell[0] - 'A';
        int rowIndex = stoi(cell.substr(1)) - 1;
        grid[rowIndex][colIndex] = 0;
    }
    
    int getValue(string formula) {
        string expr = formula.substr(1);
        size_t plusPos = expr.find('+');
        string op1 = expr.substr(0, plusPos);
        string op2 = expr.substr(plusPos + 1);
        return parseOperand(op1) + parseOperand(op2);
    }
};

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet* obj = new Spreadsheet(rows);
 * obj->setCell(cell,value);
 * obj->resetCell(cell);
 * int param_3 = obj->getValue(formula);
 */
