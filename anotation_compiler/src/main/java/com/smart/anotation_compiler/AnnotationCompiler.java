package com.smart.anotation_compiler;

import com.google.auto.service.AutoService;
import com.smart.anotation.BindPath;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * @author LinDingQiang
 * @time 2020/7/29 15:40
 * @email dingqiang.l@verifone.cn
 */
@AutoService(Process.class)
public class AnnotationCompiler extends AbstractProcessor {
    private Filer filer;

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(BindPath.class.getCanonicalName());
        return types;
    }

    /**
     * 声明支持java 版本
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(BindPath.class);

        Map<String, String> map = new HashMap<>();

        for (Element element : elementsAnnotatedWith) {
            TypeElement typeElement = (TypeElement) element;
            String key = typeElement.getAnnotation(BindPath.class).value();
            String activityName = typeElement.getQualifiedName().toString();
            map.put(key, activityName + ".class");
        }
        if (map.size() > 0) {
            creatClass(map);
        }
        return false;
    }

    private void creatClass(Map<String, String> map) {
        try {
            MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("putActivity")
                    .addModifiers(Modifier.PUBLIC)
                    .returns(void.class);

            for (String key : map.keySet()) {
                String activityName = map.get(key);
                methodBuilder.addStatement("com.smart.router.Arouter.getInstance().addActivity(\"" + key + "\"," + activityName + ")");
//                methodBuilder.addStatement("com.smart.router.Arouter.getInstance().addActivity(" + key + ", " + activityName + ")");
            }
            MethodSpec methodSpec = methodBuilder.build();

            ClassName iArouter = ClassName.get("com.smart.router", "IRouter");
            TypeSpec typeSpec = TypeSpec.classBuilder("ActivityUtil" + System.currentTimeMillis())
                    .addModifiers(Modifier.PUBLIC)
                    .addSuperinterface(iArouter)
                    .addMethod(methodSpec)
                    .build();

            JavaFile javaFile = JavaFile.builder("com.smart.utils", typeSpec).build();

            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
