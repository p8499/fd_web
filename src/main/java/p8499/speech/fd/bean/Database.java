package p8499.speech.fd.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Database {
  public static final String TABLE = "F4701";
  public static final String VIEW = "F4701View";
  public static final String NAME = "DATABASE";

  //region dbid Database ID
  public static final String FIELD_DBID = "DBID";
  protected Integer dbid = null;
  public static final int CONSTRAINT_DBID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_DBID_MIN = -99999999;
  public static final int CONSTRAINT_DBID_MAX = 99999999;

  @javax.validation.constraints.Null(groups = {Insert.class})
  @javax.validation.constraints.NotNull(groups = {Update.class})
  public Integer getDbid() {
    return dbid;
  }

  public Database setDbid(Integer dbid) {
    this.dbid = dbid;
    return this;
  }
  //endregion

  //region dbname Database Name
  public static final String FIELD_DBNAME = "DBNAME";
  protected String dbname = null;
  public static final int CONSTRAINT_DBNAME_LENGTH_STRING = 32;

  @javax.validation.constraints.Size(max = CONSTRAINT_DBNAME_LENGTH_STRING)
  public String getDbname() {
    return dbname;
  }

  public Database setDbname(String dbname) {
    this.dbname = dbname;
    return this;
  }
  //endregion

  //region dbstatus Database Status
  public static final String FIELD_DBSTATUS = "DBSTATUS";
  public static final Integer DBSTATUS_INITIALIZED = 0;
  public static final Integer DBSTATUS_DIPHONES_PREPARED = 1;
  public static final Integer DBSTATUS_BUILD_PENDING = 2;
  protected Integer dbstatus = null;
  public static final int CONSTRAINT_DBSTATUS_LENGTH_INTEGER = 1;
  public static final int CONSTRAINT_DBSTATUS_MIN = -9;
  public static final int CONSTRAINT_DBSTATUS_MAX = 9;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Min(value = CONSTRAINT_DBSTATUS_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_DBSTATUS_MAX)
  public Integer getDbstatus() {
    return dbstatus;
  }

  public Database setDbstatus(Integer dbstatus) {
    this.dbstatus = dbstatus;
    return this;
  }
  //endregion

  //region dbmanager Database Manager
  public static final String FIELD_DBMANAGER = "DBMANAGER";
  protected Integer dbmanager = null;
  public static final int CONSTRAINT_DBMANAGER_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_DBMANAGER_MIN = -99999999;
  public static final int CONSTRAINT_DBMANAGER_MAX = 99999999;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Min(value = CONSTRAINT_DBMANAGER_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_DBMANAGER_MAX)
  public Integer getDbmanager() {
    return dbmanager;
  }

  public Database setDbmanager(Integer dbmanager) {
    this.dbmanager = dbmanager;
    return this;
  }
  //endregion

  //region dbspeaker Database Speaker
  public static final String FIELD_DBSPEAKER = "DBSPEAKER";
  protected Integer dbspeaker = null;
  public static final int CONSTRAINT_DBSPEAKER_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_DBSPEAKER_MIN = -99999999;
  public static final int CONSTRAINT_DBSPEAKER_MAX = 99999999;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Min(value = CONSTRAINT_DBSPEAKER_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_DBSPEAKER_MAX)
  public Integer getDbspeaker() {
    return dbspeaker;
  }

  public Database setDbspeaker(Integer dbspeaker) {
    this.dbspeaker = dbspeaker;
    return this;
  }
  //endregion

  //region dborig Database Originator
  public static final String FIELD_DBORIG = "DBORIG";
  protected Integer dborig = null;
  public static final int CONSTRAINT_DBORIG_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_DBORIG_MIN = -99999999;
  public static final int CONSTRAINT_DBORIG_MAX = 99999999;

  @javax.validation.constraints.Min(value = CONSTRAINT_DBORIG_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_DBORIG_MAX)
  public Integer getDborig() {
    return dborig;
  }

  public Database setDborig(Integer dborig) {
    this.dborig = dborig;
    return this;
  }
  //endregion

  //region dbcrtime Database Creation Time
  public static final String FIELD_DBCRTIME = "DBCRTIME";

  @com.fasterxml.jackson.annotation.JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
  protected java.util.Date dbcrtime = null;

  public java.util.Date getDbcrtime() {
    return dbcrtime;
  }

  public Database setDbcrtime(java.util.Date dbcrtime) {
    this.dbcrtime = dbcrtime;
    return this;
  }
  //endregion

  //region dbmanagername Database Manager Name
  public static final String FIELD_DBMANAGERNAME = "DBMANAGERNAME";
  protected String dbmanagername = null;

  @javax.validation.constraints.Null(groups = {Insert.class, Update.class})
  public String getDbmanagername() {
    return dbmanagername;
  }

  public Database setDbmanagername(String dbmanagername) {
    this.dbmanagername = dbmanagername;
    return this;
  }
  //endregion

  //region dbspeakername Database Speaker Name
  public static final String FIELD_DBSPEAKERNAME = "DBSPEAKERNAME";
  protected String dbspeakername = null;

  @javax.validation.constraints.Null(groups = {Insert.class, Update.class})
  public String getDbspeakername() {
    return dbspeakername;
  }

  public Database setDbspeakername(String dbspeakername) {
    this.dbspeakername = dbspeakername;
    return this;
  }
  //endregion

  //region dbdpstatus Database Diphone Status
  public static final String FIELD_DBDPSTATUS = "DBDPSTATUS";
  protected Integer dbdpstatus = null;

  @javax.validation.constraints.Null(groups = {Insert.class, Update.class})
  public Integer getDbdpstatus() {
    return dbdpstatus;
  }

  public Database setDbdpstatus(Integer dbdpstatus) {
    this.dbdpstatus = dbdpstatus;
    return this;
  }
  //endregion

  //region dbdpc Diphones Count
  public static final String FIELD_DBDPC = "DBDPC";
  protected Integer dbdpc = null;

  @javax.validation.constraints.Null(groups = {Insert.class, Update.class})
  public Integer getDbdpc() {
    return dbdpc;
  }

  public Database setDbdpc(Integer dbdpc) {
    this.dbdpc = dbdpc;
    return this;
  }
  //endregion

  //region dbdpc0 Diphones Count Status 0
  public static final String FIELD_DBDPC0 = "DBDPC0";
  protected Integer dbdpc0 = null;

  @javax.validation.constraints.Null(groups = {Insert.class, Update.class})
  public Integer getDbdpc0() {
    return dbdpc0;
  }

  public Database setDbdpc0(Integer dbdpc0) {
    this.dbdpc0 = dbdpc0;
    return this;
  }
  //endregion

  //region dbdpc1 Diphones Count Status 1
  public static final String FIELD_DBDPC1 = "DBDPC1";
  protected Integer dbdpc1 = null;

  @javax.validation.constraints.Null(groups = {Insert.class, Update.class})
  public Integer getDbdpc1() {
    return dbdpc1;
  }

  public Database setDbdpc1(Integer dbdpc1) {
    this.dbdpc1 = dbdpc1;
    return this;
  }
  //endregion

  public Database(
      Integer dbid,
      String dbname,
      Integer dbstatus,
      Integer dbmanager,
      Integer dbspeaker,
      Integer dborig,
      java.util.Date dbcrtime,
      String dbmanagername,
      String dbspeakername,
      Integer dbdpstatus,
      Integer dbdpc,
      Integer dbdpc0,
      Integer dbdpc1) {
    if (dbid != null) this.dbid = dbid;
    if (dbname != null) this.dbname = dbname;
    if (dbstatus != null) this.dbstatus = dbstatus;
    if (dbmanager != null) this.dbmanager = dbmanager;
    if (dbspeaker != null) this.dbspeaker = dbspeaker;
    if (dborig != null) this.dborig = dborig;
    if (dbcrtime != null) this.dbcrtime = dbcrtime;
    if (dbmanagername != null) this.dbmanagername = dbmanagername;
    if (dbspeakername != null) this.dbspeakername = dbspeakername;
    if (dbdpstatus != null) this.dbdpstatus = dbdpstatus;
    if (dbdpc != null) this.dbdpc = dbdpc;
    if (dbdpc0 != null) this.dbdpc0 = dbdpc0;
    if (dbdpc1 != null) this.dbdpc1 = dbdpc1;
  }

  public Database() {
    this(null, null, null, null, null, null, null, null, null, null, null, null, null);
  }

  public Database clone() {
    return new Database(
        dbid,
        dbname,
        dbstatus,
        dbmanager,
        dbspeaker,
        dborig,
        dbcrtime,
        dbmanagername,
        dbspeakername,
        dbdpstatus,
        dbdpc,
        dbdpc0,
        dbdpc1);
  }
}