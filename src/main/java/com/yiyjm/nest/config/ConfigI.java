package com.yiyjm.nest.config;

import java.util.HashMap;
import java.util.Map;

/**
 * config 配置接口
 *
 * @author jonny
 * @date 2020/04/30
 */
public interface ConfigI {

	/**
	 * nest 基本信息
	 */

	/* 分页时，每页数量 */
	int PAGE_NUMBER = 10;

	/* 邮件接收者 */
	String[] ADMIN_MAIL = {"jonny6015@163.com", "dilys8240@163.com"};

	/* 相爱时间：年，月，日 */
	int TIME_LOVE_YEAR = 2013;
	int TIME_LOVE_MONTH = 2;
	int TIME_LOVE_DAY = 10;

	/* ServletContext 中的名字 */
	String SERVLET_FIRST_PAGE = "firstPage";

	/* 后台登录信息 */
	String TOKEN_URL = "log_in";
	String TOKEN_PHONE = "13221066206";
	String TOKEN_DO_LOGIN = "3C731628238C7D90E2C87529851730D8";
	String TOKEN_GOOGLE_ID = "423673846715-853t1fiahpgoullm4uua944dbp9f6bat.apps.googleusercontent.com";
	String TOKEN_GOOGLE_KEY = "Sv0-jyyQyocFlbG3LIDT1AAi";

	/* 首页主标题 */
	String FIRST_PAGE_TITLE_PRIMARY = "伊阳静美";
	String FIRST_PAGE_TITLE_SECONDARY = "阳 &nbsp; ❤ &nbsp; 静";
	String FIRST_PAGE_TIME_LOVE = "2013-02-10";
	String FIRST_PAGE_CONTACT_QQ = "10947580";
	String FIRST_PAGE_COPY_RIGHT = "CopyRight © 2013-";
	String FIRST_PAGE_ADMIN_MESSAGE = "欢迎访问，欢迎观看，欢迎留言";
	String FIRST_PAGE_KEYWORDS = "伊阳静美,吕静,张向阳";
	String FIRST_PAGE_TIME_STAMP_HTML = "1";

	/**
	 * nest 本地存储配置
	 * 默认使用本地存储（OSS流量收费）
	 * 使用 nginx 转发到地址 https://域名/static
	 */
	/* 本地： 上传的图片（将压缩后的保存到服务器） */
	String HTTPS_PROTOCOL = "https://";
	String HTTP_PROTOCOL = "http://";
	boolean ENABLE_LOCAL = true;
	String LOCAL_FILE_ROOT_PATH = "/data/wwwroot/yiyjm.com/static/";
	String LOCAL_BLOG_FOLDER = "blog/";
	String LOCAL_PHOTOS_FOLDER = "falls/";
	String LOCAL_URL_PREFIX = HTTPS_PROTOCOL + "yiyjm.com/static/";

	/**
	 * nest OSS存储配置  默认不启用
	 * 阿里 OSS 配置，这里使用 https 协议
	 * 上传时 http://oss-cn-shanghai.aliyuncs.com/
	 * 使用时 http://yiyjm.oss-cn-shanghai.aliyuncs.com/
	 * 自定义 http://static.yiyjm.com/
	 * 用户登录名称 yiyjm@jonny.onaliyun.com
	 */
	/* OSS： 上传的图片（将压缩后的保存到服务器，然后上传到 oss，便于快速访问） */
	boolean ENABLE_OSS = false;
	String IMAGE_LOCAL_PATH = "/data/nest/images/";
	String OSS_PROTOCOL = "https://";
	/* Bucket Name */
	String OSS_BUCKET_NAME = "yiyjm";
	/* Endpoint */
	String OSS_ENDPOINT = "oss-cn-shanghai.aliyuncs.com/";
	/* Access Key Id */
	String OSS_ACCESS_KEY_ID = "";
	/* Access Key Secret */
	String OSS_ACCESS_KEY_SECRET = "";
	/* 将博客中的图片 和 相册中的图片 分目录保存 */
	String OSS_BLOG_FOLDER = "nest/blog/";
	String OSS_PHOTOS_FOLDER = "nest/falls/";
	/* 自定义访问域名，不使用 oss 的域名进行访问 */
	String OSS_URL_PREFIX = HTTPS_PROTOCOL + "static.yiyjm.com/";

	/**
	 * nest 机器人配置
	 */
	/* 莉莉聊天机器人接口 http://www.itpk.cn/ */
	String LILI_API_KEY = "2b953da46ab18efce647ce826239b07e";
	String LILI_API_SECRET = "yddptyiwoht9";

	/* 图灵聊天机器人接口 http://www.turingapi.com/ */
	String TULING_KEY = "35eeb5ba65784de197227997449a508a";
	String TULING_SECRET = "b5d1f9ca90e59799";

	/**
	 * nest 其他配置
	 */

	/* 支持上传的图片格式， 统一使用小写 */
	String IMAGE_SUFFIX = ".jpg,.png,.jpeg,.png,.gif";
	/* 图片最大值 */
	float IMAGE_MAX_SIZE = 2048;
	/* 图片压缩质量 */
	float IMAGE_ZIP_QUALITY = 0.6f;

	/* ip缓存，8分钟只记录一次 */
	Map<String, Long> RECORD_IP_ADDR = new HashMap<>();

	//聊天防刷，每个 session_id 3秒聊一次5020
	Map<String, Long> RECORD_CHAT_ID = new HashMap<>();

	/* 6小时内留言的Ip 及 个数 */
	Map<String, Integer> LETTER_TODAY_IP = new HashMap<>();

	/* 不记录访问的 ip，通常为内网 Ip */
	String RECORD_IP_IGNORE = "0:0:0:0:0:0:0:0 0:0:0:0:0:0:0:1 127.0.0.1 0.0.0.0";

	/* 6小时内每个ip最多留言几次。防灌水。 */
	int LETTER_TODAY_IP_MAX = 10;

	/* 爬虫设置 */
	String CRAWLER_LOCAL_FOLER = "/home/crawler/";
	int CRAWLER_MAX_PAGES_TO_FETCH = 2000;
	int CRAWLER_MAX_DEPTH_OF_CRAWLING = 2;

	/* js，css 优先使用 cdn */
	String CDN_BOOTSTRAP_CSS = "//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css";
	String CDN_BOOTSTRAP_JS = "//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js";
	String CDN_MUI_CSS = "//cdnjs.cloudflare.com/ajax/libs/mui/3.7.1/css/mui.min.css";
	String CDN_MUI_JS = "//cdnjs.cloudflare.com/ajax/libs/mui/3.7.1/js/mui.min.js";
	String CDN_JQUERY_JS = "//cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js";

}

