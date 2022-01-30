#!/bin/sh

#src=punchy_recording_01.mp4
#tmp=tmp.mp4

#extract relevant segment
#ffmpeg -i $src -ss 00:00:08 -t 00:00:03 $tmp

#extract all frames

#ffmpeg -i $tmp frames/frame_%03d.bmp



i=10
for srcImage in frames/frame_0[56]*.bmp
do
	convert -extract 40x15+160+125 $srcImage subimage_$i.png
	let "i=i+1"
done
