#!/bin/sh

projectName=bobby

baseDir=$(dirname "$0")/..
destDir=/Users/angian/Dropbox/prj/game_prog/${projectName}/for_itch.io
zipfile=${projectName}_html.zip
jarfileOrig=desktop-1.0.jar
jarfileDest=${projectName}_desktop_1.0.jar

cd $baseDir

pushd html/build/dist
rm WEB-INF/deploy/html/symbolMaps/*

zip -r $zipfile . -x WEB-INF/lib/* -x WEB-INF/classes/*
mv $zipfile $destDir/

popd

cp ./desktop/build/libs/$jarfileOrig $destDir/$jarfileDest

