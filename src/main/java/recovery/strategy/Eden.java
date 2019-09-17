package recovery.strategy;

import java.util.HashMap;
import java.util.Map;

public class Eden {
    // -Xms10m -Xmx10m -Xmn8m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
    // SurvivorRatio = Eden/from = Eden/to
    public static void main(String[] args) {
        Map<Integer, byte[]> map = new HashMap<Integer, byte[]>();
        // i<2的时候空间足够，3则空间不够，进行minor gc
        for (int i = 0; i < 3; i++) {
            map.put(i, new byte[1024 * 1024]);
        }
    }


//    [GC (Allocation Failure) [PSYoungGen: 2420K->608K(4608K)] 2420K->616K(8704K), 0.0010325 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
//    Heap
//    PSYoungGen      total 4608K, used 1695K [0x00000007bfa00000, 0x00000007c0000000, 0x00000007c0000000)
//    eden space 3072K, 35% used [0x00000007bfa00000,0x00000007bfb0fdc8,0x00000007bfd00000)
//    from space 1536K, 39% used [0x00000007bfd00000,0x00000007bfd98000,0x00000007bfe80000)
//    to   space 1536K, 0% used [0x00000007bfe80000,0x00000007bfe80000,0x00000007c0000000)
//    ParOldGen       total 4096K, used 8K [0x00000007bf600000, 0x00000007bfa00000, 0x00000007bfa00000)
//    object space 4096K, 0% used [0x00000007bf600000,0x00000007bf602000,0x00000007bfa00000)
//    Metaspace       used 2961K, capacity 4496K, committed 4864K, reserved 1056768K
//    class space    used 327K, capacity 388K, committed 512K, reserved 1048576K

    // gc日志分析：在第二次产生byte数组的时候，由于eden区无法容纳了（已经使用了2420k，再加上1024k就大于eden区了），所以此时进行了一次minor gc.
    // 有1024k数据进入到了from区，所以39%空间被使用了。todo 为什么老年代区域used 8K呢？TLAB原因，可以通过参数-XX:-UseTLAB关闭TLAB (Parallel Scavenge收集器，不同收集器有
    //  不同表现)？

}
