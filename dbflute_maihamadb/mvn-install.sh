#!/bin/bash

cd `dirname $0`
cd ..

pushd ../maihama-base
mvn -e install

pushd ../maihama-common
rm -f target/maihama-common.jar
mvn -e install -Dmaven.test.skip=true
popd
