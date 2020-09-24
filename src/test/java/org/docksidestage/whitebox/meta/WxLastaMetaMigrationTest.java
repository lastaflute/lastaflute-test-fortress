package org.docksidestage.whitebox.meta;

import java.io.IOException;

import org.dbflute.helper.filesystem.FileTextIO;
import org.docksidestage.unit.UnitFortressBasicTestCase;

/**
 * @author jflute
 */
public class WxLastaMetaMigrationTest extends UnitFortressBasicTestCase {

    public void test_can_migrate_old_to_new_LastaDoc() {
        String byolddocDocFileName = "analyzed-lastadoc-20200924.json";
        String currentDocFileName = "analyzed-lastadoc.json";
        doTest_can_migrate_old_to_new(byolddocDocFileName, currentDocFileName);
    }

    public void test_can_migrate_old_to_new_Swagger() {
        String byolddocDocFileName = "swagger-20200924.json";
        String currentDocFileName = "swagger.json";
        doTest_can_migrate_old_to_new(byolddocDocFileName, currentDocFileName);
    }

    private void doTest_can_migrate_old_to_new(String byolddocFileName, String currentFileName) {
        String projectPath;
        try {
            projectPath = getProjectDir().getCanonicalPath();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to get canonical path of project directory.", e);
        }
        String byolddocJsonPath = projectPath + "/src/test/resources/meta/byolddoc/" + byolddocFileName;
        String currentJsonPath = projectPath + "/target/lastadoc/" + currentFileName;

        // ## Act ##
        String byolddocJsonText = new FileTextIO().encodeAsUTF8().read(byolddocJsonPath);
        String currentJsonText = new FileTextIO().encodeAsUTF8().read(currentJsonPath);

        // ## Assert ##
        log("byolddoc size: {}", byolddocJsonText.length());
        log("current size: {}", currentJsonText.length());
        assertEquals(byolddocJsonText, currentJsonText);
    }
}
