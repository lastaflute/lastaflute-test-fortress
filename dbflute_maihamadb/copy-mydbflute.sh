#!/bin/bash

cd `dirname $0`

rm -R ../mydbflute/dbflute-1.x
cp -R ~/works/dbflute/java8/workspace/dbflute-test-active-dockside/mydbflute/dbflute-1.x/ ../mydbflute/dbflute-1.x

. manage.sh refresh
