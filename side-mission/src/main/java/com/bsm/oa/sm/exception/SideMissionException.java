package com.bsm.oa.sm.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
public enum SideMissionException implements Supplier<RuntimeException> {

  SIDE_MISSION_TYPE_NOT_EXISTS("Side mission type does not exists."),
  REQUIRED_PROOF_TYPES_NOT_MATCHED("Provided proof resource types do not meet requirements. "),
  SIDE_MISSION_REPORT_NOT_EXISTS("Side mission report does not exists."),
  SIDE_MISSION_REPORT_RATED("User has already rated that side mission report."),
  ;

  private final String message;

  @Override
  public RuntimeException get() {
    return new ResponseStatusException(BAD_REQUEST, message);
  }

  public void raise(String details) {
    throw new ResponseStatusException(BAD_REQUEST, message + details);
  }

  public void raise() {
    throw get();
  }
}
