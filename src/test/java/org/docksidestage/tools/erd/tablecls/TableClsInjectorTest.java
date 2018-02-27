/*
 * Copyright 2015-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.tools.erd.tablecls;

import java.io.File;
import java.io.IOException;

import org.dbflute.helper.filesystem.FileTextIO;
import org.dbflute.utflute.core.PlainTestCase;

/**
 * @author jflute
 */
public class TableClsInjectorTest extends PlainTestCase {

    public void test_main_basic() {
        final String projectDirPath = getProjectDirPath();
        final String erfluteFilePath = projectDirPath + "/modeling/maihamadb.erm";
        final String backupFilePath = erfluteFilePath + ".backup";
        copyTextFile(erfluteFilePath, backupFilePath);

        ready(projectDirPath, erfluteFilePath);
        TableClsInjector.main(new String[] { erfluteFilePath });

        final String confirmationFilePath = projectDirPath + "/modeling/request/tablecls/injected_maihamadb.erm";
        copyTextFile(erfluteFilePath, confirmationFilePath);
        new File(backupFilePath).renameTo(new File(erfluteFilePath));
        TableClsInjector.refreshResource(erfluteFilePath);
    }

    private String getProjectDirPath() {
        final File projectDir = getProjectDir();
        final String projectDirPath;
        try {
            projectDirPath = projectDir.getCanonicalPath();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to get canonical path: " + projectDir, e);
        }
        return projectDirPath;
    }

    private void ready(String projectDirPath, String erfluteFilePath) {
        copyTextFile(projectDirPath + "/modeling/maihamadb.erm", erfluteFilePath);
    }

    private void copyTextFile(String fromPath, String toPath) {
        final FileTextIO textIO = new FileTextIO().encodeAsUTF8();
        final String wholeText = textIO.read(fromPath);
        textIO.write(toPath, wholeText);
    }

}
