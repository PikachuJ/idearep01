package com.anna.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 此类是MapReduce程序中,reduce阶段业务逻辑处理的类.也就是reducetask
 * KEYIN, VALUEIN, KEYOUT, VALUEOUT
 * <p>
 * KEYIN: reduce阶段输入kv中k的类型  也就是mapper输出中k的类型. 在本需求中就是单词 Text
 * VALUEIN: reduce阶段输入kv中v的类型  也就是mapper输出中v的类型. 在本需求中是单词次数1  longWritable
 * <p>
 * KEYOUT: reduce阶段输出kv中k的类型,在本需求中,是单词 Text
 * VALUEOUT: reduce阶段输出kv中v的类型,在本需求中,是单词的总次数 longWritable
 */
public class WordCountReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
    /**
     * todo mapper阶段输出的所有的数据来到reduce阶段,是如何处理的?
     * <hello,1><tom,1><hello,1><tom,1><hello,1>
     * <p>
     * todo 1. 排序  key的字典序进行排序(默认)
     * <hello,1><hello,1><hello,1><tom,1><tom,1>
     * todo 2. 分组  key相同的分为一组
     * <hello,1><hello,1><hello,1>
     * <tom,1><tom,1>
     * todo 3. 同一组的数据会组成一个新的kv对,一组去调用一次reduce方法
     * todo: 新的kv对,这组共同的key   新v: 这一组所有的value组成的一个迭代器Iterable
     * <hello,[1,1,1]>
     * <tom,[1,1]>
     */
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        //定义一个统计变量
        long count = 0;
        //遍历values的迭代器 取值累计求和
        for (LongWritable value : values) {
            count += value.get();
        }
        //todo 使用上下文对象 Context 把数据写出去
        context.write(key,new LongWritable(count));
    }
}
