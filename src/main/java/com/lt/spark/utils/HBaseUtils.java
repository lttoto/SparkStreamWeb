package com.lt.spark.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by taoshiliu on 2018/2/20.
 */
public class HBaseUtils {

    HBaseAdmin admin = null;
    Configuration configuration = null;

    private HBaseUtils() {
        configuration = new Configuration();
        configuration.set("hbase.zookeeper.quorum","hadoop000:2181");
        configuration.set("hbase.rootdir","hdfs://hadoop000:8020/hbase");

        try {
            admin = new HBaseAdmin(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HBaseUtils instance = null;
    public static synchronized HBaseUtils getInstance() {
        if(null == instance) {
            instance = new HBaseUtils();
        }
        return instance;
    }

    public HTable getTable(String tableName) {
        HTable table = null;

        try {
            table = new HTable(configuration,tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return table;
    }

    public Map<String,Long> query(String tableName,String dayCourse) throws Exception{
        Map<String,Long> map = new HashMap<String,Long>();
        HTable table = getTable(tableName);
        String cf = "info";
        String qualifer = "click_count";

        Scan scan = new Scan();
        Filter filter = new PrefixFilter(Bytes.toBytes(dayCourse));
        scan.setFilter(filter);

        ResultScanner scanner = table.getScanner(scan);
        for(Result result : scanner) {
            String row = Bytes.toString(result.getRow());
            long clickCount = Bytes.toLong(result.getValue(cf.getBytes(),qualifer.getBytes()));
            map.put(row,clickCount);
        }
        return map;
    }

    public void put(String tableName,String rowKey,String cf,String column,String value) {
        HTable table = getTable(tableName);
        Put put = new Put(Bytes.toBytes(rowKey));
        put.add(Bytes.toBytes(cf),Bytes.toBytes(column),Bytes.toBytes(value));
        try {
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        //HTable table = HBaseUtils.getInstance().getTable("course_clickcount_lt");
        //System.out.print(table.getName().getNameAsString());

        //HBaseUtils.getInstance().put("course_clickcount_lt","20171111_88","info","click_count","2");
        Map<String,Long> map = HBaseUtils.getInstance().query("course_clickcount_lt","20180219");

        for(Map.Entry<String,Long> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

}
