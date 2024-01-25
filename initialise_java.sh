#!/bin/bash


echo "Launching "
while true; do
    read -p "Initialize java project : " yn
    case $yn in 
        [Yy]* ) 
            mkdir ./logs
            touch ./logs/output1.log ./logs/output2.log
            echo "// NOTE " 1>>./logs/output2.log
            echo "// NOTE " 1>>./logs/output1.log
            date 1>>./logs/output1.log
            date 1>>./logs/output2.log
            echo "Initied Workspace by " 1>>./logs/output2.log
            echo "Initied Workspace by " 1>>./logs/output1.log
            whoami 1>>./logs/output1.log
            whoami 1>>./logs/output2.log
            mkdir ./.git
            echo "Initied git repertory " 1>>./logs/output2.log
            echo "Initied git repertory " 1>>./logs/output1.log
            break;;
        [Nn]* ) exit;;
        *) echo "Please answer yes or no";;
    esac
done
while true; do 
    read -p "Create the main file " yn 
    case $yn in 
        [Yy]*)  read -p "What's is name " name
                touch ./"$name".java 
        break;;
        [Nn]*) exit;;
        *) echo "Please answer yes or no.";;
    esac
done
while true; do 
    read -p "Launch Visual Studio Code : " yn 
    case $yn in 
        [Yy]*)  code .
                echo "Visual Studio Code launched"
            break;;
        [Nn]*) exit;;
        *) echo "Please answer yes or no.";;
    esac
done
echo "Exiting..."
echo "Bye"