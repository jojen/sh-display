#!/bin/sh
xvfb-run --server-args="-screen 0, 800x480x24" cutycapt --url=http://localhost:8080/ --out=screen.bmp