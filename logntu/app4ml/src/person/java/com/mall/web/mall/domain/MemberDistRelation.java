package com.mall.web.mall.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @Description(描述) :商会员多级关系表 @author(作者) : ventrue
 * @date (开发日期) : 2015年8月6日 下午2:01:23
 */
@Entity
@Table(name = "ml_member_dist_relation")
public class MemberDistRelation implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name = "id")
	// private int id; // 主键
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "assigned")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "user_id")
	private int userId; // 会员id
	@Column(name = "d_lower_num")
	private int dLowerNum; // 直接下级会员数
	@Column(name = "a_lower_num")
	private int aLowerNum; // 所有下级会员数
	@Column(name = "sup_01")
	private int sup01; // 上1级会员id(直接上級)
	@Column(name = "sup_02")
	private int sup02; // 上2级会员id
	@Column(name = "sup_03")
	private int sup03; // 上3级会员id
	@Column(name = "sup_04")
	private int sup04; // 上4级会员id
	@Column(name = "sup_05")
	private int sup05; // 上5级会员id
	@Column(name = "sup_06")
	private int sup06; // 上6级会员id
	@Column(name = "sup_07")
	private int sup07; // 上7级会员id
	@Column(name = "sup_08")
	private int sup08; // 上8级会员id
	@Column(name = "sup_09")
	private int sup09; // 上9级会员id
	@Column(name = "sup_10")
	private int sup10; // 上10级会员id
	@Column(name = "sup_11")
	private int sup11; // 上11级会员id
	@Column(name = "sup_12")
	private int sup12; // 上12级会员id
	@Column(name = "sup_13")
	private int sup13; // 上13级会员id
	@Column(name = "sup_14")
	private int sup14; // 上14级会员id
	@Column(name = "sup_15")
	private int sup15; // 上15级会员id
	@Column(name = "create_channel")
	private String channel; // 形成渠道(0-web，1-微信，2-qq)
	@Column(name = "create_time")
	private Date createTime; // 形成时间
	@Column(name = "remark")
	private String remark; // 备注

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getdLowerNum() {
		return dLowerNum;
	}

	public void setdLowerNum(int dLowerNum) {
		this.dLowerNum = dLowerNum;
	}

	public int getaLowerNum() {
		return aLowerNum;
	}

	public void setaLowerNum(int aLowerNum) {
		this.aLowerNum = aLowerNum;
	}

	public int getSup01() {
		return sup01;
	}

	public void setSup01(int sup01) {
		this.sup01 = sup01;
	}

	public int getSup02() {
		return sup02;
	}

	public void setSup02(int sup02) {
		this.sup02 = sup02;
	}

	public int getSup03() {
		return sup03;
	}

	public void setSup03(int sup03) {
		this.sup03 = sup03;
	}

	public int getSup04() {
		return sup04;
	}

	public void setSup04(int sup04) {
		this.sup04 = sup04;
	}

	public int getSup05() {
		return sup05;
	}

	public void setSup05(int sup05) {
		this.sup05 = sup05;
	}

	public int getSup06() {
		return sup06;
	}

	public void setSup06(int sup06) {
		this.sup06 = sup06;
	}

	public int getSup07() {
		return sup07;
	}

	public void setSup07(int sup07) {
		this.sup07 = sup07;
	}

	public int getSup08() {
		return sup08;
	}

	public void setSup08(int sup08) {
		this.sup08 = sup08;
	}

	public int getSup09() {
		return sup09;
	}

	public void setSup09(int sup09) {
		this.sup09 = sup09;
	}

	public int getSup10() {
		return sup10;
	}

	public void setSup10(int sup10) {
		this.sup10 = sup10;
	}

	public int getSup11() {
		return sup11;
	}

	public void setSup11(int sup11) {
		this.sup11 = sup11;
	}

	public int getSup12() {
		return sup12;
	}

	public void setSup12(int sup12) {
		this.sup12 = sup12;
	}

	public int getSup13() {
		return sup13;
	}

	public void setSup13(int sup13) {
		this.sup13 = sup13;
	}

	public int getSup14() {
		return sup14;
	}

	public void setSup14(int sup14) {
		this.sup14 = sup14;
	}

	public int getSup15() {
		return sup15;
	}

	public void setSup15(int sup15) {
		this.sup15 = sup15;
	}

	public int getSup(int num) {
		switch (num) {
		case 1:
			return sup01;
		case 2:
			return sup02;
		case 3:
			return sup03;
		case 4:
			return sup04;
		case 5:
			return sup05;
		case 6:
			return sup06;
		case 7:
			return sup07;
		case 8:
			return sup08;
		case 9:
			return sup09;
		case 10:
			return sup10;
		case 11:
			return sup11;
		case 12:
			return sup12;
		case 13:
			return sup13;
		case 14:
			return sup14;
		case 15:
			return sup15;
		default:
			return 0;
		}
	}

	public void setSup(int num, int value) {
		switch (num) {
		case 1:
			sup01=value;
			break;
		case 2:
			sup02=value;
			break;
		case 3:
			sup03=value;
			break;
		case 4:
			sup04=value;
			break;
		case 5:
			sup05=value;
			break;
		case 6:
			sup06=value;
			break;
		case 7:
			sup07=value;
			break;
		case 8:
			sup08=value;
			break;
		case 9:
			sup09=value;
			break;
		case 10:
			sup10=value;
			break;
		case 11:
			sup11=value;
			break;
		case 12:
			sup12=value;
			break;
		case 13:
			sup13=value;
			break;
		case 14:
			sup14=value;
			break;
		case 15:
			sup15=value;
			break;
		}
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
