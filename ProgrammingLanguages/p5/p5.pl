%
% CS 430 P5 (Prolog 1)
%
% Name: Steven Farkas
%

parent(X,Y) :- parents(X,_,Y).
parent(X,Y) :- parents(X,Y,_).

step_parent(X,Y) :- \+parent(X,Y), spouse(Y,Z), parent(X,Z).

sibling(X,Y) :- parent(X,Z), parent(Y,Z), X\==Y.

aunt_uncle(X,Y) :- parent(X,Z), sibling(Y,Z).

grandparent(X,Y) :- parent(X,Z), parent(Z,Y).

ancestor(X,Y) :- parent(X,Y).
ancestor(X,Y) :- parent(X,Z), ancestor(Z,Y).

relative(X,Y) :- ancestor(X,Z), ancestor(Y,Z).

in_law(X,Y) :- spouse(X,Z), relative(Z,Y), \+relative(X,Y).
