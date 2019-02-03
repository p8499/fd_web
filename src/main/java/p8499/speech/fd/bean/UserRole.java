package p8499.speech.fd.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRole {
  public static final String TABLE = "F0311";
  public static final String VIEW = "F0311View";
  public static final String NAME = "USERROLE";

  //region urid User Role ID
  public static final String FIELD_URID = "URID";
  protected Integer urid = null;
  public static final int CONSTRAINT_URID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_URID_MIN = -99999999;
  public static final int CONSTRAINT_URID_MAX = 99999999;

  @javax.validation.constraints.Null(groups = {Insert.class})
  @javax.validation.constraints.NotNull(groups = {Update.class})
  public Integer getUrid() {
    return urid;
  }

  public UserRole setUrid(Integer urid) {
    this.urid = urid;
    return this;
  }
  //endregion

  //region urusid User ID
  public static final String FIELD_URUSID = "URUSID";
  protected Integer urusid = null;
  public static final int CONSTRAINT_URUSID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_URUSID_MIN = -99999999;
  public static final int CONSTRAINT_URUSID_MAX = 99999999;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Min(value = CONSTRAINT_URUSID_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_URUSID_MAX)
  public Integer getUrusid() {
    return urusid;
  }

  public UserRole setUrusid(Integer urusid) {
    this.urusid = urusid;
    return this;
  }
  //endregion

  //region urrlid Role ID
  public static final String FIELD_URRLID = "URRLID";
  protected String urrlid = null;
  public static final int CONSTRAINT_URRLID_LENGTH_STRING = 32;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_URRLID_LENGTH_STRING)
  public String getUrrlid() {
    return urrlid;
  }

  public UserRole setUrrlid(String urrlid) {
    this.urrlid = urrlid;
    return this;
  }
  //endregion

  //region urrlname Role Name
  public static final String FIELD_URRLNAME = "URRLNAME";
  protected String urrlname = null;

  @javax.validation.constraints.Null(groups = {Insert.class, Update.class})
  public String getUrrlname() {
    return urrlname;
  }

  public UserRole setUrrlname(String urrlname) {
    this.urrlname = urrlname;
    return this;
  }
  //endregion

  public UserRole(Integer urid, Integer urusid, String urrlid, String urrlname) {
    if (urid != null) this.urid = urid;
    if (urusid != null) this.urusid = urusid;
    if (urrlid != null) this.urrlid = urrlid;
    if (urrlname != null) this.urrlname = urrlname;
  }

  public UserRole() {
    this(null, null, null, null);
  }

  public UserRole clone() {
    return new UserRole(urid, urusid, urrlid, urrlname);
  }
}