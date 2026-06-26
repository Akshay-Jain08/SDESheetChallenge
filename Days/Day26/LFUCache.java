/**
 * Problem: LFU Cache Implementation
 * Link: https://leetcode.com/problems/lfu-cache/
 * 
 * Time Complexity: O(1) Absolute constant time scaling for both get() and put() calls.
 * Space Complexity: O(N) Linear capacity overhead partitioning objects into frequency buckets.
 */

import java.util.HashMap;
import java.util.Map;

class LFUCache {

    // TC - O(1) & SC - O(N)
    // This approach used a slightly different version from LRU to implement LFU Cache
    // We use Doubly Linked List and two maps - keyMap and freqMap in this

    // Template of a node for the list
    class Node {
        int key;
        int value;
        int freq;
        Node next;
        Node prev;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            freq = 1;
        }
    }

    // Doubly Linked List class
    // Similar to LRU, the list used here will also possess the 
    // MRU elements near to the head and LRU elements near to the tail
    class DLL {
        // head and tail are being used as dummy nodes to be able
        // to perform additional and removal of nodes
        Node head;
        Node tail;
        int size;

        // Constructor to nititalize the DLL class members
        public DLL() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            size = 0;

            head.next = tail;
            tail.prev = head;
        }

        // addFirst method to add a node at start of the list just after the head
        public void addFirst(Node node) {
            Node nextNode = head.next;

            head.next = node;
            node.prev = head;

            node.next = nextNode;
            nextNode.prev = node;

            size++;
        }

        // removeLast method to remove the node just before the tail
        public Node removeLast() {
            Node node = tail.prev;
            Node prevNode = node.prev;

            prevNode.next = tail;
            tail.prev = prevNode;

            size--;
            return node;
        }

        // remove method to remove any particular node
        public void remove(Node node) {
            Node prevNode = node.prev;
            Node nextNode = node.next;

            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            size--;
        }
    }

    // keyMap maps the keys to their respective nodes
    Map<Integer, Node> keyMap;
    // freqMap maps a particular frequency to the list of nodes with that frequency
    Map<Integer, DLL> freqMap;
    int minFreq;
    int capacity;

    // Constructor to initialize the class members
    public LFUCache(int capacity) {
        keyMap = new HashMap<>();
        freqMap = new HashMap<>();
        minFreq = 1;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Node node = keyMap.get(key);

        // If the key is not present in the cache, return -1
        if (node == null) {
            return -1;
        }

        // If it is present in the cache, remove it from the list of current frequency
        // and add it to the list of current freq + 1
        freqMap.get(node.freq).remove(node);

        // If after the removal of the current node, the current list becomes empty
        // and the current node was in the minFreq list, then increase the minFreq
        if (freqMap.get(node.freq).size == 0 && minFreq == node.freq) {
            minFreq++;
        }

        // Increase the frequency of current node
        node.freq++;

        // If the freqMap doesn't have any list associated with the updated freq
        // create a DLL for that frequency
        if (freqMap.get(node.freq) == null) {
            freqMap.put(node.freq, new DLL());
        }
        // add the node to the updated freq list
        freqMap.get(node.freq).addFirst(node);

        return node.value;
    }
    
    public void put(int key, int value) {
        Node node = keyMap.get(key);

        // If the current key is not present in the cache, then
        // we have to make a choice out of two
        if (node == null) {
            // If the cache is full, we need to remove the LFU element
            // In case, the freq is same, follow the LRU constraint
            if (keyMap.size() == capacity) {
                // remove the last element of the minFreq list, essentially removing 
                // the LRU element within the LFU elements
                Node removed = freqMap.get(minFreq).removeLast();
                // remove the node from the keyMap as well
                keyMap.remove(removed.key);
            }

            // Now that, we have handled the full cache, we can safely add the 
            // current node to the list
            Node temp = new Node(key, value);

            // If the freqMap doesn't have any list associated with the
            // newly made node (freq = 1), create a DLL for that frequency
            if (freqMap.get(temp.freq) == null) {
                freqMap.put(temp.freq, new DLL());
            }

            // always put minFreq to 1 after adding a new Node to the list
            minFreq = 1;
            // update the freqMap and keyMap
            freqMap.get(temp.freq).addFirst(temp);
            keyMap.put(key, temp);
        }

        // If the key is already present in the cache, we must update its value
        // and increase its frequency
        else {
            node.value = value;
            freqMap.get(node.freq).remove(node);

            // If after removing the current node from its previous freq list
            // the list gets empty and the previous freq was minFreq, increase the minFreq
            if (freqMap.get(node.freq).size == 0 && minFreq == node.freq) {
                minFreq++;
            }

            // Increase the freq of current element
            node.freq++;

            // If the current element doesn't have a list associated to it
            // in freqMap, create a DLL for that frequency
            if (freqMap.get(node.freq) == null) {
                freqMap.put(node.freq, new DLL());
            }
            // update the freqMap
            freqMap.get(node.freq).addFirst(node);
        }
    }
}
