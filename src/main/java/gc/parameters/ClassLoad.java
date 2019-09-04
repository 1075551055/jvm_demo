package gc.parameters;

public class ClassLoad {
    //-verbose:class  查看类的加载和卸载
    //-XX:+TraceClassLoading 查看类加载
    //-XX:+TraceClassUnloading 查看类卸载
    //-XX:+PrintClassHistogram 查看类分布情况
    public static void main(String[] args) throws InterruptedException {
//        ClassLoader loader = new Cl
//        Thread.sleep(200000);
        // todo +PrintClassHistogram
        System.out.println("hello");
    }
}
