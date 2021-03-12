TARGETS=Main Test

all: $(TARGETS)

Main: Main.hs P4.hs
	ghc -dynamic Main.hs

Test: Test.hs P4.hs
	ghc -dynamic Test.hs

clean:
	rm -f $(TARGETS) *.hi *.o
