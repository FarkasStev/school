%
% CS 430 P5 (Prolog 1)
%

% spouse(X,Y) is symmetric
spouse(mary,edward).
spouse(edward,mary).
spouse(gail,phil).
spouse(phil,gail).
spouse(ethel,tom).
spouse(tom,ethel).
spouse(jean,frank).
spouse(frank,jean).
spouse(tina,tim).
spouse(tim,tina).

% Parents are biological parents
% parents(X,Y,Z) is read X has parents Y and Z.
parents(kelly,bill,mary).
parents(gail,mary,edward).
parents(ethel,mary,edward).
parents(jean,gail,phil).
parents(maggie,grace,chuck).
parents(tina,chuck,ethel).
parents(harry,jean,frank).
parents(tricia,tina,tim).
parents(travis,tina,tim).
