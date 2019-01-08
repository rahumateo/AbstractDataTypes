package avltree;

import adtcommon.TreeBase;
import adtcommon.TreeNodeBase;

import java.util.ArrayList;
import java.util.List;

public class AVLTree extends TreeBase<AVLNode> {
    /* With the property of:
     * Let:
     * - Hr is the height of the right child
     * - Hl is the height of the left child
     * then for every node, Hr is equals to Hl or Hl + 1
     * */

    public int getTreeHeight() {
        if (root == null) {
            return 0;
        }
        return root.height;
    }

    public void insertNode(int newData) {
        root = insert(root, newData);
    }

    private AVLNode insert(AVLNode node, int newData) throws IllegalStateException {
        if (node == null) {
            AVLNode newNode = new AVLNode(newData);
            if (root == null) {
                root = newNode;
            }
            return newNode;
        }

        if (newData < node.getData()) {
            node.setLeftChild(insert(node.getLeftChild(), newData));
        } else if (newData > node.getData()) {
            node.setRightChild(insert(node.getRightChild(), newData));
        } else {
            throw new IllegalStateException("Duplication is not allowed");
        }

        node.height = 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));

        int heightDiff = getHeightDiff(node);

        if (heightDiff > 1) {
            if (newData < node.getLeftChild().getData()) {
                return rotateRight(node);
            } else {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
                return rotateRight(node);
            }
        } else if (heightDiff < -1) {
            if (newData > node.getRightChild().getData()) {
                return rotateLeft(node);
            } else {
                node.setRightChild(rotateRight(node.getRightChild()));
                return rotateLeft(node);
            }
        }

        // case: balanced tree
        return node;
    }

    /* Auxiliary functions */
    private int getHeightDiff(AVLNode aNode) {
        if (aNode == null) {
            return 0;
        }
        return getHeight(aNode.getLeftChild()) - getHeight(aNode.getRightChild());
    }

    private int getHeight(AVLNode aNode) {
        if (aNode == null) {
            return 0;
        }
        return aNode.height;
    }

    private AVLNode rotateLeft(AVLNode aNode) {
        /*
        *          aNode
        *              \
        *              right
        *             /    \
        *      rightLeft   rightRight
        * */
        AVLNode right = aNode.getRightChild();
        AVLNode rightLeft = right.getLeftChild();

        // rotate
        right.setLeftChild(aNode);
        aNode.setRightChild(rightLeft);
        /*
         *           right
         *         /       \
         *     aNode      rightRight
         *         \
         *      rightLeft
         * */

        // update height
        // since we update the child for aNode and right, we need to update the height as well
        updateHeight(aNode);
        updateHeight(right);

        // return the new parent
        return right;
    }

    private AVLNode rotateRight(AVLNode aNode) {
        /*
         *               aNode
         *             /
         *          left
         *        /     \
         *  leftLeft    leftRight
         * */
        AVLNode left = aNode.getLeftChild();
        AVLNode leftRight = left.getRightChild();

        // rotate
        /*
         *          left
         *        /      \
         *  leftLeft      aNode
         *               /
         *          leftRight
         * */
        aNode.setLeftChild(leftRight);
        left.setRightChild(aNode);

        // update height
        // since we update the child for aNode and left, we need to update the height as well
        updateHeight(aNode);
        updateHeight(left);

        // return the new parent
        return left;
    }

    private void updateHeight(AVLNode aNode) {
        aNode.height = 1 + Math.max(
                getHeight(aNode.getLeftChild()),
                getHeight(aNode.getRightChild()));
    }

    public List<Integer> getInOrderTraversal(AVLNode aNode) {
        List<Integer> traversalList = new ArrayList<>();
        doInOrderTraversal(traversalList, aNode);
        return traversalList;
    }

    private void doInOrderTraversal(List<Integer> list, AVLNode node) {
        if (node == null) {
            return;
        }
        doInOrderTraversal(list, node.getLeftChild());
        list.add(node.getData());
        doInOrderTraversal(list, node.getRightChild());
    }
}

class AVLNode extends TreeNodeBase<AVLNode, Integer> {
    int height;

    AVLNode(Integer d) {
        super(d);
        height = 1;
    }
}