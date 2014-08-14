package com.dadaban.repository.util;

import com.dadaban.enums.StatusEnum;
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

    private static final String PAGE_METHOD = "setPage";

    private static final String CRITERIA_PREFIX = "and";

    private static final String STATUS_METHOD = "andStatusEqualTo";

    private static final String SORT_TYPE_METHOD = "setOrderByClause";

    private static final String AUTO_SORT = "auto";

    public static void buildCriteria(Object example, Object countExample, Map<String, Object> searchParams, Page page, String sortType, boolean isValidStatus) throws InvocationTargetException, IllegalAccessException {

        if (ObjectUtil.isEmpty(searchParams)) {
            return;
        }
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

        processSortType(example, sortType);
        processExample(example, page, methodParams, sortType, isValidStatus);
        processCountExample(countExample, page, methodParams, isValidStatus);
    }

    private static void processCountExample(Object countExample, Page page, Map<String, Object> methodParams, boolean isValidStatus) throws InvocationTargetException, IllegalAccessException {
        Method criteriaMethod = ReflectionUtils.findMethod(countExample.getClass(), CRITERIA_METHOD);

        Object criteria = criteriaMethod.invoke(countExample);

        if (isValidStatus) {
            Method countStatusMethod = ReflectionUtils.findMethod(criteria.getClass(), STATUS_METHOD, Integer.class);
            if (ObjectUtil.isNotEmpty(countStatusMethod)) {
                countStatusMethod.invoke(criteria, StatusEnum.valid.getCode());
            }
        }

        for (Map.Entry<String, Object> entry : methodParams.entrySet()) {

            Method method = ReflectionUtils.findMethod(criteria.getClass(), entry.getKey(), entry.getKey().getClass());
            if (ObjectUtil.isNotEmpty(method)) {
                method.invoke(criteria, entry.getValue());

            }
        }
    }

    private static void processExample(Object example, Page page, Map<String, Object> methodParams,String sortType, boolean isValidStatus) throws IllegalAccessException, InvocationTargetException {
        Method criteriaMethod = ReflectionUtils.findMethod(example.getClass(), CRITERIA_METHOD);
        Method pageMethod = ReflectionUtils.findMethod(example.getClass(), PAGE_METHOD, page.getClass());
        pageMethod.invoke(example, page);

        Object criteria = criteriaMethod.invoke(example);

        if (isValidStatus) {
            Method statusMethod = ReflectionUtils.findMethod(criteria.getClass(), STATUS_METHOD, Integer.class);
            if (ObjectUtil.isNotEmpty(statusMethod)) {
                statusMethod.invoke(criteria, StatusEnum.valid.getCode());
            }
        }

        for (Map.Entry<String, Object> entry : methodParams.entrySet()) {

            Method method = ReflectionUtils.findMethod(criteria.getClass(), entry.getKey(), entry.getKey().getClass());
            if (ObjectUtil.isNotEmpty(method)) {
                method.invoke(criteria, entry.getValue());

            }
        }
    }

    private static void processSortType(Object example, String sortType) throws IllegalAccessException, InvocationTargetException {
        if (StringUtils.isNotBlank(sortType)) {
            Method sortTypeMethod = ReflectionUtils.findMethod(example.getClass(), SORT_TYPE_METHOD, String.class);

            if (ObjectUtil.isNotEmpty(sortTypeMethod)) {
                String sort = sortType;
                if (StringUtils.equals(AUTO_SORT, sortType)){
                    sort = "id asc";
                } else if (!StringUtils.contains(sortType, " ")) {
                    sort += " desc";
                }
                sortTypeMethod.invoke(example, sort);
            }
        }
    }


    public static void buildCriteria(Object example, Object countExample, Map<String, Object> searchParams, Page page) throws InvocationTargetException, IllegalAccessException {

        buildCriteria(example, countExample, searchParams, page, null, true);
    }

    public static void buildCriteria(Object example, Object countExample, Map<String, Object> searchParams, Page page, String sortType) throws InvocationTargetException, IllegalAccessException {

        buildCriteria(example, countExample, searchParams, page,sortType, true);
    }
}
