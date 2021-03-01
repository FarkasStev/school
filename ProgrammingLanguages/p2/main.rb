#!/usr/bin/env ruby
#
# CS 430 Spring 2019 P2 (Ruby 2)
#
# This file contains a simple REPL (read/eval/print loop) for expression trees.
#

require_relative 'p2.rb'

puts 'Expression tree calculator'
puts '"q" to quit.'
calc = ExprParser.new
while true

  # prompt for the next expression
  print '>>> '; $stdout.flush
  str = $stdin.gets
  break if str.nil?
  str.strip!
  break if /q/i === str

  # parse the expression
  begin
    expr = calc.scan_str(str)
    puts 'Expression:  ' + expr.to_s
  rescue ParseError => e
    puts 'Parse error: ' + e.to_s
  end

  # run each required function

  begin
    puts 'Evaluation:  ' + expr.eval.to_s
  rescue NoMethodError
    puts 'Evaluation:  Not implemented'
  end

  begin
    puts 'Count Ops:   ' + expr.count_ops.to_s
  rescue NoMethodError
    puts 'Count Ops:   Not implemented'
  end

  begin
    puts 'Height:      ' + expr.height.to_s
  rescue NoMethodError
    puts 'Height:      Not implemented'
  end

  begin
    puts 'Postfix:     "' + expr.postfix + '"'
  rescue NoMethodError
    puts 'Postfix:     Not implemented'
  end

  begin
    puts 'Unique Ints: ' + expr.uniq_ints.to_s
  rescue NoMethodError
    puts 'Unique Ints: Not implemented'
  end
end
