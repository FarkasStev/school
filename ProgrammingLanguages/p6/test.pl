%
% CS 430 P6 (Prolog 2)
%

testEvalInt :-
    eval([eint,1], 1),
 \+ eval([eint,1], 0), !.

testEvalAdd :-
    eval([eadd,[eint,1],[eint,2]], 3),
 \+ eval([eadd,[eint,1],[eint,2]], 0), !.

testCountOps1 :-
    countOps([eadd,[eint,1],[eint,2]], 1),
 \+ countOps([eadd,[eint,1],[eint,2]], 0), !.

testHeight1 :-
    height([eint,1], 1),
 \+ height([eint,1], 0), !.

testPostfix1 :-
    postfix([eint,1], [1]),
 \+ postfix([eint,1], []), !.

testUniqInts1p2 :-
    uniqInts([eadd,[eint,1],[eint,2]], [1,2]),
 \+ uniqInts([eadd,[eint,1],[eint,2]], []), !.

