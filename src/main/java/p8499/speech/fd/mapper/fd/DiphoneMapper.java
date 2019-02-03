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
import p8499.speech.fd.mask.DiphoneMask;
import p8499.speech.fd.bean.Diphone;

@Component("diphoneMapper")
public interface DiphoneMapper {
  @Select("SELECT COUNT(*)>0 FROM F4702View WHERE DPID=#{dpid}")
  boolean exists(@Param("dpid") Integer dpid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.dpid or mask.dpdbid or mask.dplnid or mask.dpstatus or mask.dpspeaker or mask.dprctime or mask.dpspeakername'><trim prefix='SELECT' suffixOverrides=','><if test='mask.dpid'>DPID, </if><if test='mask.dpdbid'>DPDBID, </if><if test='mask.dplnid'>DPLNID, </if><if test='mask.dpstatus'>DPSTATUS, </if><if test='mask.dpspeaker'>DPSPEAKER, </if><if test='mask.dprctime'>DPRCTIME, </if><if test='mask.dpspeakername'>DPSPEAKERNAME, </if></trim>FROM F4702View WHERE DPID=#{dpid}</if></when><otherwise>SELECTDPID,DPDBID,DPLNID,DPSTATUS,DPSPEAKER,DPRCTIME,DPSPEAKERNAME FROM F4702View WHERE DPID=#{dpid}</otherwise></choose></script>")
  Diphone get(@Param("dpid") Integer dpid, @Param("mask") DiphoneMask mask);

  @org.apache.ibatis.annotations.SelectKey(
    statement = "SELECT F4702_DPID.nextval AS dpid FROM DUAL",
    before = true,
    resultType = Integer.class,
    keyColumn = "dpid",
    keyProperty = "bean.dpid"
  )
  @Insert(
      "INSERT INTO F4702 (DPID,DPDBID,DPLNID,DPSTATUS,DPSPEAKER,DPRCTIME) VALUES (#{bean.dpid,jdbcType=INTEGER},#{bean.dpdbid,jdbcType=VARCHAR},#{bean.dplnid,jdbcType=INTEGER},#{bean.dpstatus,jdbcType=SMALLINT},#{bean.dpspeaker,jdbcType=INTEGER},#{bean.dprctime,jdbcType=TIMESTAMP})")
  void add(@Param("bean") Diphone bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.dpdbid or mask.dplnid or mask.dpstatus or mask.dpspeaker or mask.dprctime'>UPDATE F4702 <set><if test='mask.dpdbid'>DPDBID=#{bean.dpdbid,jdbcType=VARCHAR}, </if><if test='mask.dplnid'>DPLNID=#{bean.dplnid,jdbcType=INTEGER}, </if><if test='mask.dpstatus'>DPSTATUS=#{bean.dpstatus,jdbcType=SMALLINT}, </if><if test='mask.dpspeaker'>DPSPEAKER=#{bean.dpspeaker,jdbcType=INTEGER}, </if><if test='mask.dprctime'>DPRCTIME=#{bean.dprctime,jdbcType=TIMESTAMP}, </if></set>WHERE DPID=#{bean.dpid}</if></when><otherwise>UPDATE F4702 SET DPDBID=#{bean.dpdbid,jdbcType=VARCHAR}, DPLNID=#{bean.dplnid,jdbcType=INTEGER}, DPSTATUS=#{bean.dpstatus,jdbcType=SMALLINT}, DPSPEAKER=#{bean.dpspeaker,jdbcType=INTEGER}, DPRCTIME=#{bean.dprctime,jdbcType=TIMESTAMP} WHERE DPID=#{bean.dpid}</otherwise></choose></script>")
  void update(@Param("bean") Diphone bean, @Param("mask") DiphoneMask mask);

  @Delete("DELETE FROM F4702 WHERE DPID=#{dpid}")
  boolean delete(Integer dpid);

  @Delete(
      "<script>DELETE FROM F4702<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.dpid'>dpid,</if><if test='mask.dpdbid'>dpdbid,</if><if test='mask.dplnid'>dplnid,</if><if test='mask.dpstatus'>dpstatus,</if><if test='mask.dpspeaker'>dpspeaker,</if><if test='mask.dprctime'>dprctime,</if><if test='mask.dpspeakername'>dpspeakername,</if></trim></when><otherwise>SELECT dpid, dpdbid, dplnid, dpstatus, dpspeaker, dprctime, dpspeakername</otherwise></choose> FROM F4702View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<Diphone> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") DiphoneMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM F4702View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT DECODE(MIN(DPID),NULL,${defaultValue}) FROM F4702View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minDpid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(DPID),NULL,${defaultValue}) FROM F4702View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxDpid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(DPLNID),NULL,${defaultValue}) FROM F4702View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minDplnid(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(DPLNID),NULL,${defaultValue}) FROM F4702View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxDplnid(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(DPSTATUS),NULL,${defaultValue}) FROM F4702View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minDpstatus(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(DPSTATUS),NULL,${defaultValue}) FROM F4702View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxDpstatus(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(DPSPEAKER),NULL,${defaultValue}) FROM F4702View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minDpspeaker(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(DPSPEAKER),NULL,${defaultValue}) FROM F4702View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxDpspeaker(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(DPRCTIME),NULL,${defaultValue}) FROM F4702View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  java.util.Date minDprctime(
      @Param("filter") FilterExpr filter, @Param("defaultValue") java.util.Date defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(DPRCTIME),NULL,${defaultValue}) FROM F4702View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  java.util.Date maxDprctime(
      @Param("filter") FilterExpr filter, @Param("defaultValue") java.util.Date defaultValue);
}