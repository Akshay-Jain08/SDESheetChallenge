/**
 * Problem: LRU Cache Implementation
 * Link: https://leetcode.com/problems/lru-cache/
 * 
 * Time Complexity: O(1) Constant runtime efficiency for both get() and put() calls.
 * Space Complexity: O(N) Linear capacity storage balancing node wrappers and map structures.
 */

import java.util.HashMap;

class LRUCache {

    // TC - O(1) & SC - O(N)
    // To implement LRU, we use a Map and a doubly linked list
    // Map is used to map the key to its respective node
    // List is used to keep the order preserved for identifying the least recently used element
    // Closer the element is to the head of list, most is it used recently
    public class Node {
        int key;
        int value;
        Node next;
        Node prev;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    HashMap<Integer, Node> map;
    int capacity;

    // We use dummy head and tail to easily perform addition and removal of the nodes
    Node head;
    Node tail;

    // Constructor to initialize the members
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;

        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node node = map.get(key);

        // If the current key doesn't exist in the cache, return -1
        if (node == null) {
            return -1;
        }

        // If it does, move node to MRU position
        deleteNode(node);
        insertAfterHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        Node node = map.get(key);

        // If the current key doesn't exist in the cache,
        // we have to make a choice out of two
        if (node == null) {
            // If the capacity is full, then remove the least recently used element
            // which will always be the node just before the tail
            if (map.size() == capacity) {
                map.remove(tail.prev.key);
                deleteNode(tail.prev);
            }

            // We gotta add the current node to the list regardless if the list
            // was full or not (we handled it already)
            // Insert the current node at the position after head, also update it to the map
            Node temp = new Node(key, value);
            insertAfterHead(temp);
            map.put(key, temp);
        }

        // If the current key already exists in the cache
        // we must update its value and place it after head in the list
        else {
            node.value = value;
            deleteNode(node);
            insertAfterHead(node);
        }
    }

    // deleteNode method which removes the particular node from the list
    public void deleteNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        // Link the neighbours of the current node, essentially removing
        // it from the list
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    // insertAfterHead method used to add the MRU to the list
    public void insertAfterHead(Node node) {
        Node nextNode = head.next;

        head.next = node;
        node.prev = head;

        node.next = nextNode;
        nextNode.prev = node;
    }
}
