import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountPairs {

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      // StringTokenizer itr = new StringTokenizer(value.toString());
String line = value.toString();
String emot = "[:;xX]-?[DP()]";
  String htag = "#[A-Za-z0-9]+";
  String ment = "@[A-Za-z0-9]+";
  String url = "https?://\\S+\\s?";
//line = line.trim().replaceAll(" +", "");
line = line.replaceAll("\\s{2,}", " ").trim();
line = line.replaceAll(emot, "");
line = line.replaceAll(htag, "");
line = line.replaceAll(ment, "");
line = line.replaceAll(url, "");
line = line.replaceAll("\\p{Punct}|\\d", "");

//line = line.replaceAll("[^a-zA-Z]", "");

      String [] tokens = line.split("\\s+");
	//if(tokens.length>1){
      for(int i = 0;i<tokens.length - 1;i++){
          for(int j = i+1;j<tokens.length;j++){
	if(!(tokens[i].length()<2)){
            word.set(tokens[i] + " , " + tokens[j]);
            context.write(word, one);
	}

          }



      }
	//}
      // while (itr.hasMoreTokens()) {
      //   word.set(itr.nextToken());
      //   context.write(word, one);
      // }
    }
  }

  public static class IntSumReducer
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
      result.set(sum);
      context.write(key, result);
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "word count");
    job.setJarByClass(WordCountPairs.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}