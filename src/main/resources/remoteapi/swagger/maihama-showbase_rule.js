// override rule.
var manualMappingClassMap = {
    'memberStatus': 'org.docksidestage.dbflute.allcommon.CDef.MemberStatus',
    'selectedReason': 'org.docksidestage.dbflute.allcommon.CDef.WithdrawalReason'
};
var pathVariableManualMappingClass = function(api, pathVariable) {
    return manualMappingClassMap[pathVariable.name];
}

var beanPropertyManualMappingClass = function(api, beanClassName, property) {
    return manualMappingClassMap[property.name];
}
