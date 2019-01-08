package adtcommon;

public abstract class NodeBase<T> {
    private T data;

    public NodeBase() {}

    public NodeBase(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
