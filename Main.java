public class Main {
    public static void main(String[] args){

        int [] ar1 = new int [] {20, 200, 2000};
        int [] res1 = Task1.work(ar1);
        for (int re : res1)
            System.out.print(Integer.toString(re) + ' ');
        System.out.println();

        double [] ar2 = new double[] {1, 2, 3, 4, 5};
        double [] res2 = Task2. work(ar2);
        for (double re : res2)
            System.out.print(Double.toString(re) + ' ');
    }
}
