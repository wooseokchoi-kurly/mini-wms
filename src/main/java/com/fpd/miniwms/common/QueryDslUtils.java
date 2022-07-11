package com.fpd.miniwms.common;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class QueryDslUtils {

    private static String START_TIME_SUFFIX = " 00:00:00";
    private static String END_TIME_SUFFIX = " 23:59:59";

    private QueryDslUtils() {
    }


    /**
     * 컬럼 비밀글 CASE
     *
     * @param isSecretPath : isSecret Path
     * @param fieldPath    : 대상 Path
     * @param fieldName    : 대상 field Alias 명
     * @return
     */
    public static StringExpression fieldIsSecret(BooleanPath isSecretPath, StringPath fieldPath, String fieldName) {
        return new CaseBuilder()
                .when(isSecretPath.eq(true)).then("비밀글입니다.")
                .otherwise(fieldPath).as(fieldName);
    }

    /**
     * 컬럼 in 조건 생성
     *
     * @param path
     * @param value
     * @return
     */
    public static BooleanExpression in(StringPath path, List<String> value) {
        return value != null && !value.isEmpty() ? path.in(value) : null;
    }

    /**
     * 컬럼 contains (like) 조건생성
     *
     * @param path
     * @param value
     * @return
     */
    public static BooleanExpression contains(StringPath path, String value) {
        return StringUtils.isNotEmpty(value) ? path.contains(value) : null;
    }

    /**
     * 공백 치환 like 검색
     *
     * @param path
     * @param value
     * @return
     */
    public static BooleanExpression containsWithoutBlank(StringPath path, String value) {
        return StringUtils.isNotBlank(value)
                ? Expressions.stringTemplate("replace({0},' ','')", path).contains(value.replace(" ", ""))
                : null;
    }

    /**
     * 컬럼 containsIgnoreCase (like) 조건생성
     *
     * @param path
     * @param value
     * @return
     */
    public static BooleanExpression containsIgnoreCase(StringPath path, String value) {
        return StringUtils.isNotEmpty(value) ? path.containsIgnoreCase(value) : null;
    }

    /**
     * 컬럼 equals 조건생성 (Integer)
     *
     * @param path
     * @param search
     * @return
     */
    public static BooleanExpression eq(NumberPath<Integer> path, Integer search) {
        return search != null
                ? path.eq(search)
                : null;
    }

    /**
     * 컬럼 equals 조건생성 (enum)
     *
     * @param path
     * @param search
     * @return
     */
    public static BooleanExpression eq(EnumPath path, Enum search) {
        return search != null
                ? path.eq(search)
                : null;
    }

    /**
     * 컬럼 equals 조건생성 (String)
     *
     * @param path
     * @param search
     * @return
     */
    public static BooleanExpression eq(StringPath path, String search) {
        return StringUtils.isNotEmpty(search)
                ? path.eq(search)
                : null;
    }

    /**
     * 컬럼 equals 조건생성 (long)
     *
     * @param path
     * @param search
     * @return
     */
    public static BooleanExpression eq(NumberPath<Long> path, Long search) {
        return search != null
                ? path.eq(search)
                : null;
    }

    /**
     * 날짜 비교 (eq)
     *
     * @param path
     * @param date : yyyy-mm-dd (from date)
     * @return
     */
    public static BooleanExpression eq(DatePath<LocalDate> path, String date) {
        return StringUtils.isNotEmpty(date)
                ? path.eq(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                : null;
    }

    /**
     * 날짜 비교 (eq)
     *
     * @param path
     * @param date : yyyy-mm-dd (from date)
     * @return
     */
    public static BooleanExpression eq(DatePath<LocalDate> path, LocalDate date) {
        return date != null
                ? path.eq(LocalDate.parse(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                : null;
    }

    /**
     * 날짜 비교 (eq)
     *
     * @param path
     * @param date : yyyy-mm-dd (from date)
     * @return
     */
    public static BooleanExpression eq(DateTimePath<LocalDateTime> path, String date) {
        return StringUtils.isNotEmpty(date)
                ? path.eq(LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                : null;
    }

    /**
     * 컬럼 equals 조건생성 (boolean)
     *
     * @param path
     * @param value
     * @return
     */
    public static BooleanExpression eq(BooleanPath path, Boolean value) {
        return value != null ? path.eq(value) : null;
    }

    public static BooleanExpression eq(NumberExpression<Long> numberExpression, Long search) {
        return search != null
                ? numberExpression.eq(search)
                : null;
    }

    /**
     * 컬럼 not equals 조건생성 (String)
     *
     * @param path
     * @param search
     * @return
     */
    public static BooleanExpression ne(StringPath path, String search) {
        return StringUtils.isNotEmpty(search)
                ? path.ne(search)
                : null;
    }

    /**
     * 컬럼 not equals 조건생성 (long)
     *
     * @param path
     * @param search
     * @return
     */
    public static BooleanExpression ne(NumberPath<Long> path, Long search) {
        return search != null
                ? path.ne(search)
                : null;
    }

    /**
     * 컬럼 not equals 조건생성 (boolean)
     *
     * @param path
     * @param value
     * @return
     */
    public static BooleanExpression ne(BooleanPath path, Boolean value) {
        return value != null ? path.ne(value) : null;
    }

    /**
     * 컬럼 not equals 조건생성 (long)
     *
     * @param path
     * @param search
     * @return
     */
    public static BooleanExpression ne(NumberPath<Integer> path, Integer search) {
        return search != null
                ? path.ne(search)
                : null;
    }

    /**
     * 날짜 비교 (between) - LocalDateTime
     *
     * @param path
     * @param fromDate : yyyy-mm-dd (to date)
     * @param toDate   : yyyy-mm-dd (from date)
     * @return
     */
    public static BooleanExpression between(DateTimePath<LocalDateTime> path, String fromDate, String toDate) {

        if (StringUtils.isNotEmpty(fromDate) && StringUtils.isNotEmpty(toDate)) {
            return path.between(LocalDateTime.parse(fromDate.concat(START_TIME_SUFFIX), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    LocalDateTime.parse(toDate.concat(END_TIME_SUFFIX), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        } else if (StringUtils.isNotEmpty(fromDate)) {
            return goe(path, fromDate);
        } else if (StringUtils.isNotEmpty(toDate)) {
            return loe(path, toDate);
        } else {
            return null;
        }
    }

    /**
     * 날짜 비교 (lt) - LocalDateTime
     *
     * @param path
     * @param date : yyyy-mm-dd (to date)
     * @return
     */
    public static BooleanExpression loe(DateTimePath<LocalDateTime> path, String date) {
        return StringUtils.isNotEmpty(date)
                ? path.loe(LocalDateTime.parse(date + END_TIME_SUFFIX, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                : null;
    }


    /**
     * 날짜 비교 (gt) - LocalDateTime
     *
     * @param path
     * @param date : yyyy-mm-dd (from date)
     * @return
     */
    public static BooleanExpression goe(DateTimePath<LocalDateTime> path, String date) {
        return StringUtils.isNotEmpty(date)
                ? path.goe(LocalDateTime.parse(date + START_TIME_SUFFIX, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                : null;
    }

    /**
     * 정렬 기준 컬럼 얻기
     *
     * @param order  : 정렬 Direction (Order.ASC, Order.DESC)
     * @param parent : 정렬 대상 QueryDsl Entity (ex) QBoardManagementInfoEntity
     * @param field  : Client에서 받은 정렬 기준 필드 명
     * @return
     */
    public static OrderSpecifier<?> getSortedColumn(Order order, Path<?> parent, String field) {
        Path<Object> fieldPath = Expressions.path(Object.class, parent, field);

        return new OrderSpecifier(order, fieldPath);
    }

    /**
     * Order의 Direction 얻기
     *
     * @param order
     * @return
     */
    public static Order getDirection(Sort.Order order) {
        return order.getDirection().isAscending() ? Order.ASC : Order.DESC;
    }

    /**
     * 정렬 컬럼  OrderSpecifier Array 생성
     *
     * @param sort     : Pageable > Sort
     * @param parent   : 정렬 대상 QueryDsl Entity (ex) QBoardManagementInfoEntity
     * @param fieldMap : Client에서 요청한 정렬 필드와 실제 entity의 필드 값 매칭용 Map
     *                 (key : client request field, value : entity field)
     * @return
     */
    public static OrderSpecifier<?>[] getOrderSpecifiers(Sort sort, Path<?> parent, Map<String, String> fieldMap) {
        List<OrderSpecifier<?>> orders = new ArrayList<>();

        if (!sort.isEmpty()) {
            for (Sort.Order order : sort) {
                Optional<String> fieldOptional = Optional.of(fieldMap.get(order.getProperty()));

                fieldOptional.ifPresent(field -> {
                    Order direction = QueryDslUtils.getDirection(order);

                    orders.add(QueryDslUtils.getSortedColumn(direction, parent, field));
                });
            }
        }

        return orders.toArray(OrderSpecifier[]::new);
    }

    /**
     * 날짜 비교 (between) - LocalDate
     *
     * @param path
     * @param fromDate : yyyy-mm-dd (to date)
     * @param toDate   : yyyy-mm-dd (from date)
     * @return
     */
    public static BooleanExpression betweenLocalDate(DatePath<LocalDate> path, String fromDate, String toDate) {

        if (StringUtils.isNotEmpty(fromDate) && StringUtils.isNotEmpty(toDate)) {
            return path.between(LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    LocalDate.parse(toDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        } else if (StringUtils.isNotEmpty(fromDate)) {
            return goeLocalDate(path, fromDate);
        } else if (StringUtils.isNotEmpty(toDate)) {
            return loeLocalDate(path, toDate);
        } else {
            return null;
        }
    }

    /**
     * 날짜 비교 (lt) - LocalDate
     *
     * @param path
     * @param date : yyyy-mm-dd (to date)
     * @return
     */
    public static BooleanExpression loeLocalDate(DatePath<LocalDate> path, String date) {
        return StringUtils.isNotEmpty(date)
                ? path.loe(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                : null;
    }

    /**
     * 날짜 비교 (gt) - LocalDate
     *
     * @param path
     * @param date : yyyy-mm-dd (from date)
     * @return
     */
    public static BooleanExpression goeLocalDate(DatePath<LocalDate> path, String date) {
        return StringUtils.isNotEmpty(date)
                ? path.goe(LocalDate.parse(date + START_TIME_SUFFIX, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                : null;
    }
}