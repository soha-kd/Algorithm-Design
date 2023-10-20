public class Node {
    int[] k;
    public Node l_child, r_child;

    public Node(int[] value)
    {
        k = value;
        r_child = null;
        l_child = null;
    }
}
