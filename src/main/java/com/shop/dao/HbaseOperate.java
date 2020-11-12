package com.shop.dao;

import com.shop.entity.Torder;
import com.shop.message.OrderInfoReq;
import com.shop.utils.PropertyManager;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ChenTengfei
 * @date 2019/9/2 11:26
 **/
@Component
public class HbaseOperate{
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    HbaseTemplate hbaseTemplate;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 增 改
     */
    public void put(Torder torder) throws Exception {
        //将命名空间写到配置文件中
        final String namespace = PropertyManager.getProperty("namespace");
        hbaseTemplate.put(namespace+":shopOrder",torder.getId().toString(),"info","productId",Bytes.toBytes(torder.getProductId()));
        hbaseTemplate.put(namespace+":shopOrder",torder.getId().toString(),"info","productName",Bytes.toBytes(torder.getProductName()));
        hbaseTemplate.put(namespace+":shopOrder",torder.getId().toString(),"info","price",Bytes.toBytes(torder.getPrice().toString()));
        hbaseTemplate.put(namespace+":shopOrder",torder.getId().toString(),"info","orderNum",Bytes.toBytes(torder.getOrderNum().toString()));
        hbaseTemplate.put(namespace+":shopOrder",torder.getId().toString(),"info","money",Bytes.toBytes(torder.getMoney().toString()));
        if(torder.getOrderTime()==null){
            torder.setOrderTime(new Date());
        }
        String time = sdf.format(torder.getOrderTime());
        hbaseTemplate.put(namespace+":shopOrder",torder.getId().toString(),"info","orderTime",Bytes.toBytes(time));

    }

    //查询
    public List<Torder> find(OrderInfoReq req) throws Exception {

        //将命名空间写到配置文件中
        final String namespace = PropertyManager.getProperty("namespace");
        logger.info("HbaseOperate req"+ req.toString());
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
        List<Torder> torderList = hbaseTemplate.find(namespace+":shopOrder", scan, new RowMapper<Torder>() {
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
        return torderList;
    }


    public String strFormat(String time){
        StringBuffer day =new StringBuffer(time);
        day.append(" 00:00:00");
        return new String(day);
    }
}