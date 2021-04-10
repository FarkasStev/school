% size(L, S) == "the size of the list L is S"

size([], 0).

% size(h:t) = 1+size(t)
size([_|Tail], Length) :- 
    size(Tail, LenOfTail),
    Length is LenOfTail + 1.

% end(l,x) == "the last emement in L is X"

end([], _) :- fail.
end([X], X).
end([_|T], E) :- end(T, E). 
  

% max (L,X) == "the maximum element in L is X"


max([], _) :- fail.
max([X], X).
max([H|T], H) :- max(T, MT), H >= MT.   % H is the max
max([H|T], MT) :- max(T, MT), H < MT.  % H is NOT the max


% cat(A, B, C) == "the concatenation of A and B is C"
%               C == A ++ B (Haskell)


cat([], B, B).
cat([A|T], B, [A|Tmp]) :- cat(T, B, Tmp).





% [empty]         == "empty tree"  
% [node, X, L, R] == "tree rooted at X with left subtree L 
%                                      and right subtree R"

% top-level: invoke recursive insertion on an empty tree
bst(L,T) :- build_bst(L, [empty], T), !.

% empty list: no tree modifications necessary
build_bst([], T, T).

% non-empty list: insert the head and recurse on the tail
build_bst([X|XS], T, TTT) :- insert_bst(X, T, TT), build_bst(XS, TT, TTT).

% empty tree: create new node
insert_bst(V, [empty], [node,V,[empty],[empty]]).

% binary node: insert into left or right subtree
insert_bst(V, [node,X,L,R], [node,X,LL,R]) :- V =< X, insert_bst(V,L,LL).
insert_bst(V, [node,X,L,R], [node,X,L,RR]) :- V  > X, insert_bst(V,R,RR).


% find(X,T) == "the element X is present in tree T"

find(_, [empty]) :- fail.
find(X, [node, X, _, _]).
find(X, [node, Y, L, _]) :- X < Y, find(X, L).
find(X, [node, Y, _, R]) :- X > Y, find(X, R).


find_bst(X,L) :- bst(L,T), find(X,T), !.




% sorted(T, L)  == "flattening tree t gives you list L"

sorted([empty], []).
sorted([node, X, L, R], S) :-
    sorted(L, LS), sorted(R, RS),
    cat(LS, [X], tmp), cat(tmp, RS, S).

sorted_bst(L,S) :- bst(L,T), sorted(T,S), !.
