Hadoop Sandbox
==============

Indexer
-------

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
