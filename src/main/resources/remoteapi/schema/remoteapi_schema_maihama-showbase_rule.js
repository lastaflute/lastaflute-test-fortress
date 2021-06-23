// _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
// RemoteApiGen your rule settings as ECMAScript5 (related to RemoteApiRule.js in freegen)
// _/_/_/_/_/_/_/_/_/_/

// ======================================================================================
//                                                                               Behavior
//                                                                               ========
// generate hierarchical behaviors if resources hanving many nests
// @Override
remoteApiRule.behaviorSubPackage = function(api) {
    if (api.url.indexOf('/ballet-dancers/') >= 0) { // e.g. resources having many nests
        return this.subPackage(api).replace(/^([^.]*)\.(.+)/, '$1.$2'); // default is $1 only
    } else {
        return baseRule.behaviorSubPackage(api);
    }
}

// @Override
remoteApiRule.behaviorRequestMethodName = function(api) {
    if (api.url.indexOf('/ballet-dancers/') >= 0 || api.url.indexOf('/products/') >= 0) {
        // always HTTP Method on request method
        var methodPart = manager.camelize(this.subPackage(api).replace(this.behaviorSubPackage(api), '').replace(/\./g, '_'));
        return 'request' + manager.initCap(methodPart) + (api.httpMethod ? manager.initCap(api.httpMethod) : '');
    } else {
        return baseRule.behaviorRequestMethodName(api);
    }
}

// @Override
remoteApiRule.behaviorRuleMethodName = function(api) {
    if (api.url.indexOf('/ballet-dancers/') >= 0 || api.url.indexOf('/products/') >= 0) {
        // always HTTP Method on rule method
        var methodPart = manager.camelize(this.subPackage(api).replace(this.behaviorSubPackage(api), '').replace(/\./g, '_'));
        return 'ruleOf' + manager.initCap(methodPart) + (api.httpMethod ? manager.initCap(api.httpMethod) : '');
    } else {
        return baseRule.behaviorRuleMethodName(api);
    }
}


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
    } else { // already no way here since (2019/08/31) 
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // logic of RemoteApiGen's rule.js was already changed so this is old code
        // _/_/_/_/_/_/_/_/_/_/
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
