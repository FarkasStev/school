--
-- CS 430 P4 (Haskell 2)
--
-- Name: Steven Farkas
--

module P4 where
import Defs

-- calculate the result of an expression
eval :: Expr -> Int
eval (EInt value) = value 
eval (EAdd left right) = eval left + eval right
eval (ESub left right) = eval left - eval right
eval (EMul left right) = eval left * eval right

-- count the total number of arithmetic operations in an expression
countOps :: Expr -> Int
countOps (EInt _) = 0
countOps (EAdd left right) = 1 + countOps left + countOps right
countOps (ESub left right) = 1 + countOps left + countOps right
countOps (EMul left right) = 1 + countOps left + countOps right

-- calculate the height of the expression tree
-- height of one node = 1
height :: Expr -> Int
height (EInt _) = 1
height (EAdd left right) 
  | height left > height right = height left
  | otherwise = height right
height (ESub left right)
  | height left > height right = height left
  | otherwise = height right
height (EMul left right)
  | height left > height right = height left
  | otherwise = height right
   

-- flatten the expression into a postfix string representation
-- use "(show i)" to convert int i to a string
postfix :: Expr -> String
postfix (EInt i) = show i
postfix (EAdd left right) = postfix left ++ " " ++ postfix right ++ " +" 
postfix (ESub left right) = postfix left ++ " " ++ postfix right ++ " -" 
postfix (EMul left right) = postfix left ++ " " ++ postfix right ++ " *" 



-- extract a sorted list of all unique integers in an expression
uniqInts :: Expr -> [Int]
uniqInts (EInt value) = value:[]
uniqInts (EAdd left right) = (sort (uniqInts left ++ uniqInts right))

uniq :: Eq t => [t] -> [t]
uniq [] = []
uniq (x:xs)
  | x == head xs =  uniq xs
  | otherwise = x : uniq xs

sort :: Ord t => [t] -> [t]
sort [] = []
sort (x:xs) = 
  let less = sort [t | t <- xs, t <= x]
      more = sort [t | t <- xs, t > x]
  in less ++ [x] ++ more
