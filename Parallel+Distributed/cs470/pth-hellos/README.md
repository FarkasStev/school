# CS 470 Condition Activity

1. Examine the source code and make sure you understand what it does. Predict
   the program’s output.

2. Run the program multiple times with different thread counts observe the
   resulting outputs. What are the patterns you observe, and why do they occur?

3. Modify the program so that all of the non-master threads say “hello” before
   the master thread says “hello.” After the master thread says “hello,” the
   other threads should all say “goodbye” before the master says “goodbye.” The
   order of the messages for individual non-master threads does not matter.

    HINT: Create a global "waiting/idle threads" counter, as well as a mutex to
    protect access to it. Use the condition to implement a barrier for the
    non-master threads after they say "hello," then busy-wait in the master
    thread before saying "hello" and releasing the non-master threads from the
    barrier.

4. Modify the program so that the non-master threads say “goodbye”
   gradually—only one per second—after the master thread says “hello.” The
   master thread should still only say “goodbye” after all of the other threads
   have said “goodbye.”

    HINT: sleep(1) will wait one second.

5. Modify the program so that the non-master threads say "goodbye" in reverse
   order of thread ID.

    HINT: Come up with a predicate check that describes which thread should be
    saying "goodbye" at any given time. This way you can wake all threads and
    only the one for which the predicate is true should stay awake.

