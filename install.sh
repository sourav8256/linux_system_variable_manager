#!/usr/bin/env bash

cp pathmanager.jar /usr/local/bin;
chmod +x /usr/local/bin/pathmanager.jar;
cp pathmanager.desktop ~/.local/share/applications;
chmod +x ~/.local/share/applications/pathmanager.desktop;
mkdir /usr/share/pathmanager
cp icon.webp /usr/share/pathmanager
echo "probably installed IDK LOL!";
