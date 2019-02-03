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
import p8499.speech.fd.mask.RoleMask;
import p8499.speech.fd.bean.Role;

@Component("roleMapper")
public interface RoleMapper {
  @Select("SELECT COUNT(*)>0 FROM F0310View WHERE RLID=#{rlid}")
  boolean exists(@Param("rlid") String rlid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.rlid or mask.rlname'><trim prefix='SELECT' suffixOverrides=','><if test='mask.rlid'>RLID, </if><if test='mask.rlname'>RLNAME, </if></trim>FROM F0310View WHERE RLID=#{rlid}</if></when><otherwise>SELECTRLID,RLNAME FROM F0310View WHERE RLID=#{rlid}</otherwise></choose></script>")
  Role get(@Param("rlid") String rlid, @Param("mask") RoleMask mask);

  @Insert(
      "INSERT INTO F0310 (RLID,RLNAME) VALUES (#{bean.rlid,jdbcType=VARCHAR},#{bean.rlname,jdbcType=VARCHAR})")
  void add(@Param("bean") Role bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.rlname'>UPDATE F0310 <set><if test='mask.rlname'>RLNAME=#{bean.rlname,jdbcType=VARCHAR}, </if></set>WHERE RLID=#{bean.rlid}</if></when><otherwise>UPDATE F0310 SET RLNAME=#{bean.rlname,jdbcType=VARCHAR} WHERE RLID=#{bean.rlid}</otherwise></choose></script>")
  void update(@Param("bean") Role bean, @Param("mask") RoleMask mask);

  @Delete("DELETE FROM F0310 WHERE RLID=#{rlid}")
  boolean delete(String rlid);

  @Delete(
      "<script>DELETE FROM F0310<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.rlid'>rlid,</if><if test='mask.rlname'>rlname,</if></trim></when><otherwise>SELECT rlid, rlname</otherwise></choose> FROM F0310View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<Role> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") RoleMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM F0310View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);
}