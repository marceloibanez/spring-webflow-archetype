package ${package}.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import ${package}.dao.RoleDao;
import ${package}.entities.EntityFilter;
import ${package}.entities.security.Role;
import ${package}.entities.security.RoleFilter;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends HibernateDao<Role> implements RoleDao {

	// @Autowired
	// private PermissionDao permissionDao;

	@Override
	protected DetachedCriteria fillCriteria(EntityFilter<Role> entityFilter, final DetachedCriteria criteria) {
		final RoleFilter filter = (RoleFilter) entityFilter;

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getName())) {
				criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));
			}

			if (StringUtils.isNotBlank(filter.getDescription())) {
				criteria.add(Restrictions.ilike("description", filter.getDescription(), MatchMode.ANYWHERE));
			}

			// if (filter.getPermission() != null) {
			// DetachedCriteria permissionCriteria = criteria.createAlias("permission", "permission");
			// permissionDao.fillCriteria(filter.getPermission(), permissionCriteria);
			// }
		}

		return criteria;
	}

}
