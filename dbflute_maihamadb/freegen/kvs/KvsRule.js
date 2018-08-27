// Based on ECMAScript5. Because Nashorn of java 8 is ECMAScript5.
// =======================================================================================
//                                                                              Definition
//                                                                              ==========

var baseRule = {

    // ===================================================================================
    //                                                                               Base
    //                                                                              ======
    schema: function(request) {
        if (request.tableMap.schema !== null) {
            return request.tableMap.schema;
        }
        return manager.initUncap(request.requestName.replace(/^Kvs(Cache|Store)/g, ''));
    },

    schemaShort: function(request) {
        if (request.tableMap.containsKey('schemaPrefix')) {
            return request.tableMap.schemaPrefix ? request.tableMap.schemaPrefix : '';
        }
        return manager.initCap(this.schema(request));
    },

    kvsPoolDiFile: function(request) {
        if (request.tableMap.kvsPoolDiFile) {
            return request.tableMap.kvsPoolDiFile;
        }
        throw new Error('override kvsPoolDiFile(request)');
    },

    // ===================================================================================
    //                                                                               Cache
    //                                                                               =====

    // ===================================================================================
    //                                                                              Option
    //                                                                              ======
    /**
     * Return java import order list.
     * @return java import order list. (NotNull)
     */
    importOrderList: function() {
        return ['java', 'javax', 'junit', 'org', 'com', 'net', 'ognl', 'mockit', 'jp'];
    },

    /**
     * Return java import order list.
     * @return java import order list. (NotNull)
     */
    converterHandler: function() {
        // MapListString Instantiate everytime because of the statefulness of...
        return {
            'toMapString': 'new org.dbflute.helper.dfmap.DfMapStyle().toMapString(map)',
            'fromMapString': 'new org.dbflute.helper.dfmap.DfMapStyle().fromMapString(mapString)'
        };
    },

    // ===================================================================================
    //                                                                                 Doc
    //                                                                                 ===
    docGeneration : true
};

var kvsRule = {};
for (var i in baseRule) {
    kvsRule[i] = baseRule[i];
}
