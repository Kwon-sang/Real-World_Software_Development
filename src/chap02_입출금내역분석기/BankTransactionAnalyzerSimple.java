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
    private static final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

    public static void main(String[] args) throws IOException {

        final String fileName = args[0];
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
    }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
        System.out.println("총 거래내역 금액 합계 : " + bankStatementProcessor.calculateTotalAmount());
        System.out.println("1월 거래내역 금액 합계 : " + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("2월 거래내역 금액 합계 : " + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
        System.out.println("총 급여내역 금액 합계 : " + bankStatementProcessor.calculateTotalForCategory("Salary"));
    }
}
