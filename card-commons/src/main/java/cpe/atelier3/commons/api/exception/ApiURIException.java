package cpe.atelier3.commons.api.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Called API URI is malformed. Please forward this error to the system administrator.")
public class ApiURIException extends Exception { }
