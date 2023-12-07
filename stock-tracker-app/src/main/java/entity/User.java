package entity;

import java.time.LocalDateTime;

public interface User {
    String getPortfolio();
    String getTradeTransaction();
    LocalDateTime getCurrentTime();
}
