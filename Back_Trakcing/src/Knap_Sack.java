public class Knap_Sack {
    public static void knapSack(int total_w, int wei[], int profit[], int count)
    {
        int k;
        int t_kn[][] = new int[count + 1][total_w + 1];
        int z = 0 , x ;
        while(z < count){
            x = z +1;
            while(x < count){
                System.out.println("calculate each state values before getting answer : ");
                System.out.println(profit[z] + "+" + profit[x] + "=" + (profit[z] +profit[x]));
                x++;
            }
            z++;
        }
        for (int i = 0; i <= count; i++) {
            for (k = 0; k <= total_w; k++) {
                if (i == 0 || k == 0)
                    t_kn[i][k] = 0;
                else if (wei[i - 1] <= k) {
                    t_kn[i][k] = Math.max(profit[i - 1] + t_kn[i - 1][k - wei[i - 1]], t_kn[i - 1][k]);
                }
                else
                    t_kn[i][k] = t_kn[i - 1][k];
            }
        }
        System.out.println("----------------------------------------------");
        System.out.println("best value : ");
        int detail = t_kn[count][total_w];
        System.out.println(detail);

        k  = total_w;
        System.out.println("weights of final items with best values : ");
        for (int i = count; i > 0 && detail > 0; i--) {
            if(detail != t_kn[i - 1][k]) {
                System.out.println(wei[i - 1] + " ");
                detail = detail - profit[i - 1];
                k = k - wei[i - 1];
            }
        }
    }
}
