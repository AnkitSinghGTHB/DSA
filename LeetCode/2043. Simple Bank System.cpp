class Bank {
private:
    vector<long long> accounts;
    int n;

public:
    Bank(vector<long long>& balance) {
        accounts = balance;
        n = balance.size();
    }
    
    bool transfer(int account1, int account2, long long money) {
        // Check if both accounts are valid
        if (account1 < 1 || account1 > n || account2 < 1 || account2 > n) {
            return false;
        }
        
        // Check if account1 has enough balance
        if (accounts[account1 - 1] < money) {
            return false;
        }
        
        // Perform transfer
        accounts[account1 - 1] -= money;
        accounts[account2 - 1] += money;
        return true;
    }
    
    bool deposit(int account, long long money) {
        // Check if account is valid
        if (account < 1 || account > n) {
            return false;
        }
        
        // Perform deposit
        accounts[account - 1] += money;
        return true;
    }
    
    bool withdraw(int account, long long money) {
        // Check if account is valid
        if (account < 1 || account > n) {
            return false;
        }
        
        // Check if account has enough balance
        if (accounts[account - 1] < money) {
            return false;
        }
        
        // Perform withdrawal
        accounts[account - 1] -= money;
        return true;
    }
};

/**
 * Your Bank object will be instantiated and called as such:
 * Bank* obj = new Bank(balance);
 * bool param_1 = obj->transfer(account1,account2,money);
 * bool param_2 = obj->deposit(account,money);
 * bool param_3 = obj->withdraw(account,money);
 */
