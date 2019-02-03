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
import p8499.speech.fd.mask.RoleAuthorityMask;
import p8499.speech.fd.bean.RoleAuthority;

@Component("roleAuthorityMapper")
public interface RoleAuthorityMapper {
  @Select("SELECT COUNT(*)>0 FROM F0321View WHERE RAID=#{raid}")
  boolean exists(@Param("raid") Integer raid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.raid or mask.rarlid or mask.raauid or mask.raauname'><trim prefix='SELECT' suffixOverrides=','><if test='mask.raid'>RAID, </if><if test='mask.rarlid'>RARLID, </if><if test='mask.raauid'>RAAUID, </if><if test='mask.raauname'>RAAUNAME, </if></trim>FROM F0321View WHERE RAID=#{raid}</if></when><otherwise>SELECTRAID,RARLID,RAAUID,RAAUNAME FROM F0321View WHERE RAID=#{raid}</otherwise></choose></script>")
  RoleAuthority get(@Param("raid") Integer raid, @Param("mask") RoleAuthorityMask mask);

  @org.apache.ibatis.annotations.SelectKey(
    statement = "SELECT F0321_RAID.nextval AS raid FROM DUAL",
    before = true,
    resultType = Integer.class,
    keyColumn = "raid",
    keyProperty = "bean.raid"
  )
  @Insert(
      "INSERT INTO F0321 (RAID,RARLID,RAAUID) VALUES (#{bean.raid,jdbcType=INTEGER},#{bean.rarlid,jdbcType=VARCHAR},#{bean.raauid,jdbcType=VARCHAR})")
  void add(@Param("bean") RoleAuthority bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.rarlid or mask.raauid'>UPDATE F0321 <set><if test='mask.rarlid'>RARLID=#{bean.rarlid,jdbcType=VARCHAR}, </if><if test='mask.raauid'>RAAUID=#{bean.raauid,jdbcType=VARCHAR}, </if></set>WHERE RAID=#{bean.raid}</if></when><otherwise>UPDATE F0321 SET RARLID=#{bean.rarlid,jdbcType=VARCHAR}, RAAUID=#{bean.raauid,jdbcType=VARCHAR} WHERE RAID=#{bean.raid}</otherwise></choose></script>")
  void update(@Param("bean") RoleAuthority bean, @Param("mask") RoleAuthorityMask mask);

  @Delete("DELETE FROM F0321 WHERE RAID=#{raid}")
  boolean delete(Integer raid);

  @Delete(
      "<script>DELETE FROM F0321<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.raid'>raid,</if><if test='mask.rarlid'>rarlid,</if><if test='mask.raauid'>raauid,</if><if test='mask.raauname'>raauname,</if></trim></when><otherwise>SELECT raid, rarlid, raauid, raauname</otherwise></choose> FROM F0321View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<RoleAuthority> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") RoleAuthorityMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM F0321View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT DECODE(MIN(RAID),NULL,${defaultValue}) FROM F0321View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minRaid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(RAID),NULL,${defaultValue}) FROM F0321View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxRaid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);
}