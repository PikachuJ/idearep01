package com.anna.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 此类是MApReduce程序中,mapper阶段业务逻辑处理的累.也就是maptask
 * todo KEYIN VALUEIN KEYOUT VALUEOUT
 * <p>
 * KEYIN: 表示mapper阶段输入kv对中k的数据类型,在默认的组件下,是每行的起始偏移量 long类型
 * VALUEIN: 表示mapper阶段输入kv对中v的数据类型,在默认的组件下,是每行的内容 String
 * <p>
 * KEYOUT: 表示mapper输出kv中k的数据类型,根据需求来,在本需求中,是单词 String
 * VALUEOUT: 表示mapper输出kv中v的数据类型,根据需求来,在本次需求中,是单词的次数1 long
 * <p>
 * todo mapperduce 使用默认的组件TextInputFormat读取数据,行为: 一行一行的读取数据
 * k: 是这一行起始位置的偏移量offset
 * v: 是这一行的内容
 * <p>
 * todo String long 等数据类型是java的数据类型.在网络传递数据的过程中,需要序列化
 * todo 但是hadoop认为java的序列化机制效率不高,比较臃肿,因此hadoop自行封装了序列化机制,并且提供了相关的数据类型
 * <p>
 * java                  hadoop
 * long     ------>     longWritable
 * String   ------>     Text
 * double   ------>     DoubleWritable
 * null     ------>     nullWritable
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
    /**
     * todo map方法就是mapper阶段,具体业务逻辑处理的代码
     * todo TextInputFormat读取一行数据,封装成一个kv对 就调用一次map方法.  读一行调用一次,处理一次
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //把 value 转换成 String 类型 ,也就是这一行的数据内容
        String line = value.toString();     //这一行的内容 hello tom hello
        //根据分隔符切割改行的数据内容
        String[] words = line.split(" ");
        //遍历数组,每个单词都标记为1
        for (String word : words) {
            //todo 使用MapReduce程序封装的上下文对象context 把数据写出去
            context.write(new Text(word),new LongWritable(1));  //<hello,1> <tom,1> <hello,1>
        }
    }
}
