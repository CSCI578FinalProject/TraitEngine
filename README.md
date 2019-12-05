# TraitEngine
## What is this repository
This repository includes the Trait Comparator and the input of Tomcat function level ACDC clusters as an example. 

Trait Comparator calculates each cluster's matching score of a pre-defined pattern and output the ranking of clusters based on their scores. Pattern are composed of various traits that look for specific characteristics in the source code.
## How?
Trait Comparator will first parse each cluster's source code file in the input directory. For each function in a cluster, it will calculate the matching scores of a pre-defined pattern. Then it will add up all function matching scores to the cluster matching score.
## Input
cluster source file 
## Ouput
cluster ranking
## How to Run?
todo
