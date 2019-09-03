<div align="center">
<blockquote>
<p><h3>This project is a test bench to measure and investigate heap allocation of Apache Maven.</h3></p>
</blockquote>
</div>

<p align="center">
  <a href="#General-set-up">General set up</a> •
  <a href="#Benchmark-heap-allocation-of-several-Maven-releases">Benchmark heap allocation of several Maven releases</a>
</p>
<p align="center">
  <a href="#Investigate-where-heap-allocation-comes-from">Investigate where heap allocation comes from</a> •
  <a href="#Acknowledgments">Acknowledgments</a> •
  <a href="#License">License</a> 
</p>

We hope that..
can help

This project is mainly based on QuickPerf library

mvn verify

At this moment, this project allows to benchmark and investigate the origin of heap allocation of *mvn validate*.

Measures have been done executing "mvn validate" on Apache Camel project. 

Feel free to use this project and contribute to it!

# General set up

This project contains two types of test.
*MvnValidateAllocationByMaven3VersionTest* allows to benchmark heap allocation on several Maven 3 distributions. *MvnValidateTest* is more dedicated to get an idea of heap allocation level and to investigate its origin.

This general set up part describes configurations common to both test.

In this project we use [QuickPerf](https://github.com/quick-perf/quickperf) to measure and investigate heap allocation level.

The needed Maven 3 distributions are downloaded by the tests. It is done in method annotated *@Before*. See [Measure on Maven head](#Measure-on-Maven-head) part if you want to measure heap allocation of the current Maven head.
 
Heap allocation level is measured with the help of [*@MeasureHeapAllocation*](https://github.com/quick-perf/doc/wiki/JVM-annotations#Verify-heap-allocation) QuickPerf annotation. This annotation allows to measure heap allocation level of the method annotated with @Test.
) QuickPerf annotation. This annotation measures the heap allocation level of the thread running the method annotated @Test.
Feel free to contribute to QuickPerf by adding an implementation allowing to measure the global allocation coming of all the threads!

We check that *mvn validate* does not allocates on several thread by profiling the JVM with the help of [@ProfileJvm](https://github.com/quick-perf/doc/wiki/JVM-annotations#ProfileJvm).

@HeapSize allows to fix the heap size. As we are going to see thereafter, between Maven 3.2.5 and Maven 3.6.2, heap allocation value is maximum for 


properties


## Clone the project on which to apply mvn validate

We you can for example use Apache Camel project to measure heap allocation of a Maven goal:
```
git clone -n https://github.com/apache/camel.git
git checkout c409ab7aabb971065fc8384a861904d2a2819be5
```
We have selected an Apache Camel commit for which ```mvn validate``` can be applied from Maven 3.2.5 to Maven 3.6.2. 
This Apache Camel version contains 841 modules.

## Measure on Maven head
// Rajouter cd dans doc et tester

To measure heap allocation on Maven head you have to before build a Maven distribution.
To do this, you can execute```mvn -DdistributionTargetDir="{maven-distrib-location}/apache-maven-head" clean package``` by replacing {maven-distrib-location} by the url given by the *maven.binaries.path* property of *maven-bench.properties* file. 


# Benchmark heap allocation of several Maven releases

Expliquer fichier property

Measures can be launched with this command line:  ```mvn -Dtest=MvnValidateAllocationByMaven3VersionTest test```
Before doing it, you can close your IDE, web browser or available applications to free memory.

The benchmark results are exported into a maven-memory-allocation-{date-time}.csv file. The execution context (processor, OS, ...) is reported in an execution-context-{date-time}.txt file.

Below

exécution context

Measures took 1 hour and 12 minutes.

warm mesure



<p align="center">
    <img src="measures/mvn-validate-on-camel.png">
</p>
<p align="center">Heap allocation after executing <i>mvn validate</i> on Apache Camel for several Maven versions<p>



# Investigate where heap allocation comes from

 Voir d'où vient l'allocation en heap
 En 3.2.5 mettre copie écran avec tableau de char[]
 
 Mettre copie écran 3.6.2
 
 @ExpectNoJvmIssue

[JVM annotations](https://github.com/quick-perf/doc/wiki/JVM-annotations)

[QuickPerf documentation](https://github.com/quick-perf/doc/wiki/QuickPerf)

:octocat: Github repository [here](https://github.com/quick-perf/quickperf)

Mesure profiling + JMC

Montrer exemples capture JMC pour 3.5.2 et 3.6.2

=> 1 thread

mettre taille heap 3.5.2 et 3.6.2

Faire tuto pour ouvrir JFR dans JMC ancien et récent


Mettre exemple @ExpectNoJVM issue


# Acknowledgments
Many thanks to Hervé Boutemy for his help and support to start this project.

# License
[Apache License 2.0](/LICENSE.txt)
