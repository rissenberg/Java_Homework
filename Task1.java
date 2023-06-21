/*

10)     Метод получает на вход массив и возвращает новый массив, в котором
        находятся только трехзначные элементы исходного.

*/
public class Task1 {
    public static int[] work(int [] mas){
        int [] res = new int[0];
        for (int ma : mas) {
            if (ma / 100 != 0 && ma / 1000 == 0){
                int [] tmp = new int[res.length + 1];
                System.arraycopy(res, 0, tmp, 0, res.length);
                tmp[res.length] = ma;
                res = new int[tmp.length];
                System.arraycopy(tmp, 0, res, 0, tmp.length);
            }
        }
        return res;
    }
}
