package com.asset.application.dto;
import lombok.Data;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
@Data
public class ExternalPriceDto {
    private String symbol;
    private BigDecimal lastPrice;
    private BigDecimal changeAmount;
    private BigDecimal changePercent;
    private BigDecimal openPrice;
    private BigDecimal highPrice;
    private BigDecimal lowPrice;
    private BigDecimal referencePrice;
    private BigDecimal ceilingPrice;
    private BigDecimal floorPrice;
    private BigDecimal volume;
    private BigDecimal matchedVolume;
    private BigDecimal foreignBuyVol;
    private BigDecimal foreignSellVol;
    private ZonedDateTime timestamp;
}
