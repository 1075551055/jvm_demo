package gc.root;

public class MethodAreaStaticProperties {
    // 当properties = null，该成员内存可以被回收
    private byte[] member = new byte[2 * 1024 * 1024];
    public static byte[] instance;

    public MethodAreaStaticProperties(String name) {
    }

    // -Xmx10m -Xms10m -XX:+PrintGCDetails
    public static void main(String[] args) {
        MethodAreaStaticProperties properties = new MethodAreaStaticProperties("test");
        properties.instance = new byte[2 * 1024 * 1024];
        // new byte[2 * 1024 * 1024]不会被回收，因为instance是gc root，它们保持着连接。
        // new MethodAreaStaticProperties("test")则会被回收，因为与properties（gc root）断开了连接
        properties = null;
        System.gc();
    }

}
