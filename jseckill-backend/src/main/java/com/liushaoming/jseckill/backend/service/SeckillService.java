package com.liushaoming.jseckill.backend.service;

import com.liushaoming.jseckill.backend.dto.Exposer;
import com.liushaoming.jseckill.backend.dto.SeckillExecution;
import com.liushaoming.jseckill.backend.entity.Seckill;
import com.liushaoming.jseckill.backend.exception.SeckillException;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public interface SeckillService {

    /**
     * 查询所有秒杀记录
     *
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     *
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启输出秒杀接口地址,
     * 否则输出系统时间和秒杀时间
     *
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     * @throws TimeoutException 
     * @throws IOException 
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, IOException, TimeoutException;

    SeckillExecution updateInventory(long seckillId, long userPhone)
            throws SeckillException;

    /**
     * 在Redis中真正进行秒杀操作
     * @param seckillId
     * @param userPhone
     * @throws SeckillException
     */
    void handleInRedis(long seckillId, long userPhone) throws SeckillException;

    public int isGrab(long seckillId, long userPhone);

	boolean executeSeckillTest(long seckillId, long userPhone, String md5);

	int countNum();
}