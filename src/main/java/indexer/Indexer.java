package indexer;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

/**
 * Created by aseigneurin on 25/03/2014.
 */
public class Indexer {
    public static void main(String[] args) throws IOException {
        JobConf conf = new JobConf(Indexer.class);

        conf.setMapOutputKeyClass(Text.class);
        conf.setMapOutputValueClass(Text.class);
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);

        conf.setMapperClass(IndexerMapper.class);
        conf.setReducerClass(IndexerReducer.class);
        conf.setCombinerClass(IndexerReducer.class);

        FileInputFormat.setInputPaths(conf, new Path("indexer-input"));
        FileOutputFormat.setOutputPath(conf, new Path("indexer-output"));

        JobClient.runJob(conf);
    }
}
