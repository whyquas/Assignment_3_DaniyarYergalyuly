public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return name.equals(other.name);
    }

    @Override
    public String toString() {
        return "Student{" + name + '}';
    }
}
