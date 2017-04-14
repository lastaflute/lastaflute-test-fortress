#!/bin/bash

cd `dirname $0`
cd ..

TOMCAT_BASE=../../../tomcat8

pushd $TOMCAT_BASE/logs
tail -f catalina.out
popd
