class Solution {
  private:
    stack<int> mainStack;
    stack<int> minStack;
  public:
    Solution() {
        //idts that we need to write anything here
    }

    // Add an element to the top of Stack
    void push(int x) {
        mainStack.push(x);
        if (minStack.empty() || x <= minStack.top()) {
            minStack.push(x);
        }
    }

    // Remove the top element from the Stack
        
    void pop() {
        if (mainStack.empty()) {
            return;
        }
        if (mainStack.top() == minStack.top()) {
            minStack.pop();
        }
        mainStack.pop();
    }

        
    // Returns top element of the Stack
    int peek() {
        if (mainStack.empty()) {
            return -1;
        }
        return mainStack.top();
    }
        

    // Finds minimum element of Stack
    int getMin() {
        if (minStack.empty()) {
            return -1;
        }
        return minStack.top();
    }
};
