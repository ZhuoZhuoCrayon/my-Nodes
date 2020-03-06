package sort;

/**
 * @Classname ShellSort
 * @Description 希尔排序实现
 * @Date 2020/3/6 21:13
 * @Created by Crayon
 */
public class ShellSort {
    public void sort(int[] data, int length){
        int dx = 1;
        while(dx < length / 3) dx = 3 * dx + 1;
        while(dx >= 1){
            for(int i = dx; i < length; i++){
                int key = data[i];
                int j = i - dx;
                while(j >= 0 && data[j] > key){
                    data[j + dx] = data[j];
                    j -= dx;
                }
                data[j + dx] = key;
            }
            dx = dx / 3;
        }
    }
    public static void main(String[] args) {
        int length = 10;
        int[] data = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        ShellSort shellSort = new ShellSort();
        shellSort.sort(data, length);
        for(int e : data){
            System.out.print(e + ",");
        }
        System.out.println();
    }
}
