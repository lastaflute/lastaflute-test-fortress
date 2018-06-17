#!/bin/bash

cd `dirname $0`
. ./_project.sh

export answer=y

sh ./manage.sh replace-schema
taskReturnCode=$?

if [ $taskReturnCode -ne 0 ];then
  exit $taskReturnCode;
fi
