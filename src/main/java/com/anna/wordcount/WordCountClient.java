package com.anna.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 此类是MapReduce程序运行时的主类,用于各种参数属性的指定设置 任务提交动作
 */
public class WordCountClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //配置文件的对象---可以设置各种属性
        Configuration conf = new Configuration();

        //创建job对象的实例 用于各种属性的封装 和任务的提交
        Job job = Job.getInstance(conf, "WordCount");

        //指定本次mr程序运行的主类
        job.setJarByClass(WordCountClient.class);

        //指定本次mr程序运行的mapper类和reduce类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //指定mapper阶段输出的key value 的数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        //指定reduce阶段输出的key value 的数据类型 也就是整个mapreduce程序最终输出的数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        //指定本次mr程序输入 输出路径
        FileInputFormat.setInputPaths(job, new Path("/wordcount/input"));
        FileOutputFormat.setOutputPath(job, new Path("/wordcount/output"));

        //todo 提交job  submit() 提交之后,客户端就和程序断开了连接,无法实时获取程序执行的情况
        boolean result = job.waitForCompletion(true);   //true表示开启监控并打印程序执行的信息
        //根据返回的结果,决定程序如何退出  如果执行成功,正常状态码0退出  否则1异常退出
        System.exit(result ? 0 : 1);

    }
}
