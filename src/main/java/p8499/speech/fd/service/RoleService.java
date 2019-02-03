package p8499.speech.fd.service;

import p8499.speech.fd.FilterExpr;
import p8499.speech.fd.OrderByListExpr;
import p8499.speech.fd.mask.RoleMask;
import p8499.speech.fd.bean.Role;
import p8499.speech.fd.mapper.fd.RoleMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("roleService")
public class RoleService {
  @Transactional(value = "fd_transaction", readOnly = true)
  public Role get(String rlid, RoleMask mask) {
    return roleMapper.get(rlid, mask);
  }

  @Transactional(value = "fd_transaction")
  public Role add(Role bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    roleMapper.add(bean);
    return bean;
  }

  @Transactional(value = "fd_transaction")
  public Role update(Role bean, RoleMask mask) {
    Set<ConstraintViolation<Role>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<Role> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    roleMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(String rlid) {
    return roleMapper.delete(rlid);
  }

  @Transactional(value = "fd_transaction")
  public void delete(FilterExpr filter) {
    roleMapper.deleteWhere(filter);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return roleMapper.count(filter);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public List<Role> query(
      FilterExpr filter, OrderByListExpr orderByList, long start, long count, RoleMask mask) {
    return roleMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public boolean exists(String rlid) {
    return roleMapper.exists(rlid);
  }

  @Value(value = "#{roleMapper}")
  protected RoleMapper roleMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}