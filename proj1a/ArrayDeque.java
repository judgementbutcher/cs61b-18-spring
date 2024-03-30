public class ArrayDeque <T>{

    private  int capability;
    private int size;
    private T [] arr;

    private int first;
    private int last;

    private double usage;


    public ArrayDeque(){
        arr = (T []) new Object[8];
        capability = 8;
        size = 0;
        first = 0;
        last = 0;
        usage = 0;
    }

    private int addOne(int index){
        return index + 1 % capability;
    }

    private int minusOne(int index){
        if(index == 0){
            return capability - 1;
        }
        return index - 1;
    }

    //可以采用二分的方式，每次到大小了就申请两倍
    private void resize(){
        T [] res = (T [])new Object[2 * capability];
        System.arraycopy(arr,first,res,first,size);
        arr = res;
        capability *= 2;
    }

    public void addFirst(T item){
        if(size == capability){
            resize();
        }
        first = minusOne(first);
        arr[first] = item;
        size++;
    }

    public void addLast(T item){
        if(size == capability){
            resize();
        }
        arr[last] = item;
        last = addOne(last);
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for(int i = first; i != last;i = (i + 1) % capability){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //在remove函数中，我们要时刻检查usage的比率，如果过大，就需要释放一些空间

    public T removeFirst(){
        if(size == 0){
            return null;
        }
        T res = arr[first];
        first = addOne(first);
        return res;
    }

    public T removeLast(){
        if(size == 0){
            return null;
        }
       last = minusOne(last);
        T res = arr[last];
        return res;
    }

    public T get(int index){
        return arr[first + index % capability];
    }

}
