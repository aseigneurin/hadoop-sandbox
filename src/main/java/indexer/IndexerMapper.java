package indexer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by aseigneurin on 25/03/2014.
 */
public class IndexerMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {

    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        FileSplit file = (FileSplit) reporter.getInputSplit();
        Text filename = new Text(file.getPath().getName());

        StringTokenizer tokenizer = new StringTokenizer(value.toString(), "\t\r\n\"',.:;!?()/*0123456789- ");
        while (tokenizer.hasMoreTokens()) {
            Text token = new Text(tokenizer.nextToken().toLowerCase());
            output.collect(token, filename);
        }
    }
}
