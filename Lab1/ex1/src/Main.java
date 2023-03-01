public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String[] languages={"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

    int n = (int) (Math.random() * 1_000_000);
    System.out.println(n);
    n=n*3;
    System.out.println(n);
    n=n+0b10101;
    System.out.println(n);
    String hexa="FF";
    int value = Integer.parseInt(hexa, 16);
    n=n+value;
    System.out.println(n);
    n=n*6;
    System.out.println(n);
    int result = n;
        while(result>9) {
        int sum = 0;
        while (result !=0) {
            sum = sum + result % 10;
            result=result/10;
        }
        result = sum;
    }
        System.out.println("Willy-nilly, this semester I will learn " + languages[result] + ".");

}
}