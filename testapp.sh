#!/bin/bash

if [ -f "./bin/PasswordCriteria.contracts" ]; then 
	rm "./bin/PasswordCriteria.contracts"
fi
	
javac -cp "lib/*" "-Acom.google.java.contract.classpath=lib/cofoja.asm-1.2-20140817.jar:lib/commons-lang3-3.4.jar" "-Acom.google.java.contract.classoutput=bin" -d bin src/*.java

java -cp "bin:lib/*" BugzillaUI