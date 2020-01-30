# jmh-benchmarks
A collection of micro benchmarks testing features using the JMH framework

#Running the benchmarks
1. git clone https://github.com/charliegracie/jmh-benchmarks.git
2. cd jmh-benchmarks
3. mvn clean package
4. java -Xms64m -Xmx64m <JVM-options> -jar target/benchmarks.jar <benchmarkname> <jmh-options>

An example running the SimpleMath benchmark with OpenJDK JDK 11 using ParallelGC and profiling the GC with JMH:
java -Xms64m -Xmx64m -XX:+UseParallelGC -jar target/benchmarks.jar SimpleMath -prof gc