package com.bookstore.dao;

//Абстрактный класс
public abstract class DAOFactory {

 // Список типов DAO, поддерживаемых генератором
 public static final int MYSQL = 1;
 public static final int ORACLE = 2;

 /*
 Создать абстрактные методы для каждого DAO.
 Реализовывать эти методы д/конкретные генераторы.

 public abstract CustomerDAO getCustomerDAO();
 public abstract AccountDAO getAccountDAO();
 public abstract OrderDAO getOrderDAO();

 */

 public static DAOFactory getDAOFactory(
         int whichFactory) {

     switch (whichFactory) {
         case MYSQL:
             //return new MysqlDAOFactory();
         case ORACLE    :
             //return new OracleDAOFactory();
         default           :
             return null;
     }
 }
}
