package gc.root;

public class LocalVariableTableGc {
    public static void main(String[] args) {
//        localVar1();
//        localVar2();
//        localVar3();
//        localVar4();
        localVar5();
    }

    // 以下所有方法都以printGC参数的结果来分析，不要受freeMemory API的值影响
    // 使用-XX:+ DisableExplicitGC来禁止RMI调用System.gc产生minor gc,full gc,它是否会整理整个堆视不同的收集器行为不一样
    // -Xms10m -Xmx10m -XX:+PrintGC
    public static void localVar1() {
        // 调用gc方法后此块内存没有被回收，因为还在作用域内. a是gc root(虚拟机栈中的本地变量)，在这个作用域内这块new byte[6 * 1024 * 1024]内存与a并没有断开连接
        byte[] a = new byte[6 * 1024 * 1024];
        System.gc();
    }

    public static void localVar2() {
        // 调用gc方法后此块内存被回收，因为没有引用了，就是与GC root:a没有联系了，断开了连接关系
        byte[] a = new byte[6 * 1024 * 1024];
        a = null;
        System.gc();
    }

    public static void localVar3() {
        System.out.println(Runtime.getRuntime().freeMemory());
        // 调用gc方法后此块内存没有被回收，因为在Java中是引用作用域，GC root:a对这块内存还有引用，存在联系，所以没法回收。
        // GC root结点可以是栈中的对象，a就是这样一种结点
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        System.gc();
        System.out.println(Runtime.getRuntime().freeMemory());
    }

    public static void localVar4() {
        System.out.println(Runtime.getRuntime().freeMemory());
        // 局部变量表中b复用了a的槽位
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        int b = 10;
        System.gc();
        System.out.println(Runtime.getRuntime().freeMemory());
    }

    public static void localVar5() {
        // 栈桢调用完了，localVar1栈桢被销毁，自然包括里面的所有局部变量，这样byte数组就没有引用了，这时候调用gc就能回收掉这块内存
        localVar1();
        System.gc();
    }

}
