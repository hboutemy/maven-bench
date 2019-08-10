import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.experimental.results.PrintableResult;
import org.quickperf.repository.LongFileRepository;
import org.quickperf.repository.ObjectFileRepository;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import static org.junit.experimental.results.PrintableResult.testResult;

public class MvnValidateAllocationByMaven3Version {

    public static final String MAVEN_3_VERSION_FILE_NAME = "Maven3Version";

    private final String tempDirPath = System.getProperty("java.io.tmpdir");

    private final FilenameFilter quickPerfDirFilter =
            (dir, name) -> name.contains("QuickPerf");

    private final File tempDir = new File(tempDirPath);

    @Test
    public void measure() throws IOException {

        // Cas où failure count = 1

        // Supprimer les répertoires QuickPerf dans temp

        // Enregister  l'allocation dans un répertoire mesure
        // à la racine du projet
        // Mettre OS, JDK, ... dans fichier de mesure
        // Mettre un timestamp sur nom du fichier de mesures

        // Mesurer allocation pour différentes version de Maven

        // Mettre le code pour l'export Csv dans une classe à part

        String csvFileName = "maven_memory_allocation_2000_modules.csv";
        AllocationCsvExporter allocationCsvExporter = buildAllocationCsvExporter(csvFileName);

        //Maven3Version maven3Version = Maven3Version.V_3_5_3;

        int numberOfMeasuresByVersion = 5;

        Class<?> testClass = MvnValidate.class;

        for (Maven3Version maven3Version : Maven3Version.values()) {
            //MvnValidate.MAVEN_3_VERSION = maven3Version;
            FileUtils.deleteQuietly(new File(tempDirPath + File.separator
                                               + MAVEN_3_VERSION_FILE_NAME));
            ObjectFileRepository.INSTANCE.save(  tempDirPath
                                               , MAVEN_3_VERSION_FILE_NAME
                                               , maven3Version);
            long[] allocations =
                    measureAllocationSeveralTimes(testClass
                                                , numberOfMeasuresByVersion);

            allocationCsvExporter.writeResultToCsv(maven3Version, allocations);
        }

    }

    private AllocationCsvExporter buildAllocationCsvExporter(String fileName) {
        String srcTestResourcePath = findSrcTestResourcePath();
        String resultFilePath =
                  srcTestResourcePath
                + File.separator + "measures"
                + File.separator + fileName;
        return new AllocationCsvExporter(resultFilePath);
    }

    private String findSrcTestResourcePath() {
        File file = new File("src/test/resources");
        return file.getAbsolutePath();
    }

    private long[] measureAllocationSeveralTimes(Class<?> testClass, int numberOfMeasuresByVersion) throws IOException {
        long[] allocations = new long[numberOfMeasuresByVersion];

        for (int i = 0; i < numberOfMeasuresByVersion; i++) {
            long allocationInBytes = measureAllocation(testClass);
            allocations[i] = allocationInBytes;
        }
        return allocations;
    }

    private Long measureAllocation(Class<?> testClass) throws IOException {
        deleteQuickPerfFoldersInTemp();
        PrintableResult printableResult = testResult(testClass);
        Long allocationInBytes = retrieveMeasuredAllocationInBytes();
        System.out.println("allocationInBytes = " + allocationInBytes);
        return allocationInBytes;
    }


    private void deleteQuickPerfFoldersInTemp() throws IOException {
        File[] quickPerfFoldersBeforeMeasure = tempDir.listFiles(quickPerfDirFilter);
        for (File quickPerfFolder : quickPerfFoldersBeforeMeasure) {
            FileUtils.deleteDirectory(quickPerfFolder);
        }
    }

    private Long retrieveMeasuredAllocationInBytes() {
        LongFileRepository longFileRepository = new LongFileRepository();
        String[] quickPerfFolders = tempDir.list(quickPerfDirFilter);
        if (quickPerfFolders.length != 1) {
            throw new IllegalStateException("Several QuickPerf folders found in temp.");
        }
        String quickPerfFolderPath = tempDirPath
                                   + File.separator
                                   + quickPerfFolders[0];
        return longFileRepository.find(quickPerfFolderPath, "allocation.ser");
    }

}
