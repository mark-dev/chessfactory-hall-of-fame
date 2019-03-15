#!/bin/bash

# https://database.lichess.org/standard/list.txt
while IFS='' read -r line || [[ -n "$line" ]]; do
    wget $line
done < "$1"
