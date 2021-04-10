#!/bin/bash

#PROLOG="gprolog"
PROLOG="swipl"

FILES="p6.pl test.pl"
TMP="_tmp.pl"

NUMYES=0
NUMNO=0
TOTAL=0

run_query() {
    echo -n "$1: "
    cat $FILES >"$TMP"
    if [ "$PROLOG" == "gprolog" ]; then
        r="$(gprolog --consult-file $TMP --query-goal "$1" <<< "fail." | grep -oE "(yes|no)$" | head -n 1)"
    elif [ "$PROLOG" == "swipl" ]; then
        r="no" && swipl -qt "$1." $TMP && r="yes"
    fi
    rm -f "$TMP"
    echo "$r"
    if [ "$r" == "yes" ]; then
        NUMYES=$((NUMYES+1))
    else
        NUMNO=$((NUMNO+1))
    fi
    TOTAL=$((TOTAL+1))
}

# public tests
run_query "testEvalInt"
run_query "testEvalAdd"
run_query "testCountOps1"
run_query "testHeight1"
run_query "testPostfix1"
run_query "testUniqInts1p2"

# private tests

pct=$(echo "scale=1; $NUMYES/$TOTAL*100" | bc | sed -e's/\..*$//')
echo "$pct% passed"

