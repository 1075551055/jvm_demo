package gc.parameters;

public class PermTest {
    // 方法区测试
    // -XX:+PrintGCDetails -XX:MaxMetaspaceSize=5m, 非堆参数，方法区，不属于-Xmx/-Xms
    // MaxMetaspaceSize Java1.8配置方法区大小
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            // todo 使用Cglib产生类
        }
    }
}
