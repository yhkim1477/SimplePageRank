# SimplePageRank
Simple PageRank simulator for Java

## Features
* Support PageRank algorithm
* Support CSV file reader utility

## Example
```java
SimplePageRank spr = new SimplePageRank();
try {
spr.initialize("res/TestLinks.csv");
spr.iterateEval();
spr.print();
} catch (IOException e) {
e.printStackTrace();
}
```

## References
Wiki: https://en.wikipedia.org/wiki/PageRank
