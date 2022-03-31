package class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

//加强堆
public class HeapGreater<T> {

    private ArrayList<T> list;
    //反向索引表
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comp;

    public HeapGreater(Comparator<? super T> comp) {
        this.comp = comp;
        list = new ArrayList<>();
        heapSize = 0;
        indexMap = new HashMap<>();
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public void push(T obj) {
        list.add(obj);
        indexMap.put(obj, heapSize + 1);
        heapInsert(heapSize++);
    }

    public T pop() {
        T ans = list.get(0);
        swap(0, --heapSize);
        indexMap.remove(ans);
        list.remove(heapSize);
        heapify(0);
        return ans;
    }

    public void remove(T obj) {
        int index = indexMap.get(obj);
        T replace = list.get(heapSize - 1);
        list.remove(--heapSize);
        indexMap.remove(obj);
        if (replace != obj) {
            indexMap.put(replace, index);
            list.set(index, replace);
            resign(replace);
        }
    }

    private void heapInsert(int index) {
        while (comp.compare(list.get(index), list.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && comp.compare(list.get(left + 1), list.get(left)) < 0 ? left + 1 : left;
            largest = comp.compare(list.get(index), list.get(largest)) < 0 ? index : largest;
            if (largest == index) return;
            swap(largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    private void swap(int i, int j) {
        T oi = list.get(i);
        T oj = list.get(j);
        indexMap.put(oi, j);
        indexMap.put(oj, i);
        list.set(i, oj);
        list.set(j, oi);
    }

}
