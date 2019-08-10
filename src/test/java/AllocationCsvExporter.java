import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LongSummaryStatistics;

public class AllocationCsvExporter {

    private final String resultFilePath;

    public AllocationCsvExporter(String resultFilePath) {
        this.resultFilePath = resultFilePath;
    }

    public void writeResultToCsv(Maven3Version maven3Version, long[] allocations) throws IOException {

        int numberOfAllocations = allocations.length;
        CSVFormat csvFormat = buildCsvFormat(resultFilePath, numberOfAllocations);

        FileWriter fileWriter = new FileWriter(resultFilePath, true);
        try (CSVPrinter csvPrinter = new CSVPrinter(fileWriter, csvFormat)) {
            List<Object> csvRecord = buildCsvRecord(maven3Version, allocations);
            csvPrinter.printRecord(csvRecord);
        }

    }

    private CSVFormat buildCsvFormat(String resultFilePath, int numberOfAllocations) {
        if (new File(resultFilePath).exists()) {
            return CSVFormat.DEFAULT;
        }
        String[] csvHeaders = buildCsvHeaders(numberOfAllocations);
        return CSVFormat.DEFAULT.withHeader(csvHeaders);
    }

    private String[] buildCsvHeaders(int numberOfAllocations) {
        String[] csvHeaders = new String[numberOfAllocations + 4];
        csvHeaders[0] = "Maven version";
        csvHeaders[1] = "Average (bytes)";
        csvHeaders[2] = "Min (bytes)";
        csvHeaders[3] = "Max (bytes)";

        for (int i = 1; i < numberOfAllocations + 1; i++) {
            csvHeaders[i + 3] = "Allocation" + " " + i + " "+ "(bytes)";
        }
        return csvHeaders;
    }

    private List<Object> buildCsvRecord(Maven3Version maven3Version, long[] allocations) {

        List<Object> csvRecord = new ArrayList<>(allocations.length + 4);

        csvRecord.add(maven3Version);

        LongSummaryStatistics allocationStatistics = Arrays.stream(allocations).summaryStatistics();
        csvRecord.add(allocationStatistics.getAverage());
        csvRecord.add(allocationStatistics.getMin());
        csvRecord.add(allocationStatistics.getMax());

        for (int i = 0; i < allocations.length; i++) {
            csvRecord.add(allocations[i]);
        }
        return csvRecord;
    }

}
