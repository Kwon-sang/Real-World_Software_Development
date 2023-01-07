package project1_입출금내역분석기.v3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

public class Application_v3 {

    private static final String RESOURCE = "src/project1_입출금내역분석기/";
    private static final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

    public static void main(String[] args) throws IOException {
        final Path path = Paths.get(RESOURCE + args[0]);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFromCSV(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
    }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
        System.out.println("총 거래내역 금액 합계 : " + bankStatementProcessor.calculateTotalAmount());
        System.out.println("1월 거래내역 금액 합계 : " + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("2월 거래내역 금액 합계 : " + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
        System.out.println("총 급여내역 금액 합계 : " + bankStatementProcessor.calculateTotalForCategory("급여"));
    }
}
