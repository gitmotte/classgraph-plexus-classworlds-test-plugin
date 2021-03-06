package classgraph;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.InfoList;
import io.github.classgraph.ScanResult;

public class ClassGraphUtils {

    public static void doClassGraphCalls(Class<?> superClass) {
        ClassGraph cg = new ClassGraph()
                .whitelistPackages("classgraph");
        cg.verbose();

        // scan but do not close ScanResults
        getSuperClasses(cg, superClass);
        getClassNamesAnnotated(cg, MyAnnotation.class);
    }

    private static List<String> getSuperClasses(ClassGraph cg, Class<?> superCls) {
        List<String> classNames = null;
        ScanResult x = cg.scan(); // do not close !
        if (superCls.isInterface()) {
            classNames = toList(x.getClassesImplementing(superCls.getName()));
        } else {
            classNames = toList(x.getSubclasses(superCls.getName()));
            classNames.add(superCls.getName());
        }
        return classNames;
    }

    private static List<String> getClassNamesAnnotated(ClassGraph cg, Class<? extends Annotation> annotationClass) {
        ScanResult r = cg.enableAnnotationInfo().scan(); // do not close !
        ClassInfoList cli = r.getClassesWithAnnotation(annotationClass.getName());
        return toList(cli);
    }

    /**
     * @param namesList
     * @return
     */
    private static List<String> toList(InfoList<?> namesList) {
        // encapsulate to new ArrayList (maybe we need to add items)
        return new ArrayList<>(namesList.getNames());
    }

}
