package tech.hakandurmaz.soapcoursemanagement;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

import java.math.BigInteger;

@SoapFault(faultCode = FaultCode.CUSTOM,customFaultCode = "{http://hakandurmaz.tech/courses}")
public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String s) {
        super(s);
    }
}
