#!/bin/bash

make

rm -rf init mult print

echo -e "\n== BASELINE =="
./hello -nl 1

echo -e "\n\n== PARALLEL =="
echo -e "\n1 thread:"
./hello -nl 1 --noDebug --N=5000000 --dataParTasksPerLocale=1
echo -e "\n2 threads:"
./hello -nl 1 --noDebug --N=5000000 --dataParTasksPerLocale=2
echo -e "\n4 threads:"
./hello -nl 1 --noDebug --N=5000000 --dataParTasksPerLocale=4
echo -e "\n8 threads:"
./hello -nl 1 --noDebug --N=5000000 --dataParTasksPerLocale=8
echo -e "\n16 threads:"
./hello -nl 1 --noDebug --N=5000000 --dataParTasksPerLocale=16

echo -e "\n\n== DISTRIBUTION =="
echo -e "\n1 locale:"
./hello -nl 1 --noDebug --N=20000 --dataParTasksPerLocale=8 | sort -n
echo -e "\n2 locales:"
./hello -nl 2 --noDebug --N=20000 --dataParTasksPerLocale=8 | sort -n
echo -e "\n4 locales:"
./hello -nl 4 --noDebug --N=20000 --dataParTasksPerLocale=8 | sort -n
echo -e "\n8 locales:"
./hello -nl 8 --noDebug --N=20000 --dataParTasksPerLocale=8 | sort -n
./hello -nl 8 --vdebug | sort -n

