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
import p8499.speech.fd.mask.AuthorityMask;
import p8499.speech.fd.bean.Authority;

@Component("authorityMapper")
public interface AuthorityMapper {
  @Select("SELECT COUNT(*)>0 FROM F0320View WHERE AUID=#{auid}")
  boolean exists(@Param("auid") String auid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.auid or mask.auname'><trim prefix='SELECT' suffixOverrides=','><if test='mask.auid'>AUID, </if><if test='mask.auname'>AUNAME, </if></trim>FROM F0320View WHERE AUID=#{auid}</if></when><otherwise>SELECTAUID,AUNAME FROM F0320View WHERE AUID=#{auid}</otherwise></choose></script>")
  Authority get(@Param("auid") String auid, @Param("mask") AuthorityMask mask);

  @Insert(
      "INSERT INTO F0320 (AUID,AUNAME) VALUES (#{bean.auid,jdbcType=VARCHAR},#{bean.auname,jdbcType=VARCHAR})")
  void add(@Param("bean") Authority bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.auname'>UPDATE F0320 <set><if test='mask.auname'>AUNAME=#{bean.auname,jdbcType=VARCHAR}, </if></set>WHERE AUID=#{bean.auid}</if></when><otherwise>UPDATE F0320 SET AUNAME=#{bean.auname,jdbcType=VARCHAR} WHERE AUID=#{bean.auid}</otherwise></choose></script>")
  void update(@Param("bean") Authority bean, @Param("mask") AuthorityMask mask);

  @Delete("DELETE FROM F0320 WHERE AUID=#{auid}")
  boolean delete(String auid);

  @Delete(
      "<script>DELETE FROM F0320<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.auid'>auid,</if><if test='mask.auname'>auname,</if></trim></when><otherwise>SELECT auid, auname</otherwise></choose> FROM F0320View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<Authority> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") AuthorityMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM F0320View<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);
}