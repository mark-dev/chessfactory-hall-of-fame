#!/usr/bin/env bash

convert imgbackup/*.png -resize 256x256 -set filename:original %t "%[filename:original].png"