package project1_입출금내역분석기.v2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

public class Application_v2 {

    private static final String RESOURCE = "src/project1_입출금내역분석기/";

    public static void main(String[] args) throws IOException {
        final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

        final Path path = Paths.get(RESOURCE + args[0]);
        List<String> lines = Files.readAllLines(path);
        List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFromCSV(lines);

        System.out.println("총 입출금 합계 :  " + calculateTotalAmount(bankTransactions));
        System.out.println("1월 입출금 목록 : " + selectInMonth(bankTransactions, Month.JANUARY));
    }

    public static double calculateTotalAmount(List<BankTransaction> transactions) {
        return transactions.stream()
                .mapToDouble(BankTransaction::getAmount)
                .sum();
    }

    public static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions, final Month month) {
        return bankTransactions.stream()
                .filter(x -> x.getDate().getMonth() == month)
                .collect(Collectors.toList());
    }
}
