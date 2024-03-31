/** 采用循环结构实现双端队列*/
public class LinkedListDeque<T> implements Deque<T> {
    private DNode sentinel;
    private int size;
    public class DNode {
        T first;
        DNode next;
        DNode prev;

        public DNode(T item) {
            this.first = item;
            this.next = null;
            this.prev = null;
        }

        public DNode() {
            this.next = null;
            this.prev = null;
        }
    }

    /** 创建一个空的双端队列*/
    public LinkedListDeque() {
        sentinel = new DNode();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    private T getRecursive(int index, DNode p) {
        if (index == 0) {
            return p.first;
        }
        return getRecursive(index - 1, p.next);
    }
    /** 用递归的方法获取index处的元素*/
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }

    /** 在双端队列头部加入元素*/
    @Override
    public void addFirst(T item) {
        DNode p = new DNode(item);
        p.next = sentinel.next;
        p.prev = sentinel;
        sentinel.next.prev = p;
        sentinel.next = p;
        size++;
    }

    /** 在双端队列的尾部加入元素*/
    @Override
    public void addLast(T item) {
        DNode p = new DNode(item);
        p.prev = sentinel.prev;
        sentinel.prev.next = p;
        sentinel.prev = p;
        p.next = sentinel;
        size++;
    }

    /** 判断双端队列是否为空*/
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** 返回双端队列的长度*/
    @Override
    public int size() {
        return size;
    }

    /** 打印双端队列*/
    @Override
    public void printDeque() {
        DNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.first + " ");
            p = p.next;
        }
    }

    /** 移除并返回双端队列的头部元素*/
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T res = sentinel.next.first;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return res;
    }

    /** 移除并返回双端队列的尾部元素*/
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T res = sentinel.prev.first;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return res;
    }

    /** 用迭代的方式获取index处的元素*/
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        DNode p = sentinel.next;
        //前面的判断条件保证了index一定是在链表中的
        while (index > 0) {
            p = p.next;
            index--;
        }
        return p.first;
    }
}
