package p8499.speech.fd.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Authority {
  public static final String TABLE = "F0320";
  public static final String VIEW = "F0320View";
  public static final String NAME = "AUTHORITY";

  //region auid Authority ID
  public static final String FIELD_AUID = "AUID";
  public static final String AUID_DATABASE = "database";
  protected String auid = null;
  public static final int CONSTRAINT_AUID_LENGTH_STRING = 32;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_AUID_LENGTH_STRING)
  public String getAuid() {
    return auid;
  }

  public Authority setAuid(String auid) {
    this.auid = auid;
    return this;
  }
  //endregion

  //region auname Authority Name
  public static final String FIELD_AUNAME = "AUNAME";
  protected String auname = null;
  public static final int CONSTRAINT_AUNAME_LENGTH_STRING = 64;

  @javax.validation.constraints.Size(max = CONSTRAINT_AUNAME_LENGTH_STRING)
  public String getAuname() {
    return auname;
  }

  public Authority setAuname(String auname) {
    this.auname = auname;
    return this;
  }
  //endregion

  public Authority(String auid, String auname) {
    if (auid != null) this.auid = auid;
    if (auname != null) this.auname = auname;
  }

  public Authority() {
    this(null, null);
  }

  public Authority clone() {
    return new Authority(auid, auname);
  }
}