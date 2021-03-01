--
-- CS 430 Spring 2019 P3 (Haskell 1)
--
-- Name: Steven Farkas
--

module P3 where

-- A list of all factors of n.
factors :: Integral a => a -> [a]
factors n = filter(\x-> n `mod` x ==0)[1..n]

-- True iff n is prime.
isPrime :: Integral a => a -> Bool
isPrime n = length (factors n) == 2

-- A list of all prime factors of n.
primeFactors :: Integral a => a -> [a]
primeFactors n = filter isPrime (factors n)

-- A list of primes up to n.
primesUpTo :: Integral a => a -> [a]
primesUpTo n = filter isPrime [1..n]

-- True iff n is a perfect number.
-- A number n is perfect if the sum of its factors is 2*n.
isPerfect :: Integral a => a -> Bool
isPerfect n = sum (factors n) == 2 * n

-- A list of all perfect numbers up to n.
perfectUpTo :: Integral a => a -> [a]
perfectUpTo n = filter isPerfect [1..n]

-- The next prime greater than n.
nextPrime :: Integral a => a -> a
nextPrime n
    | isPrime(n + 1) = n + 1
    | otherwise  = nextPrime(n + 1)

-- A list of the first n primes.
generatePrimes :: Integral a => a -> [a]
generatePrimes n = []