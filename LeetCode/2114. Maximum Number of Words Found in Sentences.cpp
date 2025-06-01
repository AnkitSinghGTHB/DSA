class Solution {
public:
    int mostWordsFound(vector<string>& sentences) {
        //so O(n^2) a word counter and an array
        int n=sentences.size();
        int mxCount =0;
        for (int i=0;i<n;i++){
            int tCount=1;
            int k=sentences[i].size();
            if (k==0){
                continue;
            }
            for (int j=0;j<k;j++){
                if (sentences[i][j]==' '){
                    tCount++;
                }
            }
            mxCount=max(mxCount, tCount);
        }
        return mxCount;
    }
};
