package p8499.speech.fd.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role {
  public static final String TABLE = "F0310";
  public static final String VIEW = "F0310View";
  public static final String NAME = "ROLE";

  //region rlid Role ID
  public static final String FIELD_RLID = "RLID";
  protected String rlid = null;
  public static final int CONSTRAINT_RLID_LENGTH_STRING = 32;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_RLID_LENGTH_STRING)
  public String getRlid() {
    return rlid;
  }

  public Role setRlid(String rlid) {
    this.rlid = rlid;
    return this;
  }
  //endregion

  //region rlname Role Name
  public static final String FIELD_RLNAME = "RLNAME";
  protected String rlname = null;
  public static final int CONSTRAINT_RLNAME_LENGTH_STRING = 64;

  @javax.validation.constraints.Size(max = CONSTRAINT_RLNAME_LENGTH_STRING)
  public String getRlname() {
    return rlname;
  }

  public Role setRlname(String rlname) {
    this.rlname = rlname;
    return this;
  }
  //endregion

  public Role(String rlid, String rlname) {
    if (rlid != null) this.rlid = rlid;
    if (rlname != null) this.rlname = rlname;
  }

  public Role() {
    this(null, null);
  }

  public Role clone() {
    return new Role(rlid, rlname);
  }
}