package classgraph;

@MyAnnotation
public class TestWithMain extends MySuperClass {
    public static void main(String[] args) {
        // scan but do not close ScanResults
        ClassGraphUtils.doClassGraphCalls(MySuperClass.class);

        // do not catch the exception - but classgraph finalises
        // WITHOUT java.lang.NoClassDefFoundError:
        // nonapi/io/github/classgraph/utils/FileUtils$3
        throw new IllegalArgumentException("do not catch exception");
        // ==> everything is fine.
    }
}
