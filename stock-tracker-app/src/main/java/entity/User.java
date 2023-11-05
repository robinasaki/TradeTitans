package entity;

import java.time.LocalDateTime;

public interface User {
    String getPortfolio();
    String getTradeTransaction();
    LocalDateTime getCurrentTime();
}

// TODO: Not everything in here may be correct. May need to change/add to this later.
