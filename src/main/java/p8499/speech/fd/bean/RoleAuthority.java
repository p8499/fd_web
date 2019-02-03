package p8499.speech.fd.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleAuthority {
  public static final String TABLE = "F0321";
  public static final String VIEW = "F0321View";
  public static final String NAME = "ROLEAUTHORITY";

  //region raid Role Authority ID
  public static final String FIELD_RAID = "RAID";
  protected Integer raid = null;
  public static final int CONSTRAINT_RAID_LENGTH_INTEGER = 6;
  public static final int CONSTRAINT_RAID_MIN = -999999;
  public static final int CONSTRAINT_RAID_MAX = 999999;

  @javax.validation.constraints.Null(groups = {Insert.class})
  @javax.validation.constraints.NotNull(groups = {Update.class})
  public Integer getRaid() {
    return raid;
  }

  public RoleAuthority setRaid(Integer raid) {
    this.raid = raid;
    return this;
  }
  //endregion

  //region rarlid Role ID
  public static final String FIELD_RARLID = "RARLID";
  protected String rarlid = null;
  public static final int CONSTRAINT_RARLID_LENGTH_STRING = 32;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_RARLID_LENGTH_STRING)
  public String getRarlid() {
    return rarlid;
  }

  public RoleAuthority setRarlid(String rarlid) {
    this.rarlid = rarlid;
    return this;
  }
  //endregion

  //region raauid Authority ID
  public static final String FIELD_RAAUID = "RAAUID";
  protected String raauid = null;
  public static final int CONSTRAINT_RAAUID_LENGTH_STRING = 32;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_RAAUID_LENGTH_STRING)
  public String getRaauid() {
    return raauid;
  }

  public RoleAuthority setRaauid(String raauid) {
    this.raauid = raauid;
    return this;
  }
  //endregion

  //region raauname Authority Name
  public static final String FIELD_RAAUNAME = "RAAUNAME";
  protected String raauname = null;

  @javax.validation.constraints.Null(groups = {Insert.class, Update.class})
  public String getRaauname() {
    return raauname;
  }

  public RoleAuthority setRaauname(String raauname) {
    this.raauname = raauname;
    return this;
  }
  //endregion

  public RoleAuthority(Integer raid, String rarlid, String raauid, String raauname) {
    if (raid != null) this.raid = raid;
    if (rarlid != null) this.rarlid = rarlid;
    if (raauid != null) this.raauid = raauid;
    if (raauname != null) this.raauname = raauname;
  }

  public RoleAuthority() {
    this(null, null, null, null);
  }

  public RoleAuthority clone() {
    return new RoleAuthority(raid, rarlid, raauid, raauname);
  }
}