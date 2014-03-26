package indexer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by aseigneurin on 25/03/2014.
 */
public class IndexerReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
    @Override
    public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        Set<String> filenames = new TreeSet<String>();
        while (values.hasNext()) {
            String filename = values.next().toString();
            filenames.add(filename);
        }

        StringBuilder res = new StringBuilder();
        for (String filename : filenames) {
            if (res.length() > 0)
                res.append(", ");
            res.append(filename);
        }

        Text resText = new Text(res.toString());
        output.collect(key, resText);
    }
}
