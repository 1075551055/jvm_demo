package gc.root;

public class ReferenceCountingGC {
    public static void main(String[] args) {
        // 定义两个对象
        Student student = new Student();
        Teacher teacher = new Teacher();

        // 对象相互引用
        student.instance = teacher;
        teacher.instance = student;

        // 两个对象置为NULL
        student = null;
        teacher = null;
    }

    static class Student {
        public Teacher instance;
    }

    static class Teacher {
        public Student instance;
    }

}
