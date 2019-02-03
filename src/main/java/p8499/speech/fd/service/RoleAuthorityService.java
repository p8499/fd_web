package p8499.speech.fd.service;

import p8499.speech.fd.FilterExpr;
import p8499.speech.fd.OrderByListExpr;
import p8499.speech.fd.mask.RoleAuthorityMask;
import p8499.speech.fd.bean.RoleAuthority;
import p8499.speech.fd.mapper.fd.RoleAuthorityMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("roleAuthorityService")
public class RoleAuthorityService {
  @Transactional(value = "fd_transaction", readOnly = true)
  public RoleAuthority get(Integer raid, RoleAuthorityMask mask) {
    return roleAuthorityMapper.get(raid, mask);
  }

  @Transactional(value = "fd_transaction")
  public RoleAuthority add(RoleAuthority bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    roleAuthorityMapper.add(bean);
    return bean;
  }

  @Transactional(value = "fd_transaction")
  public RoleAuthority update(RoleAuthority bean, RoleAuthorityMask mask) {
    Set<ConstraintViolation<RoleAuthority>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<RoleAuthority> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    roleAuthorityMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(Integer raid) {
    return roleAuthorityMapper.delete(raid);
  }

  @Transactional(value = "fd_transaction")
  public void delete(FilterExpr filter) {
    roleAuthorityMapper.deleteWhere(filter);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return roleAuthorityMapper.count(filter);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public List<RoleAuthority> query(
      FilterExpr filter,
      OrderByListExpr orderByList,
      long start,
      long count,
      RoleAuthorityMask mask) {
    return roleAuthorityMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public boolean exists(Integer raid) {
    return roleAuthorityMapper.exists(raid);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minRaid(FilterExpr filter, Integer defaultValue) {
    return roleAuthorityMapper.minRaid(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxRaid(FilterExpr filter, Integer defaultValue) {
    return roleAuthorityMapper.maxRaid(filter, defaultValue);
  }

  @Value(value = "#{roleAuthorityMapper}")
  protected RoleAuthorityMapper roleAuthorityMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}