# TraitEngine

## What is this repository

This repository includes the Trait Comparator and the input of Tomcat as an example. 

Trait Comparator calculates each fucntional section's matching score of a pre-defined pattern and output **(1) a ranking of the original ACDC clusters** and **(2) a ranking of the functional sections with their corresponding original ACDC clusters.** Pattern are composed of various traits that look for specific characteristics in the source code such as keyword and return type. 

This engine is a  part of pipeline of Clustering Function Sections Using Grained ACDC. Current it only support source code's sematic analysis by extracting keywords. However, it can be replaced with a module of sematic analysis by machine learning or NLP to achieve better performance in the future.

## How?

Trait Comparator will first parse each function section's source code file in the input directory like `input/function_source_code_tomcat_6.0`. For each function in a cluster, it will calculate the matching scores based on a pre-defined pattern. Then it will add up all functions' matching scores to obtain the matching score of a function section. Based on function sections' scores, it will generate a ranking of function sections.

It then can map the functional sections back to original ACDC clusters and calculate the matching score of orignal ACDC cluster by adding up the matching scores of functions sections that is associated with that cluster. For example, if function section A has score 10 and it has a function that comes from a specific file/class in original ACDC cluster B, cluster B will be added with 10 more score. By having all clusters' score, we are able to generate the ranking of original ACDC clusters. The score of each cluster can be interpreted as the amount of code related to an architectural decision. The increment rate of the score is linear, which means that if a cluster A’s score is twice as cluster B’s score, cluster A has twice as much architectural decision related code as cluster B.

## Input

All input files should be placed in `Input/` dir

(1) Function sections' source code in a directory. Eg. `TraitEngine/Trait-Comparater/input/function_source_code_tomcat_6.0`

(2) Original ACDC cluster result. Eg. `original_ACDC_cluster_tomcat_6.0.rsf`

(3) Function sections result. Eg. `Modified_ACDC_cluster_tomcat_6.0.rsf`



## Ouput

All output file will be placed in `out/` dir

(1) Ranking of original ACDC cluster with their scores. Eg.`Original_ACDC_Cluster_Ranking_6.0.txt`

(2) Ranking of functional sections with their scores and their related original ACDC cluster. Eg.`Cluster_Relation_6.0.txt`

## How to Run?

Please make sure to have **Maven** installed.

Under directory `Trait-Comparator`

(1) `mvn install`

(2) `mvn exec:java -Dexec.mainClass=Main -Dexec.args="{function_section_source_code_folder} {original_ACDC_cluster_result} {function_sections_result} {version_num}"`

Eg.`mvn exec:java -Dexec.mainClass=Main  -Dexec.args="input/function_source_code_tomcat_6.0 input/original_ACDC_cluster_tomcat_6.0.rsf input/modified_ACDC_cluster_tomcat_6.0.rsf 6.0"`
