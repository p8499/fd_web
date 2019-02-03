package p8499.speech.fd.mask;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import p8499.speech.fd.Mask;

@JsonSerialize(using = DatabaseMask.Serializer.class)
public class DatabaseMask implements Mask<DatabaseMask> {
  protected boolean dbid = false;

  public boolean getDbid() {
    return dbid;
  }

  public DatabaseMask setDbid(boolean dbid) {
    this.dbid = dbid;
    return this;
  }

  protected boolean dbname = false;

  public boolean getDbname() {
    return dbname;
  }

  public DatabaseMask setDbname(boolean dbname) {
    this.dbname = dbname;
    return this;
  }

  protected boolean dbstatus = false;

  public boolean getDbstatus() {
    return dbstatus;
  }

  public DatabaseMask setDbstatus(boolean dbstatus) {
    this.dbstatus = dbstatus;
    return this;
  }

  protected boolean dbmanager = false;

  public boolean getDbmanager() {
    return dbmanager;
  }

  public DatabaseMask setDbmanager(boolean dbmanager) {
    this.dbmanager = dbmanager;
    return this;
  }

  protected boolean dbspeaker = false;

  public boolean getDbspeaker() {
    return dbspeaker;
  }

  public DatabaseMask setDbspeaker(boolean dbspeaker) {
    this.dbspeaker = dbspeaker;
    return this;
  }

  protected boolean dborig = false;

  public boolean getDborig() {
    return dborig;
  }

  public DatabaseMask setDborig(boolean dborig) {
    this.dborig = dborig;
    return this;
  }

  protected boolean dbcrtime = false;

  public boolean getDbcrtime() {
    return dbcrtime;
  }

  public DatabaseMask setDbcrtime(boolean dbcrtime) {
    this.dbcrtime = dbcrtime;
    return this;
  }

  protected boolean dbmanagername = false;

  public boolean getDbmanagername() {
    return dbmanagername;
  }

  public DatabaseMask setDbmanagername(boolean dbmanagername) {
    this.dbmanagername = dbmanagername;
    return this;
  }

  protected boolean dbspeakername = false;

  public boolean getDbspeakername() {
    return dbspeakername;
  }

  public DatabaseMask setDbspeakername(boolean dbspeakername) {
    this.dbspeakername = dbspeakername;
    return this;
  }

  protected boolean dbdpstatus = false;

  public boolean getDbdpstatus() {
    return dbdpstatus;
  }

  public DatabaseMask setDbdpstatus(boolean dbdpstatus) {
    this.dbdpstatus = dbdpstatus;
    return this;
  }

  protected boolean dbdpc = false;

  public boolean getDbdpc() {
    return dbdpc;
  }

  public DatabaseMask setDbdpc(boolean dbdpc) {
    this.dbdpc = dbdpc;
    return this;
  }

  protected boolean dbdpc0 = false;

  public boolean getDbdpc0() {
    return dbdpc0;
  }

  public DatabaseMask setDbdpc0(boolean dbdpc0) {
    this.dbdpc0 = dbdpc0;
    return this;
  }

  protected boolean dbdpc1 = false;

  public boolean getDbdpc1() {
    return dbdpc1;
  }

  public DatabaseMask setDbdpc1(boolean dbdpc1) {
    this.dbdpc1 = dbdpc1;
    return this;
  }

  public DatabaseMask(
      boolean dbid,
      boolean dbname,
      boolean dbstatus,
      boolean dbmanager,
      boolean dbspeaker,
      boolean dborig,
      boolean dbcrtime,
      boolean dbmanagername,
      boolean dbspeakername,
      boolean dbdpstatus,
      boolean dbdpc,
      boolean dbdpc0,
      boolean dbdpc1) {
    this.dbid = dbid;
    this.dbname = dbname;
    this.dbstatus = dbstatus;
    this.dbmanager = dbmanager;
    this.dbspeaker = dbspeaker;
    this.dborig = dborig;
    this.dbcrtime = dbcrtime;
    this.dbmanagername = dbmanagername;
    this.dbspeakername = dbspeakername;
    this.dbdpstatus = dbdpstatus;
    this.dbdpc = dbdpc;
    this.dbdpc0 = dbdpc0;
    this.dbdpc1 = dbdpc1;
  }

  public DatabaseMask() {}

  @Override
  public DatabaseMask all(boolean b) {
    this.dbid = b;
    this.dbname = b;
    this.dbstatus = b;
    this.dbmanager = b;
    this.dbspeaker = b;
    this.dborig = b;
    this.dbcrtime = b;
    this.dbmanagername = b;
    this.dbspeakername = b;
    this.dbdpstatus = b;
    this.dbdpc = b;
    this.dbdpc0 = b;
    this.dbdpc1 = b;
    return this;
  }

  @Override
  public DatabaseMask keys(boolean b) {
    this.dbid = b;
    return this;
  }

  @Override
  public DatabaseMask attributes(boolean b) {
    this.dbname = b;
    this.dbstatus = b;
    this.dbmanager = b;
    this.dbspeaker = b;
    this.dborig = b;
    this.dbcrtime = b;
    return this;
  }

  @Override
  public DatabaseMask physicals(boolean b) {
    this.dbid = b;
    this.dbname = b;
    this.dbstatus = b;
    this.dbmanager = b;
    this.dbspeaker = b;
    this.dborig = b;
    this.dbcrtime = b;
    return this;
  }

  @Override
  public DatabaseMask virtuals(boolean b) {
    this.dbmanagername = b;
    this.dbspeakername = b;
    this.dbdpstatus = b;
    this.dbdpc = b;
    this.dbdpc0 = b;
    this.dbdpc1 = b;
    return this;
  }

  @Override
  public boolean get(String p) {
    if (p.equals("dbid")) return dbid;
    else if (p.equals("dbname")) return dbname;
    else if (p.equals("dbstatus")) return dbstatus;
    else if (p.equals("dbmanager")) return dbmanager;
    else if (p.equals("dbspeaker")) return dbspeaker;
    else if (p.equals("dborig")) return dborig;
    else if (p.equals("dbcrtime")) return dbcrtime;
    else if (p.equals("dbmanagername")) return dbmanagername;
    else if (p.equals("dbspeakername")) return dbspeakername;
    else if (p.equals("dbdpstatus")) return dbdpstatus;
    else if (p.equals("dbdpc")) return dbdpc;
    else if (p.equals("dbdpc0")) return dbdpc0;
    else if (p.equals("dbdpc1")) return dbdpc1;
    return false;
  }

  @Override
  public DatabaseMask set(String p, boolean b) {
    if (p.equals("dbid")) this.dbid = b;
    else if (p.equals("dbname")) this.dbname = b;
    else if (p.equals("dbstatus")) this.dbstatus = b;
    else if (p.equals("dbmanager")) this.dbmanager = b;
    else if (p.equals("dbspeaker")) this.dbspeaker = b;
    else if (p.equals("dborig")) this.dborig = b;
    else if (p.equals("dbcrtime")) this.dbcrtime = b;
    else if (p.equals("dbmanagername")) this.dbmanagername = b;
    else if (p.equals("dbspeakername")) this.dbspeakername = b;
    else if (p.equals("dbdpstatus")) this.dbdpstatus = b;
    else if (p.equals("dbdpc")) this.dbdpc = b;
    else if (p.equals("dbdpc0")) this.dbdpc0 = b;
    else if (p.equals("dbdpc1")) this.dbdpc1 = b;
    return this;
  }

  public static class Serializer extends JsonSerializer<DatabaseMask> {
    @Override
    public void serialize(DatabaseMask value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException, JsonProcessingException {
      gen.writeStartObject();
      if (value.getDbid()) {
        gen.writeFieldName("dbid");
        gen.writeBoolean(value.getDbid());
      }
      if (value.getDbname()) {
        gen.writeFieldName("dbname");
        gen.writeBoolean(value.getDbname());
      }
      if (value.getDbstatus()) {
        gen.writeFieldName("dbstatus");
        gen.writeBoolean(value.getDbstatus());
      }
      if (value.getDbmanager()) {
        gen.writeFieldName("dbmanager");
        gen.writeBoolean(value.getDbmanager());
      }
      if (value.getDbspeaker()) {
        gen.writeFieldName("dbspeaker");
        gen.writeBoolean(value.getDbspeaker());
      }
      if (value.getDborig()) {
        gen.writeFieldName("dborig");
        gen.writeBoolean(value.getDborig());
      }
      if (value.getDbcrtime()) {
        gen.writeFieldName("dbcrtime");
        gen.writeBoolean(value.getDbcrtime());
      }
      if (value.getDbmanagername()) {
        gen.writeFieldName("dbmanagername");
        gen.writeBoolean(value.getDbmanagername());
      }
      if (value.getDbspeakername()) {
        gen.writeFieldName("dbspeakername");
        gen.writeBoolean(value.getDbspeakername());
      }
      if (value.getDbdpstatus()) {
        gen.writeFieldName("dbdpstatus");
        gen.writeBoolean(value.getDbdpstatus());
      }
      if (value.getDbdpc()) {
        gen.writeFieldName("dbdpc");
        gen.writeBoolean(value.getDbdpc());
      }
      if (value.getDbdpc0()) {
        gen.writeFieldName("dbdpc0");
        gen.writeBoolean(value.getDbdpc0());
      }
      if (value.getDbdpc1()) {
        gen.writeFieldName("dbdpc1");
        gen.writeBoolean(value.getDbdpc1());
      }
      gen.writeEndObject();
    }
  }
}