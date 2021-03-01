/*
 * CS 470 Example
 *
 * Demonstrates basic Chapel constructs.
 */

use VisualDebug;    // use visual debugger

// local
writeln("Hello world!");

// distributed
for loc in Locales do
  on loc do
    writeln("Hello from locale #", here.id, " [", here.name, "]");

exit();     // remove this to run the rest of the program


/*****************************************************************************
                                DATA PARALLELISM
 *****************************************************************************/

// NOTE: Local thread counts can be limited using a runtime flag as follows:
//
//   ./hello -nl <Nlocales> --dataParTasksPerLocale=<Nthreads>
//

// configuration variables
config const N = 16;
config const noDebug = false;
config const vdebug = false;

// domain and array declaration
const numDomain = {1..N};
var nums : [numDomain] int;

// initialize array (domain iteration) w/ timing
use Time;
var t: Timer;
t.start();
for i in numDomain do
  nums[i] = i;
const serialTime = t.elapsed();

if !noDebug then writeln("  Nums: ", nums);

// modify array (array iteration) w/ timing
t.clear();
forall x in nums do
  x = x * 2;
const parallelTime = t.elapsed();
writef("Serial init: %8r  Parallel mult: %8r\n", serialTime, parallelTime);

if !noDebug then writeln("  Nums: ", nums);

exit();     // remove this to run the rest of the program


/*****************************************************************************
                         DISTRIBUTED DATA PARALLELISM
 *****************************************************************************/

// distributed domain and array declaration
use BlockDist;
const numDistDomain = numDomain
  dmapped Block(boundingBox=numDomain);
var numsDist : [numDistDomain] int;

// initialize distributed array (domain iteration)
if vdebug then startVdebug("init");
forall i in numDistDomain {

  // no need for "on" clause if the domain/array is distributed!
  if numLocales > 1 && !noDebug then
    writef("  %8i at locale #%2i [%s]\n", i, here.id, here.name);

  numsDist[i] = i;
}
if vdebug then stopVdebug();

if !noDebug then writeln("  DistNums: ", numsDist);

// modify distributed array (array iteration)
if vdebug then startVdebug("mult");
forall x in numsDist do
  x = x * 2;
if vdebug then stopVdebug();

if !noDebug {
  if vdebug then startVdebug("print");
  writeln("  DistNums: ", numsDist);
  if vdebug then stopVdebug();
}

