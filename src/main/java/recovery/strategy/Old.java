package recovery.strategy;

import java.util.HashMap;
import java.util.Map;

public class Old {

    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) {
        // 老年代gc
//        fullGc();

        // 大对象
//        bigObject();

        //bigObject2(); x

        // 老年代内存担保机制
//        guarantee();

        // 对象年龄阈值设置
//        ageThreshold();

        // 动态对象年龄
        dynamicAge();
    }

    private static void dynamicAge() {
        // 注意：默认新生代就占用了1m多空间，所以垃圾回收的时候这部分也会给回收，这部分具体是什么内容呢（https://stackoverflow.com/questions/57951258/jdk-8-run-empty-main-function-why-psyounggen-already-used-about-1000k-space）？
        // -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+UseSerialGC
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1M / 4];
        // allocation1, allocation2大于等于survivor空间一半
        allocation2 = new byte[_1M / 4];
        allocation3 = new byte[4 * _1M];
        // 此处发生gc
        allocation4 = new byte[4 * _1M];
        allocation4 = null;
        // 此处发生gc, 虽然MaxTenuringThreshold年龄是15，但是allocation1, allocation2大于等于survivor空间一半，所以进入老年代
        allocation4 = new byte[4 * _1M];
    }

    private static void ageThreshold() {
        // 年龄 -Xms20m -Xmx20m -Xmn10m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1M / 4];
        allocation2 = new byte[4 * _1M];
        // 此处发生gc， allocation1被拷贝到from survivor区，allocation2则由于survivor区装不下进入到老年代
        allocation3 = new byte[4 * _1M];
        allocation3 = null;
        // 此处发生gc，由于MaxTenuringThreshold年龄设置了为1，allocation1进入老年代
        allocation3 = new byte[4 * _1M];

    }

    private static void guarantee() {
        // -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
        byte[] b = new byte[8 * _1M];
    }

    private static void bigObject() {
        // -Xms10m -Xmx10m -Xmn6m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1048576 -XX:+UseSerialGC
        // PretenureSizeThreshold参数对于Parallel Scavenge收集器不起作用， 1048576是字节数
        // 会被移动到老年代
        byte[] obj = new byte[1 * _1M];
    }

    private static void bigObject2() {
        //  -Xmx32m -Xms32m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:PretenureSizeThreshold=1000  -XX:-UseTLAB -XX:+PrintFlagsFinal
        // UseTLAB（线程本地分配缓存）对于Parallel Scavenge收集器来说需要设置-XX:TLABSize大小
        // https://stackoverflow.com/questions/43747221/what-is-a-tlab-thread-local-allocation-buffer
        Map<Integer, byte[]> map = new HashMap<Integer, byte[]>();
        int _1k = 1024;
        for (int i = 0; i < 5 * _1k; i++) {
            byte[] b = new byte[_1k];
            map.put(i, b);
        }
    }

    private static void fullGc() {
        // -Xms10m -Xmx10m -Xmn2m -XX:+UseSerialGC -XX:+PrintGCDetails
        // gc日志中的[GC,[Full GC代表的是日志收集停顿的类型,有Full代表stop-the-world，而不是新生代或者老年代的垃圾收集。平常所说的minor gc是新生代gc,full gc/major gc是老年代gc(注意区分
        // gc日志中的Full GC，不是同一个概念)

        // 新生代空间不够，直接进入老年代
        byte[] bytes1 = new byte[6 * _1M];
        bytes1 = null;
        // 此处发生老年代gc
        byte[] bytes2 = new byte[3 * _1M];
    }


}
