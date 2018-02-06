package org.zhenghao.mvp.model.bean;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * 登录接口返回的数据
 * Created by www on 3/18/17.
 */
public class UserBean implements Parcelable {


    /**
     * id : 3
     * names : 18428344832
     * password : e10adc3949ba59abbe56e057f20f883e
     * gender : 0
     * mobile : 18428344832
     * birthTime : null
     * idCard : 513025197707106039
     * headUrl : null
     * status : 1
     * uuid : 6936cdd73db74055b65ab47a54fdc142
     * partyMemberInformation : {"id":1,"headPortraitUrl":"resource/img/1512360515837.png","smallHeadPortraitUrl":"resource/img/1512360515837.png","names":"何旭","dateBirth":238867200000,"education":"本科","address":"宝胜村1组","phone":"15982407130","workUnit":"宝胜村党支部","posts":null,"commitment":null,"partyGroup":"1组","nation":"汉","idcard":"513025197707106039","placeOfOrigin":"巴中","integral":0,"state":1,"createDate":1512360516000,"timeToJoinTheParty":820598400000,"sex":1,"sort":1,"partyMembersPovertyHelpNames":null}
     * createTime : 1514860610000
     */

    private int id;
    private String names;
    private String password;
    private int gender;
    private String mobile;
    private String birthTime;
    private String idCard;
    private String headUrl;
    private int status;
    private String uuid;
    private PartyMemberInformationBean partyMemberInformation;
    private long createTime;
    private VillageBean village;

    public VillageBean getVillage() {
        return village;
    }

    public void setVillage(VillageBean village) {
        this.village = village;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(String birthTime) {
        this.birthTime = birthTime;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public PartyMemberInformationBean getPartyMemberInformation() {
        return partyMemberInformation;
    }

    public void setPartyMemberInformation(PartyMemberInformationBean partyMemberInformation) {
        this.partyMemberInformation = partyMemberInformation;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public static class VillageBean implements Parcelable {

        /**
         * createBy : lpj
         * updateDate : 1517537062000
         * address : address
         * names : mio
         * updateBy : lpj
         * details : resource/html/2018/01/16/1516099155406309.html
         * id : 2
         * state : 0
         * parentId : 0
         * createDate : 1516067911000
         */

        public String createBy;
        public long updateDate;
        public String address;
        public String names;
        public String updateBy;
        public String details;

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNames() {
            return names;
        }

        public void setNames(String names) {
            this.names = names;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String id;
        public int state;
        public int parentId;
        public long createDate;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.createBy);
            dest.writeLong(this.updateDate);
            dest.writeString(this.address);
            dest.writeString(this.names);
            dest.writeString(this.updateBy);
            dest.writeString(this.details);
            dest.writeString(this.id);
            dest.writeInt(this.state);
            dest.writeInt(this.parentId);
            dest.writeLong(this.createDate);
        }

        public VillageBean() {
        }

        protected VillageBean(Parcel in) {
            this.createBy = in.readString();
            this.updateDate = in.readLong();
            this.address = in.readString();
            this.names = in.readString();
            this.updateBy = in.readString();
            this.details = in.readString();
            this.id = in.readString();
            this.state = in.readInt();
            this.parentId = in.readInt();
            this.createDate = in.readLong();
        }

        public static final Creator<VillageBean> CREATOR = new Creator<VillageBean>() {
            @Override
            public VillageBean createFromParcel(Parcel source) {
                return new VillageBean(source);
            }

            @Override
            public VillageBean[] newArray(int size) {
                return new VillageBean[size];
            }
        };
    }

    public static class PartyMemberInformationBean implements Parcelable {
        /**
         * id : 1
         * headPortraitUrl : resource/img/1512360515837.png
         * smallHeadPortraitUrl : resource/img/1512360515837.png
         * names : 何旭
         * dateBirth : 238867200000
         * education : 本科
         * address : 宝胜村1组
         * phone : 15982407130
         * workUnit : 宝胜村党支部
         * posts : null
         * commitment : null
         * partyGroup : 1组
         * nation : 汉
         * idcard : 513025197707106039
         * placeOfOrigin : 巴中
         * integral : 0
         * state : 1
         * createDate : 1512360516000
         * timeToJoinTheParty : 820598400000
         * sex : 1
         * sort : 1
         * partyMembersPovertyHelpNames : null
         */

        private int id;
        private String headPortraitUrl;
        private String smallHeadPortraitUrl;
        private String names;
        private long dateBirth;
        private String education;
        private String address;
        private String phone;
        private String workUnit;
        private String posts;
        private String commitment;
        private String partyGroup;
        private String nation;
        private String idcard;
        private String placeOfOrigin;
        private String integral;
        private String state;
        private String createDate;
        private String timeToJoinTheParty;
        private String sex;
        private String sort;
        private String partyMembersPovertyHelpNames;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHeadPortraitUrl() {
            return headPortraitUrl;
        }

        public void setHeadPortraitUrl(String headPortraitUrl) {
            this.headPortraitUrl = headPortraitUrl;
        }

        public String getSmallHeadPortraitUrl() {
            return smallHeadPortraitUrl;
        }

        public void setSmallHeadPortraitUrl(String smallHeadPortraitUrl) {
            this.smallHeadPortraitUrl = smallHeadPortraitUrl;
        }

        public String getNames() {
            return names;
        }

        public void setNames(String names) {
            this.names = names;
        }

        public long getDateBirth() {
            return dateBirth;
        }

        public void setDateBirth(long dateBirth) {
            this.dateBirth = dateBirth;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getWorkUnit() {
            return workUnit;
        }

        public void setWorkUnit(String workUnit) {
            this.workUnit = workUnit;
        }

        public String getPosts() {
            return posts;
        }

        public void setPosts(String posts) {
            this.posts = posts;
        }

        public String getCommitment() {
            return commitment;
        }

        public void setCommitment(String commitment) {
            this.commitment = commitment;
        }

        public String getPartyGroup() {
            return partyGroup;
        }

        public void setPartyGroup(String partyGroup) {
            this.partyGroup = partyGroup;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getPlaceOfOrigin() {
            return placeOfOrigin;
        }

        public void setPlaceOfOrigin(String placeOfOrigin) {
            this.placeOfOrigin = placeOfOrigin;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getTimeToJoinTheParty() {
            return timeToJoinTheParty;
        }

        public void setTimeToJoinTheParty(String timeToJoinTheParty) {
            this.timeToJoinTheParty = timeToJoinTheParty;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getPartyMembersPovertyHelpNames() {
            return partyMembersPovertyHelpNames;
        }

        public void setPartyMembersPovertyHelpNames(String partyMembersPovertyHelpNames) {
            this.partyMembersPovertyHelpNames = partyMembersPovertyHelpNames;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.headPortraitUrl);
            dest.writeString(this.smallHeadPortraitUrl);
            dest.writeString(this.names);
            dest.writeLong(this.dateBirth);
            dest.writeString(this.education);
            dest.writeString(this.address);
            dest.writeString(this.phone);
            dest.writeString(this.workUnit);
            dest.writeString(this.posts);
            dest.writeString(this.commitment);
            dest.writeString(this.partyGroup);
            dest.writeString(this.nation);
            dest.writeString(this.idcard);
            dest.writeString(this.placeOfOrigin);
            dest.writeString(this.integral);
            dest.writeString(this.state);
            dest.writeString(this.createDate);
            dest.writeString(this.timeToJoinTheParty);
            dest.writeString(this.sex);
            dest.writeString(this.sort);
            dest.writeString(this.partyMembersPovertyHelpNames);
        }

        public PartyMemberInformationBean() {
        }

        protected PartyMemberInformationBean(Parcel in) {
            this.id = in.readInt();
            this.headPortraitUrl = in.readString();
            this.smallHeadPortraitUrl = in.readString();
            this.names = in.readString();
            this.dateBirth = in.readLong();
            this.education = in.readString();
            this.address = in.readString();
            this.phone = in.readString();
            this.workUnit = in.readString();
            this.posts = in.readString();
            this.commitment = in.readString();
            this.partyGroup = in.readString();
            this.nation = in.readString();
            this.idcard = in.readString();
            this.placeOfOrigin = in.readString();
            this.integral = in.readString();
            this.state = in.readString();
            this.createDate = in.readString();
            this.timeToJoinTheParty = in.readString();
            this.sex = in.readString();
            this.sort = in.readString();
            this.partyMembersPovertyHelpNames = in.readString();
        }

        public static final Creator<PartyMemberInformationBean> CREATOR = new Creator<PartyMemberInformationBean>() {
            @Override
            public PartyMemberInformationBean createFromParcel(Parcel source) {
                return new PartyMemberInformationBean(source);
            }

            @Override
            public PartyMemberInformationBean[] newArray(int size) {
                return new PartyMemberInformationBean[size];
            }
        };
    }

    public UserBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.names);
        dest.writeString(this.password);
        dest.writeInt(this.gender);
        dest.writeString(this.mobile);
        dest.writeString(this.birthTime);
        dest.writeString(this.idCard);
        dest.writeString(this.headUrl);
        dest.writeInt(this.status);
        dest.writeString(this.uuid);
        dest.writeParcelable(this.partyMemberInformation, flags);
        dest.writeLong(this.createTime);
        dest.writeParcelable(this.village, flags);
    }

    protected UserBean(Parcel in) {
        this.id = in.readInt();
        this.names = in.readString();
        this.password = in.readString();
        this.gender = in.readInt();
        this.mobile = in.readString();
        this.birthTime = in.readString();
        this.idCard = in.readString();
        this.headUrl = in.readString();
        this.status = in.readInt();
        this.uuid = in.readString();
        this.partyMemberInformation = in.readParcelable(PartyMemberInformationBean.class.getClassLoader());
        this.createTime = in.readLong();
        this.village = in.readParcelable(VillageBean.class.getClassLoader());
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}