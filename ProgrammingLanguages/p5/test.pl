%
% CS 430 P5 (Prolog 1)
%

% public tests

test_parent :-
    parent(tricia,tina),
    \+parent(tina,tricia),
    !.

test_step_parent :-
    step_parent(kelly,edward),
    \+step_parent(edward,kelly),
    !.

test_sibling :-
    sibling(tricia,travis),
    \+sibling(tricia,tina),
    !.

test_aunt_uncle :-
    aunt_uncle(tina,gail),
    \+aunt_uncle(tina,edward),
    !.

test_grandparent :-
    grandparent(travis,ethel),
    \+grandparent(travis,tina),
    !.

test_ancestor :-
    ancestor(tricia,ethel),
    \+ancestor(tricia,phil),
    !.

test_relative :-
    relative(tricia,ethel),
    \+relative(tricia,phil),
    !.

test_in_law :-
    in_law(tom,gail),
    \+in_law(tom,grace),
    !.

