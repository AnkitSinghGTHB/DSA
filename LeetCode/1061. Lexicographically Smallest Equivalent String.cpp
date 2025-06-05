class Solution {
public:
    //let me define helper funcs
    int find(int x, vector<int>& parent){
        //did u know that? we use & for referencing, hence we dont pass
        //the whole vector, and just its pointer, which significantly helps
        //with performance. idkman, just felt like writing it here lol
        if(parent[x]!=x){
            parent[x]=find(parent[x],parent);//this is the path compression
        }
        return parent[x];
    }
    void unionsets(int x,int y, vector<int>& parent){
        int px=find(x,parent);
        int py=find(y,parent);
        if (px==py){return;}
        if(px<py){
            parent[py]=px;
        }
        else{
            parent[px]=py;
        }
    }
    string smallestEquivalentString(string s1, string s2, string baseStr) {
        //this goes above my head tbh, should i implement graph here?
        //or maybe i could use unionfind with path compression
        //so i would first init chars a to z as its own parent
        // then for each pair (s1[i],s2[i]), union their grps
        // and for each char c in basestr, i find its grp leader and use
        // that in the result, grp leader would technically be the smallest
        vector<int> parent(26);
        for(int i=0;i<26;i++){
            parent[i]=i;
        }
        for(int i=0;i<s1.size();i++){
            unionsets(s1[i]-'a',s2[i]-'a',parent);
        }
        string result="";
        for(char c:baseStr){
            int group=find(c-'a',parent);
            result+=(char)(group+'a');
        }
        return result;
    }
};
