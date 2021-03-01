#
# CS 430 Spring 2019 P2 (Ruby 2)
#
# This file contains definitions for expression tree structures. It is meant to
# be required into other Ruby scripts; running this one by itself has no effect.
#

require_relative 'parser.tab.rb'

#
# Base expression tree node (shouldn't be instantiated)
#
class Expr

  attr_accessor :children

  def initialize
    @children = []    # list of child nodes
  end

  def to_s
    ""
  end

end

#
# Integer expression node
#
class EInt < Expr

  attr_accessor :val    # numeric value

  def initialize (value)
    @children = []
    @val = value
  end

  def to_s
    @val.to_s
  end

end

#
# Binary operator expression node
#
class EBinOp < Expr

  attr_accessor :op     # operator: '+', '-', or '*'

  def initialize (left, right, op)
    @children = [left, right]
    @op = op
  end

  def left
    @children[0]
  end

  def right
    @children[1]
  end

  def to_s
    '(' + @children[0].to_s + @op.to_s + @children[1].to_s + ')'
  end

end

#
# Operator-specific expression nodes
#
class EAdd < EBinOp
  def initialize (left, right)
    super(left, right, '+')
  end
end

class ESub < EBinOp
  def initialize (left, right)
    super(left, right, '-')
  end
end

class EMul < EBinOp
  def initialize (left, right)
    super(left, right, '*')
  end
end

#
# Parse an expression string and build the corresponding expression tree.
#
# This is a wrapper around a lexer/parser that was generated automatically from
# a grammar using the Rex and Racc tools.
#
def parse_expr (str)
  ExprParser.new.scan_str(str)
end
