package sort;

/**
 * @Classname InsertSort
 * @Description 插入排序实现
 * @Date 2020/3/5 0:02
 * @Created by Crayon
 */
public class InsertSort {
    public void sort(int[] data, int length){
        for(int i = 1; i < length; i++){
            int key = data[i];
            int j = i - 1;
            while(j >= 0 && data[j] > key){
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int length = 15;
        int[] data = {1, 5, 6, 7, 77, 8, 10, 7, 65, 100, -1, 15, 13, 189, 1024};
        InsertSort insertSort = new InsertSort();
        insertSort.sort(data, length);
        for(int e : data){
            System.out.print(e + ",");
        }
        System.out.println();
    }
}
