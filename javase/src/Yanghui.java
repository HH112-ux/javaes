import java.util.Arrays;
import java.util.Scanner;

/**
 * @author jh
 * @project PACKAGE_NAME
 * @time 2026/1/3
 */
public class Yanghui {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = makeYH(n);

        int space = n * 2;
        for (int i = 0; i < n; i++) {
            // 打印前置空格，逐行减少2个
            for (int j = 0; j < space; j++) {
                System.out.print(" ");
            }
            space -= 2;
            // 打印元素，每个占4位
            for (int k = 0; k <= i; k++) {
                System.out.printf("%6d", arr[i][k]);
            }
            System.out.println();
        }


        for (int i = 0; i < n; i++) {
            System.out.println();
            for (int k = n-i; k >0; k--) {
                System.out.print("  ");
            }
            for (int j = 0; j < n; j++) {

                if (arr[i][j] !=0) {
                    if(arr[i][j] <10)
                System.out.print("   "+arr[i][j]);
                    else
                        System.out.print("  "+arr[i][j]);
                }
            }
        }
    }
    private static int[][] makeYH(int n) {
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
        arr[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i==j) {
                    arr[i][j] = 1;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                arr[i][j]=arr[i-1][j-1]+arr[i-1][j];
            }
        }
        return arr;
    }
}
