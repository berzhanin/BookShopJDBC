package com.bookstore.dao;

//����������� �����
public abstract class DAOFactory {

 // ������ ����� DAO, �������������� �����������
 public static final int MYSQL = 1;
 public static final int ORACLE = 2;

 /*
 ������� ����������� ������ ��� ������� DAO.
 ������������� ��� ������ �/���������� ����������.

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
