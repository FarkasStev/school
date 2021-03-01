TARGET=pth_condvar
CFLAGS=-g -O0 --std=c99 -Wall -pedantic

test: $(TARGET)
	valgrind --tool=helgrind ./$(TARGET)

$(TARGET): pth_condvar.c
	gcc $(CFLAGS) -o $@ $< -lpthread

clean:
	rm -f $(TARGET)
