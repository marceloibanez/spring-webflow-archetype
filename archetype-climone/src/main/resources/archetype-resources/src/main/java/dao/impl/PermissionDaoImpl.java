package ${package}.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import ${package}.dao.PermissionDao;
import ${package}.entities.EntityFilter;
import ${package}.entities.security.Permission;
import ${package}.entities.security.PermissionFilter;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDaoImpl extends HibernateDao<Permission> implements PermissionDao {

	@Override
	protected DetachedCriteria fillCriteria(EntityFilter<Permission> entityFilter, DetachedCriteria criteria) {
		final PermissionFilter filter = (PermissionFilter) entityFilter;

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getName())) {
				criteria.add(Restrictions.ilike("name", filter.getName()));
			}
		}

		return criteria;
	}

}
