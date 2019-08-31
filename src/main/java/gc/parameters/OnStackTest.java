package gc.parameters;

public class OnStackTest {
    public static class User {
        long age = 0;
    }

    public static void alloc() {
        User user = new User();
        user.age = 18;
    }

    //栈上分配：jvm做的优化，就是把所有的没有逃逸（没有对外暴露的）的对象分配在栈上，随着栈桢（方法结束）的销毁而销毁，减少不必要的gc
    // -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-UseTLAB -XX:+EliminateAllocations
    //-XX:DoEscapeAnalysis开启逃逸分析，-XX:+EliminateAllocations开启标量替换（默认是开启的），栈上分配需要在server模式下，并且
    //逃逸分配和标量替换需要同时开启，否则不起作用
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        // 一对象16字节，包括8字节对象头，数据区为long age：8字节
        // 1000000对象->约16m
        for (int i = 0; i < 1000000 * 10; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        Thread.sleep(300000);
    }

    // todo Allocation Failure? 如何查看各个区的大小

}
