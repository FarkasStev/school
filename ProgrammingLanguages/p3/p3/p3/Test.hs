--
-- CS 430 Spring 2019 P3 (Haskell 1)
--

module Main where
import P3

testFactors =
  [(factors 1) == [1],
   (factors 12) == [1,2,3,4,6,12]]

testIsPrime =
  [(isPrime 3),
   not (isPrime 4)]

testPrimeFactors =
  [primeFactors 12 == [2,3]]

testPrimesUpTo =
  [primesUpTo 5 == [2,3,5]]

testIsPerfect =
  [not (isPerfect 2),
        isPerfect 6]

testPerfectUpTo =
  [perfectUpTo 27 == [6]]

testNextPrime =
  [nextPrime 5 == 7]

testGeneratePrimes =
  [generatePrimes 1 == [2]]

testAll = testFactors ++ testIsPrime ++ testPrimeFactors ++ testPrimesUpTo ++
          testIsPerfect ++ testPerfectUpTo ++ testNextPrime ++ testGeneratePrimes

countCorrect []         = 0
countCorrect (True:xs)  = 1 + (countCorrect xs)
countCorrect (False:xs) =     (countCorrect xs)

buildRatioStr results =
  let correct = countCorrect results
      total   = length results
  in (show correct) ++ "/" ++ (show total)

calcRatio results =
  let correct = countCorrect results
      total   = length results
  in truncate $ (fromIntegral correct) / (fromIntegral total) * 100.0

summarize results =
  let key = map (\t -> if t then '.' else 'X') results
  in (buildRatioStr results) ++ "   " ++ key

main = do
  let allTestResults = testAll
  let result = "  Factors:         " ++ (summarize testFactors)     ++ "\n" ++
               "  IsPrime:         " ++ (summarize testIsPrime) ++ "\n" ++
               "  PrimeFactors:    " ++ (summarize testPrimeFactors)   ++ "\n" ++
               "  PrimesUpTo:      " ++ (summarize testPrimesUpTo)  ++ "\n" ++
               "  IsPerfect:       " ++ (summarize testIsPerfect) ++ "\n" ++
               "  PerfectUpTo:     " ++ (summarize testPerfectUpTo) ++ "\n" ++
               "  NextPrime:       " ++ (summarize testNextPrime) ++ "\n" ++
               "  GeneratePrimes:  " ++ (summarize testGeneratePrimes) ++ "\n" ++
               "  TOTAL:           " ++ (buildRatioStr allTestResults)     ++ "\n" ++
               "  " ++ (show $ calcRatio allTestResults) ++ "% passed"
  putStrLn $ "\n" ++ result

