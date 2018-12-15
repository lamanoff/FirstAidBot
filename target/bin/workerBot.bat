@REM ----------------------------------------------------------------------------
@REM Copyright 2001-2004 The Apache Software Foundation.
@REM
@REM Licensed under the Apache License, Version 2.0 (the "License");
@REM you may not use this file except in compliance with the License.
@REM You may obtain a copy of the License at
@REM
@REM      http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing, software
@REM distributed under the License is distributed on an "AS IS" BASIS,
@REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM See the License for the specific language governing permissions and
@REM limitations under the License.
@REM ----------------------------------------------------------------------------
@REM

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\org\xerial\sqlite-jdbc\3.25.2\sqlite-jdbc-3.25.2.jar;"%REPO%"\com\petersamokhin\vk-bot-java-sdk\0.1.3\vk-bot-java-sdk-0.1.3.jar;"%REPO%"\org\json\json\20170516\json-20170516.jar;"%REPO%"\org\slf4j\slf4j-log4j12\1.7.25\slf4j-log4j12-1.7.25.jar;"%REPO%"\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar;"%REPO%"\com\sparkjava\spark-core\2.6.0\spark-core-2.6.0.jar;"%REPO%"\org\eclipse\jetty\jetty-server\9.4.4.v20170414\jetty-server-9.4.4.v20170414.jar;"%REPO%"\javax\servlet\javax.servlet-api\3.1.0\javax.servlet-api-3.1.0.jar;"%REPO%"\org\eclipse\jetty\jetty-http\9.4.4.v20170414\jetty-http-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\jetty-util\9.4.4.v20170414\jetty-util-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\jetty-io\9.4.4.v20170414\jetty-io-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\jetty-webapp\9.4.4.v20170414\jetty-webapp-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\jetty-xml\9.4.4.v20170414\jetty-xml-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\jetty-servlet\9.4.4.v20170414\jetty-servlet-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\jetty-security\9.4.4.v20170414\jetty-security-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\websocket\websocket-server\9.4.4.v20170414\websocket-server-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\websocket\websocket-common\9.4.4.v20170414\websocket-common-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\websocket\websocket-client\9.4.4.v20170414\websocket-client-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\jetty-client\9.4.4.v20170414\jetty-client-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\websocket\websocket-servlet\9.4.4.v20170414\websocket-servlet-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\websocket\websocket-api\9.4.4.v20170414\websocket-api-9.4.4.v20170414.jar;"%REPO%"\log4j\log4j\1.2.17\log4j-1.2.17.jar;"%REPO%"\com\firstaid\FirstAidBot\1.0-SNAPSHOT\FirstAidBot-1.0-SNAPSHOT.jar
set EXTRA_JVM_ARGUMENTS=
goto endInit

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS% %EXTRA_JVM_ARGUMENTS% -classpath %CLASSPATH_PREFIX%;%CLASSPATH% -Dapp.name="workerBot" -Dapp.repo="%REPO%" -Dbasedir="%BASEDIR%" com.firstaid.Main %CMD_LINE_ARGS%
if ERRORLEVEL 1 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=1

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@endlocal

:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
