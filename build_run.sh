#!/bin/bash


while true; do 
    read -p "Compile all java files ?? " yn
    case $yn in 
        [Yy]* ) 
            # NOTE executing compiling the entire project
            echo "// NOTE " 1>>./logs/output2.log; date 1>>./logs/output2.log
            echo "// NOTE " 1>>./logs/output1.log; date 1>>./logs/output1.log
            echo "compilation" 1>>./logs/output1.log
            echo "compilation" 1>>./logs/output2.log
            time javac -d . *.java 1>>./logs/output1.log 2>>./logs/output2.log
            break;;
        [Nn]* ) exit;;
        *) echo "Please answer yes or no.";;
    esac
done
while true; do 
    read -p "Run the main class " yn
    case $yn in 
        [Yy]* )  echo "
------------Launching the main class------------
            "
            echo "// NOTE " 1>>./logs/output2.log; date 1>>./logs/output2.log
            echo "// NOTE " 1>>./logs/output1.log; date 1>>./logs/output1.log
            echo "Running $1" 1>>./logs/output1.log
            echo "Running $1" 1>>./logs/output2.log
            time java "$1" 1>>./logs/output1.log 2>>./logs/output2.log
            echo "
------------Exiting the main class------------

            "
            break            
        ;;
        [Nn]* ) exit;;
        *) echo "Please answer yes or no.";;
    esac
done
while true; do
    read -p "Continue ?? " yn
    case $yn in
        [Yy]* ) bash build_run.sh $1
            break;;
        [Nn]* ) exit;;
        *) echo "Please answer yes or no.";;
    esac
done