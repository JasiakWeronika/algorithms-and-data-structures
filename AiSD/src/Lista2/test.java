package Lista2;

public class test {
    public void insert() {
        System.out.println("=ŚREDNIA(Q2:Q11)");
        for (int i = 1; i < 100; i++) {
            System.out.println("=ŚREDNIA(Q" + i + "2:Q" + (i + 1) + "1)");
        }
    }

    public static void main(String[] args) {
        test t = new test();
        t.insert();
    }
}