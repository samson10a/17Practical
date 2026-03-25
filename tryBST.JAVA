
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
                    current = current.right;
                } else {
                    // Duplicate key — ignore
                    return;
                }
            }
        }
        // Search for a key; returns node or null
        tNode search(int key) {
            tNode current = root;
            while (current != null) {
                if (key == current.key) return current;
                current = (key < current.key) ? current.left : current.right;
            }
            return null;
        }// Find minimum node in a subtree
        tNode minimum(tNode node) {
            while (node.left != null) node = node.left;
            return node;
        } // Transplant: replace subtree rooted at u with subtree rooted at v
        void transplant(tNode u, tNode v) {
            if (u.parent == null) {
                root = v;
            } else if (u == u.parent.left) {
                u.parent.left = v;
            } else {
                u.parent.right = v;
            }
            if (v != null) {
                v.parent = u.parent;
            }
        }// Standard BST delete
        void delete(tNode z) {
            if (z == null) return;
            if (z.left == null) {
                transplant(z, z.right);
            } else if (z.right == null) {
                transplant(z, z.left);
            } else {
                tNode y = minimum(z.right);
                if (y.parent != z) {
                    transplant(y, y.right);
                    y.right = z.right;
                    y.right.parent = y;
                }
                transplant(z, y);
                y.left = z.left;
                y.left.parent = y;
            }
        }
        // Delete node by key
        void delete(int key) {
            delete(search(key));
        }

        // isBST: checks that every node satisfies BST properties
        boolean isBST() {
            return isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private boolean isBSTHelper(tNode node, int min, int max) {
            if (node == null) return true;
            if (node.key <= min || node.key >= max) return false;
            return isBSTHelper(node.left, min, node.key)
                && isBSTHelper(node.right, node.key, max);
        } // Count nodes
        int size() {
            return sizeHelper(root);
        }

        private int sizeHelper(tNode node) {
            if (node == null) return 0;
            return 1 + sizeHelper(node.left) + sizeHelper(node.right);
        }

        // Height of the tree
        int height() {
            return heightHelper(root);
        }

        private int heightHelper(tNode node) {
            if (node == null) return 0;
            return 1 + Math.max(heightHelper(node.left), heightHelper(node.right));
        }// In-order traversal (for debugging small trees)
        void inOrder() {
            inOrderHelper(root);
            System.out.println();
        }private void inOrderHelper(tNode node) {
            if (node == null) return;
            inOrderHelper(node.left);
            System.out.print(node.key + " ");
            inOrderHelper(node.right);
        }




