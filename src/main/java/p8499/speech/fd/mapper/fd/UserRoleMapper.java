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
import p8499.speech.fd.mask.UserRoleMask;
import p8499.speech.fd.bean.UserRole;

@Component("userRoleMapper")
public interface UserRoleMapper {
  @Select("SELECT COUNT(*)>0 FROM F0311View WHERE URID=#{urid}")
  boolean exists(@Param("urid") Integer urid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.urid or mask.urusid or mask.urrlid or mask.urrlname'><trim prefix='SELECT' suffixOverrides=','><if test='mask.urid'>URID, </if><if test='mask.urusid'>URUSID, </if><if test='mask.urrlid'>URRLID, </if><if test='mask.urrlname'>URRLNAME, </if></trim>FROM F0311View WHERE URID=#{urid}</if></when><otherwise>SELECTURID,URUSID,URRLID,URRLNAME FROM F0311View WHERE URID=#{urid}</otherwise></choose></script>")
  UserRole get(@Param("urid") Integer urid, @Param("mask") UserRoleMask mask);

  @org.apache.ibatis.annotations.SelectKey(
    statement = "SELECT F0311_URID.nextval AS urid FROM DUAL",
    before = true,
    resultType = Integer.class,
    keyColumn = "urid",
    keyProperty = "bean.urid"
  )
  @Insert(
      "INSERT INTO F0311 (URID,URUSID,URRLID) VALUES (#{bean.urid,jdbcType=INTEGER},#{bean.urusid,jdbcType=INTEGER},#{bean.urrlid,jdbcType=VARCHAR})")
  void add(@Param("bean") UserRole bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.urusid or mask.urrlid'>UPDATE F0311 <set><if test='mask.urusid'>URUSID=#{bean.urusid,jdbcType=INTEGER}, </if><if test='mask.urrlid'>URRLID=#{bean.urrlid,jdbcType=VARCHAR}, </if></set>WHERE URID=#{bean.urid}</if></when><otherwise>UPDATE F0311 SET URUSID=#{bean.urusid,jdbcType=INTEGER}, URRLID=#{bean.urrlid,jdbcType=VARCHAR} WHERE URID=#{bean.urid}</otherwise></choose></script>")
  void update(@Param("bean") UserRole bean, @Param("mask") UserRoleMask mask);

  @Delete("DELETE FROM F0311 WHERE URID=#{urid}")
  boolean delete(Integer urid);

  @Delete(
      "<script>DELETE FROM F0311<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.urid'>urid,</if><if test='mask.urusid'>urusid,</if><if test='mask.urrlid'>urrlid,</if><if test='mask.urrlname'>urrlname,</if></trim></when><otherwise>SELECT urid, urusid, urrlid, urrlname</otherwise></choose> FROM F0311View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<UserRole> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") UserRoleMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM F0311View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT DECODE(MIN(URID),NULL,${defaultValue}) FROM F0311View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minUrid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(URID),NULL,${defaultValue}) FROM F0311View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxUrid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(URUSID),NULL,${defaultValue}) FROM F0311View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minUrusid(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(URUSID),NULL,${defaultValue}) FROM F0311View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxUrusid(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);
}