package p8499.speech.fd.service;

import p8499.speech.fd.FilterExpr;
import p8499.speech.fd.OrderByListExpr;
import p8499.speech.fd.mask.AuthorityMask;
import p8499.speech.fd.bean.Authority;
import p8499.speech.fd.mapper.fd.AuthorityMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("authorityService")
public class AuthorityService {
  @Transactional(value = "fd_transaction", readOnly = true)
  public Authority get(String auid, AuthorityMask mask) {
    return authorityMapper.get(auid, mask);
  }

  @Transactional(value = "fd_transaction")
  public Authority add(Authority bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    authorityMapper.add(bean);
    return bean;
  }

  @Transactional(value = "fd_transaction")
  public Authority update(Authority bean, AuthorityMask mask) {
    Set<ConstraintViolation<Authority>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<Authority> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    authorityMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(String auid) {
    return authorityMapper.delete(auid);
  }

  @Transactional(value = "fd_transaction")
  public void delete(FilterExpr filter) {
    authorityMapper.deleteWhere(filter);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return authorityMapper.count(filter);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public List<Authority> query(
      FilterExpr filter, OrderByListExpr orderByList, long start, long count, AuthorityMask mask) {
    return authorityMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public boolean exists(String auid) {
    return authorityMapper.exists(auid);
  }

  @Value(value = "#{authorityMapper}")
  protected AuthorityMapper authorityMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}