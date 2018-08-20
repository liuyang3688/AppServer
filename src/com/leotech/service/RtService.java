package com.leotech.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leotech.model.Triple;
import com.leotech.util.CommonFunc;
import redis.clients.jedis.Jedis;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RtService {
	private static Jedis jedis;
	static {
        jedis = new Jedis("47.96.127.175", 6379, 5000);
        try {
            connect();
        } catch (Exception e) {
            System.out.println("Jedis: connect to redis server exception.");
        }
	}
	public static void connect() throws Exception {
        jedis.auth("admin");
    }

	public static JSONObject getRtData() {
        JSONObject datas = new JSONObject();

        try {
            if (! jedis.isConnected()) {
                // 如果未连接，尝试连接一次
                System.out.println("getRtData: 当前未连接，尝试一次。");
                connect();
            }
            if (jedis.isConnected()) {
                // 取所有Keys
                Set<String> keySet = jedis.keys("*");
                for (String s : keySet) {
                    if (s.matches("^\\d{2}-\\d{3}")){
                        Map<String, String> hm = jedis.hgetAll(s);
                        datas.put(s, hm);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

	    return datas;
	}

    public static JSONObject getRtData(int bjlx) {
        JSONObject datas = new JSONObject();

        try {
            if (! jedis.isConnected()) {
                // 如果未连接，尝试连接一次
                System.out.println("getRtData: 当前未连接，尝试一次。");
                connect();
            }
            if (jedis.isConnected()) {
                String pattern = String.format("%02d", bjlx) + "-*";
                // 取所有Keys
                Set<String> keySet = jedis.keys(pattern);
                for (String s : keySet) {
                    if (s.matches("^\\d{2}-\\d{3}")){
                        Map<String, String> hm = jedis.hgetAll(s);
                        datas.put(s, hm);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datas;
    }

    public static JSONObject getRtData(int bjlx, int bjid) {
        JSONObject datas = new JSONObject();

        try {
            if (! jedis.isConnected()) {
                // 如果未连接，尝试连接一次
                System.out.println("getRtData: 当前未连接，尝试一次。");
                connect();
            }
            if (jedis.isConnected()) {
                String key = String.format("%02d", bjlx) + "-" + String.format("%03d", bjid);
                // 取所有Keys
                Map<String, String> hm = jedis.hgetAll(key);
                datas.put(key, hm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datas;
    }

	public static void MockData() {
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("1", "24.11");
        hm.put("2", "31.2");
        jedis.hmset("01-001", hm);

        hm = new HashMap<String, String>();
        hm.put("1", "11.22");
        hm.put("2", "22.33");
        jedis.hmset("01-002", hm);

        // 类型2
        hm = new HashMap<String, String>();
        hm.put("1", "1.1");
        hm.put("2", "2.2");
        jedis.hmset("02-001", hm);

        // 类型3
        hm = new HashMap<String, String>();
        hm.put("1", "5.6");
        hm.put("2", "6.7");
        jedis.hmset("03-001", hm);
    }
}
