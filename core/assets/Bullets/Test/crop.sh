INDEX=0
left=0
top=0
hei=1024
wid=2048
var=256
one=1
while [ $top -lt $hei ]
do
	left=0
	while [ $left -lt $wid ]
	do
		convert explosion.png -crop 256x256+$left+$top explosion_$INDEX.png
		left=`expr $left + $var`
		INDEX=`expr $INDEX + $one`
	done
	top=`expr $top + $var`
done

