public class MyTestingClass {
    private int id;

    public MyTestingClass(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return (id * 31 + 17) ^ (id >>> 3);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MyTestingClass)) return false;
        MyTestingClass other = (MyTestingClass) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "TestClass{" + id + '}';
    }
}
