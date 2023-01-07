package project1_입출금내역분석기.v2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class BankStatementCSVParser {

    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public List<BankTransaction> parseLinesFromCSV(final List<String> lines) {
        return lines.stream()
                .map(this::parseFromCSV)
                .collect(Collectors.toList());
    }

    private BankTransaction parseFromCSV(final String line) {
        String[] columns = line.split(",");

        final LocalDate localDate =  LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        return new BankTransaction(localDate, amount, description);
    }
}
