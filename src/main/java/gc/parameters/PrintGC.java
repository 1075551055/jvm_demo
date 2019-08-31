package gc.parameters;

public class PrintGC {
    // 1. -XX:+PrintGC  打印gc信息，信息量简单
    // 2. -XX:+PrintGCDetails 打印gc信息，信息量详细，会在程序退出的时候把堆和非堆（方法区）的具体信息输出来
    // 3. -XX:+PrintHeapAtGC 会在每次GC的前后把堆信息打印出来，当然也包括方法区
    // 4. -XX:+PrintGCTimeStamps 会把GC发生的时间点打印出来，这个时间戳是从应用程序启动开始计算，这个参数可以帮忙看到GC的时间间隔
    // 5. -XX:+PrintGCApplicationConcurrentTime 打印应用程序执行时间 -XX:+PrintGCApplicationStoppedTime 打印应用程序由于GC而产生的停顿时间
    // 6. -XX:+PrintReferenceGC 打印四大引用具体GC信息,强引用，软引用，弱引用，虚引用
    // 7. -Xloggc:log/gc.log 打印GC信息到具体文件
    public static void main(String[] args) {

    }
}
