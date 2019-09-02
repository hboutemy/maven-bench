<div align="center">
<blockquote>
<p><h3>This project is a test bench to measure and investigate heap allocation of Apache Maven.</h3></p>
</blockquote>
</div>

<p align="center">
  <a href="#Set-up">Set up</a> •
  <a href="#Benchmark-heap-allocation-Maven-releases">Benchmark heap allocation of Maven releasess</a>
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

# Set up

Automatically download of Maven distribution

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
To measure heap allocation on Maven head you have to before build a Maven distribution.
To do this, you can execute```mvn -DdistributionTargetDir="{maven-distrib-location}/apache-maven-head" clean package``` by replacing {maven-distrib-location} by the url given by the *maven.binaries.path* property of maven-bench.properties. 


## Manually retrieve Maven 3.6.2 distribution

At his time, 3.6.2 artifacts are going to be released.
These artifacts can't be automatically downloaded by this test bench. 
Please download these artifacts from [https://dist.apache.org/repos/dist/dev/maven/maven-3/3.6.2/binaries/](https://dist.apache.org/repos/dist/dev/maven/maven-3/3.6.2/binaries/).
After that, please paste the 3.6.2 distribution in the location pointed by the *maven.binaries.path* property contained in *maven-bench.properties* file.

At his time, Maven 3.6.2 is not yet officialy released.
This test bench is not able to automatically download.
Waiting for the official 3.6.2 release, please download 3.6.2 artifacts from the Github repository of Maven]() and 
To build a 3.6.2 distribution


# Benchmark heap allocation of Maven releases

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
