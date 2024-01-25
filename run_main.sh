#!/bin/bash

while true; do 
    read -p "Run this class $1 ?? 
Please be aware with the cases
Answer = " yn
    case $yn in 
        [Yy]* ) 
            echo "
------------Launching the main class------------
            "
            echo "// NOTE " 1>>./logs/output2.log; date 1>>./logs/output2.log
            echo "// NOTE " 1>>./logs/output1.log; date 1>>./logs/output1.log
            time java "$1" 1>>./logs/output1.log 2>>./logs/output2.log
            echo "
------------Exiting the main class------------

            "
            break            
        ;;
        [Nn]* ) break;;
        *) echo "You must choose yes or no";;
    esac
done
while true; do 
    read -p "Exit (Ee) or continue (Cc) ?? " ec
    case $ec in 
        [Ee]* ) exit;;
        [Cc]* ) bash run_main.sh "$1";;
        * ) echo "Please choose an answer";;
    esac
done