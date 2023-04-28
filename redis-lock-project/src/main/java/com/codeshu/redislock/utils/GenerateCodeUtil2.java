package com.codeshu.redislock.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 生成业务编号 ：业务名称+年月日+4位顺序编码
 * 例如：XJRW202211220001 XJRW202211220002
 * 一天10000个编码不会重复（不利用redis分布式锁）
 * 第二天归0001
 *
 * 注：如果要一个业务编号单独用一个redis顺序编号，则可以用业务编号作为redis的key
 * 比如XJRW202211220001，电商业务再去获取时是DS202211220001而不是DS202211220002
 */
@Component
public class GenerateCodeUtil2 {
    //redis顺序编号的key
    public static final String DATE_AND_SEQ_NUM_KEY = "dateAndSeqNum:key";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 生成业务编码
     * @param businessCodeEnum 业务名称
     * @return 业务编码
     */
    public synchronized String generate(BusinessCodeEnum businessCodeEnum){
        //生成的业务编码(XJRWyyyyMMddxxxx)
        String result = null;

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        //redis生成的顺序编号yyyyMMddxxxx
        String dateAndSeqNum = null;

        //尝试到redis获取key为dateAndSeqNum:key的顺序编号
        if (redisTemplate.opsForValue().get(DATE_AND_SEQ_NUM_KEY) != null) {
            //获取到则进行使用
            dateAndSeqNum = redisTemplate.opsForValue().get(DATE_AND_SEQ_NUM_KEY).toString();
        }

        //如果获取不到，则设置顺序编号为yyyyMMdd0001，将其往redis中存入key为dateAndSeqNum:key的value中
        if (Objects.isNull(dateAndSeqNum)) {
            //当前时间yyyyMMdd
            String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
            //顺序后缀0001
            String seqNum = String.format("%04d", 1);
            //拼接成初始顺序编号yyyyMMdd0001
            dateAndSeqNum = date + seqNum;
            //存入key为dateAndSeqNum:key的value中，且过期时间当前时间距离明天0点的秒数
            redisTemplate.opsForValue().set(DATE_AND_SEQ_NUM_KEY, dateAndSeqNum,getSecondsNextEarlyMorning(),TimeUnit.SECONDS);
        }

        //生成唯一业务编码
        StringBuilder stringBuilder = new StringBuilder();
        //业务编号
        String code = businessCodeEnum.getCode();
        //业务编号 + 当前时间 + 顺序后缀，即业务编号+ 顺序编号，作为唯一业务编码
        result = stringBuilder.append(code).append(dateAndSeqNum).toString();

        //redis的顺序编号自增一，更新顺序编号
        redisTemplate.opsForValue().increment(DATE_AND_SEQ_NUM_KEY, 1);

        return result;
    }

    /**
     * 当前时间距离明天0点的秒数
     * @return 比如现在2022年11月23日15:23，距离2022年11月24日00:00的秒数就为31020秒
     */
    public static Long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }
}
