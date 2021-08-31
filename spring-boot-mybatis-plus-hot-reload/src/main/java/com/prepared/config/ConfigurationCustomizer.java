package com.prepared.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;

/**
 * Callback interface that can be customized a {@link MybatisConfiguration} object generated on auto-configuration.
 *
 * @author Kazuki Shimizu
 * @since 1.2.1
 */
@FunctionalInterface
public interface ConfigurationCustomizer {

	/**
	 * Customize the given a {@link MybatisConfiguration} object.
	 *
	 * @param configuration the configuration object to customize
	 */
	void customize(MybatisConfiguration configuration);
}