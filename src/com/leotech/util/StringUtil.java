package com.leotech.util;import java.io.ByteArrayOutputStream;import java.io.UnsupportedEncodingException;import java.text.DecimalFormat;import java.text.NumberFormat;import java.util.regex.Matcher;import java.util.regex.Pattern;public class StringUtil{	 private static DecimalFormat df = new DecimalFormat("#.00");	 private static double IESLAB_FLT_EPSILON = 1.E-005D;	 private static String hexString = "0123456789ABCDEF";	public static String toUTF8(String str) throws Exception	{		return new String(str.getBytes("ISO-8859-1"), "UTF-8");	}	public static String toGBK(String str) throws Exception	{		return new String(str.getBytes("ISO-8859-1"), "GBK");	}	public static String utfToGBK(String str) throws Exception	{		return new String(str.getBytes("UTF-8"), "GBK");	}	public static String toASCII(String str) throws Exception	{		return new String(str.getBytes("ISO-8859-1"), "US-ASCII");	}	/**	 * 对特殊字符转义	 */	public static String transferredMeaning(String str) throws Exception	{		String re = "";		if (str != null && !"".equals(str))		{			re = str.replaceAll("<", "&lt;");			re = re.replaceAll(">", "&gt;");			return re;		} else			return "";	}	/**	 * 判断字符是不是数字	 */	public static boolean isNumeric(String str)	{		Pattern pattern = Pattern.compile("[0-9]*");		Matcher isNum = pattern.matcher(str);		if (!isNum.matches())		{			return false;		}		return true;	}	/**	 * 保留几位小数	 **/	 public static String formatDoubleNon(double s)	  {	    String val = null;	    if (isEqual(s, 0.0D)) {	      val = "0.0";	      return val;	    }	    double st = s;	    val = df.format(st);	    return val;	  }	 	 public static String formatTimeStr(String s)	  {		String val = "";	    if(s!=null){	    	if(s.length()>1){	    		val = s.substring(0, s.indexOf("."));	    	}	    }	    return val;	  }			/*		 * 将字符串编码成16进制数字,适用于所有字符（包括中文）		 */		public static String encode(String str)		{			String re = null;			if(null == str || "".equals(str)){				return "";			}			//根据默认编码获取字节数组			StringBuilder sb = null;			byte[] bytes;			try {				bytes = str.getBytes("gbk");				sb=new StringBuilder(bytes.length*2);				//将字节数组中每个字节拆解成2位16进制整数				for(int i=0;i<bytes.length;i++)				{				sb.append(hexString.charAt((bytes[i]&0xf0)>>4));				sb.append(hexString.charAt((bytes[i]&0x0f)>>0));				}				re = sb.toString();			} catch (UnsupportedEncodingException e) {				//IesLogger.instance().runInfo("将字符串编码成16进制数字异常：" + str);				e.printStackTrace();			}			return re;					}		/*		 * 将16进制数字解码成字符串,适用于所有字符（包括中文）		 */		public static String decode(String bytes)		{			String re = null;			try{				ByteArrayOutputStream baos=new ByteArrayOutputStream(bytes.length()/2);				//将每2位16进制整数组装成一个字节				for(int i=0;i<bytes.length();i+=2)				baos.write((hexString.indexOf(bytes.charAt(i))<<4 |hexString.indexOf(bytes.charAt(i+1))));				re = new String(baos.toByteArray());			}catch (Exception e) {				//IesLogger.instance().runInfo("将16进制数字解码成字符串异常：" + bytes);				// TODO: handle exception			}						return re;		} 				 public static boolean isEqual(double left, double right)		  {		    return (left - IESLAB_FLT_EPSILON < right) && (right < left + IESLAB_FLT_EPSILON);		  }				public static void main(String[] args) {			System.out.println(encode(new String("黄岛电厂")));			System.out.println(decode("E9BB84E5B29BE794B5E58E82"));		}				public static String saveFragment(String value, int t){		    String val = value;		    int index = val.indexOf('.');		    if (index != -1) {		      NumberFormat ddf1 = NumberFormat.getNumberInstance();		      ddf1.setMaximumFractionDigits(t);		      double s = Double.parseDouble(val);		      val = ddf1.format(s);		    }		    return val;	   }}