/**
 * ERP1WebServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package se.lu.ics.grupp14;

public class ERP1WebServiceLocator extends org.apache.axis.client.Service implements se.lu.ics.grupp14.ERP1WebService {

    public ERP1WebServiceLocator() {
    }


    public ERP1WebServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ERP1WebServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ERP1WebServiceSoap
    private java.lang.String ERP1WebServiceSoap_address = "http://localhost/ERP1WebApplication/ERP1WebService.asmx";

    public java.lang.String getERP1WebServiceSoapAddress() {
        return ERP1WebServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ERP1WebServiceSoapWSDDServiceName = "ERP1WebServiceSoap";

    public java.lang.String getERP1WebServiceSoapWSDDServiceName() {
        return ERP1WebServiceSoapWSDDServiceName;
    }

    public void setERP1WebServiceSoapWSDDServiceName(java.lang.String name) {
        ERP1WebServiceSoapWSDDServiceName = name;
    }

    public se.lu.ics.grupp14.ERP1WebServiceSoap getERP1WebServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ERP1WebServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getERP1WebServiceSoap(endpoint);
    }

    public se.lu.ics.grupp14.ERP1WebServiceSoap getERP1WebServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            se.lu.ics.grupp14.ERP1WebServiceSoapStub _stub = new se.lu.ics.grupp14.ERP1WebServiceSoapStub(portAddress, this);
            _stub.setPortName(getERP1WebServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setERP1WebServiceSoapEndpointAddress(java.lang.String address) {
        ERP1WebServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (se.lu.ics.grupp14.ERP1WebServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                se.lu.ics.grupp14.ERP1WebServiceSoapStub _stub = new se.lu.ics.grupp14.ERP1WebServiceSoapStub(new java.net.URL(ERP1WebServiceSoap_address), this);
                _stub.setPortName(getERP1WebServiceSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ERP1WebServiceSoap".equals(inputPortName)) {
            return getERP1WebServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://grupp14.ics.lu.se", "ERP1WebService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://grupp14.ics.lu.se", "ERP1WebServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ERP1WebServiceSoap".equals(portName)) {
            setERP1WebServiceSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
