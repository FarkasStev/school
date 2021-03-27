#!/bin/bash

#PROLOG="gprolog"
PROLOG="swipl"

FILES="facts.pl p5.pl test.pl"
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

run_query "test_parent"
run_query "test_step_parent"
run_query "test_sibling"
run_query "test_aunt_uncle"
run_query "test_grandparent"
run_query "test_ancestor"
run_query "test_relative"
run_query "test_in_law"

pct=$(echo "scale=1; $NUMYES/$TOTAL*100" | bc | sed -e's/\..*$//')
echo "$pct% passed"

