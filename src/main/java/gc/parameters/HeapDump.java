package gc.parameters;

import java.util.ArrayList;
import java.util.List;

public class HeapDump {
    // 设置堆大小以及当发生堆内存溢出的时候dump出堆相关信息放到a.dump文件中，dump文件可以使用MAT工具进行分析
    // -Xmx20m -Xms20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./a.dump
    // "-XX:OnOutOfMemoryError=xx.bat"  用于当发生内存溢出执行某个脚本，可以作报警，自救或者通知作用等
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add(new byte[1024 * 1024]);
        }
    }
}
