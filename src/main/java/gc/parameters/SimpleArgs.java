package gc.parameters;

public class SimpleArgs {
    // java -Xmx3m gc.parameters.SimpleArgs testArgs
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
        System.out.println(Runtime.getRuntime().maxMemory());
        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().freeMemory());
        System.out.println(3 * 1024 * 1024);
    }
}
