import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

    class  Item {
        int wei;
        int pro;
        int count;
        public Item(int pro, int wei, int count) {
            this.pro = pro;
            this.wei = wei;
            this.count = count;
        }
    }
    class order implements Comparator<Node> {
        public int compare(Node n1, Node n2) {
            boolean ans = n1.WC > n2.WC;
            return ans? 1 : -1;
        }
    }

    class order_r implements Comparator<Item> {
        public int compare(Item n1, Item n2) {
            boolean ans = (float) n1.pro / n1.wei > (float) n2.pro / n2.wei;
            return ans ? -1 : 1;
        }
    }
    class Node {
        public Node() {
        }
        float BC;
        float WC;
        int depth;
        boolean flag;
        float t_pro;
        float t_wei;

        public Node(Node node) {
            this.t_pro = node.t_pro;
            this.t_wei = node.t_wei;
            this.BC = node.BC;
            this.WC = node.WC;
            this.depth = node.depth;
            this.flag = node.flag;
        }
    }

    class KnapSack {
        static int num;
        static float total_wei;

        static float BC_(float t_pro, float t_wei, int coun, Item de[]) {
            float pro = t_pro;
            float weight = t_wei;
            for (int i = coun; i < num; i++) {
                if (weight + de[i].wei <= total_wei) {
                    weight = weight + de[i].wei;
                    pro = pro - de[i].pro;
                } else {
                    pro = pro - (total_wei - weight) / de[i].wei * de[i].pro;
                    break;
                }
            }
            return pro;
        }

        static float WC_(float t_pro, float t_wei, int coun, Item de[]) {
            float pro = t_pro;
            float weight = t_wei;
            for (int i = coun; i < num; i++) {
                if (weight + de[i].wei <= total_wei) {
                    weight = weight + de[i].wei;
                    pro = pro - de[i].pro;
                } else {
                    break;
                }
            }
            return pro;
        }

        static void dedic(Node a, float BC, float WC, int depth, boolean flag, float t_pro, float total_wei) {
            a.BC = BC;
            a.WC = WC;
            a.depth = depth;
            a.flag = flag;
            a.t_pro = t_pro;
            a.t_wei = total_wei;
        }

        public static void solve(Item item_k[]) {
            Arrays.sort(item_k, new order_r());
            Node p_item, l_item, r_item;
            p_item = new Node();
            l_item = new Node();
            r_item = new Node();
            float minimum_o = 0, final_o = Integer.MAX_VALUE;
            p_item.t_pro = p_item.t_wei = p_item.BC = p_item.WC = 0;
            p_item.depth = 0;
            p_item.flag = false;

            PriorityQueue<Node> q1 = new PriorityQueue<Node>(new order());
            q1.add(p_item);

            boolean p_val[] = new boolean[num];
            boolean f_val[] = new boolean[num];

            while (!q1.isEmpty()) {
                p_item = q1.poll();
                if (p_item.BC > minimum_o || p_item.BC >= final_o) {
                    continue;
                }
                if (p_item.depth != 0)
                    p_val[p_item.depth - 1] = p_item.flag;
                if (p_item.depth == num) {
                    if (p_item.WC < final_o) {
                        for (int i = 0; i < num; i++)
                            f_val[item_k[i].count] = p_val[i];
                        final_o = p_item.WC;
                    }
                    continue;
                }

                int ord = p_item.depth;

                dedic(r_item, BC_(p_item.t_pro, p_item.t_wei, ord + 1, item_k), WC_(p_item.t_pro, p_item.t_wei, ord + 1, item_k), ord + 1, false, p_item.t_pro, p_item.t_wei);
                if (p_item.t_wei + item_k[p_item.depth].wei <= total_wei) {
                    l_item.BC = BC_(p_item.t_pro - item_k[ord].pro, p_item.t_wei + item_k[ord].wei, ord + 1, item_k);
                    l_item.WC = WC_(p_item.t_pro - item_k[ord].pro, p_item.t_wei + item_k[ord].wei, ord + 1, item_k);
                    dedic(l_item, l_item.BC, l_item.WC, ord + 1, true, p_item.t_pro - item_k[ord].pro, p_item.t_wei + item_k[ord].wei);
                } else {
                    l_item.BC = l_item.WC = 1;
                }
                minimum_o = Math.min(minimum_o, l_item.WC);
                minimum_o = Math.min(minimum_o, r_item.WC);

                if (minimum_o >= l_item.BC)
                    q1.add(new Node(l_item));
                if (minimum_o >= r_item.BC)
                    q1.add(new Node(r_item));
            }
            System.out.println("\n" + "best profit : " + (-final_o));
        }
    }
