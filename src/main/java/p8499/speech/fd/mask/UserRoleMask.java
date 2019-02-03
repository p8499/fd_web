package p8499.speech.fd.mask;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import p8499.speech.fd.Mask;

@JsonSerialize(using = UserRoleMask.Serializer.class)
public class UserRoleMask implements Mask<UserRoleMask> {
  protected boolean urid = false;

  public boolean getUrid() {
    return urid;
  }

  public UserRoleMask setUrid(boolean urid) {
    this.urid = urid;
    return this;
  }

  protected boolean urusid = false;

  public boolean getUrusid() {
    return urusid;
  }

  public UserRoleMask setUrusid(boolean urusid) {
    this.urusid = urusid;
    return this;
  }

  protected boolean urrlid = false;

  public boolean getUrrlid() {
    return urrlid;
  }

  public UserRoleMask setUrrlid(boolean urrlid) {
    this.urrlid = urrlid;
    return this;
  }

  protected boolean urrlname = false;

  public boolean getUrrlname() {
    return urrlname;
  }

  public UserRoleMask setUrrlname(boolean urrlname) {
    this.urrlname = urrlname;
    return this;
  }

  public UserRoleMask(boolean urid, boolean urusid, boolean urrlid, boolean urrlname) {
    this.urid = urid;
    this.urusid = urusid;
    this.urrlid = urrlid;
    this.urrlname = urrlname;
  }

  public UserRoleMask() {}

  @Override
  public UserRoleMask all(boolean b) {
    this.urid = b;
    this.urusid = b;
    this.urrlid = b;
    this.urrlname = b;
    return this;
  }

  @Override
  public UserRoleMask keys(boolean b) {
    this.urid = b;
    return this;
  }

  @Override
  public UserRoleMask attributes(boolean b) {
    this.urusid = b;
    this.urrlid = b;
    return this;
  }

  @Override
  public UserRoleMask physicals(boolean b) {
    this.urid = b;
    this.urusid = b;
    this.urrlid = b;
    return this;
  }

  @Override
  public UserRoleMask virtuals(boolean b) {
    this.urrlname = b;
    return this;
  }

  @Override
  public boolean get(String p) {
    if (p.equals("urid")) return urid;
    else if (p.equals("urusid")) return urusid;
    else if (p.equals("urrlid")) return urrlid;
    else if (p.equals("urrlname")) return urrlname;
    return false;
  }

  @Override
  public UserRoleMask set(String p, boolean b) {
    if (p.equals("urid")) this.urid = b;
    else if (p.equals("urusid")) this.urusid = b;
    else if (p.equals("urrlid")) this.urrlid = b;
    else if (p.equals("urrlname")) this.urrlname = b;
    return this;
  }

  public static class Serializer extends JsonSerializer<UserRoleMask> {
    @Override
    public void serialize(UserRoleMask value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException, JsonProcessingException {
      gen.writeStartObject();
      if (value.getUrid()) {
        gen.writeFieldName("urid");
        gen.writeBoolean(value.getUrid());
      }
      if (value.getUrusid()) {
        gen.writeFieldName("urusid");
        gen.writeBoolean(value.getUrusid());
      }
      if (value.getUrrlid()) {
        gen.writeFieldName("urrlid");
        gen.writeBoolean(value.getUrrlid());
      }
      if (value.getUrrlname()) {
        gen.writeFieldName("urrlname");
        gen.writeBoolean(value.getUrrlname());
      }
      gen.writeEndObject();
    }
  }
}