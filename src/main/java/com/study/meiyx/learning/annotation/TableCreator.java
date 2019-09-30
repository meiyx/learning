package com.study.meiyx.learning.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {

    public static String createTableSql(String className) throws ClassNotFoundException {
        Class<?> cl = Class.forName(className);
        DBTable dbTable = cl.getAnnotation(DBTable.class);

        if(dbTable == null) {
            System.out.println(
                    "No DBTable annotations in class " + className);
            return null;
        }
        String tableName = dbTable.name();

        if(tableName.length() < 1) {
            tableName = cl.getName().toUpperCase();
        }
        List<String> columnDefs = new ArrayList<String>();

        for(Field field : cl.getDeclaredFields()) {
            String columnName = null;

            Annotation[] anns = field.getDeclaredAnnotations();
            if(anns.length < 1) {
                continue;
            }


            if(anns[0] instanceof SQLInteger) {
                SQLInteger sInt = (SQLInteger) anns[0];

                if(sInt.name().length() < 1) {
                    columnName = field.getName().toUpperCase();
                }
                else {
                    columnName = sInt.name();
                }

                columnDefs.add(columnName + " INT" +
                        getConstraints(sInt.constraint()));
            }

            if(anns[0] instanceof SQLString) {
                SQLString sString = (SQLString) anns[0];

                if(sString.name().length() < 1) {
                    columnName = field.getName().toUpperCase();
                }
                else {
                    columnName = sString.name();
                }
                columnDefs.add(columnName + " VARCHAR(" +
                        sString.value() + ")" +
                        getConstraints(sString.constraint()));
            }


        }

        StringBuilder createCommand = new StringBuilder(
                "CREATE TABLE " + tableName + "(");
        for(String columnDef : columnDefs) {
            createCommand.append("\n    " + columnDef + ",");
        }


        String tableCreate = createCommand.substring(
                0, createCommand.length() - 1) + ");";
        return tableCreate;
    }


    /**
     * 判断该字段是否有其他约束
     * @param con
     * @return
     */
    private static String getConstraints(Constraints con) {
        String constraints = "";
        if(!con.allowNull()) {
            constraints += " NOT NULL";
        }
        if(con.primaryKey()) {
            constraints += " PRIMARY KEY";
        }
        if(con.unique()) {
            constraints += " UNIQUE";
        }
        return constraints;
    }

    public static void main(String[] args) throws Exception {
        String[] arg={"com.study.meiyx.learning.annotation.Member"};
        for(String className : arg) {
            System.out.println("Table Creation SQL for " +
                    className + " is :\n" + createTableSql(className));
        }

        /**
         * 输出结果：
         Table Creation SQL for com.zejian.annotationdemo.Member is :
         CREATE TABLE MEMBER(
         ID VARCHAR(50) NOT NULL PRIMARY KEY,
         NAME VARCHAR(30) NOT NULL,
         AGE INT NOT NULL,
         DESCRIPTION VARCHAR(150)
         );
         */
    }
}