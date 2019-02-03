package p8499.speech.fd.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Diphone {
  public static final String TABLE = "F4702";
  public static final String VIEW = "F4702View";
  public static final String NAME = "DIPHONE";

  //region dpid Diphone ID
  public static final String FIELD_DPID = "DPID";
  protected Integer dpid = null;
  public static final int CONSTRAINT_DPID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_DPID_MIN = -99999999;
  public static final int CONSTRAINT_DPID_MAX = 99999999;

  @javax.validation.constraints.Null(groups = {Insert.class})
  @javax.validation.constraints.NotNull(groups = {Update.class})
  public Integer getDpid() {
    return dpid;
  }

  public Diphone setDpid(Integer dpid) {
    this.dpid = dpid;
    return this;
  }
  //endregion

  //region dpdbid Database ID
  public static final String FIELD_DPDBID = "DPDBID";
  protected String dpdbid = null;
  public static final int CONSTRAINT_DPDBID_LENGTH_STRING = 10;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_DPDBID_LENGTH_STRING)
  public String getDpdbid() {
    return dpdbid;
  }

  public Diphone setDpdbid(String dpdbid) {
    this.dpdbid = dpdbid;
    return this;
  }
  //endregion

  //region dplnid Diphone Line
  public static final String FIELD_DPLNID = "DPLNID";
  protected Integer dplnid = null;
  public static final int CONSTRAINT_DPLNID_LENGTH_INTEGER = 6;
  public static final int CONSTRAINT_DPLNID_MIN = -999999;
  public static final int CONSTRAINT_DPLNID_MAX = 999999;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Min(value = CONSTRAINT_DPLNID_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_DPLNID_MAX)
  public Integer getDplnid() {
    return dplnid;
  }

  public Diphone setDplnid(Integer dplnid) {
    this.dplnid = dplnid;
    return this;
  }
  //endregion

  //region dpstatus Diphone Status
  public static final String FIELD_DPSTATUS = "DPSTATUS";
  public static final Integer DPSTATUS_INITIALIZED = 0;
  public static final Integer DPSTATUS_RECORDED = 1;
  public static final Integer DEFAULT_DPSTATUS = 0;
  protected Integer dpstatus = DEFAULT_DPSTATUS;
  public static final int CONSTRAINT_DPSTATUS_LENGTH_INTEGER = 1;
  public static final int CONSTRAINT_DPSTATUS_MIN = -9;
  public static final int CONSTRAINT_DPSTATUS_MAX = 9;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Min(value = CONSTRAINT_DPSTATUS_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_DPSTATUS_MAX)
  public Integer getDpstatus() {
    return dpstatus;
  }

  public Diphone setDpstatus(Integer dpstatus) {
    this.dpstatus = dpstatus;
    return this;
  }
  //endregion

  //region dpspeaker Diphone Speaker
  public static final String FIELD_DPSPEAKER = "DPSPEAKER";
  protected Integer dpspeaker = null;
  public static final int CONSTRAINT_DPSPEAKER_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_DPSPEAKER_MIN = -99999999;
  public static final int CONSTRAINT_DPSPEAKER_MAX = 99999999;

  @javax.validation.constraints.Min(value = CONSTRAINT_DPSPEAKER_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_DPSPEAKER_MAX)
  public Integer getDpspeaker() {
    return dpspeaker;
  }

  public Diphone setDpspeaker(Integer dpspeaker) {
    this.dpspeaker = dpspeaker;
    return this;
  }
  //endregion

  //region dprctime Diphone Record Time
  public static final String FIELD_DPRCTIME = "DPRCTIME";

  @com.fasterxml.jackson.annotation.JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
  protected java.util.Date dprctime = null;

  public java.util.Date getDprctime() {
    return dprctime;
  }

  public Diphone setDprctime(java.util.Date dprctime) {
    this.dprctime = dprctime;
    return this;
  }
  //endregion

  //region dpspeakername Diphone Speaker Name
  public static final String FIELD_DPSPEAKERNAME = "DPSPEAKERNAME";
  protected String dpspeakername = null;

  @javax.validation.constraints.Null(groups = {Insert.class, Update.class})
  public String getDpspeakername() {
    return dpspeakername;
  }

  public Diphone setDpspeakername(String dpspeakername) {
    this.dpspeakername = dpspeakername;
    return this;
  }
  //endregion

  public Diphone(
      Integer dpid,
      String dpdbid,
      Integer dplnid,
      Integer dpstatus,
      Integer dpspeaker,
      java.util.Date dprctime,
      String dpspeakername) {
    if (dpid != null) this.dpid = dpid;
    if (dpdbid != null) this.dpdbid = dpdbid;
    if (dplnid != null) this.dplnid = dplnid;
    if (dpstatus != null) this.dpstatus = dpstatus;
    if (dpspeaker != null) this.dpspeaker = dpspeaker;
    if (dprctime != null) this.dprctime = dprctime;
    if (dpspeakername != null) this.dpspeakername = dpspeakername;
  }

  public Diphone() {
    this(null, null, null, null, null, null, null);
  }

  public Diphone clone() {
    return new Diphone(dpid, dpdbid, dplnid, dpstatus, dpspeaker, dprctime, dpspeakername);
  }
}