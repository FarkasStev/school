%
% CS 430 P6 (Prolog 2)
%
% Name: Steven Farkas
%

eval([T,X], X) :- T = eint.
eval([T,Z,Y], X) :- T = eadd, eval(Z, A),  eval(Y, B), X is A + B.
eval([T,Z,Y], X) :- T = emul, eval(Z, A),  eval(Y, B), X is A * B.
eval([T,Z,Y], X) :- T = esub, eval(Z, A),  eval(Y, B), X is A - B.


countOps([_,_], 0).
countOps([_,Z,Y], X) :- countOps(Z,A), countOps(Y,B), X is A + B + 1.

height([_,_], 1).
height([_,Z,Y], X) :- height(Z,A), height(Y,B), A >= B, X is A + 1.
height([_,Z,Y], X) :- height(Z,A), height(Y,B), A < B, X is B + 1.


postfix([T,X], [X]) :- T = eint.
postfix([T,Z,Y], X) :- T = eadd, postfix(Z, A), postfix(Y, B), append(A, B, N), append(N, [+], X).
postfix([T,Z,Y], X) :- T = emul, postfix(Z, A), postfix(Y, B), append(A, B, N), append(N, [*], X).
postfix([T,Z,Y], X) :- T = esub, postfix(Z, A), postfix(Y, B), append(A, B, N), append(N, [-], X).


uniqInts([_,X], [X]).
uniqInts([_,Z,Y], X) :- uniqInts(Z,A), uniqInts(Y,B), append(A,B,C), sort(C,X).

