
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
        }int collectEvens(int[] evens) {
            int[] idx = {0};
            collectEvensHelper(root, evens, idx);
            return idx[0];
        }

        private void collectEvensHelper(tNode node, int[] evens, int[] idx) {
            if (node == null) return;
            collectEvensHelper(node.left, evens, idx);
            if (node.key % 2 == 0) {
                evens[idx[0]++] = node.key;
            }
            collectEvensHelper(node.right, evens, idx);
        }

        // Clear the tree
        void clear() {
            root = null;
        }
    }static void populateBST(BST tree, int lo, int hi) {
        if (lo > hi) return;
        int mid = lo + (hi - lo) / 2;
        tree.insert(mid);
        populateBST(tree, lo, mid - 1);
        populateBST(tree, mid + 1, hi);
    } static void removeEvens(BST tree, int totalNodes) {
        // Collect all even keys first to avoid modifying tree during traversal
        int[] evens = new int[totalNodes / 2 + 1];
        int count = tree.collectEvens(evens);
        for (int i = 0; i < count; i++) {
            tree.delete(evens[i]);
        }
    }static double mean(long[] times) {
        double sum = 0;
        for (long t : times) sum += t;
        return sum / times.length;
    }

    static double stddev(long[] times, double avg) {
        double sum = 0;
        for (long t : times) {
            double diff = t - avg;
            sum += diff * diff;
        }
        return Math.sqrt(sum / times.length);
    }





