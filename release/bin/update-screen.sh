#!/bin/sh
base_dir=$(dirname $(dirname $(realpath $0)) )
pic_dir="$base_dir/waveshare/pic"

xvfb-run --server-args="-screen 0, 800x480x24" cutycapt --delay=2000 --url=$1 --out=$pic_dir/screen.png
convert $pic_dir/screen.png -gravity center -crop 800x480+0+0 -threshold 80% -monochrome $pic_dir/screen.bmp
python3 $base_dir/waveshare/py/update-screen.py