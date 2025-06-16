//did they forget to add the linkedlist struct?
#include <bits/stdc++.h>
using namespace std;
struct Node{
    int key;
    int value;
    Node *next;
    Node *prev;
    Node(int k, int v){
        key=k;
        value=v;
        next =nullptr;
        prev=nullptr;
    }
};
class LRUCache {
  private:
  public:
    //ok i had to go through the documentation and found
    //some interesting appraoch using a doubly linkedlist
    unordered_map<int,Node*> cacheMap;
    int capacity;
    Node *head;
    Node *tail;
    LRUCache(int capacity) {
        this->capacity=capacity;
        head=new Node(-1,-1);
        tail=new Node(-1,-1);
        head->next=tail;
        tail->prev=head; //doubly linkedlist
    }
    void remove(Node *node){
        Node *prevNode = node->prev;
        Node *nextNode = node->next;
        prevNode->next= nextNode;
        nextNode->prev=prevNode;
    }
    void add(Node *node){
        Node *nextNode=head->next;
        head->next= node;
        node->prev= head;
        node->next=nextNode;
        nextNode->prev=node;
    }
    int get(int key) {
        if (cacheMap.find(key)==cacheMap.end()){
            return-1;
        }
        Node *node=cacheMap[key];
        remove(node);//custom fn
        add(node);//custrom fn
        return node->value;
    }

        
    void put(int key, int value) {
        if(cacheMap.find(key)!=cacheMap.end()){
            Node *oldNode=cacheMap[key];
            remove(oldNode);
            delete oldNode;
        }
        Node *node=new Node(key,value);
        cacheMap[key]=node;
        add(node);
        if(cacheMap.size() >capacity){
            Node *nodeToDelete = tail->prev;
            remove(nodeToDelete);
            cacheMap.erase(nodeToDelete->key);
            delete nodeToDelete;
        }
    }
};
