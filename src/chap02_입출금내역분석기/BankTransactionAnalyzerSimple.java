package chap02_입출금내역분석기;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionAnalyzerSimple {

    private static final String RESOURCES = "src/main/resources/";
//
    public static void main(String[] args) throws IOException {
//
//        //모든 거래내역의 합 계산
//        final Path path = Paths.get(RESOURCES + args[0]);
//        final List<String> lines = Files.readAllLines(path);
//        double total = 0d;
//        for (final String line : lines) {
//            final String[] columns = line.split(",");
//            final double amount = Double.parseDouble(columns[1]);
//            total += amount;
//        }
//
//        System.out.println("모든 거래내역에 대한 금액 총합은 [" + total + "]원 입니다.");
//
//        //1월 입출금 내역 합계 계산
//        final Path path = Paths.get(RESOURCES + args[0]);
//        final List<String> lines = Files.readAllLines(path);
//        double total = 0d;
//        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        for (final String line : lines) {
//            final String[] columns = line.split(",");
//            final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
//            if (date.getMonth() == Month.JANUARY) {
//                final double amount = Double.parseDouble(columns[1]);
//                total += amount;
//            }
//        }
//
//        System.out.println("1월 모든 거래내역에 대한 금액 총합은 [" + total + "]원 입니다.");

        final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

        final String fileName = args[0];
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFromCSV(lines);

        System.out.println("총 입출금 합계는 " + calculateTotalAmount(bankTransactions) + " 입니다.");
        System.out.println("1월 입출금 합계는 " + selectInMonth(bankTransactions, Month.JANUARY) + " 입니다.");
    }

    public static double calculateTotalAmount(final List<BankTransaction> bankTransactions) {
        double total = 0d;
        for (final BankTransaction bankTransaction : bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions, final Month month) {
        final List<BankTransaction> bankTransactionsInMonth = new ArrayList<>();
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {
                bankTransactionsInMonth.add(bankTransaction);
            }
        }
        return bankTransactionsInMonth;
    }
}
