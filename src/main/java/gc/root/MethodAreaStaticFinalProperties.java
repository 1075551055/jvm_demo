package gc.root;

public class MethodAreaStaticFinalProperties {
    // 不会被回收
    private static final MethodAreaStaticProperties PROPERTIES = new MethodAreaStaticProperties("static final");

    public MethodAreaStaticFinalProperties(String name) {

    }

    public static void main(String[] args) {
        MethodAreaStaticProperties methodAreaStaticProperties = new MethodAreaStaticProperties("instance");
        // methodAreaStaticProperties会被回收，但是PROPERTIES不会被回收，它就是一个gc root，没有断开连接
        methodAreaStaticProperties = null;
        System.gc();
    }
}
