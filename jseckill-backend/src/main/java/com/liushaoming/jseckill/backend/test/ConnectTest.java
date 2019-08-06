package com.liushaoming.jseckill.backend.test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectTest {
	public static void main(String[] args) throws InterruptedException {
		int count=2;
		final CountDownLatch cdl=new CountDownLatch(count);
		for (int i=0; i < count; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {	
					cdl.countDown();
					try {
						cdl.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {				
						connect();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		
}
	
	private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
	public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }
	 
	    private static String getTel() {
	        int index=getNum(0,telFirst.length-1);
	        String first=telFirst[index];
	        String second=String.valueOf(getNum(1,888)+10000).substring(1);
	        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
	        return first+second+third;
	    }
	public static void connect() throws Exception {
		int i[]= {1000,1001,1002,1003};
		int index = (int) (Math.random() * i.length);
		final String urlStr="http://localhost:27000/seckill/execution/1000/13926570222/c2097fe5d7ddf03207e4c3126b3d57b8";
//		final String urlStr="http://localhost:27000/seckill/execution/"+i[0]+"/"+getTel()+"/c2097fe5d7ddf03207e4c3126b3d57b8";
//		final String urlStr="http://localhost:8099";
		System.out.println(urlStr);
		URL url=new URL(urlStr);
		HttpURLConnection  urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoInput(true);
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");
		urlConnection.connect();
		System.out.println(urlConnection.getInputStream());
	}
	
}