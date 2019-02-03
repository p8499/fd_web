package p8499.speech.fd.mask;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import p8499.speech.fd.Mask;

@JsonSerialize(using = UserMask.Serializer.class)
public class UserMask implements Mask<UserMask> {
  protected boolean usid = false;

  public boolean getUsid() {
    return usid;
  }

  public UserMask setUsid(boolean usid) {
    this.usid = usid;
    return this;
  }

  protected boolean usalias = false;

  public boolean getUsalias() {
    return usalias;
  }

  public UserMask setUsalias(boolean usalias) {
    this.usalias = usalias;
    return this;
  }

  protected boolean usfrom = false;

  public boolean getUsfrom() {
    return usfrom;
  }

  public UserMask setUsfrom(boolean usfrom) {
    this.usfrom = usfrom;
    return this;
  }

  protected boolean uspswd = false;

  public boolean getUspswd() {
    return uspswd;
  }

  public UserMask setUspswd(boolean uspswd) {
    this.uspswd = uspswd;
    return this;
  }

  protected boolean usname = false;

  public boolean getUsname() {
    return usname;
  }

  public UserMask setUsname(boolean usname) {
    this.usname = usname;
    return this;
  }

  protected boolean usstatus = false;

  public boolean getUsstatus() {
    return usstatus;
  }

  public UserMask setUsstatus(boolean usstatus) {
    this.usstatus = usstatus;
    return this;
  }

  public UserMask(
      boolean usid,
      boolean usalias,
      boolean usfrom,
      boolean uspswd,
      boolean usname,
      boolean usstatus) {
    this.usid = usid;
    this.usalias = usalias;
    this.usfrom = usfrom;
    this.uspswd = uspswd;
    this.usname = usname;
    this.usstatus = usstatus;
  }

  public UserMask() {}

  @Override
  public UserMask all(boolean b) {
    this.usid = b;
    this.usalias = b;
    this.usfrom = b;
    this.uspswd = b;
    this.usname = b;
    this.usstatus = b;
    return this;
  }

  @Override
  public UserMask keys(boolean b) {
    this.usid = b;
    return this;
  }

  @Override
  public UserMask attributes(boolean b) {
    this.usalias = b;
    this.usfrom = b;
    this.uspswd = b;
    this.usname = b;
    this.usstatus = b;
    return this;
  }

  @Override
  public UserMask physicals(boolean b) {
    this.usid = b;
    this.usalias = b;
    this.usfrom = b;
    this.uspswd = b;
    this.usname = b;
    this.usstatus = b;
    return this;
  }

  @Override
  public UserMask virtuals(boolean b) {
    return this;
  }

  @Override
  public boolean get(String p) {
    if (p.equals("usid")) return usid;
    else if (p.equals("usalias")) return usalias;
    else if (p.equals("usfrom")) return usfrom;
    else if (p.equals("uspswd")) return uspswd;
    else if (p.equals("usname")) return usname;
    else if (p.equals("usstatus")) return usstatus;
    return false;
  }

  @Override
  public UserMask set(String p, boolean b) {
    if (p.equals("usid")) this.usid = b;
    else if (p.equals("usalias")) this.usalias = b;
    else if (p.equals("usfrom")) this.usfrom = b;
    else if (p.equals("uspswd")) this.uspswd = b;
    else if (p.equals("usname")) this.usname = b;
    else if (p.equals("usstatus")) this.usstatus = b;
    return this;
  }

  public static class Serializer extends JsonSerializer<UserMask> {
    @Override
    public void serialize(UserMask value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException, JsonProcessingException {
      gen.writeStartObject();
      if (value.getUsid()) {
        gen.writeFieldName("usid");
        gen.writeBoolean(value.getUsid());
      }
      if (value.getUsalias()) {
        gen.writeFieldName("usalias");
        gen.writeBoolean(value.getUsalias());
      }
      if (value.getUsfrom()) {
        gen.writeFieldName("usfrom");
        gen.writeBoolean(value.getUsfrom());
      }
      if (value.getUspswd()) {
        gen.writeFieldName("uspswd");
        gen.writeBoolean(value.getUspswd());
      }
      if (value.getUsname()) {
        gen.writeFieldName("usname");
        gen.writeBoolean(value.getUsname());
      }
      if (value.getUsstatus()) {
        gen.writeFieldName("usstatus");
        gen.writeBoolean(value.getUsstatus());
      }
      gen.writeEndObject();
    }
  }
}