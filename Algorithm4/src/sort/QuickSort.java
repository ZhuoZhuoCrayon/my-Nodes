package sort;

/**
 * @Classname QuickSort
 * @Description 快排实现
 * @Date 2020/3/3 23:20
 * @Created by Crayon
 */
public class QuickSort {
    public QuickSort(int[] data){
        sort(data, 0, data.length - 1);
    }
    public void sort(int[] data, int left, int right){
        if(left >= right) return;

        int index = setPosition(data, left, right);
        sort(data, left, index - 1);
        sort(data, index + 1, right);
    }
    public int setPosition(int[] data, int left, int right){
        int tmp = data[left];
        while(left < right){
            while(left < right && tmp <= data[right]){
                right--;
            }
            if(left < right) data[left++] = data[right];
            while(left < right && tmp > data[left]){
                left++;
            }
            if(left < right) data[right--] = data[left];
        }
        data[left] = tmp;
        return left;
    }
    public static void main(String[] args) {
        int length = 15;
        int[] data = {1, 5, 6, 7, 77, 8, 10, 7, 65, 100, -1, 15, 13, 189, 1024};
        QuickSort quickSort = new QuickSort(data);
        for(int e : data){
            System.out.print(e + ",");
        }
        System.out.println();
    }
}
