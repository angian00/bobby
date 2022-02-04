#!/bin/sh

#src=punchy_recording_01.mp4
src=recording_carpet.mp4
tmp=tmp.mp4

#extract relevant segment
#ffmpeg -i $src -ss 00:00:08 -t 00:00:05 $tmp

#extract all frames
#ffmpeg -i $tmp frames/recording_carpet_frame_%03d.bmp
#ffmpeg -i $src frames/recording_carpet_frame_%03d.bmp



i=30
for srcImage in frames/recording_carpet_frame_0[34]*.bmp
do
	convert -extract 50x10+160+135 $srcImage subimage_$i.png
	let "i=i+1"
done
