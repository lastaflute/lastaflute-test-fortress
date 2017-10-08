// override rule.
var pathVariableClassificationDeployment = function(api, pathVariable) {
    var map = {};
    return map[pathVariable.name];
}

var beanPropertyClassificationDeployment = function(api, beanClassName, property) {
    var map = {
        'memberStatus': 'org.docksidestage.dbflute.allcommon.CDef.MemberStatus',
        'selectedReason': 'org.docksidestage.dbflute.allcommon.CDef.WithdrawalReason'
    };
    return map[property.name];
}
