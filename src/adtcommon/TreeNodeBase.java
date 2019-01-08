package adtcommon;

public abstract class TreeNodeBase<N extends TreeNodeBase<N, T>, T> extends NodeBase<T> {
    private N leftChild;
    private N rightChild;

    public TreeNodeBase(T data) {
        super(data);
    }

    public void setLeftChild(N node) {
        leftChild = node;
    }

    public N getLeftChild() {
        return leftChild;
    }

    public void setRightChild(N node) {
        rightChild = node;
    }

    public N getRightChild() {
        return rightChild;
    }
}
