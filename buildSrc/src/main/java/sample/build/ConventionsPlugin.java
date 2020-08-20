package sample.build;

import java.util.Map;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.plugins.JavaPlugin;

public class ConventionsPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		project.getPlugins().withType(JavaPlugin.class, javaPlugin -> {
			Map.Entry<String, Configuration> redis = Map.entry("redis",
					project.getConfigurations().create("redisImplementation"));
			Map<String, Configuration> configurations = Map.ofEntries(redis,
					Map.entry("jdbc", project.getConfigurations().create("jdbcImplementation")),
					Map.entry("hazelcast", project.getConfigurations().create("hazelcastImplementation")),
					Map.entry("mongoDb", project.getConfigurations().create("mongoDbImplementation")));
			String profile = (String) project.findProperty("profile");
			if (profile == null) {
				profile = redis.getKey();
			}
			Configuration active = configurations.getOrDefault(profile, redis.getValue());
			project.getConfigurations().getByName("implementation", configuration ->
					configuration.extendsFrom(active));
		});
	}

}
