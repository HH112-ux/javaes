import java.util.Arrays;
import java.util.Random;

public class Main {
    static String[] pokerDeck = new String[52];
//一
    public static void printLotto() {
        Random random = new Random();
        System.out.print("您的大乐透号码为：");
        for (int i = 0; i < 10; i++) {
            int num = random.nextInt(99) + 1;
            System.out.printf("%d ", num);
        }
    }
//二，三
    public static void printPoker() {
        String[] pokerDeck = makePoker();
        System.out.println();
        for (String poker : pokerDeck) {
            System.out.print(poker);
            if(poker.equals("梅花K")||poker.equals("方块K")||poker.equals("黑桃K")||poker.equals("红桃K"))
                System.out.println();
        }
    }

    public static void drawPoker() {
        String[] pokerDeck = makePoker();
        System.out.println(pokerDeck[0] + " " + pokerDeck[4] + " " + pokerDeck[49]);
    }

    private static String[] makePoker() {
        System.out.println();
        String[] suits = {"黑桃", "红桃", "梅花", "方片"};
        String[] muns = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        int i = 0;
        for (String suit : suits) {
            for (String rank : muns) {
                pokerDeck[i++] = suit + rank;
            }
        }
        return pokerDeck;
    }
//四
    public static void printCount(char[] arr) {

        int[] count = new int[128];
        for (char c : arr) {
            count[c]++;
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                System.out.println((char) i + "--" + count[i]);
            }
        }
    }
    //五
    public static double getAvg(int[] arr) {
        double sum =getSum(arr);
        double avg = sum / arr.length;
        getNum(arr, avg);
        return avg ;
    }

    private static int getNum(int[] arr, double avg) {
        int i=0;
        for(int j: arr){
            if(j> avg){
                i++;
            }
        }
        return i ;
    }

    public static double getSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }
//六
    public static boolean sym(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] != arr[arr.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }
//七
    public static boolean equals(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        printLotto();
        printPoker();
        drawPoker();
        char[] chars = {'a', 'l', 'f', 'm', 'f', 'o', 'b', 'b', 's', 'n'};
        printCount(chars);
        int[] scores = {95, 92, 75, 56, 98, 71, 80, 58, 91, 91};
        System.out.printf("高于平均分:%f 的 个数有%d 个%n", getAvg(scores), getNum(scores,getAvg(scores)));
        int[] arr1 = {1, 2, 3, 4, 3, 2, 1};
        int[] arr2 = {1, 2, 3, 4, 5, 2, 1};
        int[] arr3 = {1, 2, 3, 4, 3, 2, 1};
        System.out.println(Arrays.toString(arr1) + " 是否对称:" + sym(arr1));
        System.out.println(Arrays.toString(arr2) + " 是否对称:" + sym(arr2));
        System.out.println(Arrays.toString(arr1) + " 与 " + Arrays.toString(arr3) + " 是否一致:" + equals(arr1, arr3));
    }
}