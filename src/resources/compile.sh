#!/bin/bash

JAVA_HOME=~/opt/jdk1.7.0_111
JAVA_BIN=$JAVA_HOME/bin
SERVLET_API=~/opt/JBossAS7.1-eclipse/jboss-as-7.1.1.Final/modules/javax/servlet/api/main/jboss-servlet-api_3.0_spec-1.0.0.Final.jar

$JAVA_BIN/javah -jni -d . -classpath .:$SERVLET_API:../../build/classes com.olleb.bad.core.CoreServlet

gcc -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/linux" -fPIC -shared -L/usr/lib -o libcore-$(arch).so core.c

