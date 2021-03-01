#
# CS 430 P1 (Ruby 1)
#
# Name: Steven Farkas
#

# return an array of all factors of n
def factors (n)
  return (1..n).select {| x | n % x == 0}.to_a
end

# return an array of all prime numbers less than or equal to n
def primes (n)
  return (1..n).select {| x | factors(x).length == 2}
end

# return an array of all prime factors of n
def prime_factors (n)
  return factors(n) & primes(n)
end

# return an array of all perfect numbers less than or equal to n
def perfects (n)
  return (1..n).select {| x | factors(x).uniq.sum / 2 == x}
end

# return an array of Pythagorean triples whose elements are less than or equal to n
def pythagoreans (n)
  triples = []
  a = 1
  b = 1
  c = 1

  for c in (1..n)
    for b in (1..c)
      for a in (1..b)
        if a**2 + b**2 == c**2
          triples << [a,b,c]
        end
      end
    end
  end

  return triples.sort
end
