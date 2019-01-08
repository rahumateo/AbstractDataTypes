package avltree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestAVLTree {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        testAVLTree();
        testRange();
    }

    public static void testAVLTree() {
        String control = "0 1 2 3 4 5 6 7 8 9";

        List<Integer> intList = Arrays.asList(new Integer[]{1, 4, 5, 7, 8, 3, 2, 9, 0, 6});
        AVLTree avlTree = initTree(intList);
        System.out.println(control.equals(getInOrderTraversal(avlTree)));

        intList = Arrays.asList(new Integer[]{0, 1, 2, 3 , 4 , 5, 6, 7, 8, 9});
        avlTree = initTree(intList);
        System.out.println(control.equals(getInOrderTraversal(avlTree)));

        intList = Arrays.asList(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0});
        avlTree = initTree(intList);
        System.out.println(control.equals(getInOrderTraversal(avlTree)));
    }

    private static void testRange() {
        List<Integer> intList = getListRangeForward(0, 100);
        AVLTree avlTree = initTree(intList);
        System.out.println(isSorted(avlTree.getInOrderTraversal(avlTree.getRoot())));

        intList = getListRangeForward(99, 0);
        avlTree = initTree(intList);
        System.out.println(isSorted(avlTree.getInOrderTraversal(avlTree.getRoot())));
    }

    private static List<Integer> getListRangeBackward(int start, int end) {
        List<Integer> intList = new ArrayList<>();
        for (int ii = start; ii >= end; ii--) {
            intList.add(ii);
        }
        return intList;
    }

    private static List<Integer> getListRangeForward(int start, int end) {
        List<Integer> intList = new ArrayList<>();
        for (int ii = start; ii <= end; ii++) {
            intList.add(ii);
        }
        return intList;
    }

    private static boolean isSorted(List<Integer> integerList) {
        for (int ii = 0; ii < integerList.size() - 1; ii++) {
            if (integerList.get(ii) > integerList.get(ii+1)) {
                return false;
            }
        }
        return true;
    }

    private static AVLTree initTree(List<Integer> integerList) {
        AVLTree avlTree = new AVLTree();
        for (Integer item : integerList) {
            avlTree.insertNode(item);
            AVLNode avlNode = avlTree.getRoot();
        }
        return avlTree;
    }

    private static String getInOrderTraversal(AVLTree avlTree) {
        List<Integer> traversal = avlTree.getInOrderTraversal(avlTree.getRoot());

        StringBuilder sb = new StringBuilder();
        for (Integer item : traversal) {
            sb.append(item).append(" ");
        }

        return sb.toString().trim();
    }
}
