

@echo off
set "diffFile=./diff_output/test13_diff.txt"
set "inFile=../test_txt/test_input/test13.txt"
set "outFile=../test_txt/test_output/test13_out.txt"
set "expectedFile=../test_txt/test_expected/test13_exp.txt"

echo teszt futtatása...
java -jar ../../../projlab.jar  "%inFile%" "%outFile%"
git diff --output="%diffFile%" --no-index "%outFile%" "%expectedFile%"

call :CheckEmpty "%diffFile%"
goto :eof

:CheckEmpty
if exist "%inFile%" (
    if %~z1 == 0 echo Test PASSED
    if %~z1 NEQ  0 echo Test FAILED
) else (
    echo No inputfile
)
goto :eof