package gc.parameters;

public class LocalVariableTable {
    public void localVar1() {
        // classs属性的局部变量表中的index 0是this
        int a = 0;
        System.out.println(a);
        int b = 0;
    }

    public void localVar2() {
        // a过了作用域，它的槽位index会被b复用
        {
            int a = 0;
            System.out.println(a);
        }
        int b = 0;
    }

}
