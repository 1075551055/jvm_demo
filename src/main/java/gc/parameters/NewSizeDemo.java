package gc.parameters;

public class NewSizeDemo {
    // -Xmn  设置新生代大小，一般设置为整个堆的1/3，1/4
    // -XX:SurvivorRatio   设置新生代eden区与from/to survivor区的比例， = eden/from = eden/to
    // todo 为什么新生代默认就使用了1m左右的空间？
    public static void main(String[] args) {
        // Java HotSpot(TM) 64-Bit Server VM warning: NewSize (1536k) is greater than the MaxNewSize (1024k). A new max generation size of 1536k will be used.
        //-Xmx20m -Xms20m -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails


        // -XX:PretenureSizeThreshold=3  设置年龄大小，达到该值就进入老年代。如果survivor区的同年龄对象已经等于或大于该区域的一半就算没达到年龄阈值也会进入老年代
        // java -XX:+PrintFlagsFinal -XX:NewSize=1k -version | grep NewSize     ===>jdk 8
        // -XX:NewRatio 设置老年代/新生代的比例
        byte[] bytes = null;
        for (int i = 0; i < 1; i++) {
//            bytes = new byte[1 * 1024 * 1024];
        }
    }
}
