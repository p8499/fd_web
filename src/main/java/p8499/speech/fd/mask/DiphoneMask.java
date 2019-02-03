package p8499.speech.fd.mask;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import p8499.speech.fd.Mask;

@JsonSerialize(using = DiphoneMask.Serializer.class)
public class DiphoneMask implements Mask<DiphoneMask> {
  protected boolean dpid = false;

  public boolean getDpid() {
    return dpid;
  }

  public DiphoneMask setDpid(boolean dpid) {
    this.dpid = dpid;
    return this;
  }

  protected boolean dpdbid = false;

  public boolean getDpdbid() {
    return dpdbid;
  }

  public DiphoneMask setDpdbid(boolean dpdbid) {
    this.dpdbid = dpdbid;
    return this;
  }

  protected boolean dplnid = false;

  public boolean getDplnid() {
    return dplnid;
  }

  public DiphoneMask setDplnid(boolean dplnid) {
    this.dplnid = dplnid;
    return this;
  }

  protected boolean dpstatus = false;

  public boolean getDpstatus() {
    return dpstatus;
  }

  public DiphoneMask setDpstatus(boolean dpstatus) {
    this.dpstatus = dpstatus;
    return this;
  }

  protected boolean dpspeaker = false;

  public boolean getDpspeaker() {
    return dpspeaker;
  }

  public DiphoneMask setDpspeaker(boolean dpspeaker) {
    this.dpspeaker = dpspeaker;
    return this;
  }

  protected boolean dprctime = false;

  public boolean getDprctime() {
    return dprctime;
  }

  public DiphoneMask setDprctime(boolean dprctime) {
    this.dprctime = dprctime;
    return this;
  }

  protected boolean dpspeakername = false;

  public boolean getDpspeakername() {
    return dpspeakername;
  }

  public DiphoneMask setDpspeakername(boolean dpspeakername) {
    this.dpspeakername = dpspeakername;
    return this;
  }

  public DiphoneMask(
      boolean dpid,
      boolean dpdbid,
      boolean dplnid,
      boolean dpstatus,
      boolean dpspeaker,
      boolean dprctime,
      boolean dpspeakername) {
    this.dpid = dpid;
    this.dpdbid = dpdbid;
    this.dplnid = dplnid;
    this.dpstatus = dpstatus;
    this.dpspeaker = dpspeaker;
    this.dprctime = dprctime;
    this.dpspeakername = dpspeakername;
  }

  public DiphoneMask() {}

  @Override
  public DiphoneMask all(boolean b) {
    this.dpid = b;
    this.dpdbid = b;
    this.dplnid = b;
    this.dpstatus = b;
    this.dpspeaker = b;
    this.dprctime = b;
    this.dpspeakername = b;
    return this;
  }

  @Override
  public DiphoneMask keys(boolean b) {
    this.dpid = b;
    return this;
  }

  @Override
  public DiphoneMask attributes(boolean b) {
    this.dpdbid = b;
    this.dplnid = b;
    this.dpstatus = b;
    this.dpspeaker = b;
    this.dprctime = b;
    return this;
  }

  @Override
  public DiphoneMask physicals(boolean b) {
    this.dpid = b;
    this.dpdbid = b;
    this.dplnid = b;
    this.dpstatus = b;
    this.dpspeaker = b;
    this.dprctime = b;
    return this;
  }

  @Override
  public DiphoneMask virtuals(boolean b) {
    this.dpspeakername = b;
    return this;
  }

  @Override
  public boolean get(String p) {
    if (p.equals("dpid")) return dpid;
    else if (p.equals("dpdbid")) return dpdbid;
    else if (p.equals("dplnid")) return dplnid;
    else if (p.equals("dpstatus")) return dpstatus;
    else if (p.equals("dpspeaker")) return dpspeaker;
    else if (p.equals("dprctime")) return dprctime;
    else if (p.equals("dpspeakername")) return dpspeakername;
    return false;
  }

  @Override
  public DiphoneMask set(String p, boolean b) {
    if (p.equals("dpid")) this.dpid = b;
    else if (p.equals("dpdbid")) this.dpdbid = b;
    else if (p.equals("dplnid")) this.dplnid = b;
    else if (p.equals("dpstatus")) this.dpstatus = b;
    else if (p.equals("dpspeaker")) this.dpspeaker = b;
    else if (p.equals("dprctime")) this.dprctime = b;
    else if (p.equals("dpspeakername")) this.dpspeakername = b;
    return this;
  }

  public static class Serializer extends JsonSerializer<DiphoneMask> {
    @Override
    public void serialize(DiphoneMask value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException, JsonProcessingException {
      gen.writeStartObject();
      if (value.getDpid()) {
        gen.writeFieldName("dpid");
        gen.writeBoolean(value.getDpid());
      }
      if (value.getDpdbid()) {
        gen.writeFieldName("dpdbid");
        gen.writeBoolean(value.getDpdbid());
      }
      if (value.getDplnid()) {
        gen.writeFieldName("dplnid");
        gen.writeBoolean(value.getDplnid());
      }
      if (value.getDpstatus()) {
        gen.writeFieldName("dpstatus");
        gen.writeBoolean(value.getDpstatus());
      }
      if (value.getDpspeaker()) {
        gen.writeFieldName("dpspeaker");
        gen.writeBoolean(value.getDpspeaker());
      }
      if (value.getDprctime()) {
        gen.writeFieldName("dprctime");
        gen.writeBoolean(value.getDprctime());
      }
      if (value.getDpspeakername()) {
        gen.writeFieldName("dpspeakername");
        gen.writeBoolean(value.getDpspeakername());
      }
      gen.writeEndObject();
    }
  }
}