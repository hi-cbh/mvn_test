package com.mail.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {
	  /**
	   * ��ȡ��ǰ��ϵͳʱ�䲢��ʽ��
	   * 
	   * @param dateFormat
	   *          ����ʱ��ĸ�ʽ
	   * @return String ��ʽ������ַ���
	   * @see <a href="https://docs.oracle.com/javase/tutorial/i18n/format/simpleDateFormat.html">Java
	   *      Date Format Pattern Syntax</a><br>
	   * @see SimpleDateFormat#SimpleDateFormat(String pattern)
	   */
	  public static String getSystemTime(String dateFormat) {
	    SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
	    Date curDate = new Date(System.currentTimeMillis());
	    return formatter.format(curDate);
	  }
}
