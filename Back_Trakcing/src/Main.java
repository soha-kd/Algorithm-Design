import java.util.Scanner;

public class Main {
    public static void main(String arg[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter number of items you want to add : ");
        int num = scanner.nextInt();
        int[] pro = new int[num];
        int[] wei = new int[num];
        for (int i = 0; i < num; i++) {
            System.out.println("enter profit of item : ");
            pro[i] = scanner.nextInt();
            System.out.println("enter weight : ");
            wei[i] = scanner.nextInt();
        }
        System.out.println("total weight of knapsack : ");
        int total_w = scanner.nextInt();
        Knap_Sack.knapSack(total_w, wei, pro, num);
    }
}
