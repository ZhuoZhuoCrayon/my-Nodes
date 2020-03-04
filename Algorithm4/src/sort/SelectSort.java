package sort;

/**
 * @Classname SelectSort
 * @Description 选择排序实现
 * @Date 2020/3/5 0:13
 * @Created by Crayon
 */
public class SelectSort {
    public void sort(int[] data, int length){
        for(int i = 0; i < length - 1; i++){
            int index = i;
            for(int j = i + 1; j < length; j++){
                if(data[j] < data[index]){
                    index = j;
                }
            }
            int tmp = data[index];
            data[index] = data[i];
            data[i] = tmp;
        }
    }
    public static void main(String[] args) {
        int length = 15;
        int[] data = {1, 5, 6, 7, 77, 8, 10, 7, 65, 100, -1, 15, 13, 189, 1024};
        SelectSort selectSort = new SelectSort();
        selectSort.sort(data, length);
        for(int e : data){
            System.out.print(e + ",");
        }
        System.out.println();
    }
}
