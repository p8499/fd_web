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
import p8499.speech.fd.mask.UserMask;
import p8499.speech.fd.bean.User;

@Component("userMapper")
public interface UserMapper {
  @Select("SELECT COUNT(*)>0 FROM F0301View WHERE USID=#{usid}")
  boolean exists(@Param("usid") Integer usid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.usid or mask.usalias or mask.usfrom or mask.uspswd or mask.usname or mask.usstatus'><trim prefix='SELECT' suffixOverrides=','><if test='mask.usid'>USID, </if><if test='mask.usalias'>USALIAS, </if><if test='mask.usfrom'>USFROM, </if><if test='mask.uspswd'>USPSWD, </if><if test='mask.usname'>USNAME, </if><if test='mask.usstatus'>USSTATUS, </if></trim>FROM F0301View WHERE USID=#{usid}</if></when><otherwise>SELECTUSID,USALIAS,USFROM,USPSWD,USNAME,USSTATUS FROM F0301View WHERE USID=#{usid}</otherwise></choose></script>")
  User get(@Param("usid") Integer usid, @Param("mask") UserMask mask);

  @org.apache.ibatis.annotations.SelectKey(
    statement = "SELECT F0301_USID.nextval AS usid FROM DUAL",
    before = true,
    resultType = Integer.class,
    keyColumn = "usid",
    keyProperty = "bean.usid"
  )
  @Insert(
      "INSERT INTO F0301 (USID,USALIAS,USFROM,USPSWD,USNAME,USSTATUS) VALUES (#{bean.usid,jdbcType=INTEGER},#{bean.usalias,jdbcType=VARCHAR},#{bean.usfrom,jdbcType=VARCHAR},#{bean.uspswd,jdbcType=VARCHAR},#{bean.usname,jdbcType=VARCHAR},#{bean.usstatus,jdbcType=SMALLINT})")
  void add(@Param("bean") User bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.usalias or mask.usfrom or mask.uspswd or mask.usname or mask.usstatus'>UPDATE F0301 <set><if test='mask.usalias'>USALIAS=#{bean.usalias,jdbcType=VARCHAR}, </if><if test='mask.usfrom'>USFROM=#{bean.usfrom,jdbcType=VARCHAR}, </if><if test='mask.uspswd'>USPSWD=#{bean.uspswd,jdbcType=VARCHAR}, </if><if test='mask.usname'>USNAME=#{bean.usname,jdbcType=VARCHAR}, </if><if test='mask.usstatus'>USSTATUS=#{bean.usstatus,jdbcType=SMALLINT}, </if></set>WHERE USID=#{bean.usid}</if></when><otherwise>UPDATE F0301 SET USALIAS=#{bean.usalias,jdbcType=VARCHAR}, USFROM=#{bean.usfrom,jdbcType=VARCHAR}, USPSWD=#{bean.uspswd,jdbcType=VARCHAR}, USNAME=#{bean.usname,jdbcType=VARCHAR}, USSTATUS=#{bean.usstatus,jdbcType=SMALLINT} WHERE USID=#{bean.usid}</otherwise></choose></script>")
  void update(@Param("bean") User bean, @Param("mask") UserMask mask);

  @Delete("DELETE FROM F0301 WHERE USID=#{usid}")
  boolean delete(Integer usid);

  @Delete(
      "<script>DELETE FROM F0301<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.usid'>usid,</if><if test='mask.usalias'>usalias,</if><if test='mask.usfrom'>usfrom,</if><if test='mask.uspswd'>uspswd,</if><if test='mask.usname'>usname,</if><if test='mask.usstatus'>usstatus,</if></trim></when><otherwise>SELECT usid, usalias, usfrom, uspswd, usname, usstatus</otherwise></choose> FROM F0301View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<User> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") UserMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM F0301View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT DECODE(MIN(USID),NULL,${defaultValue}) FROM F0301View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minUsid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(USID),NULL,${defaultValue}) FROM F0301View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxUsid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(USSTATUS),NULL,${defaultValue}) FROM F0301View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minUsstatus(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(USSTATUS),NULL,${defaultValue}) FROM F0301View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxUsstatus(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);
}