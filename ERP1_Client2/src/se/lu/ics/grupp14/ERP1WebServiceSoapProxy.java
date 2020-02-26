package se.lu.ics.grupp14;

public class ERP1WebServiceSoapProxy implements se.lu.ics.grupp14.ERP1WebServiceSoap {
  private String _endpoint = null;
  private se.lu.ics.grupp14.ERP1WebServiceSoap eRP1WebServiceSoap = null;
  
  public ERP1WebServiceSoapProxy() {
    _initERP1WebServiceSoapProxy();
  }
  
  public ERP1WebServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initERP1WebServiceSoapProxy();
  }
  
  private void _initERP1WebServiceSoapProxy() {
    try {
      eRP1WebServiceSoap = (new se.lu.ics.grupp14.ERP1WebServiceLocator()).getERP1WebServiceSoap();
      if (eRP1WebServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)eRP1WebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)eRP1WebServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (eRP1WebServiceSoap != null)
      ((javax.xml.rpc.Stub)eRP1WebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public se.lu.ics.grupp14.ERP1WebServiceSoap getERP1WebServiceSoap() {
    if (eRP1WebServiceSoap == null)
      _initERP1WebServiceSoapProxy();
    return eRP1WebServiceSoap;
  }
  
  public boolean createEmployee(java.lang.String no_, java.lang.String firstName, java.lang.String lastName, java.lang.String jobTitle, java.lang.String address, java.lang.String phoneNumber) throws java.rmi.RemoteException{
    if (eRP1WebServiceSoap == null)
      _initERP1WebServiceSoapProxy();
    return eRP1WebServiceSoap.createEmployee(no_, firstName, lastName, jobTitle, address, phoneNumber);
  }
  
  public se.lu.ics.grupp14.Employee[] readEmployee() throws java.rmi.RemoteException{
    if (eRP1WebServiceSoap == null)
      _initERP1WebServiceSoapProxy();
    return eRP1WebServiceSoap.readEmployee();
  }
  
  public boolean updateEmployee(java.lang.String no, java.lang.String firstName, java.lang.String lastName, java.lang.String jobTitle, java.lang.String address, java.lang.String phoneNumber) throws java.rmi.RemoteException{
    if (eRP1WebServiceSoap == null)
      _initERP1WebServiceSoapProxy();
    return eRP1WebServiceSoap.updateEmployee(no, firstName, lastName, jobTitle, address, phoneNumber);
  }
  
  public boolean deleteEmployee(java.lang.String no) throws java.rmi.RemoteException{
    if (eRP1WebServiceSoap == null)
      _initERP1WebServiceSoapProxy();
    return eRP1WebServiceSoap.deleteEmployee(no);
  }
  
  
}