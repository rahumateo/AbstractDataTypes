package adtcommon;

public abstract class TreeBase<T extends TreeNodeBase> {
    protected T root;

    public T getRoot() {
        return root;
    }
}
