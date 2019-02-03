package p8499.speech.fd.mask;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import p8499.speech.fd.Mask;

@JsonSerialize(using = AuthorityMask.Serializer.class)
public class AuthorityMask implements Mask<AuthorityMask> {
  protected boolean auid = false;

  public boolean getAuid() {
    return auid;
  }

  public AuthorityMask setAuid(boolean auid) {
    this.auid = auid;
    return this;
  }

  protected boolean auname = false;

  public boolean getAuname() {
    return auname;
  }

  public AuthorityMask setAuname(boolean auname) {
    this.auname = auname;
    return this;
  }

  public AuthorityMask(boolean auid, boolean auname) {
    this.auid = auid;
    this.auname = auname;
  }

  public AuthorityMask() {}

  @Override
  public AuthorityMask all(boolean b) {
    this.auid = b;
    this.auname = b;
    return this;
  }

  @Override
  public AuthorityMask keys(boolean b) {
    this.auid = b;
    return this;
  }

  @Override
  public AuthorityMask attributes(boolean b) {
    this.auname = b;
    return this;
  }

  @Override
  public AuthorityMask physicals(boolean b) {
    this.auid = b;
    this.auname = b;
    return this;
  }

  @Override
  public AuthorityMask virtuals(boolean b) {
    return this;
  }

  @Override
  public boolean get(String p) {
    if (p.equals("auid")) return auid;
    else if (p.equals("auname")) return auname;
    return false;
  }

  @Override
  public AuthorityMask set(String p, boolean b) {
    if (p.equals("auid")) this.auid = b;
    else if (p.equals("auname")) this.auname = b;
    return this;
  }

  public static class Serializer extends JsonSerializer<AuthorityMask> {
    @Override
    public void serialize(AuthorityMask value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException, JsonProcessingException {
      gen.writeStartObject();
      if (value.getAuid()) {
        gen.writeFieldName("auid");
        gen.writeBoolean(value.getAuid());
      }
      if (value.getAuname()) {
        gen.writeFieldName("auname");
        gen.writeBoolean(value.getAuname());
      }
      gen.writeEndObject();
    }
  }
}