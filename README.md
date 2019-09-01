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
  <a href="#Contribute">Contribute</a> 
</p>

We hope that..
can help

This project is mainly based on QuickPerf library

mvn verify

Remercier Hervé Boutemy

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
This Apache Camel version contains XXX modules.

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

You can execute this  command line to launch the measurements:  ```mvn -Dtest=MvnValidateAllocationByMaven3VersionTest test```
Before doing ti, you can close your IDE, web browser or available applications to free memory.

Résultas exportés dans CSV

exécution context

warm mesure


# Investigate where heap allocation comes 

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

# Contribute

# License
[Apache License 2.0](/LICENSE.txt)
