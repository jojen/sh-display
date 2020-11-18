#!/bin/sh
base_dir=$(dirname $(dirname $(realpath $0)) )
pic_dir="$base_dir/waveshare/pic"

echo "source: $1"
echo "base_dir: $base_dir"
echo "pic_dir: $pic_dir"

xvfb-run --server-args="-screen 0, 800x480x24" cutycapt --url=$1 --out=$pic_dir/screen.png
convert $pic_dir/screen.png -crop 800x480 -colorspace gray -depth 2 -t                    ype truecolor $pic_dir/screen.bmp
python3 $base_dir/waveshare/py/updatescreen.py