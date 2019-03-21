package classgraph;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;

@MyAnnotation
@Mojo(name = TestMojo.NAME, defaultPhase = LifecyclePhase.GENERATE_SOURCES,
        aggregator = true, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME,
        requiresDependencyCollection = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class TestMojo extends AbstractMojo {

    public static final String NAME = "test-mojo";

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        ClassGraphUtils.doClassGraphCalls(AbstractMojo.class);

        // MojoExecutionException - if an unexpected problem occurs. Throwing
        // this exception causes a "BUILD ERROR" message to be displayed.
        // MojoFailureException - if an expected problem (such as a compilation
        // failure) occurs. Throwing this exception causes a "BUILD FAILURE"
        // message to be displayed.
        throw new MojoExecutionException("do not catch exception");

        // !!
        // throw documented exception within maven-mojo - classgraph finalises
        // with java.lang.NoClassDefFoundError:
        // nonapi/io/github/classgraph/utils/FileUtils$3

    }

}
