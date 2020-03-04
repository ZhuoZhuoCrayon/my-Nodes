package sort;

/**
 * @Classname BubbleSort
 * @Description 冒泡排序实现
 * @Date 2020/3/5 0:07
 * @Created by Crayon
 */
public class BubbleSort {
    public void sort(int[] data, int length){
        for(int i = 0; i < length - 1; i++){
            for(int j = 0; j < length - i - 1; j++){
                if(data[j] > data[j + 1]){
                    swap(data, j, j + 1);
                }
            }
        }
    }
    public void swap(int[] data, int i, int j){
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void main(String[] args) {
        int length = 15;
        int[] data = {1, 5, 6, 7, 77, 8, 10, 7, 65, 100, -1, 15, 13, 189, 1024};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(data, length);
        for(int e : data){
            System.out.print(e + ",");
        }
        System.out.println();
    }
}
