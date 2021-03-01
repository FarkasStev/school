#
# CS 430 P1 (Ruby 1)
#

require "test/unit"
require_relative "p1.rb"

class P1Test < Test::Unit::TestCase

  def test_factors_1
    assert_equal([1], factors(1))
  end

  def test_factors_12
    assert_equal([1,2,3,4,6,12], factors(12))
  end

  def test_primes_10
    assert_equal([2,3,5,7], primes(10))
  end

  def test_prime_factors_1
    assert_equal([], prime_factors(1))
  end

  def test_prime_factors_12
    assert_equal([2,3], prime_factors(12))
  end

  def test_perfects_10
    assert_equal([6], perfects(10))
  end

  def test_pythagoreans_15
    assert_equal([[3,4,5],[5,12,13],[6,8,10],[9,12,15]], pythagoreans(15))
  end

end
