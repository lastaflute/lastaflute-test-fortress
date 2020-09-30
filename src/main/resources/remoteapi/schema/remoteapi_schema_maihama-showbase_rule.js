// _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
// RemoteApiGen your rule settings as ECMAScript5 (related to RemoteApiRule.js in freegen)
// _/_/_/_/_/_/_/_/_/_/
// =======================================================================================
//                                                                                  Option
//                                                                                  ======
//
// -----------------------------------------------------
//                                          Type Mapping
//                                          ------------
// @Override
remoteApiRule.target = function(api) { // you can select generated API 
    if (baseRule.target(api)) { // don't forget calling super's
        // and define your original selecting (except)
        if (api.url.indexOf('/method/onbodyjson') !== -1 && api.httpMethod === 'get') { // get$onbodyjson()
            return false; // unsupported at RemoteApiGen for now (you can request by your manual method)
        }
        return true;
    } else {
        // no param/return is not generated as default, so specify it
        // HTTP METHOD determination is for excepting "parameters"
        return api.url.indexOf('/wx/remogen/tricky/allnone') !== -1 && api.httpMethod === 'post';
        // general way however also needs undefined determination? (api.consumes is undefined in other project)
        //return (api.consumes === null || api.consumes.length === 0) && (api.produces === null || api.produces.length === 0);
    }
}

// @Override
remoteApiRule.typeMap = function() {
    var typeMap = baseRule.typeMap();
    typeMap['array'] = 'org.eclipse.collections.api.list.ImmutableList';
    return typeMap;
}

// name and type mapping for e.g. classification
var manualMappingClassMap = {
    'memberStatus': 'org.docksidestage.dbflute.allcommon.CDef.MemberStatus',
    'selectedReason': 'org.docksidestage.dbflute.allcommon.CDef.WithdrawalReason'
};

// @Override
remoteApiRule.pathVariableManualMappingClass = function(api, pathVariable) {
    return manualMappingClassMap[pathVariable.name];
}

// @Override
remoteApiRule.beanPropertyManualMappingClass = function(api, beanClassName, property) {
    return manualMappingClassMap[property.name];
}
