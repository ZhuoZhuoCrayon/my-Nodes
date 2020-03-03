package sort;

/**
 * @Classname heapSort
 * @Description 堆排序
 * @Date 2020/3/2 22:34
 * @Created by Crayon
 */
public class heapSort {

    public heapSort(int[] data, int length){
        for(int i = length / 2; i >= 0; i--){
            this.sink(data, i, length);
        }
        while(length > 0){
            this.swap(data, 0, --length);
            this.sink(data, 0, length);
        }
    }

    public void swim(int[] data, int k, int length){
        int p = (k - 1) / 2;
        while(p >= 0 && data[p] < data[k]){
            this.swap(data, p, k);
            k = p;
            p = (k - 1) / 2;
        }
    }
    public void sink(int[] data, int k, int length){
        int c = 2 * k + 1;
        while(c < length){
            if(c < length - 1 && data[c] < data[c + 1]){
                c++;
            }
            if(data[c] > data[k]){
                this.swap(data, c, k);
                k = c;
                c = 2 * k + 1;
            }else{
                break;
            }
        }
    }
    public void swap(int[] data, int i, int j){
        data[i] = data[i] ^ data[j];
        data[j] = data[i] ^ data[j];
        data[i] = data[i] ^ data[j];
    }

    public static void main(String[] args) {
        int length = 15;
        int[] data = {1, 5, 6, 7, 77, 8, 10, 7, 65, 100, -1, 15, 13, 189, 1024};
        System.out.println(data.length);
        heapSort sort = new heapSort(data, length);
        for(int e : data){
            System.out.print(e + ",");
        }
        System.out.println();
    }
}
