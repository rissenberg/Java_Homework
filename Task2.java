
/*
2)  Метод принимает на вход массив из вещественных чисел. Метод
    возвращает сумму только тех элементов массива, четность которых
    совпадает с четностью большинства.
*/

// Данное задание сформулировано некорректно, поскольку понятие четности применимо только к целым числам
public class Task2 {
    public static double[] work(double[] mas) {
        int even = 0; int noteven = 0;
        for (double ma : mas) {
            if (ma % 2 == 0)
                even += 1;
            else
                noteven += 1;
        }
        double [] res = new double[0];
        for (double ma : mas) {
            if(even < noteven && ma % 2 != 0 || even > noteven && ma % 2 == 0){
                double [] tmp = new double[res.length + 1];
                System.arraycopy(res, 0, tmp, 0, res.length);
                tmp[res.length] = ma;
                res = new double[tmp.length];
                System.arraycopy(tmp, 0, res, 0, tmp.length);
            }

        }
        return res;
    }
}
