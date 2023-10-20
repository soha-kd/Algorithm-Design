import java.util.Scanner;

public class SearchTree {
    public static Node tree_root = null;
    public static boolean is_found=false;

    static Node leave_Nodes(Node R, int[] key) {
        if (R == null) {
            R = new Node(key);
            return R;
        }
        if (R.k[0] > key[0] || (R.k[0] == key[0] &&  R.k[1] > key[1]))
            R.l_child = leave_Nodes(R.l_child, key);
        else if (R.k[0] < key[0] || (R.k[0] == key[0] && R.k[1] < key[1]))
            R.r_child = leave_Nodes(R.r_child, key);

        return R;
    }

    static void leave_Nodes(int[] num) {
        tree_root = leave_Nodes(tree_root, num);
    }

    static void search(Node node,int[] key){
        if(tree_root == null){
            System.out.println("tree is empty! ");
        }
        else{
            if(node.k[0] == key[0] && node.k[1] == key[1]){
                is_found = true;
                System.out.println("key : "+key[0]+","+key[1]);
                return;
            }
            if(!is_found && node.l_child != null){
                System.out.println(" key isn't"+node.k[0]+","+node.k[1]);
                System.out.println("going to search in left side of tree ");
                search(node.l_child,key);
            }
            if(!is_found && node.r_child != null){
                System.out.println(" key isn't "+node.k[0]+","+node.k[1]);
                System.out.println("going to search in right side of tree");
                search(node.r_child,key);
            }
        }
    }
    public static void main(String[] args) {
        int node;
        Scanner input = new Scanner(System.in);
        System.out.println("enter nodes' number : ");
        node = input.nextInt();

        System.out.println("enter values like : {number (space/enter) another number}");
        System.out.println("first pair of velues will be root.");
        for (int i=0 ; i<node ; i++){
            System.out.println("enter two values for " +"node " + i);
            SearchTree.leave_Nodes(new int[]{input.nextInt(), input.nextInt()});
        }
        System.out.println("enter key : ");
        SearchTree.search(SearchTree.tree_root,new int[]{input.nextInt(),input.nextInt()});

        if (SearchTree.is_found)
            System.out.println("found ! ");
        else
            System.out.println("not found ! ");
    }
}
