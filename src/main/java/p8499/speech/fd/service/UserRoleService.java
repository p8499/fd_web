package p8499.speech.fd.service;

import p8499.speech.fd.FilterExpr;
import p8499.speech.fd.OrderByListExpr;
import p8499.speech.fd.mask.UserRoleMask;
import p8499.speech.fd.bean.UserRole;
import p8499.speech.fd.mapper.fd.UserRoleMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("userRoleService")
public class UserRoleService {
  @Transactional(value = "fd_transaction", readOnly = true)
  public UserRole get(Integer urid, UserRoleMask mask) {
    return userRoleMapper.get(urid, mask);
  }

  @Transactional(value = "fd_transaction")
  public UserRole add(UserRole bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    userRoleMapper.add(bean);
    return bean;
  }

  @Transactional(value = "fd_transaction")
  public UserRole update(UserRole bean, UserRoleMask mask) {
    Set<ConstraintViolation<UserRole>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<UserRole> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    userRoleMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(Integer urid) {
    return userRoleMapper.delete(urid);
  }

  @Transactional(value = "fd_transaction")
  public void delete(FilterExpr filter) {
    userRoleMapper.deleteWhere(filter);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return userRoleMapper.count(filter);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public List<UserRole> query(
      FilterExpr filter, OrderByListExpr orderByList, long start, long count, UserRoleMask mask) {
    return userRoleMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public boolean exists(Integer urid) {
    return userRoleMapper.exists(urid);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minUrid(FilterExpr filter, Integer defaultValue) {
    return userRoleMapper.minUrid(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxUrid(FilterExpr filter, Integer defaultValue) {
    return userRoleMapper.maxUrid(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minUrusid(FilterExpr filter, Integer defaultValue) {
    return userRoleMapper.minUrusid(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxUrusid(FilterExpr filter, Integer defaultValue) {
    return userRoleMapper.maxUrusid(filter, defaultValue);
  }

  @Value(value = "#{userRoleMapper}")
  protected UserRoleMapper userRoleMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}