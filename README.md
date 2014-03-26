Hadoop Sandbox
==============

Indexer
-------

The indexer does the following:
- reads several files in input
- tokenizes files to extract individual words
- builds an index in the form word -> files

First, copy the files to the Hadoop file system:

    $ hadoop fs -mkdir indexer-input
    $ hadoop fs -copyFromLocal data/indexer-input/*.txt indexer-input

Now, build the indexer and execute it in Hadoop:

    $ mvn package
    ...
    $ hadoop jar target/hadoop-sandbox-1.0-SNAPSHOT.jar indexer.Indexer
    ...
    $ hadoop fs -cat indexer-output/part-00000 | head
    a   a.txt, b.txt, c.txt
    abetted b.txt
    able    b.txt
    about   a.txt, b.txt, c.txt
    above   a.txt, c.txt
    accepted    c.txt
    across  a.txt
    acts    b.txt
    actually    c.txt
    address a.txt

If you need to re-run the process, delete the indexer-output directory in HDFS prior to doing so:

    $ hadoop fs -rm -r -f indexer-output


Word Count
----------

The wordcount application is from [Yahoo's Hadoop tutorial](http://developer.yahoo.com/hadoop/tutorial/index.html).

It does the following:
- reads several files in input
- tokenizes files to extract individual words
- counts occurences of each word to produce an output in the form word -> count

First, copy the files to the Hadoop file system:

    $ hadoop fs -mkdir wordcount-input
    $ hadoop fs -copyFromLocal data/wordcount-input/*.txt wordcount-input

Now, build the wordcount and execute it in Hadoop:

    $ mvn package
    ...
    $ hadoop jar target/hadoop-sandbox-1.0-SNAPSHOT.jar wordcount.WordCount
    ...
    $ hadoop fs -cat wordcount-output/part-00000 | head
    "30"    1
    "amazing,"  1
    "batted 2
    "best   2
    "blunder"   1
    "boner" 1
    "did    1
    "doing  1
    "eddie  1
    "era+"  1

If you need to re-run the process, delete the wordcount-output directory in HDFS prior to doing so:

    $ hadoop fs -rm -r -f wordcount-output
