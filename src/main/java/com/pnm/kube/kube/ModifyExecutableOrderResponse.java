package com.pnm.kube.kube;

import javax.xml.bind.annotation.XmlRootElement;

class SuccessResponse {

	// Class data members
	String MessageReference;
	String ServiceName;
	String ReturnCode;
	public String getMessageReference() {
		return MessageReference;
	}
	public void setMessageReference(String messageReference) {
		MessageReference = messageReference;
	}
	public String getServiceName() {
		return ServiceName;
	}
	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}
	public String getReturnCode() {
		return ReturnCode;
	}
	public void setReturnCode(String returnCode) {
		ReturnCode = returnCode;
	}


}

@XmlRootElement
public class ModifyExecutableOrderResponse {

	public SuccessResponse getSuccessResponse() {
		return successResponse;
	}

	public void setSuccessResponse(SuccessResponse successResponse) {
		this.successResponse = successResponse;
	}

	private SuccessResponse successResponse;
}