package project1_입출금내역분석기.v1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

// this God-class Anti-pattern
public class BankApplicationV1 {
    private static final String RESOURCE = "src/project1_입출금내역분석기/";

    public static void main(String[] args) throws Exception {

        final Path path = Paths.get(RESOURCE + args[0]);
        final List<String> lines = Files.readAllLines(path);

        double total = 0d;

        for (final String line : lines) {
            final String[] columns = line.split(",");
            final double amount = Double.parseDouble(columns[1]);
            total += amount;
        }
        System.out.println("총 입출금 합계 :  " + total);
    }
}
