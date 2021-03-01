# Steven Farkas
require "test/unit"

class Node

    attr_accessor :value, :left, :right

    def initialize(value)
        @value = value
        @left = nil
        @right = nil
    end


end

class BST

    attr_reader :size

    def initialize
        @size = 0
        @root = nil
    end

    def insert(value)
        @root = Node.new(value) if @root.nil?()
        
        
    end

    def find(value)
        return search(@root, value)
    end

    def search(node, value)
        return false if node.nil?
        return node if node.value == value
        return node.left if value < node.value
        return node.right if value > node.value

    end

    private :search
end


class BST_Test < Test::Unit::TestCase
    def test_node
        node = Node.new("abc")
        assert_equal("abc", node.value)
    end

    def test_insert_find
        tree = BST.new
        tree.insert(1)
        tree.insert(2)
        tree.insert(3)
        assert(tree.find(1))
        assert_equal(3, tree.size)
    end
    
end