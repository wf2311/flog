#!/usr/bin/env bash
cd ./spring-boot-starter-flog/
mvn clean deploy --settings ./../.maven.xml -DskipTests=true -Dadditionalparam=-Xdoclint:none -Dmaven.javadoc.skip=true -B -U -Prelease