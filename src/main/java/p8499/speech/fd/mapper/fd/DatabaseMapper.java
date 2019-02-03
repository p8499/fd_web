package p8499.speech.fd.mapper.fd;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import p8499.speech.fd.FilterExpr;
import p8499.speech.fd.OrderByListExpr;
import p8499.speech.fd.mask.DatabaseMask;
import p8499.speech.fd.bean.Database;

@Component("databaseMapper")
public interface DatabaseMapper {
  @Select("SELECT COUNT(*)>0 FROM F4701View WHERE DBID=#{dbid}")
  boolean exists(@Param("dbid") String dbid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.dbid or mask.dbname or mask.dbstatus or mask.dbmanager or mask.dbspeaker or mask.dborig or mask.dbcrtime or mask.dbmanagername or mask.dbspeakername or mask.dbdpstatus or mask.dbdpc'><trim prefix='SELECT' suffixOverrides=','><if test='mask.dbid'>DBID, </if><if test='mask.dbname'>DBNAME, </if><if test='mask.dbstatus'>DBSTATUS, </if><if test='mask.dbmanager'>DBMANAGER, </if><if test='mask.dbspeaker'>DBSPEAKER, </if><if test='mask.dborig'>DBORIG, </if><if test='mask.dbcrtime'>DBCRTIME, </if><if test='mask.dbmanagername'>DBMANAGERNAME, </if><if test='mask.dbspeakername'>DBSPEAKERNAME, </if><if test='mask.dbdpstatus'>DBDPSTATUS, </if><if test='mask.dbdpc'>DBDPC, </if></trim>FROM F4701View WHERE DBID=#{dbid}</if></when><otherwise>SELECTDBID,DBNAME,DBSTATUS,DBMANAGER,DBSPEAKER,DBORIG,DBCRTIME,DBMANAGERNAME,DBSPEAKERNAME,DBDPSTATUS,DBDPC FROM F4701View WHERE DBID=#{dbid}</otherwise></choose></script>")
  Database get(@Param("dbid") String dbid, @Param("mask") DatabaseMask mask);

  @Insert(
      "INSERT INTO F4701 (DBID,DBNAME,DBSTATUS,DBMANAGER,DBSPEAKER,DBORIG,DBCRTIME) VALUES (#{bean.dbid,jdbcType=VARCHAR},#{bean.dbname,jdbcType=VARCHAR},#{bean.dbstatus,jdbcType=SMALLINT},#{bean.dbmanager,jdbcType=INTEGER},#{bean.dbspeaker,jdbcType=INTEGER},#{bean.dborig,jdbcType=INTEGER},#{bean.dbcrtime,jdbcType=TIMESTAMP})")
  void add(@Param("bean") Database bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.dbname or mask.dbstatus or mask.dbmanager or mask.dbspeaker or mask.dborig or mask.dbcrtime'>UPDATE F4701 <set><if test='mask.dbname'>DBNAME=#{bean.dbname,jdbcType=VARCHAR}, </if><if test='mask.dbstatus'>DBSTATUS=#{bean.dbstatus,jdbcType=SMALLINT}, </if><if test='mask.dbmanager'>DBMANAGER=#{bean.dbmanager,jdbcType=INTEGER}, </if><if test='mask.dbspeaker'>DBSPEAKER=#{bean.dbspeaker,jdbcType=INTEGER}, </if><if test='mask.dborig'>DBORIG=#{bean.dborig,jdbcType=INTEGER}, </if><if test='mask.dbcrtime'>DBCRTIME=#{bean.dbcrtime,jdbcType=TIMESTAMP}, </if></set>WHERE DBID=#{bean.dbid}</if></when><otherwise>UPDATE F4701 SET DBNAME=#{bean.dbname,jdbcType=VARCHAR}, DBSTATUS=#{bean.dbstatus,jdbcType=SMALLINT}, DBMANAGER=#{bean.dbmanager,jdbcType=INTEGER}, DBSPEAKER=#{bean.dbspeaker,jdbcType=INTEGER}, DBORIG=#{bean.dborig,jdbcType=INTEGER}, DBCRTIME=#{bean.dbcrtime,jdbcType=TIMESTAMP} WHERE DBID=#{bean.dbid}</otherwise></choose></script>")
  void update(@Param("bean") Database bean, @Param("mask") DatabaseMask mask);

  @Delete("DELETE FROM F4701 WHERE DBID=#{dbid}")
  boolean delete(String dbid);

  @Delete(
      "<script>DELETE FROM F4701<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.dbid'>dbid,</if><if test='mask.dbname'>dbname,</if><if test='mask.dbstatus'>dbstatus,</if><if test='mask.dbmanager'>dbmanager,</if><if test='mask.dbspeaker'>dbspeaker,</if><if test='mask.dborig'>dborig,</if><if test='mask.dbcrtime'>dbcrtime,</if><if test='mask.dbmanagername'>dbmanagername,</if><if test='mask.dbspeakername'>dbspeakername,</if><if test='mask.dbdpstatus'>dbdpstatus,</if><if test='mask.dbdpc'>dbdpc,</if></trim></when><otherwise>SELECT dbid, dbname, dbstatus, dbmanager, dbspeaker, dborig, dbcrtime, dbmanagername, dbspeakername, dbdpstatus, dbdpc</otherwise></choose> FROM F4701View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<Database> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") DatabaseMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM F4701View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT DECODE(MIN(DBSTATUS),NULL,${defaultValue}) FROM F4701View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minDbstatus(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(DBSTATUS),NULL,${defaultValue}) FROM F4701View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxDbstatus(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(DBMANAGER),NULL,${defaultValue}) FROM F4701View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minDbmanager(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(DBMANAGER),NULL,${defaultValue}) FROM F4701View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxDbmanager(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(DBSPEAKER),NULL,${defaultValue}) FROM F4701View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minDbspeaker(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(DBSPEAKER),NULL,${defaultValue}) FROM F4701View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxDbspeaker(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(DBORIG),NULL,${defaultValue}) FROM F4701View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minDborig(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(DBORIG),NULL,${defaultValue}) FROM F4701View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxDborig(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(DBCRTIME),NULL,${defaultValue}) FROM F4701View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  java.util.Date minDbcrtime(
      @Param("filter") FilterExpr filter, @Param("defaultValue") java.util.Date defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(DBCRTIME),NULL,${defaultValue}) FROM F4701View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  java.util.Date maxDbcrtime(
      @Param("filter") FilterExpr filter, @Param("defaultValue") java.util.Date defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(DBDPSTATUS),NULL,${defaultValue}) FROM F4701View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minDbdpstatus(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(DBDPSTATUS),NULL,${defaultValue}) FROM F4701View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxDbdpstatus(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(DBDPC),NULL,${defaultValue}) FROM F4701View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minDbdpc(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(DBDPC),NULL,${defaultValue}) FROM F4701View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxDbdpc(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);
}