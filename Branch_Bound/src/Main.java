import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter number of items you want to add : ");
        KnapSack.num = scanner.nextInt();
        Item items[] = new Item[KnapSack.num];

        int pro;
        int wei;
        int[] pro_p = new int[KnapSack.num];
        for (int i = 0; i < KnapSack.num ; i++) {
            System.out.println("enter profit of items : ");
            pro = scanner.nextInt();
            pro_p[i] = pro;
            System.out.println("enter weight of items : ");
            wei = scanner.nextInt();
            items[i] = new Item(pro , wei , i);
        }
        System.out.println("enter total weight of knapsack : ");
        KnapSack.total_wei = scanner.nextInt();

        int z = 0 , x ;
        while(z < KnapSack.num){
            x = z +1;
            while(x < KnapSack.num){
                System.out.println("calculate each state values before getting answer : ");
                System.out.println(pro_p[z] + "+" + pro_p[x] + "=" + (pro_p[z] + pro_p[x]));
                x++;
            }
            z++;
        }
        KnapSack.solve(items);
    }
}

