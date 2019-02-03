package p8499.speech.fd.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
  public static final String TABLE = "F0301";
  public static final String VIEW = "F0301View";
  public static final String NAME = "USER";

  //region usid User ID
  public static final String FIELD_USID = "USID";
  protected Integer usid = null;
  public static final int CONSTRAINT_USID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_USID_MIN = -99999999;
  public static final int CONSTRAINT_USID_MAX = 99999999;

  @javax.validation.constraints.Null(groups = {Insert.class})
  @javax.validation.constraints.NotNull(groups = {Update.class})
  public Integer getUsid() {
    return usid;
  }

  public User setUsid(Integer usid) {
    this.usid = usid;
    return this;
  }
  //endregion

  //region usalias User Alias
  public static final String FIELD_USALIAS = "USALIAS";
  protected String usalias = null;
  public static final int CONSTRAINT_USALIAS_LENGTH_STRING = 32;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_USALIAS_LENGTH_STRING)
  public String getUsalias() {
    return usalias;
  }

  public User setUsalias(String usalias) {
    this.usalias = usalias;
    return this;
  }
  //endregion

  //region usfrom User From
  public static final String FIELD_USFROM = "USFROM";
  public static final String USFROM_WECHAT = "wechat";
  public static final String USFROM_QQ = "qq";
  public static final String USFROM_PHONE = "phone";
  public static final String USFROM_LOCAL = "local";
  protected String usfrom = null;
  public static final int CONSTRAINT_USFROM_LENGTH_STRING = 8;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_USFROM_LENGTH_STRING)
  public String getUsfrom() {
    return usfrom;
  }

  public User setUsfrom(String usfrom) {
    this.usfrom = usfrom;
    return this;
  }
  //endregion

  //region uspswd User Password
  public static final String FIELD_USPSWD = "USPSWD";
  protected String uspswd = null;
  public static final int CONSTRAINT_USPSWD_LENGTH_STRING = 32;

  @javax.validation.constraints.Size(max = CONSTRAINT_USPSWD_LENGTH_STRING)
  public String getUspswd() {
    return uspswd;
  }

  public User setUspswd(String uspswd) {
    this.uspswd = uspswd;
    return this;
  }
  //endregion

  //region usname User Name
  public static final String FIELD_USNAME = "USNAME";
  protected String usname = null;
  public static final int CONSTRAINT_USNAME_LENGTH_STRING = 32;

  @javax.validation.constraints.Size(max = CONSTRAINT_USNAME_LENGTH_STRING)
  public String getUsname() {
    return usname;
  }

  public User setUsname(String usname) {
    this.usname = usname;
    return this;
  }
  //endregion

  //region usstatus User Status
  public static final String FIELD_USSTATUS = "USSTATUS";
  public static final Integer USSTATUS_NORMAL = 0;
  public static final Integer USSTATUS_LOCKED = 1;
  protected Integer usstatus = null;
  public static final int CONSTRAINT_USSTATUS_LENGTH_INTEGER = 1;
  public static final int CONSTRAINT_USSTATUS_MIN = -9;
  public static final int CONSTRAINT_USSTATUS_MAX = 9;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Min(value = CONSTRAINT_USSTATUS_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_USSTATUS_MAX)
  public Integer getUsstatus() {
    return usstatus;
  }

  public User setUsstatus(Integer usstatus) {
    this.usstatus = usstatus;
    return this;
  }
  //endregion

  public User(
      Integer usid, String usalias, String usfrom, String uspswd, String usname, Integer usstatus) {
    if (usid != null) this.usid = usid;
    if (usalias != null) this.usalias = usalias;
    if (usfrom != null) this.usfrom = usfrom;
    if (uspswd != null) this.uspswd = uspswd;
    if (usname != null) this.usname = usname;
    if (usstatus != null) this.usstatus = usstatus;
  }

  public User() {
    this(null, null, null, null, null, null);
  }

  public User clone() {
    return new User(usid, usalias, usfrom, uspswd, usname, usstatus);
  }
}