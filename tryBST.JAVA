
// CSC 211 - Practical 7 - Binary Search Tree
// Consulted: Claude for structure and timing advice
// Author:Mosa Nkuna 4446478 
// Date: 25 March 2026
// Constructs a perfect balanced BST with integers [1..2^n - 1],


public class tryBST {

    static class tNode {
        int key;
        tNode left, right, parent;

        tNode(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
    static class BST {
                        tNode root;

                        BST() {
                            root = null;
                        }

                        // Standard BST insert
                        void insert(int key) {
                            tNode newNode = new tNode(key);
                            if (root == null) {
                                root = newNode;
                                return;
                            }
                            tNode current = root;
                            while (true) {
                                if (key < current.key) {
                                    if (current.left == null) {
                                        current.left = newNode;
                                        newNode.parent = current;
                                        return;
                                        current = current.left;
                } else if (key > current.key) {
                    if (current.right == null) {
                        current.right = newNode;
                        newNode.parent = current;
                        return;
                    }
