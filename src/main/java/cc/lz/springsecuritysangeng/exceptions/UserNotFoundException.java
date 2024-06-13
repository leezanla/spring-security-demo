/**
 * @author leezan
 * @Data 5/31/2024 10:54 AM
 */
package cc.lz.springsecuritysangeng.exceptions;

public class UserNotFoundException extends RuntimeException{
    private Integer exceptionCode;
    private String exceptionMessage;

    public UserNotFoundException() {
    }

    public UserNotFoundException(Integer exceptionCode, String exceptionMessage) {
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
    }

    public Integer getExceptionCode() {
        return exceptionCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
