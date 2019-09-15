package recovery.strategy;

public class Old {
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

        // 大对象 -Xms10m -Xmx10m -Xmn6m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1048576 -XX:+UseParNewGC
        // PretenureSizeThreshold参数对于Parallel Scavenge收集器不起作用， 1048576是字节数
        bigObject();

        // 老年代内存担保机制
//        guarantee();

        //

    }

    private static void guarantee() {
        // 年龄 -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1M / 4];
        allocation2 = new byte[_1M * 4];
        // allocation3, allocation4会被分配到老年代，因为新生代没有足够空间，老年代有足够空间担保
        allocation3 = new byte[_1M * 4];
        allocation3 = null;
        allocation3 = new byte[4 * _1M];
    }

    private static void bigObject() {
        // 会被移动到老年代
        byte[] obj = new byte[1 * _1M];
    }

    private static void fullGc() {
//        bytes1 = new byte[1024 * 1024];
//        bytes2 = new byte[1024 * 1024];
//        bytes3 = new byte[1024 * 1024];
//        bytes4 = new byte[1024 * 1024];
    }


}
