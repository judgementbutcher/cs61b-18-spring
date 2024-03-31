public class ArrayDeque<T> {

    private  int capability;
    private int size;
    private T [] arr;
    private int first;
    private int last;



    public ArrayDeque() {
        arr = (T []) new Object[8];
        capability = 8;
        size = 0;
        first = 0;
        last = 0;
    }

    private int addOne(int index) {
        //如果用capability会导致数组越界
        if(index == capability - 1){
            return 0;
        }
        return index + 1;
    }

    private int minusOne(int index) {
        if (index == 0) {
            return capability - 1;
        }
        return index - 1;
    }

    /** resize 和 shrink 不能直接使用一个arraycopy，因为可能会出现到达结尾然后取模到最开始的情况*/

    //可以采用二分的方式，每次到大小了就申请两倍,然后将元素从新数组的开始位置排列
    private void resize() {
        T [] res = (T []) new Object[2 * capability];
        for (int i = 0, j = first; j != last; j = addOne(j), i++) {
            res[i] = arr[j];
        }
        arr = res;
        capability *= 2;
        first = 0;
        last = size;
    }

    //当我们发现冗余空间太多的时候就需要缩小数组
    private void shrink(){
        while ((double) size / capability < 0.3) {
            capability -= 1;
        }
        T [] ans = (T []) new Object[capability];
        for (int i = 0, j = first; j != last; j = addOne(j), i++) {
            ans[i] = arr[j];
        }
        arr = ans;
        first = 0;
        last = size;
    }

    private boolean waste(){
        return (double) size / capability >= 0.3;
    }

    public void addFirst(T item) {
        if (size == capability) {
            resize();
        }
        first = minusOne(first);
        arr[first] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == capability) {
            resize();
        }
        arr[last] = item;
        last = addOne(last);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = first; i != last; i = addOne(i)) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //在remove函数中，我们要时刻检查usage的比率，如果过大，就需要释放一些空间
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T res = arr[first];
        first = addOne(first);
        size--;
        if (waste()) {
            shrink();
        }
        return res;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        last = minusOne(last);
        T res = arr[last];
        size--;
        if (waste()) {
            shrink();
        }
        return res;
    }

    public T get(int index) {
        if(index < 0 || index >= size){
            return null;
        }
        //确保下标是在数组范围内的
        return arr[first + index % capability];
    }

}
