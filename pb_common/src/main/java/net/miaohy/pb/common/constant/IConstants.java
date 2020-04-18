package net.miaohy.pb.common.constant;

/**
 * 常量定义接口
 *
 * @author Jerry Cheng
 * @date Dec 13, 2019
 */
public interface IConstants {
    // Default server thread retries
    final static int DEFAULT_SERVER_THREAD_RETRIES = 3;
    final static int DEFAULT_SERVER_THREAD_MIN_KEEP_TIME = 10 * 60 * 1000;

    // default thread sleep(unit: millisecond)
    final static int DEFAULT_THREAD_SLEEP = 5000;

    // 获取热门搜索词前20
    final static int MAX_HOT_TERM_LENGTH = 20;


}
