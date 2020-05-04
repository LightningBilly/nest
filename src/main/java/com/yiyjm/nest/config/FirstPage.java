package com.yiyjm.nest.config;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * 第一页
 * 首页显示的信息，放到 servletContext 里进行缓存
 *
 * @author jonny
 * @date 2020/04/30
 */
public class FirstPage {
	/**
	 * 网站显示的信息
	 */
	private String titlePrimary = Config.FIRST_PAGE_TITLE_PRIMARY;
	private String titleSecondary = Config.FIRST_PAGE_TITLE_SECONDARY;
	private String timeLove = Config.FIRST_PAGE_TIME_LOVE;
	private String contactQQ = Config.FIRST_PAGE_CONTACT_QQ;
	private String copyRight = Config.FIRST_PAGE_COPY_RIGHT;
	private String adminMessage = Config.FIRST_PAGE_ADMIN_MESSAGE;
	private String keywords = Config.FIRST_PAGE_KEYWORDS;
	private String description = Config.FIRST_PAGE_KEYWORDS;
	/**
	 * js,css 缓存时间戳
	 */
	private String timeStampHtml = Config.FIRST_PAGE_TIME_STAMP_HTML;
	/**
	 * cdn 地址
	 */
	private String cdnBootstrapCss = Config.CDN_BOOTSTRAP_CSS;
	private String cdnBootstrapJs = Config.CDN_BOOTSTRAP_JS;
	private String cdnMuiCss = Config.CDN_MUI_CSS;
	private String cdnMuiJs = Config.CDN_MUI_JS;
	private String cdnJqueryJs = Config.CDN_JQUERY_JS;

	/**
	 * 时间信息
	 * 今年是哪年，copyright中更新使用
	 */
	private int currencyYear;
	/**
	 * 相爱了 多少年，多少月，多少日
	 */
	private int lovePeriodYears;
	private int lovePeriodMonths;
	private int lovePeriodDays;
	/**
	 * 相爱总天数
	 */
	private int loveTotalDays;

	/**
	 * 第一页
	 */
	public FirstPage() {
		LocalDate today = LocalDate.now();
		LocalDate original = LocalDate.of(Config.TIME_LOVE_YEAR, Config.TIME_LOVE_MONTH, Config.TIME_LOVE_DAY);
		Period period = Period.between(original, today);

		lovePeriodYears = period.getYears();
		lovePeriodMonths = period.getMonths();
		lovePeriodDays = period.getDays();
		loveTotalDays = (int) ChronoUnit.DAYS.between(original, today);
		currencyYear = today.getYear();
	}

	/**
	 * 得到cdn梅css
	 * 获取 CdnMuiCss
	 *
	 * @return {@link String}
	 */
	public String getCdnMuiCss() {
		return cdnMuiCss;
	}

	/**
	 * 设置cdn梅css
	 * 设置 CdnMuiCss
	 *
	 * @param cdnMuiCss cdn梅css
	 */
	public void setCdnMuiCss(String cdnMuiCss) {
		this.cdnMuiCss = cdnMuiCss;
	}

	/**
	 * 得到cdn梅js
	 * 获取 CdnMuiJs
	 *
	 * @return {@link String}
	 */
	public String getCdnMuiJs() {
		return cdnMuiJs;
	}

	/**
	 * 设置cdn梅js
	 *
	 * @param cdnMuiJs cdn梅js
	 */
	public void setCdnMuiJs(String cdnMuiJs) {
		this.cdnMuiJs = cdnMuiJs;
	}

	/**
	 * 得到cdn引导css
	 *
	 * @return {@link String}
	 */
	public String getCdnBootstrapCss() {
		return cdnBootstrapCss;
	}

	/**
	 * cdn引导设置css
	 *
	 * @param cdnBootstrapCss cdn引导css
	 */
	public void setCdnBootstrapCss(String cdnBootstrapCss) {
		this.cdnBootstrapCss = cdnBootstrapCss;
	}

	/**
	 * 得到cdn引导js
	 *
	 * @return {@link String}
	 */
	public String getCdnBootstrapJs() {
		return cdnBootstrapJs;
	}

	/**
	 * 设置cdn引导js
	 *
	 * @param cdnBootstrapJs cdn引导js
	 */
	public void setCdnBootstrapJs(String cdnBootstrapJs) {
		this.cdnBootstrapJs = cdnBootstrapJs;
	}

	/**
	 * 得到cdn jquery js
	 *
	 * @return {@link String}
	 */
	public String getCdnJqueryJs() {
		return cdnJqueryJs;
	}

	/**
	 * 设置cdn jquery js
	 *
	 * @param cdnJqueryJs cdn jquery js
	 */
	public void setCdnJqueryJs(String cdnJqueryJs) {
		this.cdnJqueryJs = cdnJqueryJs;
	}

	/**
	 * 获取时间戳的html
	 *
	 * @return {@link String}
	 */
	public String getTimeStampHtml() {
		return timeStampHtml;
	}

	/**
	 * 设置时间戳的html
	 *
	 * @param timeStampHtml 时间戳的html
	 */
	public void setTimeStampHtml(String timeStampHtml) {
		this.timeStampHtml = timeStampHtml;
	}

	/**
	 * 得到货币一年
	 *
	 * @return int
	 */
	public int getCurrencyYear() {
		return currencyYear;
	}

	/**
	 * 设置货币一年
	 *
	 * @param currencyYear 货币一年
	 */
	public void setCurrencyYear(int currencyYear) {
		this.currencyYear = currencyYear;
	}

	/**
	 * 爱期间年
	 *
	 * @return int
	 */
	public int getLovePeriodYears() {
		return lovePeriodYears;
	}

	/**
	 * 爱期间年
	 *
	 * @param lovePeriodYears 爱期间年
	 */
	public void setLovePeriodYears(int lovePeriodYears) {
		this.lovePeriodYears = lovePeriodYears;
	}

	/**
	 * 让爱期月
	 *
	 * @return int
	 */
	public int getLovePeriodMonths() {
		return lovePeriodMonths;
	}

	/**
	 * 集爱期月
	 *
	 * @param lovePeriodMonths 爱期月
	 */
	public void setLovePeriodMonths(int lovePeriodMonths) {
		this.lovePeriodMonths = lovePeriodMonths;
	}

	/**
	 * 让爱期天
	 *
	 * @return int
	 */
	public int getLovePeriodDays() {
		return lovePeriodDays;
	}

	/**
	 * 集爱期天
	 *
	 * @param lovePeriodDays 爱期天
	 */
	public void setLovePeriodDays(int lovePeriodDays) {
		this.lovePeriodDays = lovePeriodDays;
	}

	/**
	 * 有爱总天
	 *
	 * @return int
	 */
	public int getLoveTotalDays() {
		return loveTotalDays;
	}

	/**
	 * 设置总爱天
	 *
	 * @param loveTotalDays 爱总天
	 */
	public void setLoveTotalDays(int loveTotalDays) {
		this.loveTotalDays = loveTotalDays;
	}

	/**
	 * 得到冠军的主要
	 *
	 * @return {@link String}
	 */
	public String getTitlePrimary() {
		return titlePrimary;
	}

	/**
	 * 设置标题的主要
	 *
	 * @param titlePrimary 标题主要
	 */
	public void setTitlePrimary(String titlePrimary) {
		this.titlePrimary = titlePrimary;
	}

	/**
	 * 得到标题二次
	 *
	 * @return {@link String}
	 */
	public String getTitleSecondary() {
		return titleSecondary;
	}

	/**
	 * 设置标题二次
	 *
	 * @param titleSecondary 标题二次
	 */
	public void setTitleSecondary(String titleSecondary) {
		this.titleSecondary = titleSecondary;
	}

	/**
	 * 有时间爱
	 *
	 * @return {@link String}
	 */
	public String getTimeLove() {
		return timeLove;
	}

	/**
	 * 设置时间的爱
	 *
	 * @param timeLove 时间的爱
	 */
	public void setTimeLove(String timeLove) {
		this.timeLove = timeLove;
	}

	/**
	 * 得到联系qq
	 *
	 * @return {@link String}
	 */
	public String getContactQQ() {
		return contactQQ;
	}

	/**
	 * 设置联系qq
	 *
	 * @param contactQQ 联系qq
	 */
	public void setContactQQ(String contactQQ) {
		this.contactQQ = contactQQ;
	}

	/**
	 * 获得版权
	 *
	 * @return {@link String}
	 */
	public String getCopyRight() {
		return copyRight;
	}

	/**
	 * 集版权
	 *
	 * @param copyRight 版权
	 */
	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}

	/**
	 * 得到管理信息
	 *
	 * @return {@link String}
	 */
	public String getAdminMessage() {
		return adminMessage;
	}

	/**
	 * 集管理消息
	 *
	 * @param adminMessage 管理信息
	 */
	public void setAdminMessage(String adminMessage) {
		this.adminMessage = adminMessage;
	}

	/**
	 * 得到关键字
	 *
	 * @return {@link String}
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * 设置关键字
	 *
	 * @param keywords 关键字
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * 得到描述
	 *
	 * @return {@link String}
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 集描述
	 *
	 * @param description 描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
