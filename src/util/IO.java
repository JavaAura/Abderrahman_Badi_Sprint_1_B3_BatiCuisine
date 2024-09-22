package util;

import java.util.Scanner;

public class IO {

    public static Scanner scanner = null;

    private IO() {
    }

    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in).useDelimiter(System.lineSeparator());
        }
        return scanner;
    }

    public static void sysPause() {
        if (scanner == null)
            scanner = getScanner();
        System.out.println("Press Enter to continue...");
        scanner.next();
    }
}
