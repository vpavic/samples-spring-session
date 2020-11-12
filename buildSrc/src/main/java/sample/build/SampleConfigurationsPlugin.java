package sample.build;

import java.util.Map;
import java.util.Map.Entry;

import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.plugins.JavaPlugin;

class SampleConfigurationsPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		project.getPlugins().withType(JavaPlugin.class, javaPlugin -> {
			Entry<String, Configuration> redisImplementation = Map.entry("redis",
					project.getConfigurations().create("redisImplementation"));
			Map<String, Configuration> implementationConfigurations = Map.ofEntries(redisImplementation,
					Map.entry("jdbc", project.getConfigurations().create("jdbcImplementation")),
					Map.entry("hazelcast", project.getConfigurations().create("hazelcastImplementation")),
					Map.entry("mongoDb", project.getConfigurations().create("mongoDbImplementation")));
			Map<String, Configuration> runtimeConfigurations = Map.ofEntries(
					Map.entry("jdbc", project.getConfigurations().create("jdbcRuntime")));
			String profile = (String) project.findProperty("profile");
			if (profile == null) {
				profile = redisImplementation.getKey();
			}
			Configuration activeImplementation = implementationConfigurations.get(profile);
			if (activeImplementation == null) {
				throw new GradleException("Invalid profile: " + profile);
			}
			project.getConfigurations().getByName("implementation", configuration ->
					configuration.extendsFrom(activeImplementation));
			Configuration activeRuntime = runtimeConfigurations.get(profile);
			if (activeRuntime != null) {
				project.getConfigurations().getByName("runtime", configuration ->
						configuration.extendsFrom(activeRuntime));
			}
		});
	}

}
