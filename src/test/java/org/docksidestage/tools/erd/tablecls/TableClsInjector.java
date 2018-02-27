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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dbflute.helper.filesystem.FileTextIO;
import org.dbflute.helper.filesystem.FileTextLineFilter;
import org.dbflute.helper.mapstring.MapListFile;
import org.dbflute.infra.manage.refresh.DfRefreshResourceRequest;
import org.dbflute.util.DfCollectionUtil;
import org.dbflute.util.Srl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 */
public class TableClsInjector {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(TableClsInjector.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final String requestFilePath; // absolute path from project dir
    protected final String erfluteFilePath; // me too

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TableClsInjector(String requestFilePath, String erfluteFilePath) {
        this.requestFilePath = requestFilePath;
        this.erfluteFilePath = erfluteFilePath;
    }

    // ===================================================================================
    //                                                                              Inject
    //                                                                              ======
    public void injectTableCls() {
        final StringBuilder sb = new StringBuilder();
        InputStream ins = null;
        try {
            logger.debug("...Reading tableCls request file: {}", requestFilePath);
            ins = prepareRequestResourceStream();
            final Map<String, Object> map = new MapListFile().readMap(ins);
            for (Entry<String, Object> entry : map.entrySet()) {
                final String keywordName = entry.getKey();
                logger.debug("...Building tableCls XML part: {}", keywordName);
                checkAlreadyExists(keywordName);
                @SuppressWarnings("unchecked")
                final Map<String, Object> elementMap = (Map<String, Object>) entry.getValue();
                final String alias = (String) elementMap.get("alias");
                final String comment = (String) elementMap.get("comment");
                final String codeType = (String) elementMap.get("codeType");

                // begin table
                sb.append(ln()).append("<table>");

                sb.append(ln()).append("\t<physical_name>").append(buildTableName(keywordName)).append("</physical_name>");
                sb.append(ln()).append("\t<logical_name>").append(buildTableAlias(alias)).append("</logical_name>");
                sb.append(ln()).append("\t<description>").append(comment).append("</description>");
                sb.append(ln()).append("\t<font_name>Lucida Grande</font_name>");
                sb.append(ln()).append("\t<font_size>9</font_size>");
                sb.append(ln()).append("\t<x>0</x>"); // as default
                sb.append(ln()).append("\t<y>0</y>");
                sb.append(ln()).append("\t<color>");
                sb.append(ln()).append("\t\t<r>255</r>"); // CLS color
                sb.append(ln()).append("\t\t<g>251</g>");
                sb.append(ln()).append("\t\t<b>143</b>");
                sb.append(ln()).append("\t</color>");
                sb.append(ln()).append("\t<connections>");
                sb.append(ln()).append("\t</connections>");

                // begin columns
                sb.append(ln()).append("\t<columns>");

                // CODE
                sb.append(ln()).append("\t\t<normal_column>");
                sb.append(ln()).append("\t\t\t<physical_name>").append(keywordName).append("_CODE").append("</physical_name>");
                sb.append(ln()).append("\t\t\t<logical_name>").append(alias).append(" Code").append("</logical_name>");
                sb.append(ln()).append("\t\t\t<type>").append(buildCodeType(codeType)).append("(n)").append("</type>"); // 
                sb.append(ln()).append("\t\t\t<length>").append(buildCodeLength(codeType)).append("</length>");
                sb.append(ln()).append("\t\t\t<not_null>true</not_null>");
                sb.append(ln()).append("\t\t\t<primary_key>true</primary_key>");
                sb.append(ln()).append("\t\t</normal_column>");

                // NAME
                sb.append(ln()).append("\t\t<normal_column>");
                sb.append(ln()).append("\t\t\t<physical_name>").append(keywordName).append("_NAME").append("</physical_name>");
                sb.append(ln()).append("\t\t\t<logical_name>").append(alias).append(" Name").append("</logical_name>");
                sb.append(ln()).append("\t\t\t<type>varchar(n)</type>");
                sb.append(ln()).append("\t\t\t<length>255</length>");
                sb.append(ln()).append("\t\t\t<not_null>true</not_null>");
                sb.append(ln()).append("\t\t</normal_column>");

                // Common Column
                sb.append(ln()).append("\t\t<column_group>Common Column</column_group>");

                // end columns
                sb.append(ln()).append("\t</columns>");

                sb.append(ln()).append("\t<indexes>");
                sb.append(ln()).append("\t</indexes>");
                sb.append(ln()).append("\t<compound_unique_key_list>");
                sb.append(ln()).append("\t</compound_unique_key_list>");
                sb.append(ln()).append("\t<table_properties>");
                sb.append(ln()).append("\t</table_properties>");

                // end table
                sb.append(ln()).append("</table>");
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read the resource: " + requestFilePath, e);
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException ignored) {}
            }
        }
        final String tableClsTag = tab(tab(Srl.ltrim(sb.toString())));
        logger.debug("{}{}", ln(), tableClsTag);
        logger.debug("...Injecting tableCls XML part to ERFlute: {}", erfluteFilePath);
        reflectToERFlute(tableClsTag);
    }

    // -----------------------------------------------------
    //                                          Request File
    //                                          ------------
    private InputStream prepareRequestResourceStream() throws FileNotFoundException {
        return new FileInputStream(new File(requestFilePath));
    }

    // -----------------------------------------------------
    //                                       Duplicate Check
    //                                       ---------------
    private void checkAlreadyExists(String keywordName) {
        final String wholeText = new FileTextIO().encodeAsUTF8().read(erfluteFilePath);
        if (Srl.containsIgnoreCase(wholeText, "<physical_name>" + buildTableName(keywordName) + "</physical_name>")) {
            throw new IllegalStateException("The table classification already exists: " + keywordName);
        }
    }

    // -----------------------------------------------------
    //                                    Reflect to ERFlute
    //                                    ------------------
    private void reflectToERFlute(String tableClsTag) {
        new FileTextIO().encodeAsUTF8().rewriteFilteringLine(erfluteFilePath, new FileTextLineFilter() {

            private boolean done;

            @Override
            public String filter(String line) {
                if (!done && line.trim().equals("</diagram_walkers>")) {
                    done = true;
                    return tableClsTag + ln() + line;
                }
                return line;
            }
        });
    }

    // -----------------------------------------------------
    //                                          Item Builder
    //                                          ------------
    private String buildTableName(String keywordName) {
        return "CLS_" + keywordName;
    }

    private String buildTableAlias(String alias) {
        return alias;
    }

    private String buildCodeType(String codeType) {
        if (!Srl.equalsIgnoreCase(codeType, "char", "varchar")) {
            throw new IllegalArgumentException("The codeType is not allowed, use char or varchar: but=" + codeType);
        }
        return codeType.equalsIgnoreCase("char") ? "character" : "varchar";
    }

    private int buildCodeLength(String codeType) {
        return codeType.equalsIgnoreCase("char") ? 3 : 10; // char(3) or varchar(10)
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private String tab(String str) {
        final List<String> lineList = Srl.splitList(str, ln());
        final StringBuilder sb = new StringBuilder();
        int index = 0;
        for (String element : lineList) {
            if (index > 0) {
                sb.append(ln());
            }
            sb.append(tab()).append(element);
            ++index;
        }
        return sb.toString();
    }

    protected String ln() {
        return "\n";
    }

    protected String tab() {
        return "\t";
    }

    // ===================================================================================
    //                                                                               Main
    //                                                                              ======
    public static void main(String[] args) {
        if (args.length == 0) {
            String msg = "Not found the argument, set like this 'java [class-name] [erm-absolute-path]': actual=" + Arrays.asList(args);
            throw new IllegalArgumentException(msg);
        }
        final String erfluteFilePath = backslashToSlash(args[0]);
        if (!erfluteFilePath.endsWith(".erm")) {
            String msg = "First argument should be absolute path to .erm file: " + erfluteFilePath;
            throw new IllegalArgumentException(msg);
        }
        final String requestFilePath = buildRequestFilePath(erfluteFilePath);
        final TableClsInjector injector = createInjector(requestFilePath, erfluteFilePath);
        injector.injectTableCls();
        makeDebugXmlFile(requestFilePath, erfluteFilePath);
        refreshResource(erfluteFilePath);
    }

    private static TableClsInjector createInjector(String requestFilePath, String erfluteFilePath) {
        return new TableClsInjector(requestFilePath, erfluteFilePath);
    }

    public static String buildRequestFilePath(String erfluteFilePath) { // public just in case
        final String modelingDirPath = Srl.substringLastFront(erfluteFilePath, "/");
        return modelingDirPath + "/request/tablecls/tablecls_request.dfprop";
    }

    // -----------------------------------------------------
    //                                             Debug XML
    //                                             ---------
    private static void makeDebugXmlFile(String requestFilePath, String erfluteFilePath) {
        final String xmlPureName = "injected_" + Srl.replace(Srl.substringLastRear(erfluteFilePath, "/"), ".erm", ".xml");
        final String xmlFilePath = Srl.substringLastFront(requestFilePath, "/") + "/" + xmlPureName;
        copyTextFile(erfluteFilePath, xmlFilePath);
    }

    private static void copyTextFile(String fromPath, String toPath) {
        final FileTextIO textIO = new FileTextIO().encodeAsUTF8();
        final String wholeText = textIO.read(fromPath);
        textIO.write(toPath, wholeText);
    }

    // -----------------------------------------------------
    //                                               Refresh
    //                                               -------
    public static void refreshResource(String erfluteFilePath) { // public just in case
        final String erfluteDirPath = Srl.substringLastFront(erfluteFilePath, "/");
        final File projectDir = getProjectDir(new File(erfluteDirPath));
        final String projectName = projectDir.getName();
        final List<String> projectNameList = Arrays.asList(projectName);
        final String requestUrl = "http://localhost:8386/";
        final DfRefreshResourceRequest request = new DfRefreshResourceRequest(projectNameList, requestUrl);
        try {
            request.refreshResources();
        } catch (IOException continued) {
            logger.info("Failed to refresh resources: {}", continued.getMessage());
        }
    }

    // copied from PlainTestCase
    /**
     * Get the directory object of the (application or Eclipse) project.
     * @return The file object of the directory. (NotNull)
     */
    private static File getProjectDir(File subDir) {
        final Set<String> markSet = defineProjectDirMarkSet();
        for (File dir = subDir; dir != null; dir = dir.getParentFile()) {
            if (dir.isDirectory()) {
                if (Arrays.stream(dir.listFiles()).anyMatch(file -> markSet.contains(file.getName()))) {
                    return dir;
                }
            }
        }
        throw new IllegalStateException("Not found the project dir marks: " + markSet);
    }

    /**
     * Define the marks of the (application or Eclipse) project.
     * @return the set of mark file name for the project. (NotNull)
     */
    private static Set<String> defineProjectDirMarkSet() {
        return DfCollectionUtil.newHashSet("build.xml", "pom.xml", "build.gradle", ".project", ".idea");
    }

    // -----------------------------------------------------
    //                                          Small Helper
    //                                          ------------
    private static String backslashToSlash(String filePath) {
        return Srl.replace(filePath, "\\", "/");
    }
}
