#!/bin/sh
base_dir=$(dirname $(dirname $(realpath $0)) )
pic_dir="$base_dir/waveshare/pic"

xvfb-run --server-args="-screen 0, 480x800x24" cutycapt --url=$1 --out=$pic_dir/screen.png
convert $pic_dir/screen.png -rotate 90 -gravity center -crop 800x480+0+0 -threshold 90% -monochrome $pic_dir/screen.bmp
python3 $base_dir/waveshare/py/update-screen.py