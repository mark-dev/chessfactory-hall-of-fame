#!/bin/bash

# TODO: wget has options for this scenario, just pass file
# https://database.lichess.org/standard/list.txt
while IFS='' read -r line || [[ -n "$line" ]]; do
    wget $line
done < "$1"
