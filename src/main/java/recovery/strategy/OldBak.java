package recovery.strategy;

import java.util.HashMap;
import java.util.Map;

public class OldBak {
    // todo 为什么是分配到新生代而不是方法区？这不是常量池的内容吗
//    private static final byte[] bytes1 = new byte[1024 * 1024];
//    private static byte[] bytes2;
//    private static byte[] bytes3;
//    private static byte[] bytes4;

    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) {
//        fullGc();
//        bytes1 = new byte[1024 * 1024];
//        bytes2 = new byte[1024 * 1024];
//        bytes3 = new byte[1024 * 1024];
//        bytes4 = new byte[1024 * 1024];

        // 大对象
//        bigObject();

//        bigObject2();

        // 老年代内存担保机制
        guarantee();

        // 对象年龄阈值设置
//        ageThreshold();

        // 动态对象年龄
//        dynamicAge();
    }

    private static void dynamicAge() {
        // 注意：默认新生代就占用了1m多空间，所以垃圾回收的时候这部分也会给回收，这部分具体是什么内容呢（https://stackoverflow.com/questions/57951258/jdk-8-run-empty-main-function-why-psyounggen-already-used-about-1000k-space）？
        // -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+UseSerialGC
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1M / 4];
        // allocation1, allocation2大于survivor空间一半
        allocation2 = new byte[_1M / 4];
        allocation3 = new byte[4 * _1M];
        allocation4 = new byte[4 * _1M];
        allocation4 = null;
        allocation4 = new byte[4 * _1M];
    }

    private static void ageThreshold() {
        // 年龄 -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1M / 4];
        allocation2 = new byte[4 * _1M];
        allocation3 = new byte[4 * _1M];
        allocation3 = null;
        allocation3 = new byte[4 * _1M];

    }

    private static void guarantee() {
//        // 年龄 -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
//        byte[] allocation1, allocation2, allocation3;
//        allocation1 = new byte[_1M / 4];
//        allocation2 = new byte[_1M * 4];
//        // allocation3, allocation4会被分配到老年代，因为新生代没有足够空间(新生代没有足够空间放对象的时候是不会进行minor gc的)，老年代有足够空间担保（Parallel scavenge收集器
//        // 的表现，如果是serial收集器则会产生两次GC）
//        allocation3 = new byte[_1M * 4];
//        allocation3 = null;
//        allocation3 = new byte[4 * _1M];


        byte[] b = new byte[8 * _1M];
    }

    private static void bigObject() {
        // -Xms10m -Xmx10m -Xmn6m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1048576 -XX:+UseParNewGC
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
        byte[] bytes1 = new byte[6 * _1M];
        bytes1 = null;
        byte[] bytes2 = new byte[2 * _1M];
//        byte[] bytes3 = new byte[2 * _1M];
//        bytes3 = null;
////        byte[] bytes4 = new byte[2 * _1M];
    }


}
