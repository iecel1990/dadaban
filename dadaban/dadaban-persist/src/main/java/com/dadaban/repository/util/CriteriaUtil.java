package com.dadaban.repository.util;

import com.dadaban.enums.StatusEnum;
import com.dadaban.repository.model.EventExample;
import com.dadaban.utils.ObjectUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by jrose on 8/5/14.
 */
public class CriteriaUtil {
    private static Map<String, OperatorAction> operatorActionMap = Maps.newHashMapWithExpectedSize(5);

    static {
        operatorActionMap.put("LIKE", new LikeOperatorAction());
        operatorActionMap.put("EQ", new EqualToOperatorAction());
    }

    private static final String SEPARATOR = "_";

    private static final String CRITERIA_METHOD = "createCriteria";

    private static final String CRITERIA_PREFIX = "and";

    public static void buildCriteria(Object example, Object countExample, Map<String, Object> searchParams) throws InvocationTargetException, IllegalAccessException {

        Map<String, Object> methodParams = Maps.newHashMapWithExpectedSize(searchParams.size());


        for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
            String key = entry.getKey();
            String operator = StringUtils.substringBefore(key, SEPARATOR);
            String property = StringUtils.substringAfter(key, SEPARATOR);

            OperatorAction operatorAction = operatorActionMap.get(operator);

            if (StringUtils.isNotBlank(operatorAction.getAction())) {
                StringBuilder sb = new StringBuilder();
                sb.append(CRITERIA_PREFIX);
                sb.append(StringUtils.capitalize(property));
                sb.append(operatorAction.getAction());
                methodParams.put(sb.toString(), operatorAction.process(entry.getValue()));
            }
        }

        Method criteriaMethod = ReflectionUtils.findMethod(example.getClass(), CRITERIA_METHOD);
        Object criteria = criteriaMethod.invoke(example);


        for (Map.Entry<String, Object> entry : methodParams.entrySet()) {

            Method method = ReflectionUtils.findMethod(criteria.getClass(), entry.getKey(), entry.getKey().getClass());
            if (ObjectUtil.isNotEmpty(method)) {

                method.invoke(criteria, entry.getValue());

            }
        }


    }
}