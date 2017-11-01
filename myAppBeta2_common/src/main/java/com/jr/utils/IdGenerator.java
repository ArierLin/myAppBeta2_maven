package com.jr.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdGenerator {
	
	private static Logger log = LoggerFactory.getLogger(IdGenerator.class);

	public static final char PROGRAM_TYPE = 'G';

	public static final char MUSIC_PROGRAM_TYPE = 'M';

	public static final char MUSIC_CONTENT_TYPE = 'C';

	public static final char FILM_PROGRAM_TYPE = 'F';

	public static final char TV_PROGRAM_TYPE = 'T';

	public static final char SPECIAL_PROGRAM_TYPE = 'S';

	public static final char VIDEO_CONTENT_TYPE = 'V';

	public static final char PIC_PROGRAM_TYPE = 'P';

	public static final char PIC_CONTENT_TYPE = 'J';

	public static final char PRODUCT_TYPE = 'K';

	public static final char WEATHER = 'W';

	public static final char AD_TEMPLATE = 'T';

	public static final char AD_ITEM = 'I';

	private IdGenerator() {
		// do nothing
	}

	public static String generateId16(char type) {

		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String sysDatetime = sdf.format(rightNow.getTime());

		String id = type + sysDatetime + IdGenerator.generateRandomStr(3);
		return id;
	}
	
	public static String generatePProgramId() {
		return "pprogram_"+generateRandomStr(23);
	}
	/**
	 * 生产点播/UGC评论信息内部id
	 * @return
	 */
	public static String generateRemarkId() {
		return "remark_"+generateRandomStr(23);
	}
	/**
	 * 生产直播评论信息内部id
	 * @return
	 */
	public static String generateZhiboRemarkId() {
		return "zhiboremark_"+generateRandomStr(23);
	}
	/**
	 * 生产音频评论文件名
	 * @return
	 */
	public static String generateCommentId() {
		return "comment_"+generateRandomStr(23);
	}
	
	public static String generateUGCColumnId(){
		return "UGC_"+generateRandomStr(23);
	}
	
	public static String generateColumnIcon(){
		return "column_icon_"+generateRandomStr(23);
	}
	public static String generateConfigId(){
		return "Config_"+generateRandomStr(20);
	}
	public static String generatePProgramStreamUrl(){
		return "pprogram_stream_url_"+generateRandomStr(23);
	}
	
	public static String generatePProgramIcon(){
		return "pprogram_icon_"+generateRandomStr(23);
	}
	
	public static String generateOperateLogId(){
		return "operate_log_"+generateRandomStr(23);
	}

	public static String generateRandomStr(int strLen) {
		if (strLen <= 0) {
			log.error("[IDGenerator].generateRandomStr() param strLen must be positive");
			return null;
		}
		Random r = new Random();
		int nRandom = 0;
		StringBuilder strRandom = new StringBuilder("");
		for (int nCount = 0; nCount < strLen / 2; nCount++) {
			nRandom = r.nextInt();
			if (nRandom < 0) {
				nRandom *= -1;
			}

			nRandom %= 100;
			if (nRandom < 10) {
				// strRandom += "0" + nRandom;
				strRandom.append("0").append(nRandom);
			} else {
				// strRandom += nRandom;
				strRandom.append(nRandom);
			}
		}
		if (strLen % 2 != 0) {
			nRandom = r.nextInt();
			if (nRandom < 0) {
				nRandom *= -1;
			}
			nRandom %= 10;
			// strRandom += nRandom;
			strRandom.append(nRandom);
		}
		return strRandom.toString();
	}

	public static void main(String[] s) {

		System.out.println(IdGenerator.generateRandomStr(32));

	}

}
