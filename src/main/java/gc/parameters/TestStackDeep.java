package gc.parameters;

public class TestStackDeep {
    private static int count = 1;

    private static void recursive() {
        count++;
        recursive();
    }

    // -Xss160k -XX:+PrintFlagsFinal
    // -Xss160k  栈大小
    public static void main(String[] args) {
        try {
            recursive();
        } catch (Error e) {
            System.out.println("count:" + count);
        }
    }
}
