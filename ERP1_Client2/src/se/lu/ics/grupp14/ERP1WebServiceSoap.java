/**
 * ERP1WebServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package se.lu.ics.grupp14;

public interface ERP1WebServiceSoap extends java.rmi.Remote {
    public boolean createEmployee(java.lang.String no_, java.lang.String firstName, java.lang.String lastName, java.lang.String jobTitle, java.lang.String address, java.lang.String phoneNumber) throws java.rmi.RemoteException;
    public se.lu.ics.grupp14.Employee[] readEmployee() throws java.rmi.RemoteException;
    public boolean updateEmployee(java.lang.String no, java.lang.String firstName, java.lang.String lastName, java.lang.String jobTitle, java.lang.String address, java.lang.String phoneNumber) throws java.rmi.RemoteException;
    public boolean deleteEmployee(java.lang.String no) throws java.rmi.RemoteException;
}
