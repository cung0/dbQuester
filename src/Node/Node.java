package Node.src.Node;

public abstract class Node{
    protected final Main f;

    public Node(Main main) {
        this.f = main;
    }
    public abstract boolean validate();
    public abstract int execute();
}
