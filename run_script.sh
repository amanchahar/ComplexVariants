#!/bin/bash


IFS='.' read -a files <<< "$1"

#javac VCFtoBED.java
#java VCFtoBED $1
#sudo python src/gwava_annotate.py "${files[0]}.bed" gwava_features.csv
#tail -n +2 gwava_features.csv | sort -k1n > gwava_features.csv
echo $1
javac VCFtoFasta.java
java VCFtoFasta $1
perl profileComplexSeq.pl "${files[0]}.fa"
javac CaballeroFeaturesSort.java
java CaballeroFeaturesSort "${files[0]}.complex"
javac AlternateAllele.java
java AlternateAllele $1
g++ suffix_lcp.cpp -o suffix_lcp
./suffix_lcp "${files[0]}.fa"
javac MergeCSV.java
java MergeCSV gwava_features.csv periodicity.csv CaballeroFeatures.csv AlleleProperties.csv
