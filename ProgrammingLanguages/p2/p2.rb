#
# CS 430 Spring 2019 P2 (Ruby 2)
#
# Name: Steven Farkas
#

require_relative 'defs.rb'

class EInt
    def eval
        return val
    end

    def postfix
        return to_s
    end

    def count_ops
        return 0
    end

    def height
        return 1
    end

    def uniq_ints
        return [val]
    end
end


class EAdd
    def eval
        return children[0].eval + children[1].eval
    end
end

class ESub
    def eval
        return children[0].eval - children[1].eval
    end
end 

class EMul
    def eval
        return children[0].eval * children[1].eval
    end 
end

class EBinOp
    def postfix
        return children[0].postfix + ' ' + children[1].postfix + ' ' + op.to_s
    end

    def count_ops
        return 1 + children[0].count_ops + children[1].count_ops
    end

    def height
        return 1 + children[0].height
    end

    def uniq_ints
        nums = children[0].uniq_ints + children[1].uniq_ints
        return nums.uniq.sort
    end
end