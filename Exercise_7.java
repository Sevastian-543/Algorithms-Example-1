public class Exercise_7 {

    // All of the indexing methods below (parent, left child,
    // right child, etc) are written for arrays which are
    // 1-based.  Rewrite each of these methods so that they
    // work for 0-based arrays.

    // Then implement the heapify method which builds a min
    // heap (in-place) for the array passed as a parameter.

    // Once you have your heapify method working, run your
    // program on random permutations of the integers 1..n
    // for various values of n to verify that its performance
    // is n lg n.

    public static void heapify(int[] a) {
        int l = a.length;
        // Turn this array into a binary heap
        for(int i = 0; i < l; i++){
            int current = i;
            while(!isRoot(current)){
                int p = parent(current);
                if(a[current] < a[p]){
                    swap(a, current, p);
                    current = p;
                }
                else break;
            }
        }
    }

    public static int parent(int node) {return ((node + 1) / 2 )-1;}

    public static int leftChild(int node) {
        return 2 * (node + 1);
    }

    public static int rightChild(int node) {
        return 2 * (node + 2);
    }

    public static boolean isRoot(int node) {
        return node == 0;
    }

    public static boolean isLeaf(int node, int size) {return node * 2 + 1 > size;}

    public static int numberKids(int index, int size) {
        int n = size - (2 * index);
        if (n < 0) {
            return 2;
        } else if (n > 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static int random(int n) {
        return (int) (n * Math.random());
    }

    public static int[] randomPermutation(int n) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = i+1;
        }
        for (int i = 0; i < n; i++) {
            int j = i + random(n-i);
            swap(result, i, j);
        }
        return result;
    }

    public static int[] getValues(String[] args) {
        int[] result = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            result[i] = Integer.parseInt(args[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a;
        if (args.length > 1) {
            a = getValues(args);
        } else if (args.length > 0) {
            a = randomPermutation(Integer.parseInt(args[0]));
        } else {
            a = randomPermutation(7);
        }

        for(int x: a) System.out.print(x + " ");
        System.out.println(" ");

        Timer timer = new Timer();
        heapify(a);
        double time = timer.elapsed();

        if (a.length < 100) {
            String separator = "";
            for (int i = 0; i < a.length; i++) {
                System.out.print(separator);
                System.out.print(a[i]);
                separator = " ";
            }
            System.out.println();
        } else {
            if (time > 0.1) {
                System.out.println(time + " sec");
            } else if (time > 0.0001) {
                System.out.println(1_000 * time + " msec");
            } else if (time > 0.0000001) {
                System.out.println(1_000_000 * time + "μsec");
            } else {
                System.out.println(1_000_000_000 * time + "nsec");
            }
        }
    }
}