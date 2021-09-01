package com.flyhub.saccox.userservice.exception;

import com.flyhub.library.apiresponse.ApiResponseFormat;
import com.flyhub.library.apiresponse.ErrorFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(CustomNotFoundException customNotFoundException, WebRequest webRequest) {
        logger.info("Not Found Exception: " + customNotFoundException.getMessage());
        return new ResponseEntity<>(new ApiResponseFormat(false, new ErrorFormat(HttpStatus.NOT_FOUND.value(), "Entity not found.", webRequest.getDescription(false)), customNotFoundException.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomNoContentException.class)
    public ResponseEntity<?> handleNoContentException(CustomNoContentException customNoContentException, WebRequest webRequest) {
        logger.info("No Content Exception: " + customNoContentException.getMessage());
        return new ResponseEntity<>(new ApiResponseFormat(false, new ErrorFormat(HttpStatus.NOT_FOUND.value(), "Entities not found.", webRequest.getDescription(false)), customNoContentException.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomInvalidInputException.class)
    public ResponseEntity<?> handleInvalidInputException(CustomInvalidInputException customInvalidInputException, WebRequest webRequest) {
        logger.info("Invalid Input Exception: " + customInvalidInputException.getMessage());
        return new ResponseEntity<>(new ApiResponseFormat(false, new ErrorFormat(HttpStatus.BAD_REQUEST.value(), "Invalid input provided.", webRequest.getDescription(false)), customInvalidInputException.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomEmptyInputException.class)
    public ResponseEntity<?> handleEmptyInputException(CustomEmptyInputException customEmptyInputException, WebRequest webRequest) {
        logger.info("Empty Input Exception: " + customEmptyInputException.getMessage());
        return new ResponseEntity<>(new ApiResponseFormat(false, new ErrorFormat(HttpStatus.BAD_REQUEST.value(), "Empty input provided.", webRequest.getDescription(false)), customEmptyInputException.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomAlreadyExistsException.class)
    public ResponseEntity<?> handleAlreadyExistsException(CustomAlreadyExistsException customAlreadyExistsException, WebRequest webRequest) {
        logger.info("Already Exists Exception: " + customAlreadyExistsException.getMessage());
        return new ResponseEntity<>(new ApiResponseFormat(false, new ErrorFormat(HttpStatus.CONFLICT.value(), "Entity already exists.", webRequest.getDescription(false)), customAlreadyExistsException.getMessage(), null), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomNumberFormatException.class)
    public ResponseEntity<?> handleNumberFormatException(CustomNumberFormatException customNumberFormatException, WebRequest webRequest) {
        logger.info("Number Format Exception: " + customNumberFormatException.getMessage());
        return new ResponseEntity<>(new ApiResponseFormat(false, new ErrorFormat(HttpStatus.BAD_REQUEST.value(), "Entity already exists.", webRequest.getDescription(false)), customNumberFormatException.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomJsonPatchException.class)
    public ResponseEntity<?> handleJsonPatchException(CustomJsonPatchException customJsonPatchException, WebRequest webRequest) {
        logger.info("Json Patch Exception: " + customJsonPatchException.getMessage());
        return new ResponseEntity<>(new ApiResponseFormat(false, new ErrorFormat(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error.", webRequest.getDescription(false)), customJsonPatchException.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomJsonProcessingException.class)
    public ResponseEntity<?> handleJsonProcessingException(CustomJsonProcessingException customJsonProcessingException, WebRequest webRequest) {
        logger.info("Json Processing Exception: " + customJsonProcessingException.getMessage());
        return new ResponseEntity<>(new ApiResponseFormat(false, new ErrorFormat(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error.", webRequest.getDescription(false)), customJsonProcessingException.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomJwtAccessTokenExpiredException.class)
    public ResponseEntity<?> handleJwtAccessTokenExpiredException(CustomJwtAccessTokenExpiredException customJwtAccessTokenExpiredException, WebRequest webRequest) {
        logger.info("JWT Access Token Expired Exception: " + customJwtAccessTokenExpiredException.getMessage());
        return new ResponseEntity<>(new ApiResponseFormat(false, new ErrorFormat(HttpStatus.BAD_REQUEST.value(), "JWT Access Token expired.", webRequest.getDescription(false)), customJwtAccessTokenExpiredException.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomJwtAccessTokenStillValidException.class)
    public ResponseEntity<?> handleJwtAccessTokenStillValidException(CustomJwtAccessTokenStillValidException customJwtAccessTokenStillValidException, WebRequest webRequest) {
        logger.info("JWT Access Token Still Valid Exception: " + customJwtAccessTokenStillValidException.getMessage());
        return new ResponseEntity<>(new ApiResponseFormat(false, new ErrorFormat(HttpStatus.CONFLICT.value(), "JWT Access Token still valid.", webRequest.getDescription(false)), customJwtAccessTokenStillValidException.getMessage(), null), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomJwtRefreshTokenExpiredException.class)
    public ResponseEntity<?> handleJwtRefreshTokenExpiredException(CustomJwtRefreshTokenExpiredException customJwtRefreshTokenExpiredException, WebRequest webRequest) {
        logger.info("JWT Refresh Token Expired Exception: " + customJwtRefreshTokenExpiredException.getMessage());
        return new ResponseEntity<>(new ApiResponseFormat(false, new ErrorFormat(HttpStatus.BAD_REQUEST.value(), "JWT Refresh Token expired.", webRequest.getDescription(false)), customJwtRefreshTokenExpiredException.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomJwtRefreshTokenStillValidException.class)
    public ResponseEntity<?> handleJwtRefreshTokenStillValidException(CustomJwtRefreshTokenStillValidException customJwtRefreshTokenStillValidException, WebRequest webRequest) {
        logger.info("JWT Refresh Token Still Valid Exception: " + customJwtRefreshTokenStillValidException.getMessage());
        return new ResponseEntity<>(new ApiResponseFormat(false, new ErrorFormat(HttpStatus.CONFLICT.value(), "JWT Refresh Token still valid.", webRequest.getDescription(false)), customJwtRefreshTokenStillValidException.getMessage(), null), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomJwtAccessTokenInvalidException.class)
    public ResponseEntity<?> handleJwtInvalidAccessTokenException(CustomJwtAccessTokenInvalidException customJwtAccessTokenInvalidException, WebRequest webRequest) {
        logger.info("JWT Access Token Invalid Exception: " + customJwtAccessTokenInvalidException.getMessage());
        return new ResponseEntity<>(new ApiResponseFormat(false, new ErrorFormat(HttpStatus.BAD_REQUEST.value(), "JWT Access Token not valid.", webRequest.getDescription(false)), customJwtAccessTokenInvalidException.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomNotAuthorisedException.class)
    public ResponseEntity<?> handleNotAuthorisedException(CustomNotAuthorisedException customNotAuthorisedException, WebRequest webRequest) {
        logger.info("Not Authorised Exception: " + customNotAuthorisedException.getMessage());
        return new ResponseEntity<>(new ApiResponseFormat(false, new ErrorFormat(HttpStatus.UNAUTHORIZED.value(), "Request not authorised.", webRequest.getDescription(false)), customNotAuthorisedException.getMessage(), null), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception, WebRequest webRequest) {
        logger.info("Exception: " + exception.getMessage());
        return new ResponseEntity<>(new ApiResponseFormat(false, new ErrorFormat(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error.", webRequest.getDescription(false)), exception.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
