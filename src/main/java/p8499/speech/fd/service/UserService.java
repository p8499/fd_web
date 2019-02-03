package p8499.speech.fd.service;

import p8499.speech.fd.FilterExpr;
import p8499.speech.fd.OrderByListExpr;
import p8499.speech.fd.mask.UserMask;
import p8499.speech.fd.bean.User;
import p8499.speech.fd.mapper.fd.UserMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserService {
  @Transactional(value = "fd_transaction", readOnly = true)
  public User get(Integer usid, UserMask mask) {
    return userMapper.get(usid, mask);
  }

  @Transactional(value = "fd_transaction")
  public User add(User bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    userMapper.add(bean);
    return bean;
  }

  @Transactional(value = "fd_transaction")
  public User update(User bean, UserMask mask) {
    Set<ConstraintViolation<User>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<User> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    userMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(Integer usid) {
    return userMapper.delete(usid);
  }

  @Transactional(value = "fd_transaction")
  public void delete(FilterExpr filter) {
    userMapper.deleteWhere(filter);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return userMapper.count(filter);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public List<User> query(
      FilterExpr filter, OrderByListExpr orderByList, long start, long count, UserMask mask) {
    return userMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public boolean exists(Integer usid) {
    return userMapper.exists(usid);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minUsid(FilterExpr filter, Integer defaultValue) {
    return userMapper.minUsid(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxUsid(FilterExpr filter, Integer defaultValue) {
    return userMapper.maxUsid(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minUsstatus(FilterExpr filter, Integer defaultValue) {
    return userMapper.minUsstatus(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxUsstatus(FilterExpr filter, Integer defaultValue) {
    return userMapper.maxUsstatus(filter, defaultValue);
  }

  @Value(value = "#{userMapper}")
  protected UserMapper userMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}