package cn.mimiron.uaa.web.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author zhangxd
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Email address already in use")
public class EmailAlreadyUsedException extends RuntimeException {

}
