import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quickperf.junit4.QuickPerfJUnitRunner;
import org.quickperf.jvm.allocation.AllocationUnit;
import org.quickperf.jvm.annotations.HeapSize;
import org.quickperf.jvm.annotations.MeasureHeapAllocation;
import org.quickperf.repository.ObjectFileRepository;

import java.io.IOException;
import java.util.Collections;

//@Ignore
@RunWith(QuickPerfJUnitRunner.class)
public class MvnValidate {

    private static final String TEMP_DIR_PATH = System.getProperty("java.io.tmpdir");

    //public static Maven3Version MAVEN_3_VERSION = Maven3Version.V_3_6_1;
    public static Maven3Version MAVEN_3_VERSION = (Maven3Version) ObjectFileRepository.INSTANCE
            .find(TEMP_DIR_PATH, MvnValidateAllocationByMaven3Version.MAVEN_3_VERSION_FILE_NAME);


    //private final String pathOfMavenProjectUnderTest = "C:\\code\\camel";

    private final String pathOfMavenProjectUnderTest =
            new MavenProject(2_000).getPath();

    @Before
    public void before() throws IOException {

        // Faire fonctionner avec Maven 2

        System.out.println(MAVEN_3_VERSION);

        if(!MAVEN_3_VERSION.alreadyDownloaded()) {
            MAVEN_3_VERSION.download();
        }

    }

    @HeapSize(value = 9, unit = AllocationUnit.GIGA_BYTE)
    @MeasureHeapAllocation
    @Test
    public void execute_maven_validate_on_camel() throws VerificationException {
        String mavenPath = MAVEN_3_VERSION.getMavenPath();
        executeMavenValidateForMavenVersion(mavenPath);
    }

    private void executeMavenValidateForMavenVersion(String mavenPath) throws VerificationException {

        System.setProperty("verifier.forkMode", "auto"); // embedded

        System.setProperty("maven.home", mavenPath);

        Verifier verifier = new Verifier(pathOfMavenProjectUnderTest);
        String mavenVersion = verifier.getMavenVersion();
        verifier.setSystemProperty("maven.multiModuleProjectDirectory", pathOfMavenProjectUnderTest);

        verifier.executeGoals(Collections.singletonList("validate"));

    }

}
