#Steven Farkas
puts "Hello world"
x = -5
if x < 0
  puts "negative"
else
  puts "positive"
end
x = 10
while x > 0 
  puts x if x.even?
  x -= 1
end
r = rand(100)
case
when r.even?
  print r, " is even\n"
when r < 10
  print r, " is small\n"
else
  print r, " is big and odd\n"
end

puts self, self.class

def genArray(n=10)
  s = Array.new(n)
  s.each_with_index{ | w, i | s[i] = i+ 1}
  return s
end

def genArr(n=10, array)
  if n == 0
    array[0] = 0
    return array
  end
  return genArr(n - 1, array) + [n]
end

print genArray, "\n"
s =  Array.new
print genArr(10, s), "\n"