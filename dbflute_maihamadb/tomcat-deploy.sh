#!/bin/bash

cd `dirname $0`
cd ..

TOMCAT_DIR=tomcat8
TOMCAT_BASE=../../../$TOMCAT_DIR

pushd $TOMCAT_BASE/bin
sh shutdown.sh
popd

pushd ../../saflute/saflute
mvn -e install -Dmaven.test.skip=true
popd

pushd ../maihama-base
mvn -e install
popd

pushd ../maihama-common
rm -f target/maihama-common.jar
mvn -e install -Dmaven.test.skip=true
popd

pushd ../maihama-dockside
rm -Rf target/war
rm -f target/*.war
mvn -e clean package -Dmaven.test.skip=true
rm -Rf $TOMCAT_BASE/webapps/dockside
rm -f $TOMCAT_BASE/webapps/dockside.war
cp ./target/maihama-dockside.war $TOMCAT_BASE/webapps/dockside.war
popd

pushd $TOMCAT_BASE/bin
sh startup.sh
popd

cd dbflute_maihamadb
sh manage.sh refresh saflute
sh manage.sh refresh $TOMCAT_DIR
sh manage.sh refresh
