package class06;

public class Code01_Heap {

    private static class MyMaxHeap {
        private int[] arr;
        private int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            this.limit = limit;
            arr = new int[limit];
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void add(int value) {
            if (heapSize == limit) throw new RuntimeException("heap is full");
            arr[heapSize] = value;
            heapInsert(heapSize++);
        }

        public int poll() {
            if (heapSize == 0) throw new RuntimeException("heap is empty");
            int ans = arr[0];
            swap(0, --heapSize);
            heapify(0);
            return ans;
        }

        private void heapInsert(int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                largest = arr[index] > arr[largest] ? index : largest;
                if (largest == index) return;
                swap(largest, index);
                index = largest;
                left = index * 2 + 1;
            }
        }

        private void swap(int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

    }

    private static class RightMaxHeap {
        private int[] arr;
        private int limit;
        private int heapSize;

        public RightMaxHeap(int limit) {
            this.limit = limit;
            arr = new int[limit];
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (heapSize == limit) throw new RuntimeException("heap is full");
            arr[heapSize++] = value;
        }

        public int pop() {
            if (heapSize == 0) throw new RuntimeException("heap is empty");
            int maxIndex = 0;
            for (int i = 1; i < heapSize; i++) {
                if (arr[maxIndex] < arr[i]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            swap(maxIndex, --heapSize);
            return ans;
        }

        private void swap(int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

    }

    public static void main(String[] args) {

        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.add(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.poll() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.add(curValue);
                        test.push(curValue);
                    } else {
                        if (my.poll() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

    }

}
