package com;

import com.shop.entity.Torder;
import com.shop.message.OrderInfoReq;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ChenTengfei
 * @date 2019/9/1 21:30
 **/

public class TestHbaseXML {

    private ApplicationContext context;
    private HbaseTemplate hbaseTemplate;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    {
        context = new ClassPathXmlApplicationContext("spring-hbase.xml");
        hbaseTemplate = context.getBean(HbaseTemplate.class);
    }

    /**
     * 增 改
     */
    @Test
    public void put() {
        hbaseTemplate.put("userb21042c79426:shopOrder", "1002", "info", "productId", Bytes.toBytes("2001"));
        hbaseTemplate.put("userb21042c79426:shopOrder", "1002", "info", "productName", Bytes.toBytes("白色衬衫"));
        hbaseTemplate.put("userb21042c79426:shopOrder", "1002", "info", "price", Bytes.toBytes("200"));
        hbaseTemplate.put("userb21042c79426:shopOrder", "1002", "info", "orderNum", Bytes.toBytes("2"));
        hbaseTemplate.put("userb21042c79426:shopOrder", "1002", "info", "money", Bytes.toBytes("400"));
        hbaseTemplate.put("userb21042c79426:shopOrder", "1002", "info", "orderTime", Bytes.toBytes("2019-09-12 00:00:00"));
    }
    /**
    * 删
    */
    @Test
    public void delete() {

        hbaseTemplate.delete("student","1001","info");
        //hbaseTemplate.delete("student","1001","info","age");
    }
    //查询
    @Test
    public void find(){
        OrderInfoReq req = new OrderInfoReq();
        req.setBeginDate("2019-08-01");
        req.setEndDate("2019-09-01");
        //开始时间和结束时间格式为yy-mm-dd
        String beginDay = strFormat(req.getBeginDate());
        String endDay = strFormat(req.getEndDate());


        //设置过滤器，设置orderTime的条件
        List<Filter> filters = new ArrayList<>();
        filters.add(new SingleColumnValueFilter(Bytes.toBytes("info"),Bytes.toBytes("orderTime"),
                CompareFilter.CompareOp.GREATER,Bytes.toBytes(beginDay)));
        filters.add(new SingleColumnValueFilter(Bytes.toBytes("info"),Bytes.toBytes("orderTime"),
                CompareFilter.CompareOp.LESS,Bytes.toBytes(endDay)));
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL,filters);

        Scan scan = new Scan();
        scan.setFilter(filterList);
        //开始浏览hbase数据
        List<Torder> torderList = hbaseTemplate.find("userb21042c79426:shopOrder", scan, new RowMapper<Torder>() {
            @Override
            public Torder mapRow(Result result, int i){
                List<Cell> ceList = result.listCells();
                Torder Torder = new Torder();
                String row = "";
                if (ceList != null && ceList.size() > 0) {
                    for (Cell cell : ceList) {
                        row = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
                        String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                        String quali = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                        if("productId".equals(quali)){
                            Torder.setProductId(value);
                        }else if("productName".equals(quali)){
                            Torder.setProductName(value);
                        }else if("price".equals(quali)){
                            Torder.setPrice(Float.valueOf(value));
                        }else if("orderNum".equals(quali)){
                            Torder.setOrderNum(Integer.valueOf(value));
                        }else if("money".equals(quali)){
                            Torder.setMoney(Float.valueOf(value));
                        }else if("orderTime".equals(quali)){
                            try {
                                Date date = sdf.parse(value);
                                Torder.setOrderTime(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    Torder.setId(Integer.parseInt(row));
                }
                return Torder;
            }
        });

        for (Torder torder : torderList) {
            System.out.println(torder.toJsonString());
        }
    }


    public String strFormat(String time){
        StringBuffer day =new StringBuffer(time);
        day.append(" 00:00:00");
        return new String(day);
    }

}