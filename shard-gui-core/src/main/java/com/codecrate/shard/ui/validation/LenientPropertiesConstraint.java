package com.codecrate.shard.ui.validation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.binding.PropertyAccessStrategy;
import org.springframework.rules.closure.BinaryConstraint;
import org.springframework.rules.constraint.property.PropertiesConstraint;

/**
 * properties constraint that is "lenient" if exceptions occur during evaluation.
 */
public class LenientPropertiesConstraint extends PropertiesConstraint {
	private static final Log LOGGER = LogFactory.getLog(LenientPropertiesConstraint.class);

	public LenientPropertiesConstraint(String propertyName, BinaryConstraint beanPropertyExpression, String otherPropertyName) {
		super(propertyName, beanPropertyExpression, otherPropertyName);
	}

	protected boolean test(PropertyAccessStrategy domainObjectAccessStrategy) {
		try {
			return super.test(domainObjectAccessStrategy);
		} catch (Exception e) {
			LOGGER.warn("Unable to evaluate expression.  Defaulting to 'true'.", e);
			return true;
		}
	}
}