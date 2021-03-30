default: CBenchmark CBenchmarkSerial

CBenchmark: CBenchmark.c timer.h
	gcc -g -O2 --std=c99 -fopenmp -Wall -o CBenchmark CBenchmark.c

CBenchmarkSerial: CBenchmarkSerial.c timer.h
	gcc -g -O2 --std=c99 -Wall -o CBenchmarkSerial CBenchmarkSerial.c

clean:
	rm -f CBenchmark CBenchmarkSerial

