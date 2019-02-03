package p8499.speech.fd.mask;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import p8499.speech.fd.Mask;

@JsonSerialize(using = RoleAuthorityMask.Serializer.class)
public class RoleAuthorityMask implements Mask<RoleAuthorityMask> {
  protected boolean raid = false;

  public boolean getRaid() {
    return raid;
  }

  public RoleAuthorityMask setRaid(boolean raid) {
    this.raid = raid;
    return this;
  }

  protected boolean rarlid = false;

  public boolean getRarlid() {
    return rarlid;
  }

  public RoleAuthorityMask setRarlid(boolean rarlid) {
    this.rarlid = rarlid;
    return this;
  }

  protected boolean raauid = false;

  public boolean getRaauid() {
    return raauid;
  }

  public RoleAuthorityMask setRaauid(boolean raauid) {
    this.raauid = raauid;
    return this;
  }

  protected boolean raauname = false;

  public boolean getRaauname() {
    return raauname;
  }

  public RoleAuthorityMask setRaauname(boolean raauname) {
    this.raauname = raauname;
    return this;
  }

  public RoleAuthorityMask(boolean raid, boolean rarlid, boolean raauid, boolean raauname) {
    this.raid = raid;
    this.rarlid = rarlid;
    this.raauid = raauid;
    this.raauname = raauname;
  }

  public RoleAuthorityMask() {}

  @Override
  public RoleAuthorityMask all(boolean b) {
    this.raid = b;
    this.rarlid = b;
    this.raauid = b;
    this.raauname = b;
    return this;
  }

  @Override
  public RoleAuthorityMask keys(boolean b) {
    this.raid = b;
    return this;
  }

  @Override
  public RoleAuthorityMask attributes(boolean b) {
    this.rarlid = b;
    this.raauid = b;
    return this;
  }

  @Override
  public RoleAuthorityMask physicals(boolean b) {
    this.raid = b;
    this.rarlid = b;
    this.raauid = b;
    return this;
  }

  @Override
  public RoleAuthorityMask virtuals(boolean b) {
    this.raauname = b;
    return this;
  }

  @Override
  public boolean get(String p) {
    if (p.equals("raid")) return raid;
    else if (p.equals("rarlid")) return rarlid;
    else if (p.equals("raauid")) return raauid;
    else if (p.equals("raauname")) return raauname;
    return false;
  }

  @Override
  public RoleAuthorityMask set(String p, boolean b) {
    if (p.equals("raid")) this.raid = b;
    else if (p.equals("rarlid")) this.rarlid = b;
    else if (p.equals("raauid")) this.raauid = b;
    else if (p.equals("raauname")) this.raauname = b;
    return this;
  }

  public static class Serializer extends JsonSerializer<RoleAuthorityMask> {
    @Override
    public void serialize(
        RoleAuthorityMask value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException, JsonProcessingException {
      gen.writeStartObject();
      if (value.getRaid()) {
        gen.writeFieldName("raid");
        gen.writeBoolean(value.getRaid());
      }
      if (value.getRarlid()) {
        gen.writeFieldName("rarlid");
        gen.writeBoolean(value.getRarlid());
      }
      if (value.getRaauid()) {
        gen.writeFieldName("raauid");
        gen.writeBoolean(value.getRaauid());
      }
      if (value.getRaauname()) {
        gen.writeFieldName("raauname");
        gen.writeBoolean(value.getRaauname());
      }
      gen.writeEndObject();
    }
  }
}