# SimplePageRank
Simple PageRank simulator for Java

## Features
* Support PageRank algorithm
* Support CSV file reader utility

## Example
```java
SimplePageRank spr = new SimplePageRank();
spr.initialize("res/TestLinks.csv");
spr.iterateEval();
spr.print();
```

## References
Wiki: https://en.wikipedia.org/wiki/PageRank
