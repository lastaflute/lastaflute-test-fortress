package org.docksidestage.bizfw.remoteapi;

import java.util.Collections;
import java.util.Map;

/**
 * @author jflute
 */
public abstract class AbstractListGetReturn {

    private Map<String, String> headerMap;

    public void mappingHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap != null ? headerMap : Collections.emptyMap();
    }
}
