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
package org.docksidestage.whitebox.db.lazytx;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.dbflute.helper.filesystem.FileTextIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class LazyTxUnitSetupper {

    private static final Logger logger = LoggerFactory.getLogger(LazyTxUnitSetupper.class);
    private static final List<String> diXmlList = Arrays.asList("jta+transactionManagerAdapter.xml", "jta+userTransaction.xml");

    private final File testCaseBuilderDir;
    private final File projectDir;

    public LazyTxUnitSetupper(File testCaseBuilderDir, File projectDir) {
        this.testCaseBuilderDir = testCaseBuilderDir;
        this.projectDir = projectDir;
    }

    public void copyProtoToBuildDir() {
        diXmlList.forEach(diXml -> doCopyProtoToBuildDir(diXml));
    }

    private void doCopyProtoToBuildDir(String fileName) {
        try {
            FileTextIO textIO = new FileTextIO().encodeAsUTF8();
            String textPath = testCaseBuilderDir.getCanonicalPath() + "/" + fileName;
            File prototypeFile = new File(textPath);
            if (prototypeFile.exists()) {
                prototypeFile.delete();
            }
            String prototypeDir = projectDir.getCanonicalPath() + "/etc/tools/lazytx";
            String text = textIO.read(prototypeDir + "/" + fileName);
            logger.debug("...Copying prototype {}", fileName);
            textIO.write(textPath, text);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to copy prototype.", e);
        }
    }

    public void removeCopiedProto() {
        diXmlList.forEach(diXml -> doRemoveCopiedProto(diXml));
    }

    private void doRemoveCopiedProto(String fileName) {
        try {
            String textPath = testCaseBuilderDir.getCanonicalPath() + "/" + fileName;
            File file = new File(textPath);
            if (file.exists()) {
                file.delete();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to copy prototype.", e);
        }
    }
}
